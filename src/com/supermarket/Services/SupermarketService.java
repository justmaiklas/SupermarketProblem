package com.supermarket.Services;

import com.supermarket.Exceptions.NotEnoughChangeException;
import com.supermarket.Exceptions.SoldOutException;
import com.supermarket.Models.Cash;
import com.supermarket.Models.Product;
import java.util.List;

public interface SupermarketService {
    void PrintProductStorage ();
    void PrintCashRegister ();
    void PrintProductsAndPrices();
    boolean CanProductBeBought(String productName);
    Product GetProductFromStorage(String buyingProductName);

    boolean IsCashValid(double insertedCash);
    void AddCashToCashRegister(double insertedCash);

    List<Cash> getCashChange(double change) throws NotEnoughChangeException;

    void RemoveCashFromCashRegister(double value, int quantity);
    void RemoveProductFromStorage(Product product) throws SoldOutException;
}
