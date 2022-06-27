package com.supermarket.Models;

import com.supermarket.Exceptions.ProductNotFoundException;
import com.supermarket.Exceptions.SoldOutException;

import java.util.ArrayList;
import java.util.List;


public class ProductStorage {
    private List<Product> productList;

    public ProductStorage(List<Product> productList) {
        this.productList = productList;
    }
    public ProductStorage() {
        productList = new ArrayList<Product>();
        productList.add(new Product("SODA",10,2.3));
        productList.add(new Product("BREAD",10, 1.1));
        productList.add(new Product("WINE",10, 2.7));
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public void removeProductFromStorage(Product product) throws SoldOutException {

        if (product.getQuantity() < 1) {
            throw new SoldOutException(product.getName());
        }
        product.decreaseQuantity();
    }

    public Product getProductByName(String productName) {
        var currentProduct = productList.stream().filter(c -> c.getName().equals(productName)).findFirst();
        return currentProduct.isEmpty() ? null : currentProduct.get();
    }
}
