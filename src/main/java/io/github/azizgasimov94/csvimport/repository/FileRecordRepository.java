package io.github.azizgasimov94.csvimport.repository;

import io.github.azizgasimov94.csvimport.entity.FileRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRecordRepository extends JpaRepository<FileRecord, Integer> {
}
