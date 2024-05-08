package org.liptsoft.transactionmanager.exceptions;

public class CategoryAlreadyExistsException extends InvalidDataException {
    public CategoryAlreadyExistsException(String message) {
        super(message);
    }
}
