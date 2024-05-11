package org.liptsoft.transactionmanager.service;

import org.liptsoft.transactionmanager.model.Category;
import org.liptsoft.transactionmanager.model.Mcc;
import org.liptsoft.transactionmanager.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExpenseService {
    Category add(Category category);
    Category add(Category category, Mcc mccList);
    void setParentCategory(Category category, Long parent_id);
    String addMcc(Long category_id, Mcc mcc) ;
    String addSubCategories(Long mainCategory_id, Category subCategory);
    String removeCategory(Long category_id);
    List<Category> showCategories();
    List<String> showByMonth(Integer month);
    List<String> showByMonths(Long category_id);

    List<Transaction> showAllTransactions();
    List<Transaction> showTransactionsInCategory(Long category_id);
    Transaction addTransaction(Long category_id, Transaction transaction);
    String removeTransaction(Long transaction_id);
}
