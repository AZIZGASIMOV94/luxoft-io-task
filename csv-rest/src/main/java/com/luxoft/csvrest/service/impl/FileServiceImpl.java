package com.luxoft.csvrest.service.impl;

import com.luxoft.csvrest.dto.FileDTO;
import com.luxoft.csvrest.entity.FiletTableEntity;
import com.luxoft.csvrest.exception.FileErrorException;
import com.luxoft.csvrest.exception.NoEntryFoundException;
import com.luxoft.csvrest.repository.FIleRepository;
import com.luxoft.csvrest.service.inter.FIleServiceInter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FIleServiceInter {

    private final FIleRepository fIleRepository;

    @Override
    public void saveCSVFIle(MultipartFile file, Model model){

        if(file.isEmpty()) {
            throw new FileErrorException("file not found");
        } else {

            try(Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                CsvToBean<FileDTO> csvToBean = new CsvToBeanBuilder<FileDTO>(reader)
                        .withType(FileDTO.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

                List<FileDTO> files = csvToBean.parse();

                for(FileDTO f : files){
                    FiletTableEntity filetTableEntityDTO = new FiletTableEntity();
                    filetTableEntityDTO.setId(f.getId());
                    filetTableEntityDTO.setName(f.getName());
                    filetTableEntityDTO.setDescription(f.getDescription());
                    filetTableEntityDTO.setTimeStamp(f.getTimeStamp());
                    fIleRepository.save(filetTableEntityDTO);
                }

            } catch (Exception ex) {
                log.info("error occurred: "+ ex.getLocalizedMessage());
                throw new FileErrorException("error occurred while parsing the file");
            }
        }
    }

    @Override
    public FileDTO findById(Integer id) {
        Optional<FiletTableEntity> opfile = fIleRepository.findById(id);

        if(!opfile.isPresent()) {
            throw new NoEntryFoundException(id);
        }

        FiletTableEntity filetTableEntity = opfile.get();

        FileDTO fileDTO = new FileDTO();
        fileDTO.setId(filetTableEntity.getId());
        fileDTO.setName(filetTableEntity.getName());
        fileDTO.setDescription(filetTableEntity.getDescription());
        fileDTO.setTimeStamp(filetTableEntity.getTimeStamp());
        return fileDTO;
    }

    @Override
    public void deleteById(Integer id ) {
        if(!fIleRepository.existsById(id)) throw new NoEntryFoundException(id);
        fIleRepository.deleteById(id);
    }
}
