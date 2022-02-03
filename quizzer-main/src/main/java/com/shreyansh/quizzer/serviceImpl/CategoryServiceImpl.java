package com.shreyansh.quizzer.serviceImpl;

import com.shreyansh.quizzer.dto.CategoryDto;
import com.shreyansh.quizzer.entities.Category;
import com.shreyansh.quizzer.repository.CategoryRepository;
import com.shreyansh.quizzer.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
      return categoryRepository.findAll();
    }

    @Override
    public List<Category> addCategoryList(List<CategoryDto> categoryDtoList) {
        List<Category> categories = new ArrayList<>();
        categoryDtoList.forEach(
                categoryDto -> {
                    Category category = Category.builder()
                            .imageUrl(categoryDto.getImageUrl())
                            .description(categoryDto.getDescription())
                            .categoryName(categoryDto.getCategoryName())
                            .build();
                    categories.add(category);
                }
        );
        return categoryRepository.saveAll(categories);
    }

    @Override
    public Category updateCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> updateCategoryList(List<Category> categories) {
        return categoryRepository.saveAll(categories);
    }

    @Override
    public boolean categoryExistsByIdOrName(String category) {
        return categoryRepository.existsByCategoryIdOrCategoryName(category,category);
    }

    @Override
    public Category addCategory(CategoryDto categoryDto) {
        Category category = Category.builder()
                .categoryName(categoryDto.getCategoryName())
                .description(categoryDto.getDescription())
                .imageUrl(categoryDto.getImageUrl())
                .build();
        return categoryRepository.save(category);
    }

    @Override
    public Category getCategoryById(String id) {
        return categoryRepository.findById(id).get();
    }

    @Override
    public Category getCategoryByIdOrName(String category) {
        return categoryRepository.findByCategoryIdOrCategoryName(category,category);
    }

    @Override
    public void deleteCategoryByNameOrId(String nameOrId) {
        categoryRepository.deleteById(nameOrId);
    }

    @Override
    public void deleteAllCategories() {
        categoryRepository.deleteAll();
    }
}
