package org.liptsoft.transactionmanager.service;

import org.liptsoft.transactionmanager.model.Category;
import org.liptsoft.transactionmanager.model.Transaction;
import org.liptsoft.transactionmanager.repository.CategoryRepository;
import org.liptsoft.transactionmanager.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService implements CategoryService, TransactionService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public void add(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void addMcc(Category category, List<Integer> mcc) {

    }

    @Override
    public void addSubCategories(Category mainCategory, Category subCategory) {
        subCategory.setParentCategory(mainCategory);
        categoryRepository.save(subCategory);
    }


    @Override
    public void removeCategory(Category category) {

    }

    @Override
    public List<Category> showCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> showByMonth(String month) {
        return null;
    }

    @Override
    public List<String> showByMonths(Category category) {
        return null;
    }

    @Override
    public List<Transaction> showTransactionsInCategory(Long category_id) {
        return transactionRepository.findAllByCategoryId(category_id);
    }

    @Override
    public void addTransaction(Long category_id, Transaction transaction) {
        Category category = categoryRepository.findById(category_id).orElse(null);
        if (category == null) return;
        transaction.setCategory(category);
        transactionRepository.save(transaction);
    }

    @Override
    public void removeTransaction(Transaction transaction) {

    }
}
