import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Product {
	private String id;
	private float price;
	private String name;
	private int qty;
	private String img;
	private String description;
	//private Ingredient ingredient;
	
	public Product(String id, float price, String name, int qty, String img, String description) {
		this.id = id;
		this.price = price + 0.00F;
		this.name = name;
		this.qty = qty;
		this.img = img;
		this.description = description;
		//this.ingredient = ingredient;
	}

	public void editProduct(float price, String name, int qty) {
		DB_Connection objDB = new DB_Connection();
		Connection connection = objDB.get_connection();
		PreparedStatement ps = null;
		
		try {
			ps = connection.prepareStatement("UPDATE pharmacy.products SET price = ?, name = ?, qty = ?, description= ? WHERE (id = ?);");
			ps.setFloat(1, price);
			ps.setString(2, name);
			ps.setInt(3, qty);
			ps.setString(4, this.description);
			ps.setString(5, this.id);
			
			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getId() {
		return id;
	}

	public float getPrice() {
		return price;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}
	
	public String getName() {
		return name;
	}

	public int getQty() {
		return qty;
	}

	public String getImg() {
		return img;
	}
	
	public String getDescription() {
		return description;
	}

	public boolean equals(Object ob) {
		if(this.id.equals(((Product)ob).getId()))
			return true;
		return false;
	}
}
