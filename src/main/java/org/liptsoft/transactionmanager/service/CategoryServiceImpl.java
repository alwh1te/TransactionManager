package org.liptsoft.transactionmanager.service;

import org.liptsoft.transactionmanager.exceptions.InvalidDataException;
import org.liptsoft.transactionmanager.exceptions.MccAlreadyExistsException;
import org.liptsoft.transactionmanager.model.Category;
import org.liptsoft.transactionmanager.model.Mcc;
import org.liptsoft.transactionmanager.model.Transaction;
import org.liptsoft.transactionmanager.repository.CategoryRepository;
import org.liptsoft.transactionmanager.repository.MccRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private MccRepository mccRepository;

    @Override
    public void add(Category category) throws InvalidDataException {
        Mcc tmp = checkMccExists(category);
        if (tmp != null) {
            throw new MccAlreadyExistsException("Mcc already reserved for category: "
                    + tmp.getCategory().getName() + '\n');
        }
        categoryRepository.save(category);
    }

    @Override
    public void addMcc(Category category, List<Mcc> mcc) throws InvalidDataException {
        Mcc tmp = checkMccExists(category);
        if (tmp != null) {
            throw new MccAlreadyExistsException("Mcc already reserved for category: "
                    + tmp.getCategory().getName() + '\n');
        }
        category.addMcc(mcc);
        categoryRepository.save(category);
    }

    @Override
    public void addSubCategories(Category mainCategory, List<Category> subCategories) {
        mainCategory.addSubCategories(subCategories);
        categoryRepository.save(mainCategory);
    }

    @Override
    public void removeCategory(Category category) {
        if (categoryRepository.existsById(category.getId())) {
            categoryRepository.deleteById(category.getId());
        }
    }

    @Override
    public void addTransaction(Category category, Transaction transaction) {
        List<Category> categories = categoryRepository.getAllByName(category.getName());
        for (Category tmpCategory : categories) {
            tmpCategory.addTransaction(transaction);
            categoryRepository.save(tmpCategory);
        }
    }

    @Override
    public void removeTransaction(Category category, Transaction transaction) {
        category.removeTransaction(transaction);
    }

    @Override
    public List<Category> showCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> showByMonth(String month) {
        List<Category> categories = new ArrayList<>();
        for (Category category : categoryRepository.findAll()) {
            int tmpSum = category.getSum((i -> (i.equals(month))), transaction -> transaction);
            if (tmpSum > 0) {
                categories.add(category);
            }
        }
        return categories;
    }

    @Override
    public List<String> showByMonths(Category category) {
        List<String> months = List.of("January", "February", "March", "April",
                "May", "June", "July", "August", "September", "October",
                "November", "December");
        List<String> ans = new ArrayList<>();
        for (String month : months) {
            int tmpSum = category.getSum((i -> (i.equals(month))), transaction -> transaction);
            if (tmpSum > 0) {
                ans.add(month + " " + tmpSum);
            }
        }
        return ans;
    }

    private Mcc checkMccExists(Category category) {
        for (Mcc mcc : category.getMcc()) {
            if (mccRepository.existsById(mcc.getId())) {
                return mcc;
            }
        }
        return null;
    }
}
