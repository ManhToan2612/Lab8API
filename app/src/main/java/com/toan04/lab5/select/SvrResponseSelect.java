package com.toan04.lab5.select;

import com.toan04.lab5.sanpham;

public class SvrResponseSelect { // get
    private sanpham[] products;
    private String message;

    public sanpham[] getProducts() {
        return products;
    }

    public String getMessage() {
        return message;
    }
}
