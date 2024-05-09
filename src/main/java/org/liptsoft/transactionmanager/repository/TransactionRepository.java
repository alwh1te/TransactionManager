package org.liptsoft.transactionmanager.repository;

import org.liptsoft.transactionmanager.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByCategoryId(Long category_id);
    List<Transaction> findAllByCategoryIdAndMonth(Long category_id, int month);
    List<Transaction> findTransactionsByMonth(int month);
}
