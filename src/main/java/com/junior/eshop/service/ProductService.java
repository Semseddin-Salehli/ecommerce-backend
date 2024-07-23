package com.junior.eshop.service;

import com.junior.eshop.dto.request.ProductRequest;
import com.junior.eshop.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {

    List<ProductResponse> getAll();

    Long add(ProductRequest request);

    ProductResponse update(Long id, ProductRequest request);

    Long delete(Long id);

    ProductResponse getByProdId(Long id);

    List<ProductResponse> getBySupplierId(Long supplierId);

    List<ProductResponse> getByCategoryId(Long categoryId);
}
