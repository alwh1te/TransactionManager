package org.liptsoft.transactionmanager.service;

import org.liptsoft.transactionmanager.exceptions.InvalidDataException;
import org.liptsoft.transactionmanager.model.Category;
import org.liptsoft.transactionmanager.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    void add(Category category);
    void addMcc(Category category, List<Integer> mcc) ;
    void addSubCategories(Category mainCategory, Category subCategory);
    void removeCategory(Category category);
    List<Category> showCategories();
    List<Category> showByMonth(String month);
    List<String> showByMonths(Category category);
}
