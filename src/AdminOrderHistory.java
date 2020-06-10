import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class AdminOrderHistory extends JPanel {
	private MainFrame mainFrame;
	private User user;
	private ArrayList<Order> orders;
	/**
	 * Create the panel.
	 */
	public AdminOrderHistory(MainFrame mainFrame, User user) {
		this.user = user;
		this.mainFrame = mainFrame;
		
		setLayout(null);
		JLabel lblNewLabel = new JLabel("Date");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel.setBounds(609, 44, 62, 18);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Product");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_1.setBounds(768, 44, 82, 18);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Qty");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_2.setBounds(963, 44, 52, 18);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Status");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_3.setBounds(1177, 47, 82, 13);
		add(lblNewLabel_3);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblName.setBounds(155, 44, 62, 18);
		add(lblName);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblAddress.setBounds(367, 44, 99, 18);
		add(lblAddress);
		
		JLabel lblNewLabel_2_1 = new JLabel("Delivery");
		lblNewLabel_2_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_2_1.setBounds(1060, 44, 72, 18);
		add(lblNewLabel_2_1);
		
		fetchOrders();
		
		int y = 85;
		for(Order o:orders) {	
			User customer = new User(o.getCustomer_id());
			
			JLabel lblNewLabel_5 = new JLabel(customer.getName() + " " + customer.getSurname());
			lblNewLabel_5.setBounds(95, y, 161, 18);
			lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
			add(lblNewLabel_5);
			
			JLabel lblNewLabel_6 = new JLabel(customer.getAddress() + " " + customer.getCity() + " " + customer.getPostal());
			lblNewLabel_6.setBounds(270, y, 280, 18);
			lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
			add(lblNewLabel_6);
			
			JLabel lblNewLabel_4 = new JLabel(o.getDate());
			lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblNewLabel_4.setBounds(575, y, 105, 15);
			lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
			add(lblNewLabel_4);
			
			JLabel lblNewLabel_4_1 = new JLabel(getProductName(o.getProduct_id()));
			lblNewLabel_4_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblNewLabel_4_1.setBounds(750, y, 105, 15);
			lblNewLabel_4_1.setHorizontalAlignment(SwingConstants.CENTER);
			add(lblNewLabel_4_1);
			
			JLabel lblNewLabel_4_2 = new JLabel(String.valueOf(o.getQty()));
			lblNewLabel_4_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblNewLabel_4_2.setBounds(925, y, 105, 15);
			lblNewLabel_4_2.setHorizontalAlignment(SwingConstants.CENTER);
			add(lblNewLabel_4_2);
			
			if(o.getDelivery() == 1) {
				JLabel lblNewLabel_7 = new JLabel("");
				lblNewLabel_7.setIcon(new ImageIcon(AdminOrderHistory.class.getResource("/icons/check.png")));
				lblNewLabel_7.setBounds(1086, y-5, 24, 30);
				add(lblNewLabel_7);
			}
			
			String status = "Pending";
			if (o.getConfirm() == -1)
				status = "Declined";
			else if(o.getConfirm() == 1)
				status = "Accepted";
			
			JLabel lblNewLabel_4_3 = new JLabel(status);
			lblNewLabel_4_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblNewLabel_4_3.setBounds(1150, y, 105, 15);
			lblNewLabel_4_3.setHorizontalAlignment(SwingConstants.CENTER);
			add(lblNewLabel_4_3);
			
			JLabel lblAccept = new JLabel("");
			lblAccept.setIcon(new ImageIcon(AdminOrderHistory.class.getResource("/icons/confirm.png")));
			lblAccept.setBounds(1268, y-5, 28, 30);
			add(lblAccept);
			
			JLabel lblDecline = new JLabel("");
			lblDecline.setIcon(new ImageIcon(AdminOrderHistory.class.getResource("/icons/decline.png")));
			lblDecline.setBounds(1313, y-5, 28, 30);
			add(lblDecline);
			
			y += 25;
			
			MouseListener ms = new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getSource().equals(lblAccept)) {
						int confirm = JOptionPane.showConfirmDialog(mainFrame,"Are you sure you want to ACCEPT this order?");  
						if(confirm == JOptionPane.YES_OPTION){ 
							o.setConfirm(1);
							o.updateOrder();
							mainFrame.changePanel(new AdminOrderHistory(mainFrame, user));
						}
					}
					else if(e.getSource().equals(lblDecline)) {
						int confirm = JOptionPane.showConfirmDialog(mainFrame,"Are you sure you want to DECLINE this order?");  
						if(confirm == JOptionPane.YES_OPTION){ 
							o.setConfirm(-1);
							o.updateOrder();
							
							mainFrame.changePanel(new AdminOrderHistory(mainFrame, user));
						}
					}
				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					setCursor(new Cursor(Cursor.HAND_CURSOR));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}
				
			};
			lblAccept.addMouseListener(ms);
			lblDecline.addMouseListener(ms);
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
		
		String query = "SELECT * FROM pr_order ORDER BY id DESC";
		try {
			ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			orders = new ArrayList<>();
			Order order = null;
			while (rs.next()){
				order = new Order(rs.getInt("id"), rs.getInt("customer_id"), rs.getInt("product_id"), rs.getInt("qty"), rs.getString("order_date"), rs.getFloat("cost"), rs.getInt("delivery"), rs.getInt("payment_method"), rs.getInt("confirm"), rs.getInt("auto_id"));
				orders.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
