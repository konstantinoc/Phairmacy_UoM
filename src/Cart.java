import java.util.ArrayList;

public class Cart {
	private ArrayList<Product> productList;
	private ArrayList<Integer> qtyList;
	private User user;
	
	public Cart(User user) {
		this.productList = new ArrayList<>();
		this.qtyList = new ArrayList<>();
		this.user = user;
	}
	
	public void addProduct(Product p, int q) {
		int pos = productList.indexOf(p);
		if (pos == -1) {
			productList.add(p);
			qtyList.add(q);
		}
		else {
			qtyList.set(pos, qtyList.get(pos)+q);
		}
	}
	
	public int cartSize() {
		return productList.size();
	}
	
	public void changeProductQty(Product p, int q) {
		qtyList.set(productList.indexOf(p), q);
	}
	
	public float calculateTotal() {
		float total = 0;
		for(int i = 0; i < productList.size(); i++) {
			total += productList.get(i).getPrice() * qtyList.get(i);
		}
		return total;
	}

	public ArrayList<Product> getProductList() {
		return productList;
	}

	public ArrayList<Integer> getQtyList() {
		return qtyList;
	}

	public User getUser() {
		return user;
	}

	public void removeProduct(Product p) {
		qtyList.remove(productList.indexOf(p));
		productList.remove(p);
	}
	
	public PurchaseWithMemberCard continueOrder() {
		return new PurchaseWithMemberCard(user);
		
	}
}
