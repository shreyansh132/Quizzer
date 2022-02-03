package com.shreyansh.quizzer.services;

import com.shreyansh.quizzer.dto.CategoryDto;
import com.shreyansh.quizzer.entities.Category;

import java.util.List;

public interface CategoryService {
    Category getCategoryById(String id);
    Category getCategoryByIdOrName(String category);
    List<Category> getAllCategories();

    Category addCategory(CategoryDto categoryDto);
    List<Category> addCategoryList(List<CategoryDto> categoryDtoList);

    Category updateCategory(Category category);
    List<Category> updateCategoryList(List<Category> categories);

    boolean categoryExistsByIdOrName(String category);

    void deleteCategoryByNameOrId(String nameOrId);
    void deleteAllCategories();
}
