import java.io.Console;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


public class Billing {

    public static ArrayList < Inventory > shopInventory;

    public static void main(String[] args) {
    	String inputOrderPath = null; 
    	String cardPath = Config.CARD_PATH;
    	String inventoryPath = Config.INVENTORY_PATH;
    	
    	if(args.length == 0) {
    		String fileExtension = "";
    		Console console = System.console();
    		inputOrderPath = Paths.get(console.readLine("Enter the absolute path for the input CSV file: ")).toString();
    		int i = inputOrderPath.lastIndexOf('.');
    		if (i > 0) {
    		    fileExtension = inputOrderPath.substring(i+1);
    		}
    	} else {
    		// inventoryPath = Paths.get(args[0]);
            inputOrderPath = Paths.get(args[1]).toString();
    	}
        
        shopInventory = FileUtility.readInventoryCSV(inventoryPath);
        Order order = FileUtility.readInputCSV(inputOrderPath.toString());
        
        boolean success = CartManager.validateAndPlaceOrder(order, cardPath);
        
        if(success) {
        	System.out.println("Output has been written to " +Config.SUCCESS_FILE_NAME);
        	
        } else {
        	System.out.println("Output has been written to " +Config.ERROR_FILE_NAME);
        }
        
        
    }
}