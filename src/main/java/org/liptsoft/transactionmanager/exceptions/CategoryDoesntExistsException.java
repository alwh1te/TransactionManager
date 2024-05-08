package org.liptsoft.transactionmanager.exceptions;

public class CategoryDoesntExistsException extends InvalidDataException {
    public CategoryDoesntExistsException(String message) {
        super(message);
    }
}
