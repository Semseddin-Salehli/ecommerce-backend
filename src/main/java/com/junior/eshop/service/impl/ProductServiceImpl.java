package com.junior.eshop.service.impl;

import com.junior.eshop.dto.request.ProductRequest;
import com.junior.eshop.dto.response.ProductResponse;
import com.junior.eshop.enums.ExceptionCode;
import com.junior.eshop.exception.NotFoundException;
import com.junior.eshop.model.Category;
import com.junior.eshop.model.Product;
import com.junior.eshop.model.Supplier;
import com.junior.eshop.repository.CategoryRepository;
import com.junior.eshop.repository.ProductRepository;
import com.junior.eshop.repository.SupplierRepository;
import com.junior.eshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<ProductResponse> getAll() {
        List<ProductResponse> allProducts = productRepository.findAll().stream()
                .map(product -> modelMapper.map(product, ProductResponse.class)).collect(Collectors.toList());
        return allProducts;
    }

    @Override
    public Long add(ProductRequest request) {
        Supplier supplier = supplierRepository.findById(request.getSupplier_id()).orElseThrow(() ->
                new NotFoundException(Supplier.class, request.getSupplier_id(), ExceptionCode.SUPPLIER_NOT_FOUND.getCode()));

        Category category = categoryRepository.findById(request.getCategory_id()).orElseThrow(() ->
                new NotFoundException(Category.class, request.getCategory_id(), ExceptionCode.CATEGORY_NOT_FOUND.getCode()));

        Product newProd = modelMapper.map(request, Product.class);
        newProd.setCategory(category);
        newProd.setSupplier(supplier);

        return productRepository.save(newProd).getId();
    }

    @Override
    public ProductResponse update(Long id, ProductRequest request) {
        productRepository.findById(id).orElseThrow(() ->
                new NotFoundException(Product.class, id, ExceptionCode.PRODUCT_NOT_FOUND.getCode()));

        Supplier supplier = supplierRepository.findById(request.getSupplier_id()).orElseThrow(() ->
                new NotFoundException(Supplier.class, request.getSupplier_id(), ExceptionCode.SUPPLIER_NOT_FOUND.getCode()));

        Category category = categoryRepository.findById(request.getCategory_id()).orElseThrow(() ->
                new NotFoundException(Category.class, request.getCategory_id(), ExceptionCode.CATEGORY_NOT_FOUND.getCode()));

        Product newProd = Product.builder()
                .id(id)
                .category(category)
                .supplier(supplier)
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .vip(request.getVip()).build();

        productRepository.save(newProd);

        return modelMapper.map(newProd, ProductResponse.class);
    }

    @Override
    public Long delete(Long id) {
        productRepository.findById(id).orElseThrow(() ->
                new NotFoundException(Product.class, id, ExceptionCode.PRODUCT_NOT_FOUND.getCode()));

        productRepository.deleteById(id);

        return id;
    }

    @Override
    public ProductResponse getByProdId(Long id) {
        Product findProduct = productRepository.findById(id).orElseThrow(() ->
                new NotFoundException(Product.class, id, ExceptionCode.PRODUCT_NOT_FOUND.getCode()));

        return modelMapper.map(findProduct, ProductResponse.class);
    }

    @Override
    public List<ProductResponse> getBySupplierId(Long supplierId) {
        supplierRepository.findById(supplierId).orElseThrow(() ->
                new NotFoundException(Supplier.class, supplierId, ExceptionCode.SUPPLIER_NOT_FOUND.getCode()));

        List<ProductResponse> bySupplierId = productRepository.findBySupplierId(supplierId).stream()
                .map(product -> modelMapper.map(product, ProductResponse.class)).collect(Collectors.toList());

        return bySupplierId;
    }

    @Override
    public List<ProductResponse> getByCategoryId(Long categoryId) {
        categoryRepository.findById(categoryId).orElseThrow(() ->
                new NotFoundException(Category.class, categoryId, ExceptionCode.CATEGORY_NOT_FOUND.getCode()));

        List<ProductResponse> byCategoryId = productRepository.findByCategoryId(categoryId).stream()
                .map(product -> modelMapper.map(product, ProductResponse.class)).collect(Collectors.toList());

        return byCategoryId;
    }
}