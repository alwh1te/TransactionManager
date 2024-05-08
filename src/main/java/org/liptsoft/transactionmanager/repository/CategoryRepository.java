package org.liptsoft.transactionmanager.repository;

import org.liptsoft.transactionmanager.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> getAllByName(String name);
}
