package com.supermarket.Models;

public class Cash {
    private double value;
    private int quantity;

    public Cash(double value, int quantity) {
        this.value = value;
        this.quantity = quantity;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void incrementQuantity() {
        quantity++;
    }
    public void decrementQuantity() {
        quantity--;
    }
}
