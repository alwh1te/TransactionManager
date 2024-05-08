package org.liptsoft.transactionmanager.service;

import org.liptsoft.transactionmanager.exceptions.InvalidDataException;
import org.liptsoft.transactionmanager.model.Category;
import org.liptsoft.transactionmanager.model.Mcc;
import org.liptsoft.transactionmanager.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    void add(Category category) throws InvalidDataException;
    void addMcc(Category category, List<Mcc> mcc) throws InvalidDataException;
    void addSubCategories(Category mainCategory, List<Category> subCategories);
    void removeCategory(Category category);
    void addTransaction(Category category, Transaction transaction);
    void removeTransaction(Category category, Transaction transaction);
    List<Category> showCategories();
    List<Category> showByMonth(String month);
    List<String> showByMonths(Category category);
}
