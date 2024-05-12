package org.liptsoft.transactionmanager.service;

import org.liptsoft.transactionmanager.model.Category;
import org.liptsoft.transactionmanager.model.Mcc;
import org.liptsoft.transactionmanager.model.Transaction;
import org.liptsoft.transactionmanager.repository.CategoryRepository;
import org.liptsoft.transactionmanager.repository.MccRepository;
import org.liptsoft.transactionmanager.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Supplier;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private MccRepository mccRepository;

    @Override
    public Category add(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category add(Category category, Mcc mcc) {
        mcc.setParentCategory(category);
        mccRepository.save(mcc);
        return categoryRepository.save(category);
    }

    @Override
    public void setParentCategory(Category category, Long parent_id) {
        if (categoryRepository.existsById(parent_id)) {
            category.setParentCategory(categoryRepository.findById(parent_id).orElse(null));
        }
    }

    @Override
    public String addMcc(Long category_id, Mcc mcc) {
        Category category = categoryRepository.findById(category_id).orElse(null);
        mcc.setParentCategory(category);
        mccRepository.save(mcc);
        return "Added new mcc codes to: "
                + categoryRepository.findById(category_id).orElse(new Category()).getName() + " mcc codes: " + mcc;
    }

    @Override
    public String addSubCategories(Long mainCategory_id, Category subCategory) {
        if (!categoryRepository.existsById(mainCategory_id)) {
            return "Master category doesn't exists!";
        }
        subCategory.setParentCategory(categoryRepository.findById(mainCategory_id).orElse(null));
        categoryRepository.save(subCategory);
        return "added new group to: " + subCategory.getParentCategory().getName()
                + " " + mccRepository.findAllByParentCategoryId(mainCategory_id);
    }


    @Override
    public String removeCategory(Long category_id) {
        if (categoryRepository.existsById(category_id)) {
            Category category = categoryRepository.findById(category_id).orElse(new Category());
            Category parent = category.getParentCategory();
            categoryRepository.deleteById(category_id);
            return "Category: " + category.getName() + " removed from: " + (parent == null ? " without category" : " " + parent.getName());
        }
        return "There is no category with this ID!";
    }

    public List<Transaction> show(Supplier<List<Transaction>> operator) {
        return operator.get();
    }

    @Override
    public List<Category> showCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Transaction> showByMonth(Integer month) {
        return transactionRepository.findAllByMonth(month);
    }

    @Override
    public List<Transaction> showByMonths(Long category_id) {
        return transactionRepository.findAllByCategoryId(category_id);
    }

    @Override
    public List<Transaction> showAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public List<Transaction> showTransactionsInCategory(Long category_id) {
        return transactionRepository.findAllByCategoryId(category_id);
    }

    @Override
    public Transaction addTransaction(Long category_id, Transaction transaction) {
        Category category = categoryRepository.findById(category_id).orElse(null);
        if (category == null) return new Transaction();
        transaction.setCategory(category);
        return transactionRepository.save(transaction);
    }

    @Override
    public String removeTransaction(Long transaction_id) {
        if (transactionRepository.existsById(transaction_id)) {
            Transaction transaction = transactionRepository.findById(transaction_id).orElse(new Transaction());
            categoryRepository.deleteById(transaction_id);
            transactionRepository.deleteById(transaction_id);
            return "Transaction: " + transaction.getName() + " removed from: " + transaction.getCategory().getName();
        }
        return "There is no transactions with this ID!";
    }
}
