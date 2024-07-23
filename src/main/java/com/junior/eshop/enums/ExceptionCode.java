package com.junior.eshop.enums;

public enum ExceptionCode {
    USER_NOT_FOUND(100),
    PRODUCT_NOT_FOUND(101),
    CATEGORY_NOT_FOUND(102),
    SUPPLIER_NOT_FOUND(103),
    VALIDATION_EXCEPTION(104);

    private final int code;

    ExceptionCode(int i) {
        code = i;
    }

    public int getCode() {
        return code;
    }
}
