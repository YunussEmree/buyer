package com.YunussEmree.buyer.controllers;

import com.YunussEmree.buyer.category.Category;
import com.YunussEmree.buyer.category.ICategoryService;
import com.YunussEmree.buyer.core.utilities.exceptions.ResourceAlreadyExistsException;
import com.YunussEmree.buyer.core.utilities.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final ICategoryService categoryService;

    @GetMapping
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
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        try {
            Category theCategory = categoryService.updateCategory(category, id);
            return ResponseEntity.ok(new ApiResponse("Success", theCategory));
        }
        catch(ResourceNotFoundException e){
            return ResponseEntity.status(404).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long id) {
        try {
            categoryService.deleteCategoryById(id);
            return ResponseEntity.ok(new ApiResponse("Success", null));
        } catch (ResourceNotFoundException e){
            return ResponseEntity.status(404).body(new ApiResponse(e.getMessage(), null));
        }
    }


}
