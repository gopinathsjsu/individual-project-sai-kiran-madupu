

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
        String successMessage = "Item,Quantity,Price,TotalPrice\n";
        StringBuilder sb = new StringBuilder();
        sb.append(successMessage);
        HashSet < String > cards = new HashSet < > ();
        boolean updated = false;
        if (order.getEssentialCount() <= Config.ESSENTIALS_ITEM_LIMIT &&
            order.getLuxuryCount() <= Config.LUXURY_ITEM_LIMIT &&
            order.getMiscCount() <= Config.MISC_ITEM_LIMIT) {
            for (Item item: order.getOrderItems()) {
                if (item.getQuantity() > Utility.getItemQuantity(item.getItem())) {
                    errMessage = item.getItem() + " " + item.getQuantity() + "\n";
                } else {
                	String it = item.getItem();
                	int q = item.getQuantity();
                	double ip = Utility.getItemPrice(it, q);
                	totalAmount += Utility.getItemPrice(it, q);
                	if(!updated) {
                		sb.append(it);
                		sb.append(",");
                		sb.append(q);
                		sb.append(",");
                		sb.append(ip);
                		sb.append(",");
                		sb.append("#");
                		sb.append("\n");
                		updated = true;
                	} else {
                		sb.append(it);
                		sb.append(",");
                		sb.append(q);
                		sb.append(",");
                		sb.append(ip);
                		sb.append("\n");
                	}
                }
                cards.add(item.getCardNumber());
            }
            successMessage = sb.toString().replace("#", Double.toString(totalAmount));
            // System.out.println(successMessage);
            if (errMessage.isEmpty()) {
                writeCardFile(cards, cardPath);
                writeOutputFile(
                		Config.SUCCESS_FILE_NAME,
                		successMessage.getBytes(StandardCharsets.UTF_8));
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
        ArrayList < String > savedCards = FileManager.readCardCSV(cardPath);
        for (String card: savedCards) cards.add(card);
        writeOutputFile(cardPath, String.join("\n", cards).getBytes(StandardCharsets.UTF_8));
    }
}