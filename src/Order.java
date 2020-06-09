import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Order {
	private int id;
	private int customer_id;
	private int product_id;
	private int qty;
	private String date;
	private float cost;
	private int delivery;
	private int payment_method;
	private int confirm;
	
	public Order(int id,int customer_id, int product_id, int qty, String date, float cost, int delivery,
			int payment_method, int confirm) {
		this.id = id;
		this.customer_id = customer_id;
		this.product_id = product_id;
		this.qty = qty;
		this.date = date;
		this.cost = cost;
		this.delivery = delivery;
		this.payment_method = payment_method;
		this.confirm = confirm;
	}
	
	public Order(int customer_id, int product_id, int qty, String date, float cost, int delivery,
			int payment_method) {
		this.id = createId();
		this.customer_id = customer_id;
		this.product_id = product_id;
		this.qty = qty;
		this.date = date;
		this.cost = cost;
		this.delivery = delivery;
		this.payment_method = payment_method;
		this.confirm = confirm;
	}
	
	public void addOrder() {
		DB_Connection objDB = new DB_Connection();
		Connection connection = objDB.get_connection();
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement("INSERT INTO pr_order (id, customer_id, product_id, qty, date, cost, delivery, payment_method) VALUES (?, ?, ?, ?, ?, ?, ?, ?);");
			ps.setInt(1, this.id);
			ps.setInt(2, this.customer_id);
			ps.setInt(3, this.product_id);
			ps.setInt(4, this.qty);
			ps.setString(5, this.date);
			ps.setFloat(6, this.cost);
			ps.setInt(7, this.delivery);
			ps.setInt(8, this.payment_method);
			
			ps.executeUpdate();
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	private int createId() {
		int id = 0;
		DB_Connection objDB = new DB_Connection();
		Connection connection = objDB.get_connection();
		PreparedStatement ps = null;
		try {
			String query = "SELECT * FROM pr_order";
			ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			if (rs.next()){
				id = rs.getInt("id") + 1;
			}
			else
				id = 1;
		}catch(Exception e) {
			System.out.println(e);
		}
		return id;
	}

	public int getId() {
		return id;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public int getProduct_id() {
		return product_id;
	}

	public int getQty() {
		return qty;
	}

	public String getDate() {
		return date;
	}

	public float getCost() {
		return cost;
	}

	public int getDelivery() {
		return delivery;
	}

	public int getPayment_method() {
		return payment_method;
	}

	public int getConfirm() {
		return confirm;
	}
	
	
}
