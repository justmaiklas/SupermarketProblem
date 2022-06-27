package com.supermarket.Services;

import com.supermarket.Exceptions.NotEnoughChangeException;
import com.supermarket.Exceptions.PayNotAcceptedException;
import com.supermarket.Exceptions.SoldOutException;
import com.supermarket.Models.Cash;
import com.supermarket.Models.CashRegister;
import com.supermarket.Models.Product;
import com.supermarket.Models.ProductStorage;

import java.util.ArrayList;
import static com.supermarket.Utils.RoundDouble;
import java.util.Comparator;
import java.util.List;

public class SupermarketServiceImpl implements SupermarketService {
    private static SupermarketServiceImpl instance = null;
    private static CashRegister cashRegister;
    private static ProductStorage productStorage;
    @Override
    public void PrintProductStorage() {
        for (Product product : productStorage.getProductList()) {
            System.out.println(product.getName() + " Quantity: " + product.getQuantity() );
        }
    }

    @Override
    public void PrintCashRegister() {
        for (Cash cash : cashRegister.getCashList()) {
            System.out.println("Value: " + cash.getValue() + ", quantity: " + cash.getQuantity() );
        }
    }

    @Override
    public void PrintProductsAndPrices() {
        String message = "";
        for (Product product : productStorage.getProductList()) {
            if (product.getQuantity() > 0) {
                message+= product.getName() + " (price: " + product.getPrice() + ") ";
            }
        }
        System.out.println(message);
    }

    @Override
    public boolean CanProductBeBought(String productName) {
        var product = productStorage.getProductByName(productName);
        if (product == null || product.getQuantity() <= 0) {
            try {
                throw new SoldOutException(productName);
            } catch (SoldOutException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    @Override
    public Product GetProductFromStorage(String productName) {
        return productStorage.getProductByName(productName);
    }


    @Override
    public boolean IsCashValid(double insertedCash) {
        if (insertedCash < 0 || cashRegister.GetCashByValue(insertedCash) == null) {
            try {
                throw new PayNotAcceptedException(insertedCash);
            } catch (PayNotAcceptedException e) {
                e.printStackTrace();

                return false;
            }
        }
        return true;
    }

    public static SupermarketServiceImpl SupermarketServiceImplSingleton()
    {
        if (instance == null) {
            instance = new SupermarketServiceImpl();
            cashRegister = new CashRegister();
            productStorage = new ProductStorage();
        }
        return instance;
    }
    @Override
    public void AddCashToCashRegister(double insertedCash) {
        cashRegister.addCash(insertedCash);
    }

    @Override
    public List<Cash> getCashChange(double change) throws NotEnoughChangeException {
    var changeList = new ArrayList<Cash>();
    List<Cash> cashListInRegister = new ArrayList<Cash>(cashRegister.getCashList());
        change = RoundDouble(change, 1);

        while( change != 0) {
        var max = cashListInRegister.stream().max(Comparator.comparingDouble(value -> value.getValue())).get();
        if (max.getQuantity() == 0) {
            cashListInRegister.remove(max);
            continue;
        }
        if (cashListInRegister.size() == 0) {
            throw new NotEnoughChangeException();
        }
        var changeToAddCount =(int) RoundDouble(change / max.getValue(),3);
        int changeToAddCounts = (int) RoundDouble(change / max.getValue(),4);
        if (changeToAddCount == 0) {
            cashListInRegister.remove(max);
            continue;
        }
        if (changeToAddCount > max.getQuantity()) {
            changeToAddCount = max.getQuantity();
        }
        change = change - (max.getValue()*changeToAddCount);
        cashListInRegister.remove(max);
        changeList.add(new Cash(max.getValue(),changeToAddCount));
        change = RoundDouble(change, 1);
        }
    return changeList;

    }

    @Override
    public void RemoveCashFromCashRegister(double value, int quantity) {
        cashRegister.removeCash(value,quantity);
    }

    @Override
    public void RemoveProductFromStorage(Product product) throws SoldOutException {
        productStorage.removeProductFromStorage(product);
    }
}
