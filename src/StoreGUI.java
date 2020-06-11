import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class StoreGUI extends JPanel {
	private MainFrame parent;
	private User user;
	
	private JTextField txtSearch;
	private final Color backgroundColor = new Color(196, 219, 191);
	/**
	 * Create the panel.
	 */
	public StoreGUI(MainFrame parent, User user) {
		this.parent = parent;
		this.user = user;
		
		setBackground(backgroundColor);
		setLayout(null);
		
		
		fetchProducts(1);
	}

	private void fetchProducts(int page) {
		this.removeAll();
		this.repaint();
		this.revalidate();
		
		ArrayList<Product> productsList = new ArrayList<>();
		DB_Connection objDB = new DB_Connection();
		Connection connection = objDB.get_connection();
		PreparedStatement ps = null;
		try {
			String query = "SELECT * FROM products LIMIT 10 OFFSET " + (page-1)*10 + ";";
			ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				productsList.add(new Product(rs.getInt("id")));
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		
		JLabel lbladdProduct = new JLabel();
		JLabel lblAddCoupon = new JLabel();
		JLabel lblRemoveCoupon = new JLabel();
		
		if(user.getIsPharmacist() == 1) {
			lbladdProduct.setIcon(new ImageIcon(StoreGUI.class.getResource("/icons/plus.png")));
			lbladdProduct.setText("Add Product");
			lbladdProduct.setBounds(10, 10, 120, 35);
			add(lbladdProduct);
			
			lblAddCoupon.setIcon(new ImageIcon(StoreGUI.class.getResource("/icons/plus.png")));
			lblAddCoupon.setText("Add Coupon");
			lblAddCoupon.setBounds(130, 10, 120, 35);
			add(lblAddCoupon);
			
			lblRemoveCoupon.setIcon(new ImageIcon(StoreGUI.class.getResource("/icons/plus.png")));
			lblRemoveCoupon.setText("Remove Coupon");
			lblRemoveCoupon.setBounds(250, 10, 140, 35);
			add(lblRemoveCoupon);		
		}
		
		txtSearch = new JTextField();
		txtSearch.setText("Search");
		txtSearch.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txtSearch.setBackground(null);
		txtSearch.setBounds(1203, 15, 171, 19);
		add(txtSearch);
		txtSearch.setColumns(10);
		txtSearch.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		
		JButton btnSearch = new JButton();
		btnSearch.setBackground(null);
		btnSearch.setIcon(new ImageIcon(StoreGUI.class.getResource("/icons/search.png")));
		btnSearch.setBounds(1377, 10, 38, 26);
		add(btnSearch);
		
		int x = 90;
		for (int product = 0;product < productsList.size();product++ ) {
			JPanel panel1 = new JPanel();
			panel1.setLayout(null);

			try {
			ImageIcon originalIcon = new ImageIcon(StoreGUI.class.getResource("/img/" + productsList.get(product).getImg()));
			Image originalImage = originalIcon.getImage();
			Image modImage = originalImage.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
						
			JLabel lblImage = new JLabel();
			lblImage.setBounds(25, 10, 150, 150);
			lblImage.setIcon(new ImageIcon(modImage));
			
			panel1.add(lblImage);
			}catch(Exception e) {
				System.out.println(e);
			}
			
			JLabel lblNext = new JLabel("");
			lblNext.setIcon(new ImageIcon(StoreGUI.class.getResource("/icons/next_page.png")));
			lblNext.setBounds(1404, 345, 32, 38);
			
			JLabel lblBack = new JLabel("");
			lblBack.setIcon(new ImageIcon(StoreGUI.class.getResource("/icons/previus-page.png")));
			lblBack.setBounds(34, 345, 32, 38);
			
			JLabel lblName = new JLabel(String.valueOf((productsList.get(product).getName())));
			lblName.setBounds(10, 180, 190, 27);
			lblName.setHorizontalAlignment(SwingConstants.CENTER);
			panel1.add(lblName);
			
			JLabel lblPrice = new JLabel("€" + String.valueOf(productsList.get(product).getPrice()));
			lblPrice.setBounds(10,200,190,27);
			lblPrice.setHorizontalAlignment(SwingConstants.CENTER);
			panel1.add(lblPrice);
			
			JSeparator separator = new JSeparator();
			separator.setForeground(Color.BLACK);
			separator.setBounds(0, 230, 200, 1);
			panel1.add(separator);
			
			JLabel lblAddToCart = new JLabel("");
			lblAddToCart.setVisible(false);
			
			JLabel lblEditProduct = new JLabel("");
			lblEditProduct.setVisible(false);
			
			if(user.getIsPharmacist() == 0) {
				lblAddToCart.setVisible(true);
				lblAddToCart.setIcon(new ImageIcon(StoreGUI.class.getResource("/icons/add_cart_l.png")));
				lblAddToCart.setBounds(90,240,32,32);
				panel1.add(lblAddToCart);
			}
			else {
				lblEditProduct.setVisible(true);
				lblEditProduct.setIcon(new ImageIcon(StoreGUI.class.getResource("/icons/tools.png")));
				lblEditProduct.setBounds(90,240,32,32);
				panel1.add(lblEditProduct);
			}
			
			if (product < 5 )
				panel1.setBounds(x, 65, 200, 285);
			else if (product == 5) {
				x = 90;
				panel1.setBounds(x, 380, 200, 285);
			}
			else
				panel1.setBounds(x, 380, 200, 285);
			add(panel1);
			
			x += 270;
			
			if (page == 1 && productsList.size() == 10) {
				add(lblNext);
			}
			else if (page != 1 && productsList.size() < 10) {
				add(lblBack);
			}
			else if (productsList.size() == 10) {
				add(lblNext);
				add(lblBack);
			}
			Product p = productsList.get(product);
			MouseListener ml = new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getSource().equals(lblNext)) {
						fetchProducts(page+1);
					}
					else if (e.getSource().equals(lblBack))
						fetchProducts(page-1);
					else if (e.getSource().equals(panel1)) {
						parent.changePanel(new ProductGUI(parent,p,user));
					}
					else if(e.getSource().equals(lblAddToCart)) {
						if(p.getQty()>0) {
							user.getCart().addProduct(p, 1);
							JOptionPane.showMessageDialog(parent, "Product added succesfuly","Succesful operation", JOptionPane.INFORMATION_MESSAGE);
						}else {
							JOptionPane.showMessageDialog(parent, "Product is out of stock","Out of stock", JOptionPane.ERROR_MESSAGE);
						}
					}
					else if(e.getSource().equals(lblEditProduct)) {
						parent.changePanel(new ProductGUI(parent,p,user));
					}
					else if(e.getSource().equals(lbladdProduct)) {
						parent.changePanel(new AddProductGUI(parent,user));
					}
					else if(e.getSource().equals(txtSearch) && txtSearch.getText().equals("Search")) {
						txtSearch.setText(null);
					}
					else if(e.getSource().equals(lblAddCoupon)) {
						parent.changePanel(new AddCouponGUI(parent));
					}
					else if(e.getSource().equals(lblRemoveCoupon)) {
						parent.changePanel(new CouponsGUI(parent, user));
					}
				}

				@Override
				public void mousePressed(MouseEvent e) {
					
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					if(e.getSource().equals(lblAddToCart)) {
						lblAddToCart.setIcon(new ImageIcon(StoreGUI.class.getResource("/icons/add_cart.png")));
					}
					setCursor(new Cursor(Cursor.HAND_CURSOR));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					if(e.getSource().equals(lblAddToCart)) {
						lblAddToCart.setIcon(new ImageIcon(StoreGUI.class.getResource("/icons/add_cart_l.png")));
					}
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}
				
			};
			
			txtSearch.addMouseListener(ml);
			btnSearch.addMouseListener(ml);
			lbladdProduct.addMouseListener(ml);
			lblAddCoupon.addMouseListener(ml);
			lblRemoveCoupon.addMouseListener(ml);
			lblNext.addMouseListener(ml);
			lblBack.addMouseListener(ml);
			lblAddToCart.addMouseListener(ml);
			panel1.addMouseListener(ml);
			
		}
		
	}
}
