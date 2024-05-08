package org.liptsoft.transactionmanager.exceptions;

public class MccAlreadyExistsException extends InvalidDataException {
    public MccAlreadyExistsException(String message) {
        super(message);
    }
}
