package com.supermarket.Exceptions;

public class SoldOutException extends Exception {
    public SoldOutException(String productName) {
        super("This product is sold out: " + productName);
    }
}
