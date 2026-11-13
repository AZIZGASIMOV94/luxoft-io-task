package io.github.azizgasimov94.csvimport.exception;

/** The uploaded file is missing, empty or not valid CSV. Mapped to HTTP 400. */
public class InvalidFileException extends RuntimeException {

    public InvalidFileException(String message) {
        super(message);
    }
}
