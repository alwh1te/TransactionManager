package org.liptsoft.transactionmanager.repository;

import org.liptsoft.transactionmanager.model.Mcc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MccRepository extends JpaRepository<Mcc, Long> {
}
