import java.util.ArrayList;

public class Cart {
	private ArrayList<Product> productList; //keeps the products
	private ArrayList<Integer> qtyList; //keeps the quantity of each product
	private User user;
	
	// it keep the saved products for the customer to buy it.
	public Cart(User user) {
		this.productList = new ArrayList<>();
		this.qtyList = new ArrayList<>();
		this.user = user;
	}
	
	//adds the product and the quantity to the arraylists
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
	
	//returns the size of the cart
	public int cartSize() {
		return productList.size();
	}
	
	//changes the quantity of a specified product
	public void changeProductQty(Product p, int q) {
		qtyList.set(productList.indexOf(p), q);
	}
	//calculates the total cost of the cart
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
	
	// removes a product from the cart
	public void removeProduct(Product p) {
		qtyList.remove(productList.indexOf(p));
		productList.remove(p);
	}
	
	//clears the cart
	public void removeAll() {
		qtyList.clear();
		productList.clear();
	}
	
}
