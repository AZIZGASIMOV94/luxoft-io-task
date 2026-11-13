package io.github.azizgasimov94.csvimport.service;

import com.opencsv.bean.CsvToBeanBuilder;
import io.github.azizgasimov94.csvimport.dto.FileRecordDto;
import io.github.azizgasimov94.csvimport.entity.FileRecord;
import io.github.azizgasimov94.csvimport.exception.InvalidFileException;
import io.github.azizgasimov94.csvimport.exception.RecordNotFoundException;
import io.github.azizgasimov94.csvimport.repository.FileRecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileRecordServiceImpl implements FileRecordService {

    private final FileRecordRepository repository;

    @Override
    @Transactional
    public int importCsv(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new InvalidFileException("File is missing or empty");
        }

        List<FileRecordDto> rows = parse(file);
        List<FileRecord> records = rows.stream().map(this::toEntity).collect(Collectors.toList());
        // flush inside the transaction so validation errors surface here, not at commit
        repository.saveAllAndFlush(records);

        log.info("Imported {} records from {}", records.size(), file.getOriginalFilename());
        return records.size();
    }

    @Override
    @Transactional(readOnly = true)
    public FileRecordDto findById(Integer id) {
        return repository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        if (!repository.existsById(id)) {
            throw new RecordNotFoundException(id);
        }
        repository.deleteById(id);
    }

    private List<FileRecordDto> parse(MultipartFile file) {
        try (Reader reader = new BufferedReader(
                new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            return new CsvToBeanBuilder<FileRecordDto>(reader)
                    .withType(FileRecordDto.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build()
                    .parse();
        } catch (IOException | RuntimeException e) {
            log.warn("Could not parse CSV file {}: {}", file.getOriginalFilename(), e.getMessage());
            throw new InvalidFileException("Could not parse CSV file: " + e.getMessage());
        }
    }

    private FileRecord toEntity(FileRecordDto dto) {
        FileRecord record = new FileRecord();
        record.setId(dto.getId());
        record.setName(trim(dto.getName()));
        record.setDescription(trim(dto.getDescription()));
        record.setUpdatedTimestamp(trim(dto.getUpdatedTimestamp()));
        return record;
    }

    private FileRecordDto toDto(FileRecord record) {
        return new FileRecordDto(record.getId(), record.getName(),
                record.getDescription(), record.getUpdatedTimestamp());
    }

    private static String trim(String value) {
        return value == null ? null : value.trim();
    }
}
