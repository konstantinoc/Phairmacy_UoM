import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
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
	/**
	 * Create the panel.
	 */
	public PurchaseWithMemberCardGUI(MainFrame parent,User user, PurchaseWithMemberCard pwmc) {
		setBackground(formColor);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setBounds(953, 32, 476, 260);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Items()");
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 10, 59, 20);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Cost: \u20AC");
		lblNewLabel_1.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblNewLabel_1.setBounds(307, 16, 59, 20);
		panel.add(lblNewLabel_1);
		
		JCheckBox ckRedeemPoints = new JCheckBox("Redeem points");
		ckRedeemPoints.setFont(new Font("SansSerif", Font.BOLD, 15));
		ckRedeemPoints.setBounds(6, 50, 135, 21);
		panel.add(ckRedeemPoints);
		
		JCheckBox ckDelivery = new JCheckBox("Delivery");
		ckDelivery.setFont(new Font("SansSerif", Font.BOLD, 15));
		ckDelivery.setBounds(6, 79, 93, 21);
		panel.add(ckDelivery);
		
		textField = new JTextField();
		textField.setBackground(null);
		textField.setBounds(125, 110, 96, 19);
		textField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblCheckCode = new JLabel("");
		lblCheckCode.setIcon(new ImageIcon(PurchaseWithMemberCardGUI.class.getResource("/icons/book.png")));
		lblCheckCode.setBounds(225, 101, 32, 32);
		panel.add(lblCheckCode);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(307, 139, 147, 2);
		panel.add(separator);
		
		JLabel lblNewLabel_1_1 = new JLabel("- \u20AC");
		lblNewLabel_1_1.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(338, 50, 18, 20);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("- \u20AC");
		lblNewLabel_1_1_1.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblNewLabel_1_1_1.setBounds(338, 106, 18, 20);
		panel.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("+ \u20AC");
		lblNewLabel_1_1_1_1.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblNewLabel_1_1_1_1.setBounds(334, 79, 22, 20);
		panel.add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_3 = new JLabel("Total: \u20AC");
		lblNewLabel_3.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblNewLabel_3.setBounds(300, 151, 59, 13);
		panel.add(lblNewLabel_3);
		
		JLabel lblCost = new JLabel("");
		lblCost.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblCost.setBounds(364, 20, 76, 13);
		panel.add(lblCost);
		
		JLabel lblPoints = new JLabel("");
		lblPoints.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblPoints.setBounds(364, 56, 76, 13);
		panel.add(lblPoints);
		
		JLabel lblDiscound = new JLabel("");
		lblDiscound.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblDiscound.setBounds(366, 110, 76, 13);
		panel.add(lblDiscound);
		
		JLabel lblDelivery = new JLabel("");
		lblDelivery.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblDelivery.setBounds(366, 85, 76, 13);
		panel.add(lblDelivery);
		
		JLabel lblTotal = new JLabel("");
		lblTotal.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblTotal.setBounds(369, 153, 76, 13);
		panel.add(lblTotal);
		
		JLabel lblNewLabel_2 = new JLabel("Discount Code");
		lblNewLabel_2.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblNewLabel_2.setBounds(10, 112, 110, 13);
		panel.add(lblNewLabel_2);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton();
		rdbtnNewRadioButton.setBounds(10, 170, 22, 21);
		panel.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton();
		rdbtnNewRadioButton_1.setBounds(10, 207, 22, 21);
		panel.add(rdbtnNewRadioButton_1);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnNewRadioButton);
		group.add(rdbtnNewRadioButton_1);
		
		JLabel lblNewLabel_4 = new JLabel("Credit Card");
		lblNewLabel_4.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_4.setIcon(new ImageIcon(PurchaseWithMemberCardGUI.class.getResource("/icons/credit-card.png")));
		lblNewLabel_4.setBounds(38, 160, 110, 40);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Cash at your door");
		lblNewLabel_5.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_5.setIcon(new ImageIcon(PurchaseWithMemberCardGUI.class.getResource("/icons/money.png")));
		lblNewLabel_5.setBounds(38, 211, 136, 32);
		panel.add(lblNewLabel_5);
		
		JButton btnNewButton = new JButton("Checkout");
		btnNewButton.setFont(new Font("SansSerif", Font.BOLD, 18));
		btnNewButton.setBounds(300, 196, 140, 36);
		panel.add(btnNewButton);
		
		float[] price = pwmc.calculateTotal(ckRedeemPoints.isSelected(), ckDelivery.isSelected(), textField.getText());
		lblCost.setText(String.valueOf(price[0]));
		lblPoints.setText(String.valueOf(price[1]));
		lblDiscound.setText(String.valueOf(price[2]));
		lblDelivery.setText(String.valueOf(price[3]));
		lblTotal.setText(String.valueOf(price[4]));
		
		ActionListener al = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				float[] price = pwmc.calculateTotal(ckRedeemPoints.isSelected(), ckDelivery.isSelected(), textField.getText());
				lblCost.setText(String.valueOf(price[0]));
				lblPoints.setText(String.valueOf(price[1]));
				lblDiscound.setText(String.valueOf(price[2]));
				lblDelivery.setText(String.valueOf(price[3]));
				lblTotal.setText(String.valueOf(price[4]));
			}
			
		};
		
		MouseListener ms = new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				float[] price = pwmc.calculateTotal(ckRedeemPoints.isSelected(), ckDelivery.isSelected(), textField.getText());
				lblCost.setText(String.valueOf(price[0]));
				lblPoints.setText(String.valueOf(price[1]));
				lblDiscound.setText(String.valueOf(price[2]));
				lblDelivery.setText(String.valueOf(price[3]));
				lblTotal.setText(String.valueOf(price[4]));
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
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		};
		
		lblCheckCode.addMouseListener(ms);
		ckRedeemPoints.addActionListener(al);
		ckDelivery.addActionListener(al);
	}
	
	public void hideMe() {
		this.setVisible(false);
	}
}
