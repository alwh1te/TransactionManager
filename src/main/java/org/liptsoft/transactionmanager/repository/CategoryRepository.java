package org.liptsoft.transactionmanager.repository;

import org.liptsoft.transactionmanager.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByParentCategoryId(Long parentCategory_id);
}
