package io.github.azizgasimov94.csvimport.service;

import io.github.azizgasimov94.csvimport.dto.FileRecordDto;
import org.springframework.web.multipart.MultipartFile;

public interface FileRecordService {

    /**
     * Parses the CSV file and saves every row. Either all rows are saved or none.
     *
     * @return the number of imported rows
     */
    int importCsv(MultipartFile file);

    FileRecordDto findById(Integer id);

    void deleteById(Integer id);
}
