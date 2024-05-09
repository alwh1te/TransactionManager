package org.liptsoft.transactionmanager.controller;

import org.liptsoft.transactionmanager.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping("")
    public String showCategories(Model model) {
        model.addAttribute("categories", expenseService.showCategories());
        model.addAttribute("transactions", expenseService.showAllTransactions());
        return "layout";
    }
}
