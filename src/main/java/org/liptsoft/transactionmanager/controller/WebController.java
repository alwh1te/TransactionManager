package org.liptsoft.transactionmanager.controller;

import org.liptsoft.transactionmanager.model.Transaction;
import org.liptsoft.transactionmanager.queryTemplates.*;
import org.liptsoft.transactionmanager.model.Category;
import org.liptsoft.transactionmanager.model.Mcc;
import org.liptsoft.transactionmanager.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Supplier;

@Controller
public class WebController {

    @Autowired
    private ExpenseService expenseService;
    private Supplier<List<Transaction>> supplier;

    @GetMapping("/")
    public String showCategories(Model model) {
        if (supplier == null) supplier = expenseService::showAllTransactions;
        model.addAttribute("categories", expenseService.showCategories());
        model.addAttribute("transactions", expenseService.show(supplier));
        model.addAttribute("categoryQuery", new CategoryCreateQuery());
        model.addAttribute("mccQuery", new AddMccQuery());
        model.addAttribute("sort", new SortQuery());
        model.addAttribute("transactionQuery", new AddTransactionQuery());
        model.addAttribute("categoryId", new DeleteQuery());
        model.addAttribute("transactionId", new DeleteQuery());
        return "layout";
    }

    @GetMapping("/showInCategory")
    public String showInCategory(@ModelAttribute SortQuery query) {
        if (query.getCategoryId() != null) {
            this.supplier = () -> expenseService.showTransactionsInCategory(query.getCategoryId());
        }
        return "redirect:/";
    }

    @GetMapping("/showAll")
    public String showAll() {
        this.supplier = () -> expenseService.showAllTransactions();
        return "redirect:/";
    }

    @GetMapping("/sortByMonth")
    public String sortByMonth(@ModelAttribute SortQuery query) {
        if (query.getMonth() != null) {
            this.supplier = () -> expenseService.showByMonth(query.getMonth());
        }
        return "redirect:/";
    }

    @GetMapping("/sortByMonths")
    public String sortByMonths(@ModelAttribute SortQuery query) {
        if (query.getCategoryId() != null) {
            this.supplier = () -> expenseService.showByMonths(query.getCategoryId());
        }
        return "redirect:/";
    }

    @PostMapping("/addCategory")
    public String createCategory(@ModelAttribute CategoryCreateQuery categoryQuery) {
        Category category = new Category();
        category.setName(categoryQuery.getCategoryName());
        category = expenseService.add(category);
        if (categoryQuery.getParentCategory_id() != null)
            expenseService.setParentCategory(category, categoryQuery.getParentCategory_id());
        return "redirect:/";
    }

    @PostMapping("/addMcc")
    public String addMcc(@ModelAttribute AddMccQuery query) {
        expenseService.addMcc(query.getCategory_id(), new Mcc(query.getMcc()));
        return "redirect:/";
    }

    @PostMapping("/addTransaction")
    public String addTransaction(@ModelAttribute AddTransactionQuery query) {
        Transaction transaction = new Transaction();
        transaction.setName(query.getName());
        transaction.setAmount(query.getAmount());
        transaction.setMonth(query.getMonth());
        expenseService.addTransaction(query.getCategory_id(), transaction);
        return "redirect:/";
    }

    @PostMapping("/deleteCategory")
    public String deleteCategory(@ModelAttribute DeleteQuery categoryId) {
        expenseService.removeCategory(categoryId.getId());
        return "redirect:/";
    }

    @PostMapping("/deleteTransaction")
    public String deleteTransaction(@ModelAttribute DeleteQuery transactionId) {
        expenseService.removeTransaction(transactionId.getId());
        return "redirect:/";
    }
}
