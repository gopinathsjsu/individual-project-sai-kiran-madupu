import java.io.Console;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.aspose.cells.SaveFormat;
import com.aspose.cells.Workbook;


public class Billing {

    public static ArrayList < Inventory > shopInventory;

    public static void main(String[] args) {
    	String inputOrderPath = null; 
    	String cardPath = Config.CARD_PATH;
    	String inventoryPath = Config.INVENTORY_PATH;
    	boolean converted = false;
    	
    	if(args.length == 0) {
    		String fileExtension = "";
    		Console console = System.console();
    		inputOrderPath = Paths.get(console.readLine("Enter the absolute path for the input CSV file: ")).toString();
    		int i = inputOrderPath.lastIndexOf('.');
    		if (i > 0) {
    		    fileExtension = inputOrderPath.substring(i+1);
    		}
    		if(fileExtension.equals("xlsx")) {
    			//convert xlsx to csv
    			try {
    				inputOrderPath = Config.CONVERTED_INPUT_CSV;
    				Workbook book = new Workbook(inputOrderPath);
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
        
        if(success) {
        	System.out.println("Output has been written to " +Config.SUCCESS_FILE_NAME);
        	
        } else {
        	System.out.println("Output has been written to " +Config.ERROR_FILE_NAME);
        }
        
        
    }
}