package com.junior.eshop.web.rest;

import com.junior.eshop.dto.request.ProductRequest;
import com.junior.eshop.dto.response.ProductResponse;
import com.junior.eshop.service.ProductService;
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
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/all")
    public List<ProductResponse> getAll() { return productService.getAll(); }

    @GetMapping
    public ProductResponse getById(@RequestParam(name = "id") Long id) { return productService.getByProdId(id); }

    @GetMapping("/supp")
    public List<ProductResponse> getBySupplierId(@RequestParam(name = "id") Long id) { return productService.getBySupplierId(id); }

    @GetMapping("/cat")
    public List<ProductResponse> getByCategoryId(@RequestParam(name = "id") Long id) { return productService.getByCategoryId(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long save(@RequestBody ProductRequest productRequest) { return productService.add(productRequest); }

    @DeleteMapping("/{id}")
    public Long delete(@PathVariable(name = "id") Long id) { return productService.delete(id); }

    @PutMapping("/{id}")
    public ProductResponse update(@PathVariable(name = "id") Long id, @RequestBody ProductRequest productRequest) { return productService.update(id, productRequest); }
}