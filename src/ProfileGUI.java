
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class ProfileGUI extends JPanel {
	private User customer;
	private MainFrame mainFrame;
	private final Color backgroundColor = new Color(196, 219, 191);

	private JTextField txtName;
	private JTextField txtSurname;
	private JTextField txtAddress;
	private JTextField txtCity;
	private JTextField txtPostal;
	private JTextField txtPhone;
	private JTextField txtEmail;
	private JTextField txtBirthday;

	/**
	 * Create the panel Profile.
	 * Users can edit their infos.
	 * If the user is customer can move to order history panel, subscription panel and coupons panel.
	 * He can add his allergies.
	 */
	public ProfileGUI(MainFrame mainFrame, User user) {
		this.customer = user;
		this.mainFrame = mainFrame;
		
		setBackground(backgroundColor);
		setLayout(null);
		setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 6, true));
		panel.setBounds(269, 85, 326, 376);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 30, 82, 13);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Surname:");
		lblNewLabel_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(10, 65, 82, 13);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Address:");
		lblNewLabel_2.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(10, 100, 82, 13);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("City:");
		lblNewLabel_3.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(10, 135, 82, 13);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Postal Code:");
		lblNewLabel_4.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		lblNewLabel_4.setBounds(10, 170, 82, 13);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Phone Number:");
		lblNewLabel_5.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		lblNewLabel_5.setBounds(10, 205, 82, 13);
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Email:");
		lblNewLabel_6.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		lblNewLabel_6.setBounds(10, 240, 82, 13);
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Date of Birth:");
		lblNewLabel_7.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		lblNewLabel_7.setBounds(10, 275, 82, 13);
		panel.add(lblNewLabel_7);
		
		txtName = new JTextField(user.getName());
		txtName.setFont(new Font("SansSerif", Font.PLAIN, 11));
		txtName.setEditable(false);
		txtName.setBounds(93, 30, 205, 19);
		panel.add(txtName);
		txtName.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		txtName.setColumns(10);
		
		txtSurname = new JTextField(user.getSurname());
		txtSurname.setFont(new Font("SansSerif", Font.PLAIN, 11));
		txtSurname.setEditable(false);
		txtSurname.setColumns(10);
		txtSurname.setBounds(93, 65, 205, 19);
		panel.add(txtSurname);
		txtSurname.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		
		txtAddress = new JTextField(user.getAddress());
		txtAddress.setFont(new Font("SansSerif", Font.PLAIN, 11));
		txtAddress.setEditable(false);
		txtAddress.setColumns(10);
		txtAddress.setBounds(92, 100, 206, 19);
		panel.add(txtAddress);
		txtAddress.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));

		txtCity = new JTextField(user.getCity());
		txtCity.setFont(new Font("SansSerif", Font.PLAIN, 11));
		txtCity.setEditable(false);
		txtCity.setColumns(10);
		txtCity.setBounds(92, 135, 206, 19);
		panel.add(txtCity);
		txtCity.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		
		txtPostal = new JTextField(user.getPostal());
		txtPostal.setFont(new Font("SansSerif", Font.PLAIN, 11));
		txtPostal.setEditable(false);
		txtPostal.setColumns(10);
		txtPostal.setBounds(92, 170, 206, 19);
		panel.add(txtPostal);
		txtPostal.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		
		txtPhone = new JTextField(user.getPhone());
		txtPhone.setFont(new Font("SansSerif", Font.PLAIN, 11));
		txtPhone.setEditable(false);
		txtPhone.setColumns(10);
		txtPhone.setBounds(92, 205, 206, 19);
		panel.add(txtPhone);
		txtPhone.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		
		txtEmail = new JTextField(user.getEmail());
		txtEmail.setFont(new Font("SansSerif", Font.PLAIN, 11));
		txtEmail.setEditable(false);
		txtEmail.setColumns(10);
		txtEmail.setBounds(92, 240, 206, 19);
		panel.add(txtEmail);
		txtEmail.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		
		txtBirthday = new JTextField(user.getBirthday());
		txtBirthday.setFont(new Font("SansSerif", Font.PLAIN, 11));
		txtBirthday.setEditable(false);
		txtBirthday.setColumns(10);
		txtBirthday.setBounds(92, 275, 206, 19);
		panel.add(txtBirthday);
		txtBirthday.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		
		JLabel lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setIcon(new ImageIcon(ProfileGUI.class.getResource("/icons/pencil.png")));
		lblNewLabel_8.setBounds(226, 325, 32, 41);
		panel.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("");
		lblNewLabel_9.setEnabled(false);
		lblNewLabel_9.setIcon(new ImageIcon(ProfileGUI.class.getResource("/icons/save.png")));
		lblNewLabel_9.setBounds(268, 327, 32, 39);
		panel.add(lblNewLabel_9);
		
		JLabel lblOrderHistory = new JLabel("");
		lblOrderHistory.setIcon(new ImageIcon(ProfileGUI.class.getResource("/icons/history.png")));
		lblOrderHistory.setBounds(236, 523, 128, 136);
		add(lblOrderHistory);
		
		JLabel lblNewLabel_10 = new JLabel("Orders History");
		lblNewLabel_10.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNewLabel_10.setBounds(246, 669, 118, 21);
		add(lblNewLabel_10);
		
		JLabel lblSubscription = new JLabel("");
		lblSubscription.setIcon(new ImageIcon(ProfileGUI.class.getResource("/icons/subscription.png")));
		lblSubscription.setBounds(512, 523, 128, 136);
		add(lblSubscription);
		
		JLabel lblNewLabel_10_1 = new JLabel("Delivery Subscriptions");
		lblNewLabel_10_1.setIcon(null);
		lblNewLabel_10_1.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNewLabel_10_1.setBounds(493, 669, 175, 21);
		add(lblNewLabel_10_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 6, true));
		panel_1.setBounds(873, 85, 326, 376);
		add(panel_1);
		
		DefaultListModel model = new DefaultListModel() ;
		
		for(String a:user.getAllergies()) {
			model.addElement(a);
			}
		
		JList listView = new JList();
		listView.setBounds(13, 10, 303, 313);
		listView.setModel(model);
		panel_1.add(listView);
		
		JLabel lblAddAllergie = new JLabel("");
		lblAddAllergie.setIcon(new ImageIcon(ProfileGUI.class.getResource("/icons/plus.png")));
		lblAddAllergie.setBounds(141, 333, 36, 33);
		panel_1.add(lblAddAllergie);
		
		JLabel lblCoupons = new JLabel("");
		lblCoupons.setIcon(new ImageIcon(ProfileGUI.class.getResource("/icons/coupon_code.png")));
		lblCoupons.setBounds(790, 523, 128, 136);
		add(lblCoupons);
		
		JLabel lblNewLabel_10_1_1 = new JLabel("Buy Coupon Codes with points");
		lblNewLabel_10_1_1.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNewLabel_10_1_1.setBounds(745, 669, 218, 21);
		add(lblNewLabel_10_1_1);
		
		if(user.getIsPharmacist() == 1) {
			panel.setBounds(600, 85, 326, 376);
			
			lblOrderHistory.setVisible(false);
			lblSubscription.setVisible(false);
			lblCoupons.setVisible(false);
			lblNewLabel_10.setVisible(false);
			lblNewLabel_10_1.setVisible(false);
			lblNewLabel_10_1_1.setVisible(false);
			
			panel_1.setVisible(false);
		}
		
		MouseListener mouseListener = new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getSource().equals(lblNewLabel_8)) {
					lblNewLabel_8.setEnabled(false);
					lblNewLabel_9.setEnabled(true);
					txtName.setEditable(true);
					txtSurname.setEditable(true);
					txtAddress.setEditable(true);
					txtCity.setEditable(true);
					txtPostal.setEditable(true);
					txtPhone.setEditable(true);
					txtBirthday.setEditable(true);
					txtEmail.setEditable(true);
					
				}
				if(e.getSource().equals(lblNewLabel_9)) {
					lblNewLabel_8.setEnabled(true);
					lblNewLabel_9.setEnabled(false);
					txtName.setEditable(false);
					txtSurname.setEditable(false);
					txtAddress.setEditable(false);
					txtCity.setEditable(false);
					txtPostal.setEditable(false);
					txtPhone.setEditable(false);
					txtBirthday.setEditable(false);
					txtEmail.setEditable(false);
					
					user.setName(txtName.getText());
					user.setSurname(txtSurname.getText());
					user.setEmail(txtEmail.getText());
					user.setAddress(txtAddress.getText());
					user.setCity(txtCity.getText());
					user.setPostal(txtPostal.getText());
					user.setPhone(txtPhone.getText());
					user.setBirthday(txtBirthday.getText());
					
					user.updateCustomerData();
				}
				else if(e.getSource().equals(lblOrderHistory)) {
					mainFrame.changePanel(new OrderHistoryGUI(mainFrame, user));
				}
				else if(e.getSource().equals(lblSubscription)) {
					mainFrame.changePanel(new SubscriptionGUI(mainFrame, user));
				}
				else if (e.getSource().equals(lblAddAllergie)) {
					String allergie = JOptionPane.showInputDialog(mainFrame,"Enter the name of the ingredient you are allergic to.\n WARNING !!! Be careful about spelling"); 
					if (allergie != null) {
						boolean not_found = true;
						for(String a:user.getAllergies()) {
							if (a.equals(allergie)){
								not_found = false;
								break;
							}
						}
						if(not_found) {
							user.getAllergies().add(allergie);
							user.updateCustomerAllergies();
							mainFrame.changePanel(new ProfileGUI(mainFrame, user));
						}
						else {
							JOptionPane.showMessageDialog(mainFrame, "You have already added this allergi", "", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
				else if(e.getSource().equals(lblCoupons)) {
					mainFrame.changePanel(new CouponsGUI(mainFrame, user));
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
		
		lblCoupons.addMouseListener(mouseListener);
		lblAddAllergie.addMouseListener(mouseListener);
		lblSubscription.addMouseListener(mouseListener);
		lblOrderHistory.addMouseListener(mouseListener);
		lblNewLabel_8.addMouseListener(mouseListener);
		lblNewLabel_9.addMouseListener(mouseListener);
	}
}
