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
	private String ingredient;
		
	public Product(int id) {
		DB_Connection objDB = new DB_Connection();
		Connection connection = objDB.get_connection();
		PreparedStatement ps = null;
		try {
			String query = "SELECT * FROM products WHERE id = " + id;
			ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			if (rs.next()){
				this.id = id;
				this.price = rs.getFloat("price");
				this.name = rs.getString("name");
				this.qty = rs.getInt("qty");
				this.img = rs.getString("img");
				this.description = rs.getString("description");
				this.ingredient = rs.getString("ingredient");
			}
		}catch(Exception e) {
			System.out.println(e);
		}
	}

	public Product(int id, float price, String name, int qty, String img, String description, String ingredient) {
		this.id = id;
		this.price = price + 0.00F;
		this.name = name;
		this.qty = qty;
		this.img = img;
		this.description = description;
		this.ingredient = ingredient;
	}

	public void editProduct(float price, String name, int qty, String description, String ingredient) {
		DB_Connection objDB = new DB_Connection();
		Connection connection = objDB.get_connection();
		PreparedStatement ps = null;
		
		try {
			ps = connection.prepareStatement("UPDATE pharmacy.products SET price = ?, name = ?, qty = ?, description= ?, ingredient = ? WHERE (id = ?);");
			ps.setFloat(1, price);
			ps.setString(2, name);
			ps.setInt(3, qty);
			ps.setString(4, description);
			ps.setString(5, ingredient);
			ps.setInt(6, this.id);
			
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
			ps = connection.prepareStatement("INSERT INTO `pharmacy`.`products` (`id`,`price`, `name`, `qty`, `description`, `ingredient`) VALUES (?, ?, ?, ?, ?, ?);");
			ps.setInt(1, this.id);
			ps.setFloat(2, this.price);
			ps.setString(3, this.name);
			ps.setInt(4, this.qty);
			ps.setString(5, this.description);
			ps.setString(6, this.ingredient);
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
	
	public int lastYearSales(int month, int year) {
		DB_Connection objDB = new DB_Connection();
		Connection connection = objDB.get_connection();
		PreparedStatement ps = null;
		int total = 0;
		try {
			String query = "SELECT * FROM pr_order WHERE product_id = " + this.id + " AND order_date LIKE \"%/%" + month + "/" + year + "\";";
			ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				total += rs.getInt("qty");
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		return total;
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

	public String getIngredient() {
		return ingredient;
	}

	public void setIngredient(String ingredient) {
		this.ingredient = ingredient;
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
