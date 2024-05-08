package org.liptsoft.transactionmanager.exceptions;

public abstract class InvalidDataException extends Exception {
    public InvalidDataException(String message) {
        super(message);
    }
}
