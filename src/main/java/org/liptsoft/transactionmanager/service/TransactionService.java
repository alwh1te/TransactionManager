package org.liptsoft.transactionmanager.service;

import org.liptsoft.transactionmanager.model.Category;
import org.liptsoft.transactionmanager.model.Transaction;
import org.springframework.stereotype.Service;

@Service
public interface TransactionService {
    void addTransaction(Category category, Transaction transaction);
    void removeTransaction(Category category, Transaction transaction);
}
