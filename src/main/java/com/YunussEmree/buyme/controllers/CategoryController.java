package com.YunussEmree.buyme.controllers;

import com.YunussEmree.buyme.category.Category;
import com.YunussEmree.buyme.category.ICategoryService;
import com.YunussEmree.buyme.core.utilities.exceptions.ResourceAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.base-path}/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final ICategoryService categoryService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllCategories(){
        try{
            List<Category> categories = categoryService.getAllCategories();
            return ResponseEntity.ok(new ApiResponse("Success", categories));
        } catch (Exception e){
            return ResponseEntity.status(500).body(new ApiResponse("Failed", e.getMessage()));
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getCategoryById(@PathVariable Long id) {
        try{
            Category category = categoryService.getCategoryById(id);
            return ResponseEntity.ok(new ApiResponse("Success", category));
        } catch (Exception e){
            return ResponseEntity.status(404).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createCategory(@RequestBody Category category) {
        try{

            Category theCategory = categoryService.addCategory(category);
            return ResponseEntity.ok(new ApiResponse("Success", theCategory));
        } catch (ResourceAlreadyExistsException e){
            return ResponseEntity.status(409).body(new ApiResponse(e.getMessage(), null));
        }

    }

    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable Long id, @RequestBody Category category) {
        return categoryService.updateCategory(category, id);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategoryById(id);
    }


}
