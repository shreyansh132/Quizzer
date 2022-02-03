package com.shreyansh.quizzer.repository;

import com.shreyansh.quizzer.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
    Category findByCategoryIdOrCategoryName(String id, String name);
    boolean existsByCategoryIdOrCategoryName(String id, String name);
    void deleteByCategoryIdOrCategoryName(String id, String name);
}
