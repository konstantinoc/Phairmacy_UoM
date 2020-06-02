import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class PurchaseWithMemberCardGUI extends JPanel {
	private JTextField textField;
	
	private final Color btnColor = new Color(23, 128 ,0);
	private final Color formColor = new Color(196, 219, 191);
	private final Color color2 = Color.BLACK;
	private static DecimalFormat df = new DecimalFormat("0.00");
	
	private JTextField txtName;
	private JTextField txtSurname;
	private JTextField txtAddress;
	private JTextField txtCity;
	private JTextField txtPostal;
	private JTextField txtPhone;
	private JTextField txtEmail;
	private JTextField txtBirthday;
	private JLabel lblTotal;
	
	private User user;
	private MainFrame mainFrame;
	private PurchaseWithMemberCard pwmc;
	
	/**
	 * Create the panel.
	 */
	public PurchaseWithMemberCardGUI(MainFrame mainFrame,User user) {
		this.user = user;
		this.mainFrame = mainFrame;
		pwmc = new PurchaseWithMemberCard(user,this);
		
		setBackground(formColor);
		setLayout(null);
		
		showProductsInCart();
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panel.setBounds(974, 303, 424, 412);
		this.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		lblNewLabel.setBounds(10, 30, 82, 24);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Surname:");
		lblNewLabel_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(10, 75, 82, 24);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Address:");
		lblNewLabel_2.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(10, 120, 82, 24);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("City:");
		lblNewLabel_3.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(10, 165, 82, 19);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Postal Code:");
		lblNewLabel_4.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		lblNewLabel_4.setBounds(10, 210, 108, 19);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Phone Number:");
		lblNewLabel_5.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		lblNewLabel_5.setBounds(10, 255, 130, 24);
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Email:");
		lblNewLabel_6.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		lblNewLabel_6.setBounds(10, 300, 82, 19);
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Date of Birth:");
		lblNewLabel_7.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		lblNewLabel_7.setBounds(10, 345, 127, 19);
		panel.add(lblNewLabel_7);
		
		txtName = new JTextField(user.getName());
		txtName.setFont(new Font("SansSerif", Font.PLAIN, 17));
		txtName.setEditable(false);
		txtName.setBounds(151, 30, 205, 19);
		panel.add(txtName);
		txtName.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		txtName.setColumns(10);
		
		
		txtSurname = new JTextField(user.getSurname());
		txtSurname.setFont(new Font("SansSerif", Font.PLAIN, 17));
		txtSurname.setEditable(false);
		txtSurname.setColumns(10);
		txtSurname.setBounds(151, 75, 205, 19);
		panel.add(txtSurname);
		txtSurname.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		
		txtAddress = new JTextField(user.getAddress());
		txtAddress.setFont(new Font("SansSerif", Font.PLAIN, 17));
		txtAddress.setEditable(false);
		txtAddress.setColumns(10);
		txtAddress.setBounds(150, 120, 206, 19);
		panel.add(txtAddress);
		txtAddress.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));

		txtCity = new JTextField(user.getCity());
		txtCity.setFont(new Font("SansSerif", Font.PLAIN, 17));
		txtCity.setEditable(false);
		txtCity.setColumns(10);
		txtCity.setBounds(150, 165, 206, 19);
		panel.add(txtCity);
		txtCity.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));

		
		txtPostal = new JTextField(user.getPostal());
		txtPostal.setFont(new Font("SansSerif", Font.PLAIN, 17));
		txtPostal.setEditable(false);
		txtPostal.setColumns(10);
		txtPostal.setBounds(150, 210, 206, 19);
		panel.add(txtPostal);
		txtPostal.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));

		
		txtPhone = new JTextField(user.getPhone());
		txtPhone.setFont(new Font("SansSerif", Font.PLAIN, 17));
		txtPhone.setEditable(false);
		txtPhone.setColumns(10);
		txtPhone.setBounds(150, 255, 206, 19);
		panel.add(txtPhone);
		txtPhone.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));

		
		txtEmail = new JTextField(user.getEmail());
		txtEmail.setFont(new Font("SansSerif", Font.PLAIN, 17));
		txtEmail.setEditable(false);
		txtEmail.setColumns(10);
		txtEmail.setBounds(150, 300, 206, 19);
		panel.add(txtEmail);
		txtEmail.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));

		
		txtBirthday = new JTextField(user.getBirthday());
		txtBirthday.setFont(new Font("SansSerif", Font.PLAIN, 17));
		txtBirthday.setEditable(false);
		txtBirthday.setColumns(10);
		txtBirthday.setBounds(150, 345, 206, 19);
		panel.add(txtBirthday);
		txtBirthday.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		
		JPanel panel2 = new JPanel();
		panel2.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel2.setBounds(953, 32, 476, 260);
		add(panel2);
		panel2.setLayout(null);
		
		JLabel lblNewLabel12 = new JLabel("Items()");
		lblNewLabel12.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblNewLabel12.setBounds(10, 10, 59, 20);
		panel2.add(lblNewLabel12);
		
		JLabel lblNewLabel_1_2 = new JLabel("Cost: \u20AC");
		lblNewLabel_1_2.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblNewLabel_1_2.setBounds(307, 16, 59, 20);
		panel2.add(lblNewLabel_1_2);
		
		
		JCheckBox ckRedeemPoints = new JCheckBox("Redeem points");
		ckRedeemPoints.setFont(new Font("SansSerif", Font.BOLD, 15));
		ckRedeemPoints.setBounds(6, 50, 135, 21);
		panel2.add(ckRedeemPoints);
		
		JCheckBox ckDelivery = new JCheckBox("Delivery");
		ckDelivery.setFont(new Font("SansSerif", Font.BOLD, 15));
		ckDelivery.setBounds(6, 79, 93, 21);
		panel2.add(ckDelivery);
		
		textField = new JTextField();
		textField.setBackground(null);
		textField.setBounds(125, 110, 96, 19);
		textField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		panel2.add(textField);
		textField.setColumns(10);
		
		JLabel lblCheckCode = new JLabel("");
		lblCheckCode.setIcon(new ImageIcon(PurchaseWithMemberCardGUI.class.getResource("/icons/book.png")));
		lblCheckCode.setBounds(225, 101, 32, 32);
		panel2.add(lblCheckCode);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(307, 139, 147, 2);
		panel2.add(separator);
		
		JLabel lblNewLabel_1_1 = new JLabel("- \u20AC");
		lblNewLabel_1_1.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(338, 50, 18, 20);
		panel2.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("- \u20AC");
		lblNewLabel_1_1_1.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblNewLabel_1_1_1.setBounds(338, 106, 18, 20);
		panel2.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("+ \u20AC");
		lblNewLabel_1_1_1_1.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblNewLabel_1_1_1_1.setBounds(334, 79, 22, 20);
		panel2.add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_3_2 = new JLabel("Total: \u20AC");
		lblNewLabel_3_2.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblNewLabel_3_2.setBounds(300, 151, 59, 13);
		panel2.add(lblNewLabel_3_2);
		
		JLabel lblCost = new JLabel("");
		lblCost.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblCost.setBounds(364, 20, 76, 13);
		panel2.add(lblCost);
		
		JLabel lblPoints = new JLabel("");
		lblPoints.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblPoints.setBounds(364, 56, 76, 13);
		panel2.add(lblPoints);
		
		JLabel lblDiscound = new JLabel("");
		lblDiscound.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblDiscound.setBounds(366, 110, 76, 13);
		panel2.add(lblDiscound);
		
		JLabel lblDelivery = new JLabel("");
		lblDelivery.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblDelivery.setBounds(366, 85, 76, 13);
		panel2.add(lblDelivery);
		
		lblTotal = new JLabel("");
		lblTotal.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblTotal.setBounds(369, 153, 76, 13);
		panel2.add(lblTotal);
		
		JLabel lblNewLabel_2_2 = new JLabel("Discount Code");
		lblNewLabel_2_2.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblNewLabel_2_2.setBounds(10, 112, 110, 13);
		panel2.add(lblNewLabel_2_2);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton();
		rdbtnNewRadioButton.setBounds(10, 170, 22, 21);
		panel2.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton();
		rdbtnNewRadioButton_1.setBounds(10, 207, 22, 21);
		panel2.add(rdbtnNewRadioButton_1);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnNewRadioButton);
		group.add(rdbtnNewRadioButton_1);
		
		JLabel lblNewLabel_4_2 = new JLabel("Credit Card");
		lblNewLabel_4_2.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_4_2.setIcon(new ImageIcon(PurchaseWithMemberCardGUI.class.getResource("/icons/credit-card.png")));
		lblNewLabel_4_2.setBounds(38, 160, 110, 40);
		panel2.add(lblNewLabel_4_2);
		
		JLabel lblNewLabel_5_2 = new JLabel("Cash at your door");
		lblNewLabel_5_2.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_5_2.setIcon(new ImageIcon(PurchaseWithMemberCardGUI.class.getResource("/icons/money.png")));
		lblNewLabel_5_2.setBounds(38, 211, 136, 32);
		panel2.add(lblNewLabel_5_2);
		
		JButton btnCheckout = new JButton("Checkout");
		btnCheckout.setFont(new Font("SansSerif", Font.BOLD, 18));
		btnCheckout.setBounds(300, 196, 140, 36);
		panel2.add(btnCheckout);
		
		float[] price = pwmc.calculateTotal(ckRedeemPoints.isSelected(), ckDelivery.isSelected(), textField.getText());		
		lblCost.setText(df.format(price[0]));
		lblPoints.setText(df.format(price[1]));
		lblDiscound.setText(df.format(price[2]));
		lblDelivery.setText(df.format(price[3]));
		lblTotal.setText(df.format(price[4]));
		
		
		ActionListener al = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				float[] price = pwmc.calculateTotal(ckRedeemPoints.isSelected(), ckDelivery.isSelected(), textField.getText());
				lblCost.setText(df.format(price[0]));
				lblPoints.setText(df.format(price[1]));
				lblDiscound.setText(df.format(price[2]));
				lblDelivery.setText(df.format(price[3]));
				lblTotal.setText(df.format(price[4]));
			}
			
		};
		
		MouseListener ms = new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getSource().equals(lblCheckCode)){
					float[] price = pwmc.calculateTotal(ckRedeemPoints.isSelected(), ckDelivery.isSelected(), textField.getText());
					lblCost.setText(df.format(price[0]));
					lblPoints.setText(df.format(price[1]));
					lblDiscound.setText(df.format(price[2]));
					lblDelivery.setText(df.format(price[3]));
					lblTotal.setText(df.format(price[4]));
				}
				else if(e.getSource().equals(btnCheckout)) {
					checkout();
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
		
		btnCheckout.addMouseListener(ms);
		lblCheckCode.addMouseListener(ms);
		ckRedeemPoints.addActionListener(al);
		ckDelivery.addActionListener(al);
	}
	
	public void showProductsInCart() {
		int x = 32; 
		for (int i = 0; i < user.getCart().cartSize(); i++) {
			Product currentP = user.getCart().getProductList().get(i);
			
			JPanel productPanel = new JPanel();
			if(i == 0)
				productPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
			else
				productPanel.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.black));
			productPanel.setBounds(100, x, 770, 112);
			productPanel.setLayout(null);
			productPanel.setVisible(true);
			x +=112;
			
			JLabel lblImage = new JLabel();
			lblImage.setBounds(10, 5, 100, 100);
			productPanel.add(lblImage);
			
			JLabel lblTitle = new JLabel(currentP.getName());
			lblTitle.setBounds(200, 46, 284, 25);
			productPanel.add(lblTitle);
			
			JLabel lblPrice = new JLabel("€" + df.format(currentP.getPrice()));
			lblPrice.setBounds(445, 46, 97, 25);
			productPanel.add(lblPrice);
			
			JLabel lblNewLabel = new JLabel("Qty");
			lblNewLabel.setBounds(587, 46, 24, 25);
			productPanel.add(lblNewLabel);
			
			JLabel lblQty = new JLabel(String.valueOf(user.getCart().getQtyList().get(i)));
			lblQty.setBounds(612, 49, 30, 20);
			productPanel.add(lblQty);
			
			try {
				ImageIcon originalIcon = new ImageIcon(StoreGUI.class.getResource("/img/" + currentP.getImg()));
				Image originalImage = originalIcon.getImage();
				Image modImage = originalImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
							
				lblImage.setIcon(new ImageIcon(modImage));
				
				}catch(Exception e) {
					System.out.println(e);
				}
			
			add(productPanel);
		}
	}
	
	public void checkout() {
		pwmc.checkout();
	}
	
	public MainFrame getMainFrame() {
		return mainFrame;
	}

	public void hideMe() {
		this.setVisible(false);
	}
}

