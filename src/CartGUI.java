import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.JButton;

public class CartGUI extends JPanel {
	private final Color btnColor = new Color(23, 128 ,0);
	private final Color formColor = new Color(196, 219, 191);
	private final Color color2 = Color.BLACK;

	
	private static DecimalFormat df = new DecimalFormat("0.00");
	/**
	 * Create the panel.
	 */
	
	public CartGUI(MainFrame parent,User user) {
		setLayout(null);
		setBackground(formColor);
		
		JPanel total = new JPanel(null);
		total.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		total.setBounds(1100, 73, 390, 200);
		add(total);
		
		JLabel lblNewLabel_2 = new JLabel("Items("+ user.getCart().cartSize() + ")");
		lblNewLabel_2.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblNewLabel_2.setBounds(10, 21, 62, 20);
		total.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Total: \u20AC" + df.format(user.getCart().calculateTotal()));
		lblNewLabel_3.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblNewLabel_3.setBounds(270, 27, 159, 13);
		total.add(lblNewLabel_3);
		
		JButton btnPurchaseWithCard = new JButton("Purchase with member card");
		if (user.getCart().cartSize() > 0) {
			btnPurchaseWithCard.setEnabled(true);
			btnPurchaseWithCard.setBackground(btnColor);
		}
		else
			btnPurchaseWithCard.setEnabled(false);
		btnPurchaseWithCard.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnPurchaseWithCard.setBounds(10, 76, 370, 34);
		
		btnPurchaseWithCard.setForeground(color2);
		total.add(btnPurchaseWithCard);
		
		JButton btnPurchase = new JButton("Purchase ");
		if (user.getCart().cartSize() > 0) {
			btnPurchase.setEnabled(true);
			btnPurchase.setBackground(btnColor);
		}
		else
			btnPurchase.setEnabled(false);
		btnPurchase.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnPurchase.setBounds(10, 134, 370, 34);
		btnPurchase.setForeground(color2);
		total.add(btnPurchase);
		
		if (user.getCart().cartSize() == 0){
			JLabel lblNewLabel_1 = new JLabel("Your cart is empty");
			lblNewLabel_1.setFont(new Font("SansSerif", Font.PLAIN, 20));
			lblNewLabel_1.setBounds(83, 112, 409, 26);
			add(lblNewLabel_1);
		}
		
		int x = 80; 
		for (int i = 0; i < user.getCart().cartSize(); i++) {
			Product currentP = user.getCart().getProductList().get(i);
			
			JPanel panel = new JPanel();
			if(i == 0)
				panel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
			else
				panel.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.black));
			panel.setBounds(100, x, 803, 112);
			add(panel);
			panel.setLayout(null);
			x +=112;
			
			JLabel lblImage = new JLabel();
			lblImage.setBounds(10, 5, 100, 100);
			panel.add(lblImage);
			
			JLabel lblTitle = new JLabel(currentP.getName());
			lblTitle.setBounds(200, 46, 284, 25);
			panel.add(lblTitle);
			
			JLabel lblPrice = new JLabel("€" + String.valueOf(currentP.getPrice()));
			lblPrice.setBounds(445, 46, 97, 25);
			panel.add(lblPrice);
			
			JLabel lblNewLabel = new JLabel("Qty");
			lblNewLabel.setBounds(587, 46, 24, 25);
			panel.add(lblNewLabel);
			
			
			JTextField txtQty = new JTextField();
			txtQty.setText(String.valueOf(user.getCart().getQtyList().get(i)));
			txtQty.setBounds(612, 49, 30, 20);
			panel.add(txtQty);
			
			JLabel lblRefresh = new JLabel("");
			lblRefresh.setIcon(new ImageIcon(CartGUI.class.getResource("/icons/refresh.png")));
			lblRefresh.setBounds(679, 33, 35, 38);
			panel.add(lblRefresh);
			
			JLabel lblDelete = new JLabel("");
			lblDelete.setIcon(new ImageIcon(CartGUI.class.getResource("/icons/delete.png")));
			lblDelete.setBounds(744, 35, 30, 36);
			panel.add(lblDelete);
			
			try {
				ImageIcon originalIcon = new ImageIcon(StoreGUI.class.getResource("/img/" + currentP.getImg()));
				Image originalImage = originalIcon.getImage();
				Image modImage = originalImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
							
				lblImage.setIcon(new ImageIcon(modImage));
				
				}catch(Exception e) {
					System.out.println(e);
				}
			
			MouseListener ms = new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent e) {
					if(e.getSource().equals(lblImage)) {
						parent.changePanel(new ProductGUI(parent,currentP,user));
					}
					else if(e.getSource().equals(lblDelete)) {
						user.getCart().removeProduct(currentP);
						parent.changePanel(new CartGUI(parent,user));
					}
					else if(e.getSource().equals(lblRefresh)) {
						try {
							user.getCart().changeProductQty(currentP,Integer.valueOf(txtQty.getText()));
							parent.changePanel(new CartGUI(parent,user));
							JOptionPane.showMessageDialog(parent, "Quantity change succesfuly","Succesful operation", JOptionPane.INFORMATION_MESSAGE);
						}catch(Exception ex){
							System.out.println(ex);
						}
					}
					else if(e.getSource().equals(btnPurchaseWithCard)) {
						parent.changePanel(new PurchaseWithMemberCardGUI(parent,user));
						hideMe();
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
			
			lblImage.addMouseListener(ms);
			lblDelete.addMouseListener(ms);
			lblRefresh.addMouseListener(ms);
			btnPurchaseWithCard.addMouseListener(ms);
		}
	}
		
	public void hideMe() {
		this.setVisible(false);
	}
}
