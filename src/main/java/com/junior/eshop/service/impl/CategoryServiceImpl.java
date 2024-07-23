package com.junior.eshop.service.impl;

import com.junior.eshop.dto.request.CategoryRequest;
import com.junior.eshop.dto.response.CategoryResponse;
import com.junior.eshop.enums.ExceptionCode;
import com.junior.eshop.exception.NotFoundException;
import com.junior.eshop.model.Category;
import com.junior.eshop.repository.CategoryRepository;
import com.junior.eshop.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;


    @Override
    public List<CategoryResponse> getAll() {
        List<CategoryResponse> categories = categoryRepository.findAll().stream()
                .map(category -> modelMapper.map(category, CategoryResponse.class)).collect(Collectors.toList());
        return categories;
    }

    @Override
    public CategoryResponse getById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() ->
                new NotFoundException(Category.class, id, ExceptionCode.CATEGORY_NOT_FOUND.getCode()));
        return modelMapper.map(category, CategoryResponse.class);
    }

    @Override
    public CategoryResponse getByName(String categoryName) {
        Category categoryByName = categoryRepository.findCategoryByName(categoryName);

        if(categoryByName != null) {
            return modelMapper.map(categoryByName, CategoryResponse.class);
        }

        return null;
    }

    @Override
    public CategoryResponse update(Long id, CategoryRequest categoryRequest) {
        categoryRepository.findById(id).orElseThrow(() ->
                new NotFoundException(Category.class, id, ExceptionCode.CATEGORY_NOT_FOUND.getCode()));

        Category newCat = Category.builder()
                .id(id)
                .name(categoryRequest.getName()).build();

        return modelMapper.map(categoryRepository.save(newCat), CategoryResponse.class);
    }

    @Override
    public Long add(CategoryRequest categoryRequest) {
        Category savedCat = categoryRepository.save(modelMapper.map(categoryRequest, Category.class));
        return savedCat.getId();
    }

    @Override
    public void delete(Long id) {
        categoryRepository.findById(id).orElseThrow(() ->
                new NotFoundException(Category.class, id, ExceptionCode.CATEGORY_NOT_FOUND.getCode()));
        categoryRepository.deleteById(id);
    }
}
