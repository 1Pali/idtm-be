package com.sap.sct.idtmbe.exception;

public class ElementDoesNotExistException extends RuntimeException {
    public ElementDoesNotExistException(String message) {
        super(message);
    }

    public ElementDoesNotExistException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
