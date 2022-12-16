package com.luxoft.csvrest.exception;

public class NoEntryFoundException extends RuntimeException{
    public NoEntryFoundException(Integer id) {
        super(String.format("Entry with id: %d not found", id));
    }
}
