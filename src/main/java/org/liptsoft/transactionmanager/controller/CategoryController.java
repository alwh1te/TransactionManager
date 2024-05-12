package org.liptsoft.transactionmanager.controller;

import org.liptsoft.transactionmanager.model.Category;
import org.liptsoft.transactionmanager.model.Mcc;
import org.liptsoft.transactionmanager.model.Transaction;
import org.liptsoft.transactionmanager.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/categories")
    public List<Category> getCategories() {
        return expenseService.showCategories();
    }

    @PostMapping("/categories")
    public Category addCategory(@RequestBody Category category) {
        return expenseService.add(category);
    }

    @PutMapping("/categories/{category_id}/mcc")
    public Category addMcc(@PathVariable Long category_id, @RequestBody Mcc mcc) {
        return expenseService.addMcc(category_id, mcc);
    }

    @PostMapping("/categories/{category_id}/subcategories")
    public String addParentCategory(@PathVariable Long category_id, @RequestBody Category subCategory) {
        return expenseService.addParentCategory(category_id, subCategory);
    }

    @GetMapping("/categories/transactions")
    public List<Transaction> showTransactionsByMonth(@RequestParam(name = "month") int month) {
        return expenseService.showByMonth(month);
    }

    @GetMapping("/categories/transactions/{category_id}")
    public List<Transaction> showTransactionsByMonths(@PathVariable Long category_id) {
        return expenseService.showByMonths(category_id);
    }

    @DeleteMapping("/categories/{category_id}")
    public String deleteCategory(@PathVariable Long category_id) {
        return expenseService.removeCategory(category_id);
    }
}
