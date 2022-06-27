package com.supermarket.Exceptions;

public class ProductNotFoundException extends Exception {
    public ProductNotFoundException(String productName) {
        super("This product is not found in storage: " + productName);
    }
}
