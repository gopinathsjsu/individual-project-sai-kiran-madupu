

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;

public class CartManager {

    public static boolean validateAndPlaceOrder(Order order, String cardPath) {
        double totalAmount = 0.0;
        String errMessage = "";
        String successMessage = "Item,Quantity,Price\n";
        HashSet < String > cards = new HashSet < > ();
        if (order.getEssentialCount() <= Config.ESSENTIALS_ITEM_LIMIT &&
            order.getLuxuryCount() <= Config.LUXURY_ITEM_LIMIT &&
            order.getMiscCount() <= Config.MISC_ITEM_LIMIT) {
            for (Item item: order.getOrderItems()) {
//            	System.out.println("item = "+item);
//            	System.out.println("util item = "+Utility.getItemQuantity(item.getItem()));
                if (item.getQuantity() > Utility.getItemQuantity(item.getItem())) {
                    errMessage = item.getItem() + " " + item.getQuantity() + "\n";
                } else {
                    successMessage +=
                        item.getItem() +
                        "," +
                        item.getQuantity() +
                        "," +
                        Utility.getItemPrice(item.getItem(), item.getQuantity()) +
                        "\n";
                    totalAmount += Utility.getItemPrice(item.getItem(), item.getQuantity());
                }
                cards.add(item.getCardNumber());
            }
            
            if (errMessage.isEmpty()) {
                writeCardFile(cards, cardPath);
                writeOutputFile(
                		Config.SUCCESS_FILE_NAME,
                		successMessage.getBytes(StandardCharsets.UTF_8),
                		("TotalPrice" + totalAmount).getBytes(StandardCharsets.UTF_8));
                return true;
                
            } else {
                writeOutputFile(
                		Config.ERROR_FILE_NAME,
                    ("Please correct quantities for items " + errMessage).getBytes(StandardCharsets.UTF_8));
            }
            
        } else {
            writeOutputFile(
            		Config.ERROR_FILE_NAME,
                ("Please correct quantities for items. Limit for Misc is " +
                		Config.MISC_ITEM_LIMIT +
                    ", Luxury is " +
                    Config.LUXURY_ITEM_LIMIT +
                    " and Essential is " +
                    Config.ESSENTIALS_ITEM_LIMIT)
                .getBytes(StandardCharsets.UTF_8));
        }
		return false;
    }

    private static void writeOutputFile(String fileName, byte[] bytes1, byte[] bytes) {
        File file = new File(fileName);
        try {
            file.createNewFile();
            FileOutputStream oFile = new FileOutputStream(file, false);
            oFile.write(bytes);
            oFile.close();
        } catch (IOException e) {
            System.out.println("Error " + e.getMessage());
        }
    }
    
    private static void writeOutputFile(String fileName, byte[] bytes) {
        File file = new File(fileName);
        try {
            file.createNewFile();
            FileOutputStream oFile = new FileOutputStream(file, false);
            oFile.write(bytes);
            oFile.close();
        } catch (IOException e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    private static void writeCardFile(HashSet < String > cards, String cardPath) {
        ArrayList < String > savedCards = FileUtility.readCardCSV(cardPath);
        for (String card: savedCards) cards.add(card);
        writeOutputFile(cardPath, String.join("\n", cards).getBytes(StandardCharsets.UTF_8));
    }
}