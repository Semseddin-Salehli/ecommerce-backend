package com.junior.eshop.service.impl;

import com.junior.eshop.dto.request.SupplierRequest;
import com.junior.eshop.dto.response.SupplierResponse;
import com.junior.eshop.enums.ExceptionCode;
import com.junior.eshop.exception.NotFoundException;
import com.junior.eshop.model.Supplier;
import com.junior.eshop.repository.SupplierRepository;
import com.junior.eshop.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;

    @Override
    public SupplierResponse getById(Long id) {
        Supplier supplier = supplierRepository.findById(id).orElseThrow(() ->
                new NotFoundException(Supplier.class, id, ExceptionCode.SUPPLIER_NOT_FOUND.getCode()));
        return modelMapper.map(supplier, SupplierResponse.class);
    }

    @Override
    public List<SupplierResponse> getAll() {
        List<SupplierResponse> suppliers = supplierRepository.findAll().stream()
                .map(supplier -> modelMapper.map(supplier, SupplierResponse.class)).collect(Collectors.toList());
        return suppliers;
    }

    @Override
    public SupplierResponse update(Long id, SupplierRequest supplierRequest) {
        supplierRepository.findById(id).orElseThrow(() ->
                new NotFoundException(Supplier.class, id, ExceptionCode.SUPPLIER_NOT_FOUND.getCode()));

        Supplier newSup = Supplier.builder()
                .id(id)
                .description(supplierRequest.getDescription())
                .name(supplierRequest.getName()).build();

        return modelMapper.map(supplierRepository.save(newSup), SupplierResponse.class);
    }

    @Override
    public Long add(SupplierRequest supplierRequest) {
        Supplier savedSup = supplierRepository.save(modelMapper.map(supplierRequest, Supplier.class));
        return savedSup.getId();
    }

    @Override
    public void delete(Long id) {
        supplierRepository.findById(id).orElseThrow(() ->
                new NotFoundException(Supplier.class, id, ExceptionCode.SUPPLIER_NOT_FOUND.getCode()));

        supplierRepository.deleteById(id);
    }
}
