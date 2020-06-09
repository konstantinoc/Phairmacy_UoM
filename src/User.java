import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
	private int id;
	private String name;
	private String surname;
	private String email;
	private String address;
	private String city;
	private String postal;
	private String phone;
	private String birthday;
	private int isPharmacist;
	private Cart cart;
	private int points;
	private int sub;
	
	public User(String id) {		
		ResultSet rs = receiveCustomerData(id);
		cart = new Cart(this);
		try {
			while(rs.next()) {
				this.id = rs.getInt("user_id");
				this.name = rs.getString("name");
				this.surname = rs.getString("surname");
				this.email = rs.getString("email");
				this.address = rs.getString("address");
				this.city = rs.getString("city");
				this.postal = rs.getString("postal_code");
				this.phone = rs.getString("phone");
				this.birthday = rs.getString("date_of_birth");
				this.points = rs.getInt("points");
				this.isPharmacist = rs.getInt("isPharmacist");
				this.sub = rs.getInt("subscription");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private ResultSet receiveCustomerData(String id) {
		DB_Connection objDB = new DB_Connection();
		Connection connection = objDB.get_connection();
		PreparedStatement ps = null;
		try {
			String query = "SELECT * FROM customers WHERE user_id = " + id + ";";
			ps = connection.prepareStatement(query);
			return ps.executeQuery();
		}
		catch(Exception e){
			System.out.println(e);
		}
		return null;
	}
	
	public void updateCustomerData() {
		DB_Connection objDB = new DB_Connection();
		Connection connection = objDB.get_connection();
		PreparedStatement ps = null;
		
		try {
			ps = connection.prepareStatement("UPDATE pharmacy.customers SET name = ?, surname = ?, email = ?, address = ?, city = ?, postal_code = ?, phone = ?, date_of_birth = ?, points = ?, subscription = ? WHERE (user_id = ?);");
			ps.setString(1, this.name);
			ps.setString(2, this.surname);
			ps.setString(3, this.email);
			ps.setString(4, this.address);
			ps.setString(5, this.city);
			ps.setString(6, this.postal);
			ps.setString(7, this.phone);
			ps.setString(8, this.birthday);
			ps.setInt(9, this.points);
			ps.setInt(10, this.sub);
			ps.setInt(11, this.id);

			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getEmail() {
		return email;
	}

	public String getAddress() {
		return address;
	}

	public String getCity() {
		return city;
	}

	public String getPostal() {
		return postal;
	}

	public String getPhone() {
		return phone;
	}

	public String getBirthday() {
		return birthday;
	}

	public int getIsPharmacist() {
		return isPharmacist;
	}

	public Cart getCart() {
		return cart;
	}

	public int getPoints() {
		return points;
	}

	public int getSub() {
		return sub;
	}

	public void setSub(int sub) {
		this.sub = sub;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setPostal(String postal) {
		this.postal = postal;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public void setPoints(int points) {
		this.points = points;
	}
	
	
	
	
	
}