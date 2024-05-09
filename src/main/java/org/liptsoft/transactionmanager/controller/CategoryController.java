package org.liptsoft.transactionmanager.controller;

import org.liptsoft.transactionmanager.exceptions.InvalidDataException;
import org.liptsoft.transactionmanager.model.Category;
import org.liptsoft.transactionmanager.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public List<Category> getCategories() {
        return categoryService.showCategories();
    }

    @PostMapping("/categories")
    public void addCategory(@RequestBody Category category) {
        categoryService.add(category);
    }
}
