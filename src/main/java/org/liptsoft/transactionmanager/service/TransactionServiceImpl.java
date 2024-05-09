package org.liptsoft.transactionmanager.service;

import org.liptsoft.transactionmanager.model.Transaction;
import org.liptsoft.transactionmanager.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public class TransactionServiceImpl {

    private TransactionRepository transactionRepository;

    public List<Transaction> showTransactionsInCategory(Long category_id) {
//        List<Transaction> all = transactionRepository.findAll();
//        List<Transaction> result = new ArrayList<>();
//        for (Transaction transaction : all) {
//            if (transaction.getP)
//        }
        return transactionRepository.findAllByCategoryId(category_id);
    }

    public void addTransaction(Long category_id, Transaction transaction) {
//        transaction.setCategory(category_id);
        transactionRepository.save(transaction);
    }

    public void removeTransaction(Transaction transaction) {
        if (transactionRepository.existsById(transaction.getId())) {
            transactionRepository.deleteById(transaction.getId());
        }
    }
}
