package org.liptsoft.transactionmanager.controller;

import org.liptsoft.transactionmanager.model.Transaction;
import org.liptsoft.transactionmanager.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/transactions")
    public List<Transaction> showAllTransactions() {
        return expenseService.showAllTransactions();
    }

    @GetMapping("/categories/{category_id}/transactions")
    public List<Transaction> showTransactionsInCategory(@PathVariable Long category_id) {
//        transactionService.
        return expenseService.showTransactionsInCategory(category_id);
    }

    @PostMapping("/categories/{category_id}/transactions")
    public void addTransactionToCategory(@PathVariable Long category_id, @RequestBody Transaction transaction) {
        expenseService.addTransaction(category_id, transaction);
    }

    @DeleteMapping("/transactions/{transaction_id}")
    public void deleteTransaction(@PathVariable Long transaction_id) {
        expenseService.removeTransaction(transaction_id);
    }
}
