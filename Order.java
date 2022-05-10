
import java.util.ArrayList;

public class Order {

    private ArrayList<Item> orderItems;
    private Integer miscCount;
    private Integer luxuryCount;
    private Integer essentialCount;
    private String cardNumber;
    
	public Order(ArrayList<Item> orderItems, Integer miscCount, Integer luxuryCount, Integer essentialCount,
			String cardNumber) {
		super();
		this.orderItems = orderItems;
		this.miscCount = miscCount;
		this.luxuryCount = luxuryCount;
		this.essentialCount = essentialCount;
		this.cardNumber = cardNumber;
	}
	
	public ArrayList<Item> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(ArrayList<Item> orderItems) {
		this.orderItems = orderItems;
	}
	public Integer getMiscCount() {
		return miscCount;
	}
	public void setMiscCount(Integer miscCount) {
		this.miscCount = miscCount;
	}
	public Integer getLuxuryCount() {
		return luxuryCount;
	}
	public void setLuxuryCount(Integer luxuryCount) {
		this.luxuryCount = luxuryCount;
	}
	public Integer getEssentialCount() {
		return essentialCount;
	}
	public void setEssentialCount(Integer essentialCount) {
		this.essentialCount = essentialCount;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	@Override
	public String toString() {
		return "Order [orderItems=" + orderItems + ", miscCount=" + miscCount + ", luxuryCount=" + luxuryCount
				+ ", essentialCount=" + essentialCount + ", cardNumber=" + cardNumber + "]";
	}
	
}