package com.junior.eshop.service;

import com.junior.eshop.dto.request.SupplierRequest;
import com.junior.eshop.dto.response.SupplierResponse;

import java.util.List;

public interface SupplierService {

    SupplierResponse getById(Long id);

    List<SupplierResponse> getAll();

    SupplierResponse update(Long id, SupplierRequest supplierRequest);

    Long add(SupplierRequest supplierRequest);

    void delete(Long id);
}
