package main;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import main.config.Config;

public class Billing {

    public static ArrayList < Inventory > INVENTORIES;

    public static void main(String[] args) {
        Path inventoryPath = Paths.get(args[0]);
        Path cardPath = Paths.get(args[1]);
        System.out.println("Please enter the absolute path for the input file: ");
        try (Scanner sc = new Scanner(System.in)) {
            String orderPath = sc.nextLine();
            INVENTORIES = FileUtility.readCSVInitInventory(inventoryPath.toString());
            Order order = FileUtility.readCSVOrder(orderPath.toString());
            ShoppingCart.validateAndPlaceOrder(order, cardPath.toString());
        }
        System.out.println("Output has been written to " +Config.SUCCESS_FILE_NAME);
    }
}