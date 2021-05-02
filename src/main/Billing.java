package main;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import main.config.Config;

public class Billing {

    public static ArrayList < Inventory > INVENTORIES;

    public static void main(String[] args) {
        Path inventoryPath = Paths.get(args[0]);
        Path cardPath = Paths.get(args[1]);
        Path orderPath = Paths.get(args[2]);
        
        INVENTORIES = FileUtility.readInventoryCSV(inventoryPath.toString());
        Order order = FileUtility.readOrderCSV(orderPath.toString());
        ShoppingCart.validateAndPlaceOrder(order, cardPath.toString());
        
        System.out.println("Output has been written to " +Config.SUCCESS_FILE_NAME);
    }
}