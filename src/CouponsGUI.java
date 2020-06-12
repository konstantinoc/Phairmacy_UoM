import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class CouponsGUI extends JPanel {
	private ArrayList<coupon> coupons;
	/**
	 * Create the panel.
	 * Pharmacist can view and remove the coupons.
	 * Customers can purchase coupons.
	 */
	public CouponsGUI(MainFrame mainFrame, User user) {
		setLayout(null);
		
		//the location of the coupon
		int x = 80; 
		int y = 80;
		//fetches the coupons from database
		this.coupons = fetchCoupons();
		int counter = 0;
		
		//for each coupon
		for(coupon c:coupons) {
			if (counter >= 7)
				y = 330;
			
			if (counter == 7)
				x = 80;
			
			JPanel panel = new JPanel();
			panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
			panel.setBounds(x, y, 175, 219);
			add(panel);
			panel.setLayout(null);
			
			JLabel lblNewLabel = new JLabel(c.getDescription());
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
			lblNewLabel.setBounds(10, 30, 158, 50);
			panel.add(lblNewLabel);
			
			JLabel lblNewLabel_1 = new JLabel(c.getPoints() + " Points");
			lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1.setBounds(40, 99, 90, 19);
			panel.add(lblNewLabel_1);
			JButton btnNewButton = new JButton("Buy Coupon");
			JButton btnRemove = new JButton("Remove Coupon");
			/**
			 * if user is pharmacist can remove the coupon
			 * if user is customer can purchase the coupon
			 */
			if(user.getIsPharmacist() == 0) {
				btnNewButton.setFont(new Font("Dialog", Font.BOLD, 13));
				btnNewButton.setBounds(10, 175, 155, 34);
				panel.add(btnNewButton);
			}else {
				btnRemove.setFont(new Font("Dialog", Font.BOLD, 13));
				btnRemove.setBounds(10, 175, 155, 34);
				panel.add(btnRemove);
			}
			MouseListener ms = new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent e) {
					if(e.getSource().equals(btnNewButton)) {
						int confirm = JOptionPane.showConfirmDialog(mainFrame,"Are you sure you want buy this coupon?");  
						if(confirm == JOptionPane.YES_OPTION){ 
							/**
							 * if customer has enough points coupon, coupon can be purchased for use.
							 * the user's point decreases.
							 */
							if(user.getPoints() > c.getPoints()) {
								c.buyCoupon(user.getId());
								user.setPoints(user.getPoints()-c.getPoints());
								user.updateCustomerData();
								mainFrame.RefreshNavMenu();
								mainFrame.changePanel(new CouponsGUI(mainFrame, user));
								JOptionPane.showMessageDialog(mainFrame, "ATTENSION\nYour coupon code is " + c.getCode() + "\nMAKE SURE YOU WROTE DOWN THE CODE", "Your coupon code", JOptionPane.WARNING_MESSAGE);
							}
							else {
								JOptionPane.showMessageDialog(mainFrame, "You dont have enough points", "Error", JOptionPane.ERROR_MESSAGE);
							}
						}
					}
					else if(e.getSource().equals(btnRemove)) {
						int confirm = JOptionPane.showConfirmDialog(mainFrame,"Are you sure you want delete this coupon?");  
						if(confirm == JOptionPane.YES_OPTION){  
							//pharmacist remove the coupon from the database
							c.removeCoupon();
							JOptionPane.showMessageDialog(mainFrame, "Coupon removed succesfuly", "Succesful operation", JOptionPane.INFORMATION_MESSAGE);
							mainFrame.changePanel(new CouponsGUI(mainFrame, user));
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
			btnRemove.addMouseListener(ms);
			btnNewButton.addMouseListener(ms);
			x += 200;
			counter++;
		}

	}
	
	//fetches all coupons from the database
	public ArrayList<coupon> fetchCoupons() {
		ArrayList<coupon> coupons = new ArrayList<>();
		DB_Connection objDB = new DB_Connection();
		Connection connection = objDB.get_connection();
		PreparedStatement ps = null;
		
		try {
			//creates a new object coupon which is added to the arrayList
			String query = "SELECT id FROM coupons;";
			ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				coupons.add(new coupon(rs.getInt("id")));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return coupons;
	}
}
