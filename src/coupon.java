import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class coupon {
	private int id;
	private String code;
	private int product_id;
	private float discount;
	private int minimum_qty;
	private String description;
	private int points; 
	
	public coupon(String code, int product_id, float discount, int minimum_qty, String description, int points) {
		this.code = code;
		this.product_id = product_id;
		this.discount = discount;
		this.minimum_qty = minimum_qty;
		this.description = description;
		this.points = points;
	}	
	
	public coupon(int id) {
		DB_Connection objDB = new DB_Connection();
		Connection connection = objDB.get_connection();
		PreparedStatement ps = null;
		
		try {
			String query = "SELECT * FROM coupons WHERE id = " + id + ";";
			ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			if (rs.next()){
				this.id = id;
				this.code = rs.getString("c_code");
				this.product_id = rs.getInt("product_id");
				this.discount = rs.getFloat("discount");
				this.minimum_qty = rs.getInt("minimum_qty");
				this.description = rs.getString("description");
				this.points = rs.getInt("points");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public coupon(String code) {
		DB_Connection objDB = new DB_Connection();
		Connection connection = objDB.get_connection();
		PreparedStatement ps = null;
		
		try {
			String query = "SELECT * FROM coupons WHERE c_code = \"" + code + "\";";
			ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			if (rs.next()){
				this.id = rs.getInt("id");
				this.code = code;
				this.product_id = rs.getInt("product_id");
				this.discount = rs.getFloat("discount");
				this.minimum_qty = rs.getInt("minimum_qty");
				this.description = rs.getString("description");
				this.points = rs.getInt("points");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void removeCoupon() {
		DB_Connection objDB = new DB_Connection();
		Connection connection = objDB.get_connection();
		PreparedStatement ps = null;
		
		String query = "DELETE FROM coupons WHERE id = ?;";
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, this.id);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void useCoupon(int customer_id) {
		DB_Connection objDB = new DB_Connection();
		Connection connection = objDB.get_connection();
		PreparedStatement ps = null;
		
		String query = "DELETE FROM customer_coupon WHERE customer_id = ? AND coupon_id = ?;";
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, customer_id);
			ps.setInt(2, this.id);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public int addCoupon() {
		DB_Connection objDB = new DB_Connection();
		Connection connection = objDB.get_connection();
		PreparedStatement ps = null;
		
		try {
			String query = "INSERT INTO coupons (c_code, product_id, discount, minimum_qty, description, points) VALUES (?, ?, ?, ?, ?, ?);";
			ps = connection.prepareStatement(query);
			ps.setString(1, this.code);
			ps.setInt(2, this.product_id);
			ps.setFloat(3, this.discount);
			ps.setInt(4, this.minimum_qty);
			ps.setString(5, this.description);
			ps.setInt(6, this.points);
			
			ps.executeUpdate();
			return 1;
		}catch(Exception e) {
			e.printStackTrace();
			if (e.toString().contains("Dublicate")){
				return -1;
			}
		}
		return 0;
	}
	
	public void buyCoupon(int user_id) {
		DB_Connection objDB = new DB_Connection();
		Connection connection = objDB.get_connection();
		PreparedStatement ps = null;
		
		try {
			String query = "INSERT INTO customer_coupon (customer_id, coupon_id) VALUES (?, ?);";
			ps = connection.prepareStatement(query);
			ps.setInt(1, user_id);
			ps.setInt(2, this.id);

			ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public int getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public int getProduct_id() {
		return product_id;
	}

	public float getDiscount() {
		return discount;
	}

	public int getMinimum_qty() {
		return minimum_qty;
	}

	public String getDescription() {
		return description;
	}

	public int getPoints() {
		return points;
	}
}
