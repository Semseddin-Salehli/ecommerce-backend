package com.junior.eshop.service;

import com.junior.eshop.dto.request.CategoryRequest;
import com.junior.eshop.dto.response.CategoryResponse;

import java.util.List;

public interface CategoryService {

    List<CategoryResponse> getAll();

    CategoryResponse getById(Long id);

    CategoryResponse getByName(String categoryName);

    CategoryResponse update(Long id, CategoryRequest categoryRequest);

    Long add(CategoryRequest categoryRequest);

    void delete(Long id);

}
