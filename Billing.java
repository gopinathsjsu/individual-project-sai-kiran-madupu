import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import com.aspose.cells.SaveFormat;
import com.aspose.cells.Workbook;

public class Billing {

    public static ArrayList < Inventory > shopInventory;

    public static void main(String[] args) {
        String inputOrderPath = null;
        String cardPath = Config.CARD_PATH;
        String inventoryPath = Config.INVENTORY_PATH;
        try (Scanner sc = new Scanner(System.in)) {
            boolean converted = false;

            if (args.length == 0) {
                String fileExtension = "";
                System.out.print("Enter the absolute path (or) file name for the input CSV file: ");
                inputOrderPath = sc.nextLine();
                int i = inputOrderPath.lastIndexOf('.');
                if (i > 0) {
                    fileExtension = inputOrderPath.substring(i + 1);
                }
                if (fileExtension.equals("xlsx")) {
                    //convert xlsx to csv
                    try {
                        Workbook book = new Workbook(inputOrderPath);
                        inputOrderPath = Config.CONVERTED_INPUT_CSV;
                        book.save(inputOrderPath, SaveFormat.AUTO);
                        converted = true;

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else {
                // inventoryPath = Paths.get(args[0]);
                inputOrderPath = Paths.get(args[1]).toString();

            }

            shopInventory = FileManager.readInventoryCSV(inventoryPath);
            Order order = FileManager.readInputCSV(inputOrderPath, converted);

            boolean success = CartManager.validateAndPlaceOrder(order, cardPath);

            String fName = success ? Config.SUCCESS_FILE_NAME : Config.ERROR_FILE_NAME;
            System.out.println("Output has been written to " + fName);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Exiting application...");
            System.exit(0);
        }

    }
}