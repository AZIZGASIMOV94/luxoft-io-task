package com.luxoft.csvrest.entity;

import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "file_table")
@Getter
@Setter
public class FiletTableEntity {

    @Id
    @Range(min = 1)
    private Integer id;

    @Column(name = "name")
    @NotEmpty
    private String name;

    @Column(name = "description")
    @NotEmpty
    private String description;

    @Column(name = "updated_timestamp")
    @NotEmpty
    private String timeStamp;
}
