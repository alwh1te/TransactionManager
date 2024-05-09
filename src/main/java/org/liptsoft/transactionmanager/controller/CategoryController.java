package org.liptsoft.transactionmanager.controller;

import org.liptsoft.transactionmanager.model.Category;
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
    public void addCategory(@RequestBody Category category) {
        expenseService.add(category);
    }

    @PostMapping("/categories/{category_id}/subcategories")
    public void addSubCategory(@PathVariable Long category_id, @RequestBody Category subCategory) {
        expenseService.addSubCategories(category_id, subCategory);
    }

//    @GetMapping("/transactions")
//    public String showTransactions(Model model) {
//        List<Category> categories = categoryService.showCategories();
////        List<Transaction> transactions = .showTransactions();
//        model.addAttribute("categories", categories);
////        model.addAttribute("transactions", transactions);
//
//        return "layout";
//    }

    @GetMapping("/categories/transactions")
    public List<String> showTransactionsByMonth(@RequestParam(name = "month") int month) {
        return expenseService.showByMonth(month);
    }

    @GetMapping("/categories/transactions/{category_id}")
    public List<String> showTransactionsByMonths(@PathVariable Long category_id) {
        return expenseService.showByMonths(category_id);
    }

    @DeleteMapping("/categories/{category_id}")
    public void deleteCategory(@PathVariable Long category_id) {
        expenseService.removeCategory(category_id);
    }
}
