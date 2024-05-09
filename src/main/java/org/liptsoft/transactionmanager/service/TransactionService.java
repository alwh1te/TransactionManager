package org.liptsoft.transactionmanager.service;

import org.liptsoft.transactionmanager.model.Category;
import org.liptsoft.transactionmanager.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionService {
    List<Transaction> showTransactionsInCategory(Long category_id);
    void addTransaction(Long category_id, Transaction transaction);
    void removeTransaction(Transaction transaction);
}
