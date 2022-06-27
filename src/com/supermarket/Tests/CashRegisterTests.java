package com.supermarket.Tests;
import com.supermarket.Exceptions.PayNotAcceptedException;
import com.supermarket.Models.Cash;
import com.supermarket.Models.CashRegister;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNull;

public class CashRegisterTests
{
    @Test
    public void getCashInRegisterExists() {
        var cashList = GenerateListCash();
        CashRegister cashRegister = new CashRegister(cashList);
        var result = cashRegister.GetCashByValue(0.1);
        assertEquals(cashList.stream().filter(c -> c.getValue() == 0.1).findFirst().get(), result);

    }
    @Test
    public void getCashInRegisterNotExist() {
        var cashList = GenerateListCash();
        CashRegister cashRegister = new CashRegister(cashList);
        var result = cashRegister.GetCashByValue(0.2);
        assertNull(result);

    }
    @Test
    public void addCashSuccess() {
        CashRegister cashRegister = new CashRegister(GenerateListCash());
        cashRegister.addCash(0.1);
        var result = cashRegister.GetCashByValue(0.1);
        assertEquals(3, result.getQuantity());

    }
    private static List<Cash> GenerateListCash() {
        var cashList = new ArrayList<Cash>();
        cashList.add(new Cash(0.1, 2));
        cashList.add(new Cash(0.5, 10));
        cashList.add(new Cash(1, 0));
        cashList.add(new Cash(2, 0));
        return cashList;
    }
}
