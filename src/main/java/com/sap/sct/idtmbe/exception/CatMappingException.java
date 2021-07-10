package com.sap.sct.idtmbe.exception;

public class CatMappingException extends RuntimeException {
    public CatMappingException(final String message) {
        super(message);
    }

    public CatMappingException(final String message, final Throwable throwable) {
        super(message, throwable);
    }
}
