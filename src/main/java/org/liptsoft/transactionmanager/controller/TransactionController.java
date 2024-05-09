package org.liptsoft.transactionmanager.controller;

import org.liptsoft.transactionmanager.model.Transaction;
import org.liptsoft.transactionmanager.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/categories/{category_id}")
    public List<Transaction> showTransactionsInCategory(@PathVariable Long category_id) {
//        transactionService.
        return transactionService.showTransactionsInCategory(category_id);
    }

    @PostMapping("/categories/{category_id}")
    public void addTransactionToCategory(@PathVariable Long category_id, @RequestBody Transaction transaction) {
        transactionService.addTransaction(category_id, transaction);
    }
}
