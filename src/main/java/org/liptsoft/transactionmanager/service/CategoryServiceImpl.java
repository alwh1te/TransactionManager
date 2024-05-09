package org.liptsoft.transactionmanager.service;

import org.liptsoft.transactionmanager.exceptions.InvalidDataException;
import org.liptsoft.transactionmanager.exceptions.MccAlreadyExistsException;
import org.liptsoft.transactionmanager.model.Category;
import org.liptsoft.transactionmanager.model.Transaction;
import org.liptsoft.transactionmanager.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public class CategoryServiceImpl {

    private CategoryRepository categoryRepository;

    public void add(Category category) {
//        Mcc tmp = checkMccExists(category);
//        if (tmp != null) {
//            throw new MccAlreadyExistsException("Mcc already reserved for category: "
//                    + tmp.getCategory().getName() + '\n');
//        }
        categoryRepository.save(category);
    }

    public void addMcc(Category category, List<Integer> mcc) {
//        category.addMcc(mcc);
        categoryRepository.save(category);
    }

    public void addSubCategories(Category mainCategory, List<Category> subCategories) {
//        mainCategory.addSubCategories(subCategories);
        categoryRepository.save(mainCategory);
    }

    public void removeCategory(Category category) {
        if (categoryRepository.existsById(category.getId())) {
            categoryRepository.deleteById(category.getId());
        }
    }

    public List<Category> showCategories() {
            return categoryRepository.findAll();
    }

    public List<Category> showByMonth(String month) {
        List<Category> categories = new ArrayList<>();
        for (Category category : categoryRepository.findAll()) {
//            int tmpSum = category.getSum((i -> (i.equals(month))), transaction -> transaction);
//            if (tmpSum > 0) {
//                categories.add(category);
//            }
        }
        return categories;
    }

    public List<String> showByMonths(Category category) {
        List<String> months = List.of("January", "February", "March", "April",
                "May", "June", "July", "August", "September", "October",
                "November", "December");
        List<String> ans = new ArrayList<>();
        for (String month : months) {
//            int tmpSum = category.getSum((i -> (i.equals(month))), transaction -> transaction);
//            if (tmpSum > 0) {
//                ans.add(month + " " + tmpSum);
//            }
        }
        return ans;
    }
}
