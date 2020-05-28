import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
	private String id;
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
	
	public User(String id) {		
		ResultSet rs = receiveCustomerData(id);
		cart = new Cart(this);
		try {
			while(rs.next()) {
				this.id = rs.getString("user_id");
				this.name = rs.getString("name");
				this.surname = rs.getString("surname");
				this.email = rs.getString("email");
				this.address = rs.getString("address");
				this.city = rs.getString("city");
				this.postal = rs.getString("postal_code");
				this.phone = rs.getString("phone");
				this.birthday = rs.getString("date_of_birth");
				this.isPharmacist = rs.getInt("isPharmacist");
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
	
	public void updateCustomerData(String name, String surname, String email, String address, 
			String city, String postal, String phone, String birthday) {
		DB_Connection objDB = new DB_Connection();
		Connection connection = objDB.get_connection();
		PreparedStatement ps = null;
		
		try {
			ps = connection.prepareStatement("UPDATE pharmacy.customers SET name = ?, surname = ?, email = ?, address = ?, city = ?, postal_code = ?, phone = ?, date_of_birth = ? WHERE (user_id = ?);");
			ps.setString(1, name);
			ps.setString(2, surname);
			ps.setString(3, email);
			ps.setString(4, address);
			ps.setString(5, city);
			ps.setString(6, postal);
			ps.setString(7, phone);
			ps.setString(8, birthday);
			ps.setString(9, this.id);

			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		
	public String getId() {
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
	
	
	
}