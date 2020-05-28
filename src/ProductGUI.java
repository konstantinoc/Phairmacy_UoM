import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;

public class ProductGUI extends JPanel {
	private MainFrame parent;
	/**
	 * Create the panel.
	 */
	public ProductGUI(MainFrame parent,Product product, User user) {
		this.parent = parent;
		
		setLayout(null);
		
		JLabel lblBack = new JLabel();
		lblBack.setIcon(new ImageIcon(StoreGUI.class.getResource("/icons/previus-page.png")));
		lblBack.setBounds(50,50,32,32);
		add(lblBack);
		
		JLabel lblImg = new JLabel();
		lblImg.setBounds(331, 26, 300, 300);
		add(lblImg);
		
		JTextField txtTitle = new JTextField(String.valueOf(product.getName()));
		txtTitle.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtTitle.setBounds(674, 26, 508, 31);
		txtTitle.setEditable(false);
		txtTitle.setBorder(null);
		txtTitle.setBackground(null);
		add(txtTitle);
		
		JLabel lblNewLabel_2 = new JLabel("Price: \u20AC");
		lblNewLabel_2.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(676, 88, 79, 26);
		add(lblNewLabel_2);
		
		JTextField txtPrice = new JTextField();
		txtPrice.setText(String.valueOf(product.getPrice()));
		txtPrice.setFont(new Font("SansSerif", Font.PLAIN, 20));
		txtPrice.setBounds(748, 88, 88, 26);
		txtPrice.setBorder(null);
		txtPrice.setEditable(false);
		txtPrice.setBackground(null);
		add(txtPrice);
		
		JLabel lblNewLabel_3 = new JLabel("Available:");
		lblNewLabel_3.setBounds(674, 135, 57, 13);
		add(lblNewLabel_3);
		
		JTextField txtAvailable = new JTextField(String.valueOf(product.getQty()));
		txtAvailable.setBounds(735, 135, 39, 19);
		txtAvailable.setEditable(false);
		txtAvailable.setBorder(null);
		txtAvailable.setBackground(null);
		add(txtAvailable);
		
		SpinnerModel model = new SpinnerNumberModel(1, 1, 20, 1);
		JSpinner spinner = new JSpinner(model);
		spinner.setBounds(706, 168, 35, 25);
		add(spinner);
		
		JLabel lblNewLabel_4 = new JLabel("Qty:");
		lblNewLabel_4.setBounds(679, 171, 30, 13);
		add(lblNewLabel_4);
		
		JTextArea textArea = new JTextArea();
		textArea.setText(product.getDescription());
		textArea.setEditable(false);
		textArea.setBounds(331, 348, 1008, 186);
		add(textArea);
		
		JButton btnAddCart = new JButton("Add to cart");
		btnAddCart.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnAddCart.setBounds(694, 223, 129, 31);
		add(btnAddCart);
		
		JLabel lblEdit = new JLabel("");
		lblEdit.setIcon(new ImageIcon(ProductGUI.class.getResource("/icons/pencil.png")));
		lblEdit.setBounds(657, 264, 32, 39);
		
		JLabel lblSave = new JLabel("");
		lblSave.setIcon(new ImageIcon(ProductGUI.class.getResource("/icons/save.png")));
		lblSave.setBounds(699, 264, 32, 39);
		lblSave.setEnabled(false);
		
		if(user.getIsPharmacist() == 1) {
			txtTitle.setBorder(new LineBorder(Color.black,1));
			txtPrice.setBorder(new LineBorder(Color.black,1));
			txtAvailable.setBorder(new LineBorder(Color.black,1));
			
			lblNewLabel_4.setVisible(false);
			spinner.setVisible(false);
			btnAddCart.setVisible(false);
					
			add(lblEdit);
			add(lblSave);
		}
		
		MouseListener ms = new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getSource().equals(lblBack)) {
					hideMe();
					parent.changePanel(new StoreGUI(parent,user));
				}
				else if (e.getSource().equals(btnAddCart)) {
					user.getCart().addProduct(product,(int)spinner.getValue());
					parent.changePanel(new CartGUI(parent,user));
				}
				else if (e.getSource().equals(lblEdit)) {
					lblEdit.setEnabled(false);
					lblSave.setEnabled(true);
					
					txtTitle.setEditable(true);
					txtPrice.setEditable(true);
					txtAvailable.setEditable(true);
				}
				else if (e.getSource().equals(lblSave)) {
					lblEdit.setEnabled(true);
					lblSave.setEnabled(false);
					
					txtTitle.setEditable(false);
					txtPrice.setEditable(false);
					txtAvailable.setEditable(false);
					
					product.editProduct(Float.valueOf(txtPrice.getText()), txtTitle.getText(), Integer.valueOf(txtAvailable.getText()));
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
		
		lblBack.addMouseListener(ms);
		btnAddCart.addMouseListener(ms);
		lblEdit.addMouseListener(ms);
		lblSave.addMouseListener(ms);
		
		try {
			ImageIcon originalIcon = new ImageIcon(StoreGUI.class.getResource("/img/" + product.getImg()));
			Image originalImage = originalIcon.getImage();
			Image modImage = originalImage.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
						
			JLabel lblImage = new JLabel();
			lblImage.setBounds(25, 10, 150, 150);
			lblImage.setIcon(new ImageIcon(modImage));
			
			lblImg.setIcon(new ImageIcon(modImage));
		}catch(Exception e) {
				System.out.println(e);
		}
	}
	
	
	public void hideMe() {
		this.setVisible(false);
	}
}
