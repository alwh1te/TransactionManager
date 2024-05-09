package org.liptsoft.transactionmanager.service;

import org.liptsoft.transactionmanager.model.Category;
import org.liptsoft.transactionmanager.model.Mcc;
import org.liptsoft.transactionmanager.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExpenseService {
    void add(Category category);
    void addMcc(Category category, List<Mcc> mcc) ;
    void addSubCategories(Long mainCategory_id, Category subCategory);
    void removeCategory(Long category_id);
    List<Category> showCategories();
    List<String> showByMonth(Integer month);
    List<String> showByMonths(Long category_id);

    List<Transaction> showAllTransactions();
    List<Transaction> showTransactionsInCategory(Long category_id);
    void addTransaction(Long category_id, Transaction transaction);
    void removeTransaction(Long transaction_id);
}
