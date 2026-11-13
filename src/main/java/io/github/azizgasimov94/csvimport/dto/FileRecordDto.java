package io.github.azizgasimov94.csvimport.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** One CSV row, and the JSON shape returned by the API. */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileRecordDto {

    @CsvBindByName(column = "PRIMARY_KEY", required = true)
    private Integer id;

    @CsvBindByName(column = "NAME")
    private String name;

    @CsvBindByName(column = "DESCRIPTION")
    private String description;

    @CsvBindByName(column = "UPDATED_TIMESTAMP")
    private String updatedTimestamp;
}
