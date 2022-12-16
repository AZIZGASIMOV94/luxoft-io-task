package com.luxoft.csvrest.repository;

import com.luxoft.csvrest.entity.FiletTableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FIleRepository extends JpaRepository<FiletTableEntity, Integer> {

}
