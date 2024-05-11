package org.liptsoft.transactionmanager.controller;

import org.liptsoft.transactionmanager.model.Transaction;
import org.liptsoft.transactionmanager.queryTemplates.AddMccQuery;
import org.liptsoft.transactionmanager.model.Category;
import org.liptsoft.transactionmanager.queryTemplates.AddTransactionQuery;
import org.liptsoft.transactionmanager.queryTemplates.CategoryCreateQuery;
import org.liptsoft.transactionmanager.model.Mcc;
import org.liptsoft.transactionmanager.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WebController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping("")
    public String showCategories(Model model) {
        model.addAttribute("categories", expenseService.showCategories());
        model.addAttribute("transactions", expenseService.showAllTransactions());
        model.addAttribute("categoryQuery", new CategoryCreateQuery());
        model.addAttribute("mccQuery", new AddMccQuery());
        model.addAttribute("transactionQuery", new AddTransactionQuery());
        return "layout";
    }

    @GetMapping("/showByMonth")
    public String showByMonth(Model model, @ModelAttribute Integer month) {
        model.addAttribute("showByMonth", expenseService.showByMonth(month));
        return "redirect:/";
    }

    @GetMapping("/showByMonths")
    public String showByMonths(Model model, @ModelAttribute Long category_id) {
        model.addAttribute("showByMonths", expenseService.showByMonths(category_id));
        return "redirect:/";
    }

    @GetMapping("/showAllTransactionsInCategory")
    public String showAllTransactionsInCategory(Model model, @ModelAttribute Long category_id) {
        model.addAttribute("showAllTransactionsInCategory", expenseService.showTransactionsInCategory(category_id));
        return "redirect:/";
    }

    @PostMapping("/addCategory")
    public String createCategory(@ModelAttribute CategoryCreateQuery categoryQuery) {
        Category category = new Category();
        category.setName(categoryQuery.getCategoryName());
        category = expenseService.add(category);
        expenseService.setParentCategory(category, categoryQuery.getParentCategory_id());
        return "redirect:/";
    }

    @PutMapping("/addMcc")
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
}
