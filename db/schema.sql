-- Optional: the application creates this table automatically (spring.jpa.hibernate.ddl-auto=update).
-- Use this script if you prefer to create the schema yourself and run with DDL_AUTO=validate.

CREATE DATABASE IF NOT EXISTS csv_import
    CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

USE csv_import;

CREATE TABLE IF NOT EXISTS file_table (
    id                INT          NOT NULL PRIMARY KEY,
    name              VARCHAR(100) NOT NULL,
    description       VARCHAR(200) NOT NULL,
    updated_timestamp VARCHAR(255) NULL
);
