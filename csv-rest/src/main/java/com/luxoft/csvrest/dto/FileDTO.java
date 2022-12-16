package com.luxoft.csvrest.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class FileDTO implements Serializable {

    @CsvBindByName(column = "PRIMARY_KEY")
    private Integer id;

    @CsvBindByName(column = "NAME")
    private String name;

    @CsvBindByName(column = "DESCRIPTION")
    private String description;

    @CsvBindByName(column = "UPDATED_TIMESTAMP")
    private String timeStamp;
}
