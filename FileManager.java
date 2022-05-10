import java.io.BufferedReader;
import java.util.ArrayList;


public class FileManager {

    static Integer miscCount = 0;
    static Integer luxuryCount = 0;
    static Integer essentialsCount = 0;
    static final String[] categories = {"Essential", "Luxury", "Misc"};

    public static Order readInputCSV(String filePath, boolean converted) {
        ArrayList < Item > items = new ArrayList < > ();
        String row;
        String cardNumber = null;
        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(filePath))) {
            // Skip column headers
            reader.readLine();
            
            while ((row = reader.readLine()) != null) {
                String[] data = row.split(",");
                if(converted && row.startsWith("Evaluation")) {
                	continue;
                }
                // Card number might be present in only first row of the input order
                if(data.length == 3 && data[2] != null) {
                	cardNumber = data[2];
                }

                if ((data.length == 3 ||  data.length == 2 ) &&
                    validateItem(data[0], Integer.parseInt(data[1])) &&
                    validateQuantity(data[1]) &&
                    validateCardNumber(cardNumber)) {
                    items.add(new Item(data[0], Integer.parseInt(data[1]), cardNumber));
                    
                } else {
                    System.out.println("Invalid input CSV data");
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return new Order(items, miscCount, luxuryCount, essentialsCount, cardNumber);
    }

    public static ArrayList < Inventory > readInventoryCSV(String filePath) {
        ArrayList < Inventory > inventories = new ArrayList < > ();
        String row;
        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(filePath))) {
            reader.readLine();
            while ((row = reader.readLine()) != null) {
                String[] data = row.split(",");

                if (data.length == 4 && validateQuantity(data[2]) && validatePrice(data[3])) {
                    inventories.add(
                        new Inventory(
                            data[1], data[0], Integer.parseInt(data[2]), Double.parseDouble(data[3])));
                } else {
                    System.out.println("Invalid inventory dataset data");
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return inventories;
    }

    public static ArrayList < String > readCardCSV(String filePath) {
        ArrayList < String > cards = new ArrayList < > ();
        String row;
        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(filePath))) {
            reader.readLine();
            while ((row = reader.readLine()) != null) {
                cards.add(row);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return cards;
    }

    private static boolean validateCardNumber(String cardNumber) {
        String card = cardNumber.replaceAll(" ", "");
        return true;
    }

    private static boolean validateItem(String itemName, Integer quantity) {
        for (Inventory inventory: Billing.shopInventory) {
            if (inventory.getItem().equalsIgnoreCase(itemName)) {
                validateCategory(inventory.getCategory(), quantity);
                return true;
            }
        }
        return false;
    }

    private static boolean validateQuantity(String quantity) {
        return quantity.matches("-?\\d+");
    }

    private static boolean validatePrice(String quantity) {
        return quantity.matches("[-+]?[0-9]*\\.?[0-9]+");
    }

    private static boolean validateCategory(String category, int quantity) {
        if (category.equalsIgnoreCase("Misc")) miscCount += quantity;
        if (category.equalsIgnoreCase("Essentials")) essentialsCount += quantity;
        else if (category.equalsIgnoreCase("Luxury")) luxuryCount += quantity;
        
        return category.matches("Misc|Essential|Luxury");
    }
}