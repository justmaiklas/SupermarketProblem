package com.supermarket.Models;

import com.supermarket.Exceptions.PayNotAcceptedException;

import java.util.ArrayList;
import java.util.List;

public class CashRegister {
    private List<Cash> cashList;

    public CashRegister(List<Cash> cashList) {
        this.cashList = cashList;
    }

    public CashRegister() {
        cashList = new ArrayList<Cash>();
        cashList.add(new Cash(2,50));
        cashList.add(new Cash(1,50));
        cashList.add(new Cash(0.5,50));
        cashList.add(new Cash(0.1,50));
    }

    public List<Cash> getCashList() {
        return cashList;
    }

    public void setCashList(List<Cash> cashList) {
        this.cashList = cashList;
    }

    public void addCash(double value)  {
        var currentCash = GetCashByValue(value);

        currentCash.incrementQuantity();
    }
    public void removeCash(double value) {
        var currentCash = GetCashByValue(value);
        currentCash.decrementQuantity();
    }
    public void removeCash(double value, int quantity) {
        var currentCash = GetCashByValue(value);
        currentCash.setQuantity(currentCash.getQuantity() - quantity);
    }

    public Cash GetCashByValue(double value) {
        var currentCash = cashList.stream().filter(c -> c.getValue() == value).findFirst();
        return currentCash.isEmpty() ? null : currentCash.get();
    }
}
