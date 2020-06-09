import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class OrderHistoryGUI extends JPanel {
	private MainFrame mainFrame;
	private User user;
	private ArrayList<Order> orders;
	/**
	 * Create the panel.
	 */
	public OrderHistoryGUI(MainFrame mainFrame, User user) {
		this.user = user;
		this.mainFrame = mainFrame;
		
		setLayout(null);
		JLabel lblNewLabel = new JLabel("Date");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel.setBounds(430, 41, 62, 18);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Product");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_1.setBounds(605, 41, 82, 18);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Qty");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_2.setBounds(831, 41, 52, 18);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Status");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_3.setBounds(991, 44, 82, 13);
		add(lblNewLabel_3);
		
		fetchOrders();
		
		int y = 85;
		for(Order o:orders) {			
			JLabel lblNewLabel_4 = new JLabel(o.getDate());
			lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblNewLabel_4.setBounds(400, y, 105, 15);
			lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
			add(lblNewLabel_4);
			
			JLabel lblNewLabel_4_1 = new JLabel(getProductName(o.getProduct_id()));
			lblNewLabel_4_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblNewLabel_4_1.setBounds(588, y, 105, 15);
			lblNewLabel_4_1.setHorizontalAlignment(SwingConstants.CENTER);
			add(lblNewLabel_4_1);
			
			JLabel lblNewLabel_4_2 = new JLabel(String.valueOf(o.getQty()));
			lblNewLabel_4_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblNewLabel_4_2.setBounds(801, y, 105, 15);
			lblNewLabel_4_2.setHorizontalAlignment(SwingConstants.CENTER);
			add(lblNewLabel_4_2);
			
			String status = "Pending";
			if (o.getConfirm() == -1)
				status = "Declined";
			else if(o.getConfirm() == 1)
				status = "Accepted";
			JLabel lblNewLabel_4_3 = new JLabel(status);
			lblNewLabel_4_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblNewLabel_4_3.setBounds(961, y, 105, 15);
			lblNewLabel_4_3.setHorizontalAlignment(SwingConstants.CENTER);
			add(lblNewLabel_4_3);
			y += 20;
		}
	}
	
	public String getProductName(int id) {
		DB_Connection objDB = new DB_Connection();
		Connection connection = objDB.get_connection();
		PreparedStatement ps = null;
		
		String query = "SELECT name FROM products WHERE id = " + id;
		try {
			ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			if (rs.next()){
				return rs.getString("name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(id);
		return "Product is not found";
	}
	public void fetchOrders() {
		DB_Connection objDB = new DB_Connection();
		Connection connection = objDB.get_connection();
		PreparedStatement ps = null;
		
		String query = "SELECT * FROM pr_order WHERE customer_id = " + user.getId();
		try {
			ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			orders = new ArrayList<>();
			Order order = null;
			while (rs.next()){
				order = new Order(rs.getInt("id"), rs.getInt("customer_id"), rs.getInt("product_id"), rs.getInt("qty"), rs.getString("date"), rs.getFloat("cost"), rs.getInt("delivery"), rs.getInt("payment_method"), rs.getInt("confirm"));
				orders.add(order);
			}
			Collections.reverse(orders);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
