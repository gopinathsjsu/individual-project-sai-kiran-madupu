

public class Inventory {

    private String category;
    private String item;
    private Integer quantity;
    private Double price;

    public Inventory(String category, String item, Integer quantity, Double price) {
        this.category = category;
        this.item = item;
        this.quantity = quantity;
        this.price = price;
    }

    public String getItem() {
        return item;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

	@Override
	public String toString() {
		return "Inventory [category=" + category + ", item=" + item + ", quantity=" + quantity + ", price=" + price
				+ "]";
	}
    
}