package com.junior.eshop.web.rest;

import com.junior.eshop.dto.request.CategoryRequest;
import com.junior.eshop.dto.response.CategoryResponse;
import com.junior.eshop.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;

    @GetMapping("/all")
    public List<CategoryResponse> getAll() { return service.getAll(); }

    @GetMapping("/byId/{id}")
    public CategoryResponse getById(@PathVariable("id") Long id) { return service.getById(id); }

    @GetMapping
    public CategoryResponse getByName(@RequestParam("name") String name) { return service.getByName(name); }

    @PutMapping("/{id}")
    public CategoryResponse update(@PathVariable("id") Long id, @RequestBody CategoryRequest categoryRequest) { return service.update(id, categoryRequest); }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) { service.delete(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long add(@RequestBody CategoryRequest categoryRequest) { return service.add(categoryRequest); }
}
