package com.shreyansh.quizzer.controller;

import com.shreyansh.quizzer.Exception.CategoryAlreadyExists;
import com.shreyansh.quizzer.Exception.DataNotFoundException;
import com.shreyansh.quizzer.dto.CategoryDto;
import com.shreyansh.quizzer.entities.Category;
import com.shreyansh.quizzer.services.CategoryService;
import com.shreyansh.quizzer.services.QuizService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/categories")
@CrossOrigin("*")
@Slf4j
public class CategoryController {

    @Autowired
    QuizService quizService;

    @Autowired
    CategoryService categoryService;

    @GetMapping
    ResponseEntity<List<Category>> getAll(){
        return new ResponseEntity<>(categoryService.getAllCategories(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getById(@PathVariable String  id) {
        return new ResponseEntity<>(categoryService.getCategoryById(id), HttpStatus.OK);
    }

    @GetMapping("/nameOrId")
    public ResponseEntity<Category> getByIdOrName(@RequestParam(name = "value") String  nameOrId) {
        return new ResponseEntity<>(categoryService.getCategoryByIdOrName(nameOrId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Category> addNewCategory(@RequestBody CategoryDto categoryDto) {
        if(categoryService.categoryExistsByIdOrName(categoryDto.getCategoryName())){
            throw new CategoryAlreadyExists("Category with Name: " + categoryDto.getCategoryName()+" Already Exists");
        }
        return new ResponseEntity<>(categoryService.addCategory(categoryDto), HttpStatus.CREATED);
    }

    @PostMapping(value = "/list")
    public ResponseEntity<List<Category>> addCategoryList(@RequestBody List<CategoryDto> categoryDtoList) {
        return new ResponseEntity<>(categoryService.addCategoryList(categoryDtoList), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Category> updateCategory(@RequestBody Category category) {
        return new ResponseEntity<>(categoryService.updateCategory(category),HttpStatus.OK);
    }

    @PutMapping(value = "/nameOrId")
    public ResponseEntity<List<Category>> updateByNameOrId(@RequestBody List<Category> category) {
        return new ResponseEntity<>(categoryService.updateCategoryList(category),HttpStatus.OK);
    }

    @DeleteMapping(value = "/nameOrId")
    public ResponseEntity deleteByNameOrId(@RequestParam(name = "nameOrId") String  nameOrId) {
       if (!categoryService.categoryExistsByIdOrName(nameOrId)) throw new DataNotFoundException("Category with Name " + nameOrId + " doesn't exists");
           categoryService.deleteCategoryByNameOrId(nameOrId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/all")
    public ResponseEntity deleteAll() {
        categoryService.deleteAllCategories();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
