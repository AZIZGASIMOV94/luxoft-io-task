package io.github.azizgasimov94.csvimport.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Entity
@Table(name = "file_table")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class FileRecord {

    @Id
    @NotNull
    @Positive
    private Integer id;

    @NotBlank
    @Size(max = 100)
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @NotBlank
    @Size(max = 200)
    @Column(name = "description", nullable = false, length = 200)
    private String description;

    @Column(name = "updated_timestamp")
    private String updatedTimestamp;
}
