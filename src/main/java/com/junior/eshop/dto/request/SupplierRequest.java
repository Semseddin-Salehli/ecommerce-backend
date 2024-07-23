package com.junior.eshop.dto.request;

import com.junior.eshop.model.Product;
import lombok.Data;

import java.util.List;

@Data
public class SupplierRequest {
    private String name;
    private String description;
}
