package org.liptsoft.transactionmanager.repository;

import org.liptsoft.transactionmanager.model.Mcc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MccRepository extends JpaRepository<Mcc, Long> {
    boolean existsByMcc(Integer mcc);
    List<Mcc> findAllByParentCategoryId(Long category_id);
}
