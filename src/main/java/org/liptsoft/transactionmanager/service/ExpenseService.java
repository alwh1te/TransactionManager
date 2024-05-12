package org.liptsoft.transactionmanager.service;

import org.liptsoft.transactionmanager.model.Category;
import org.liptsoft.transactionmanager.model.Mcc;
import org.liptsoft.transactionmanager.model.Transaction;

import java.util.List;
import java.util.function.Supplier;

public interface ExpenseService {
    Category add(Category category);
    Category add(Category category, Mcc mccList);
    void setParentCategory(Category category, Long parent_id);
    Category addMcc(Long category_id, Mcc mcc) ;
    String addParentCategory(Long mainCategory_id, Category subCategory);
    String removeCategory(Long category_id);
    List<Transaction> show(Supplier<List<Transaction>> supplier);
    List<Category> showCategories();
    List<Transaction> showByMonth(Integer month);
    List<Transaction> showByMonths(Long category_id);

    List<Transaction> showAllTransactions();
    List<Transaction> showTransactionsInCategory(Long category_id);
    Transaction addTransaction(Long category_id, Transaction transaction);
    String removeTransaction(Long transaction_id);
}
