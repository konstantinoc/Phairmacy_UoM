import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class SubscriptionGUI extends JPanel {
	private User user;
	private MainFrame mainFrame;
	/**
	 * Create the panel subscription.
	 * customer can by a subscription for free delivery.
	 */
	public SubscriptionGUI(MainFrame mainFrame,User user) {
		this.user = user;
		this.mainFrame = mainFrame;
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Subscription days left: " + user.getSub());
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNewLabel.setBounds(36, 46, 254, 25);
		add(lblNewLabel);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_3.setBounds(185, 98, 1195, 457);
		add(panel_3);
		panel_3.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(82, 52, 271, 353);
		panel_3.add(panel);
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.setLayout(null);
		
		JButton btnOne = new JButton("Buy Subcription");
		btnOne.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnOne.setBounds(10, 296, 251, 34);
		panel.add(btnOne);
		
		JLabel lblNewLabel_1 = new JLabel("1 MONTH SUBSCRIPTION");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_1.setBounds(10, 89, 251, 34);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Cost: 150 points");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_2.setBounds(77, 133, 113, 40);
		panel.add(lblNewLabel_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(479, 52, 271, 353);
		panel_3.add(panel_1);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_1.setLayout(null);
		
		JButton btnSix = new JButton("Buy Subcription");
		btnSix.setFont(new Font("Times New Roman", Font.BOLD, 17));
		btnSix.setBounds(10, 294, 251, 34);
		panel_1.add(btnSix);
		
		JLabel lblNewLabel_1_1 = new JLabel("6 MONTH SUBSCRIPTION");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(10, 91, 251, 34);
		panel_1.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("Cost: 850 points");
		lblNewLabel_2_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_2_1.setBounds(80, 135, 113, 40);
		panel_1.add(lblNewLabel_2_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(833, 52, 271, 353);
		panel_3.add(panel_2);
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_2.setLayout(null);
		
		JButton btnYear = new JButton("Buy Subcription");
		btnYear.setFont(new Font("Times New Roman", Font.BOLD, 17));
		btnYear.setBounds(10, 295, 251, 34);
		panel_2.add(btnYear);
		
		JLabel lblNewLabel_1_2 = new JLabel("1 YEAR SUBSCRIPTION");
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_1_2.setBounds(20, 91, 226, 34);
		panel_2.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_2_2 = new JLabel("Cost: 1650 points");
		lblNewLabel_2_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_2_2.setBounds(82, 135, 113, 40);
		panel_2.add(lblNewLabel_2_2);
		
		MouseListener ms = new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				int points = 0;
				int days = 0;
				if(e.getSource().equals(btnOne)) {
					points = 150;
					days =30;
				}
				else if(e.getSource().equals(btnSix)) {
					points = 850;
					days = 180;
				}
				else if(e.getSource().equals(btnYear)) {
					points = 1650;
					days = 365;
				}
				if(user.getPoints() >= 150) {
					int confirm = JOptionPane.showConfirmDialog(mainFrame,"Are you sure you want to buy this subscription for " + points + " points?");  
					if(confirm == JOptionPane.YES_OPTION){  
						//update the customer's points and subscription days.
						user.setPoints(user.getPoints() - points);
						user.setSub(user.getSub() + days);
						user.updateCustomerData();
						mainFrame.changePanel(new SubscriptionGUI(mainFrame, user));
						mainFrame.RefreshNavMenu();
						JOptionPane.showMessageDialog(mainFrame, "Succesful", "Succesful", JOptionPane.WARNING_MESSAGE);
					}
				}
				else
					JOptionPane.showMessageDialog(mainFrame, "You dont have enough points", "Not enough points", JOptionPane.ERROR_MESSAGE);
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
		
		btnOne.addMouseListener(ms);
		btnSix.addMouseListener(ms);
		btnYear.addMouseListener(ms);

	}

}
