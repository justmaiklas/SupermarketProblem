package com.supermarket.Exceptions;

public class PayNotAcceptedException extends Exception {
    public PayNotAcceptedException (double value) {
        super("Tried to paid with not accepted cash: " + value);
    }
}
