package io.github.azizgasimov94.csvimport.exception;

/** No record exists with the requested id. Mapped to HTTP 404. */
public class RecordNotFoundException extends RuntimeException {

    public RecordNotFoundException(Integer id) {
        super("Record with id " + id + " not found");
    }
}
