package com.luxoft.csvrest.exception;

public class FileErrorException extends RuntimeException{
    public FileErrorException(String message) {
        super(String.format(message));
    }
}
