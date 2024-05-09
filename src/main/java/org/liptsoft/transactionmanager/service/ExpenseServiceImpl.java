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

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private MccRepository mccRepository;

    @Override
    public void add(Category category) {

        categoryRepository.save(category);
    }

    @Override
    public void addMcc(Category category, List<Mcc> mcc) {
        for (Mcc mcc1 : mcc) {
            mcc1.setParentCategory(category);
            mccRepository.save(mcc1);
        }
    }

    @Override
    public void addSubCategories(Long mainCategory_id, Category subCategory) {
        subCategory.setParentCategory(categoryRepository.findById(mainCategory_id).orElse(null));
        categoryRepository.save(subCategory);
    }


    @Override
    public void removeCategory(Long category_id) {
        if (categoryRepository.existsById(category_id)) {
            categoryRepository.deleteById(category_id);
        }
    }

    @Override
    public List<Category> showCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<String> showByMonth(Integer month) {
        List<Category> allCategories = categoryRepository.findAll();
        List<String> result = new ArrayList<>();
        for (Category category : allCategories) {
            double sum = 0;
            List<Transaction> transactionsInCategory = transactionRepository.findAllByCategoryIdAndMonth(category.getId(), month);
            for (Transaction transaction : transactionsInCategory) {
                sum += transaction.getAmount();
            }
            result.add(category.getName() + " amount: " + sum);
        }

        return result;
    }

    @Override
    public List<String> showByMonths(Long category_id) {
        List<Transaction> transactionsInCategory = transactionRepository.findAllByCategoryId(categoryRepository.findById(category_id).orElse(new Category()).getId());
        Map<Integer, Double> mapMonthAmount = new LinkedHashMap<>();
        for (int i = 0; i <= 12; i++) {
            mapMonthAmount.put(i, 0.0);
        }
        for (Transaction transaction : transactionsInCategory) {
            if (transaction != null) {
                mapMonthAmount.put(transaction.getMonth(), transaction.getAmount() + mapMonthAmount.get(transaction.getMonth()));
            }
        }
        List<String> result = new ArrayList<>();
        String[] months = {"January", "February", "March",
                "April", "May", "June",
                "July", "August", "September",
                "October", "November", "December"};
        for (int i = 1; i <= 12; i++) {
            result.add(months[i - 1] + " " + mapMonthAmount.get(i));
        }
        return result;
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
    public void addTransaction(Long category_id, Transaction transaction) {
        Category category = categoryRepository.findById(category_id).orElse(null);
        if (category == null) return;
        transaction.setCategory(category);
        transactionRepository.save(transaction);
    }

    @Override
    public void removeTransaction(Long transaction_id) {
        if (transactionRepository.existsById(transaction_id)) {
            transactionRepository.deleteById(transaction_id);
        }
    }
}
