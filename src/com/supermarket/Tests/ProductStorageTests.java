package com.supermarket.Tests;

import com.supermarket.Models.CashRegister;
import com.supermarket.Models.Product;
import com.supermarket.Models.ProductStorage;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;

public class ProductStorageTests {

    @Test
    public void getProductByName() {
        var productList = new ArrayList<Product>();
        productList.add(new Product("SODA",10,2.3));
        productList.add(new Product("BREAD",10, 1.1));
        productList.add(new Product("WINE",10, 2.7));
        var productStorage = new ProductStorage(productList);
        var result = productStorage.getProductByName("SODA");
        assertNotNull(result);
        assertEquals("SODA", result.getName());

    }
}
