package com.supermarket;

import com.supermarket.Exceptions.NotEnoughChangeException;
import com.supermarket.Exceptions.PayNotAcceptedException;
import com.supermarket.Exceptions.SoldOutException;
import com.supermarket.Models.Product;
import com.supermarket.Services.SupermarketServiceImpl;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) throws NotEnoughChangeException, SoldOutException {
        SupermarketServiceImpl supermarketService = SupermarketServiceImpl.SupermarketServiceImplSingleton();
        printInventories("Initial", supermarketService);
        StartProgram(supermarketService);
    }

    private static void StartProgram(SupermarketServiceImpl supermarketService) throws NotEnoughChangeException, SoldOutException {
        var buyingProduct = BuyingMenu(supermarketService);
        double paidPrice = 0;
        paidPrice = ProceedToPay(buyingProduct, supermarketService);
        PaymentSuccessful(buyingProduct,supermarketService,paidPrice);
    }

    private static void printDashSeparator() {
        System.out.println("-------------");
    }
    private static void printInventories(String prefix, SupermarketServiceImpl supermarketService) {
        printDashSeparator();
        System.out.println(prefix + " Product Inventory");
        supermarketService.PrintProductStorage();
        System.out.println(prefix + " Cash Inventory");
        supermarketService.PrintCashRegister();
        printDashSeparator();
    }
    private static String InputProductName()
    {
        Scanner sc = new Scanner(System.in);
        String productName = sc.nextLine();
        return productName.trim().toUpperCase();
    }
    private static Product BuyingMenu(SupermarketServiceImpl supermarketService) throws SoldOutException {
        Product buyingProduct = null;
        while (buyingProduct == null) {
            System.out.println("\nWhat would you like to buy? Type in the name of the desired product.");
            supermarketService.PrintProductsAndPrices();
            String buyingProductName = InputProductName();
            if (!supermarketService.CanProductBeBought(buyingProductName)) {
                continue;
            }
            buyingProduct = supermarketService.GetProductFromStorage(buyingProductName);
            System.out.println("You are trying to buy " + buyingProduct.getName() + ". You need to pay " + buyingProduct.getPrice() + ".");
        }

        return buyingProduct;

    }
    private static double ProceedToPay (Product buyingProduct, SupermarketServiceImpl supermarketService ) {
        double paidPrice = 0;
        while (paidPrice < buyingProduct.getPrice()) {
            if (paidPrice != 0) {
                System.out.println("You paid " + paidPrice + " in total. You still need to pay " + (String.format("%.1f", buyingProduct.getPrice() - paidPrice)));
            }
            System.out.println("Provide bill or coin (accepted values: 0.1, 0.5, 1, 2)");
            double insertedCash = InputCash();

            if (!supermarketService.IsCashValid(insertedCash)) {
                continue;
            }
            paidPrice += insertedCash;
            supermarketService.AddCashToCashRegister(insertedCash);
        }
        return paidPrice;

    }
    private static void PaymentSuccessful(Product buyingProduct, SupermarketServiceImpl supermarketService, double paidPrice) throws NotEnoughChangeException, SoldOutException {
        var change = paidPrice - buyingProduct.getPrice();
        System.out.println("You paid " + paidPrice + " in total. Your change will be " + (String.format("%.1f", change)));
        var changeList = supermarketService.getCashChange(change);
        if (changeList.size() > 0) {
            System.out.println("Here is you change:");
            for (var cash : changeList) {
                System.out.println("Value: " + cash.getValue() + ", quantity: " + cash.getQuantity() );
                supermarketService.RemoveCashFromCashRegister(cash.getValue(),cash.getQuantity());

            }
        }
        supermarketService.RemoveProductFromStorage(buyingProduct);
        printInventories("Updated", supermarketService);
        StartProgram(supermarketService);
    }

    private static double InputCash() {
        Scanner sc = new Scanner(System.in);
        return sc.nextDouble();
    }
}
