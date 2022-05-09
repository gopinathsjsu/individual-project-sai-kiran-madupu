public class Utility {

    public static Integer getItemQuantity(String item) {
        for (Inventory inventory: Billing.shopInventory) {
            if (inventory.getItem().equalsIgnoreCase(item)) return inventory.getQuantity();
        }
        return 0;
    }

    public static Double getItemPrice(String item, Integer quantity) {
        for (Inventory inventory: Billing.shopInventory) {
            if (inventory.getItem().equalsIgnoreCase(item)) return inventory.getPrice() * quantity;
        }
        return 0.0;
    }
}