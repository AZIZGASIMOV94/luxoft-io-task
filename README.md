# CSV Import REST API

![Java 17](https://img.shields.io/badge/Java-17-orange)
![Spring Boot 2.7](https://img.shields.io/badge/Spring%20Boot-2.7-brightgreen)

A Spring Boot REST API that imports records from an uploaded CSV file into a relational database, then lets clients fetch and delete individual records.

## Features

- **CSV upload** via `multipart/form-data`, parsed with [OpenCSV](https://opencsv.sourceforge.net/) into DTOs by column name
- **All-or-nothing import**: the whole file is saved in one transaction, so a bad row never leaves a half-imported file
- **Validation** of every row (positive id, required name and description, length limits) with Bean Validation
- **Consistent JSON errors** from a global exception handler (`400` for bad files or data, `404` for unknown ids)
- **Layered architecture**: controller → service → repository, with separate DTO and JPA entity
- **12-factor config**: database settings come from environment variables
- Runs standalone (`java -jar`) or as a WAR on an external Tomcat

## Tech stack

Java 17 · Spring Boot 2.7 (Web, Data JPA, Validation) · Hibernate · MySQL · OpenCSV · Lombok · Maven

## API

Base path: `/api/records`

| Method | Path | Description | Success |
|---|---|---|---|
| `POST` | `/import` | Upload a CSV file (form field `file`) | `201 Created` → `{"imported": 3}` |
| `GET` | `/{id}` | Get one record | `200 OK` |
| `DELETE` | `/{id}` | Delete one record | `204 No Content` |

### CSV format

```csv
PRIMARY_KEY,NAME,DESCRIPTION,UPDATED_TIMESTAMP
1,Laptop,Office laptop for the backend team,2022-12-15 16:40:14
2,Monitor,27-inch 4K monitor,2022-12-15 16:41:02
```

A ready-made file is in [`sample-data/records.csv`](sample-data/records.csv). Re-importing a row with an existing `PRIMARY_KEY` updates that record.

### Examples

```bash
# import
curl -F "file=@sample-data/records.csv" http://localhost:8080/api/records/import
# {"imported":3}

# read
curl http://localhost:8080/api/records/1
# {"id":1,"name":"Laptop","description":"Office laptop for the backend team","updatedTimestamp":"2022-12-15 16:40:14"}

# delete
curl -X DELETE http://localhost:8080/api/records/1

# unknown id
curl http://localhost:8080/api/records/999
# {"timestamp":"...","status":404,"error":"Not Found","message":"Record with id 999 not found"}
```

## Running locally

**Requirements:** Java 17+, MySQL 8 (or MariaDB) running on `localhost:3306`.

```bash
./mvnw spring-boot:run
```

The database `csv_import` and the `file_table` table are created automatically on first start.

### Configuration

| Variable | Default | Purpose |
|---|---|---|
| `DB_URL` | `jdbc:mysql://localhost:3306/csv_import?createDatabaseIfNotExist=true` | JDBC URL |
| `DB_USERNAME` | `root` | Database user |
| `DB_PASSWORD` | _(empty)_ | Database password |
| `DDL_AUTO` | `update` | Hibernate schema mode; use `validate` with [`db/schema.sql`](db/schema.sql) |

Example:

```bash
DB_USERNAME=app DB_PASSWORD=secret ./mvnw spring-boot:run
```

## Project structure

```
src/main/java/io/github/azizgasimov94/csvimport
├── CsvImportApplication.java       # entry point
├── controller/FileRecordController # REST endpoints
├── service/                        # import, find, delete (transactional)
├── repository/                     # Spring Data JPA
├── entity/FileRecord               # JPA entity with validation
├── dto/FileRecordDto               # CSV row / JSON response
└── exception/                      # custom exceptions + global handler
db/schema.sql                       # optional manual schema
sample-data/records.csv             # example input
```

## Possible improvements

- Integration tests with Testcontainers (MySQL) and MockMvc
- Store `UPDATED_TIMESTAMP` as `LocalDateTime` instead of text
- Paginated `GET /api/records` endpoint
- Upgrade to Spring Boot 3 (`jakarta.*` namespace)
- OpenAPI / Swagger UI documentation
