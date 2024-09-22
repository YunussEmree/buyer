package com.YunussEmree.buyme.category;

import java.util.List;

public interface ICategoryService {

    Category addCategory(AddCategoryRequest request);
    void deleteCategoryById(Long id);
    void updateCategory(Category category, Long categoryId);
    Category getCategoryById(Long id);
    List<Category> getAllCategories();
}
