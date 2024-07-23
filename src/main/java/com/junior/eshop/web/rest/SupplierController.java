package com.junior.eshop.web.rest;

import com.junior.eshop.dto.request.SupplierRequest;
import com.junior.eshop.dto.response.SupplierResponse;
import com.junior.eshop.service.SupplierService;
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
@RequestMapping("/supplier")
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierService service;

    @GetMapping("/all")
    public List<SupplierResponse> getAll() { return service.getAll(); }

    @GetMapping
    public SupplierResponse getById(@RequestParam("id") Long id) { return service.getById(id); }

    @PutMapping("/{id}")
    public SupplierResponse getByName(@PathVariable("id") Long id, @RequestBody SupplierRequest supplierRequest) { return service.update(id, supplierRequest); }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) { service.delete(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long add(@RequestBody SupplierRequest supplierRequest) { return service.add(supplierRequest); }
}
