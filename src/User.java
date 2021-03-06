import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
	private ArrayList<String> allergies;
	
	//finds the user with this id in database and creates an object user with his data.
	public User(int id) {		
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
				this.allergies = getUserAllergies(id); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//gets the data of a user from the database.
	private ResultSet receiveCustomerData(int id) {
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
	
	//updates user data in the database
	public void updateCustomerData() {
		DB_Connection objDB = new DB_Connection();
		Connection connection = objDB.get_connection();
		PreparedStatement ps = null;
		
		try {
			ps = connection.prepareStatement("UPDATE customers SET name = ?, surname = ?, email = ?, address = ?, city = ?, postal_code = ?, phone = ?, date_of_birth = ?, points = ?, subscription = ? WHERE (user_id = ?);");
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
	
	//update customer allergies.
	public void updateCustomerAllergies() {
		DB_Connection objDB = new DB_Connection();
		Connection connection = objDB.get_connection();
		PreparedStatement ps = null;
		
		try {
			for(String a:allergies) {
				//checks if the customer already has added this allergy.
				ps = connection.prepareStatement("SELECT * FROM user_allergie WHERE customer_id = ? AND allergie_name = ?;");
				ps.setInt(1, this.id);
				ps.setString(2, a);
				ResultSet rs = ps.executeQuery();
				//if no its inserts the allergy to the user's data.
				if(!rs.next()) {
					ps = connection.prepareStatement("INSERT INTO user_allergie (customer_id, allergie_name) VALUES(?, ?);");
					ps.setInt(1, this.id);
					ps.setString(2, a);
					ps.executeUpdate();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//gets the allergies from a user in database using his id.
	public ArrayList<String> getUserAllergies(int id){
		ArrayList<String> allergies = new ArrayList<>();
		DB_Connection objDB = new DB_Connection();
		Connection connection = objDB.get_connection();
		PreparedStatement ps = null;
		
		try {
			String query = "SELECT * FROM user_allergie WHERE customer_id = " + this.id +";";
			ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				allergies.add(rs.getString("allergie_name"));
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		return allergies;
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

	public ArrayList<String> getAllergies() {
		return allergies;
	}

	public void setAllergies(ArrayList<String> allergies) {
		this.allergies = allergies;
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