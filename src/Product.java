import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Product {
	private int id;
	private float price;
	private String name;
	private int qty;
	private String img;
	private String description;
	//private Ingredient ingredient;
	
	public Product(int id, float price, String name, int qty, String img, String description) {
		this.id = id;
		this.price = price + 0.00F;
		this.name = name;
		this.qty = qty;
		this.img = img;
		this.description = description;
		//this.ingredient = ingredient;
	}

	public Product(float price, String name, int qty, String img, String description) {
		this.price = price + 0.00F;
		this.name = name;
		this.qty = qty;
		this.img = img;
		this.description = description;
		//this.ingredient = ingredient;
	}
	
//	public boolean enoughQty(int id, int qty) {
//		Product p = null;
//		DB_Connection objDB = new DB_Connection();
//		Connection connection = objDB.get_connection();
//		PreparedStatement ps = null;
//		try {
//			String query = "SELECT qty FROM products WHERE id = " + id + ";";
//			ps = connection.prepareStatement(query);
//			ResultSet rs = ps.executeQuery();
//			
//			if(rs.next()) {
//				
//			}
//		}catch(Exception e) {
//			System.out.println(e);
//		}
//		return true;
//	}

	public void editProduct(float price, String name, int qty, String description) {
		DB_Connection objDB = new DB_Connection();
		Connection connection = objDB.get_connection();
		PreparedStatement ps = null;
		
		try {
			ps = connection.prepareStatement("UPDATE pharmacy.products SET price = ?, name = ?, qty = ?, description= ? WHERE (id = ?);");
			ps.setFloat(1, price);
			ps.setString(2, name);
			ps.setInt(3, qty);
			ps.setString(4, description);
			ps.setInt(5, this.id);
			
			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void editQty() {
		DB_Connection objDB = new DB_Connection();
		Connection connection = objDB.get_connection();
		PreparedStatement ps = null;
		
		try {
			ps = connection.prepareStatement("UPDATE products SET qty = ? WHERE (id = ?);");
			ps.setInt(1, qty);
			ps.setInt(2, this.id);
			
			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addProduct() {
		DB_Connection objDB = new DB_Connection();
		Connection connection = objDB.get_connection();
		PreparedStatement ps = null;
		
		try {
			ps = connection.prepareStatement("INSERT INTO `pharmacy`.`products` (`price`, `name`, `qty`, `description`) VALUES (?, ?, ?, ?);");
			ps.setFloat(1, this.price);
			ps.setString(2, this.name);
			ps.setInt(3, this.qty);
			ps.setString(4, this.description);
			
			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void removeProduct() {
		DB_Connection objDB = new DB_Connection();
		Connection connection = objDB.get_connection();
		PreparedStatement ps = null;
		
		try {
			ps = connection.prepareStatement("DELETE FROM `pharmacy`.`products` WHERE (`id` = ?);");
			ps.setInt(1, this.id);
			
			ps.executeUpdate();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public int getId() {
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

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public boolean equals(Object ob) {
		if(this.id == ((Product)ob).getId())
			return true;
		return false;
	}
}
