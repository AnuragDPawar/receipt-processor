package com.receiptprocessor.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String entityName, String identifier) {
        super(String.format("%s with identifier '%s' not found", entityName, identifier));
    }
}
