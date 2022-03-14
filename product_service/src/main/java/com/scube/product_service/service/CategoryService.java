package com.scube.product_service.service;

import com.scube.product_service.payload.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto saveCategory(CategoryDto categoryDto);

    ArrayList<ProductDto> createMultipleProduct(long categoryId, ArrayList<ProductDto> productDto);

    List<CategoryDto> getAllCategory();

    CategoryDto findCategoryById(long categoryId);

    CategoryDto updateCategoryById(long categoryId, CategoryDto categoryDto);

    void deleteCategoryById(long categoryId);

}
