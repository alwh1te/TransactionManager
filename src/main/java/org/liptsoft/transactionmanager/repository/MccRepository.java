package org.liptsoft.transactionmanager.repository;

import org.liptsoft.transactionmanager.model.Mcc;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MccRepository extends JpaRepository<Mcc, Integer> {
}
