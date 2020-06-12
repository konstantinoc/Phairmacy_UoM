import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class AddProductGUI extends JPanel {
	MainFrame parent;
	private JTextField txtId;
	
	/**
	 * Create the panel.
	 * Pharmacist can add Product though this panel.
	 */
	public AddProductGUI(MainFrame parent, User user) {
		this.parent = parent;
		
		setLayout(null);
		
		JLabel lblBack = new JLabel();
		lblBack.setIcon(new ImageIcon(StoreGUI.class.getResource("/icons/previus-page.png")));
		lblBack.setBounds(50,50,32,32);
		add(lblBack);
		
		JLabel lblImg = new JLabel();
		lblImg.setBounds(331, 26, 300, 300);
		add(lblImg);
		
		JTextField txtTitle = new JTextField();
		txtTitle.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtTitle.setBounds(674, 26, 508, 31);
		txtTitle.setEditable(true);
		txtTitle.setBorder(null);
		txtTitle.setBackground(null);
		add(txtTitle);
		
		JLabel lblNewLabel_2 = new JLabel("Price: \u20AC");
		lblNewLabel_2.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(676, 88, 79, 26);
		add(lblNewLabel_2);
		
		JTextField txtPrice = new JTextField();
		txtPrice.setFont(new Font("SansSerif", Font.PLAIN, 20));
		txtPrice.setBounds(748, 88, 88, 26);
		txtPrice.setBorder(null);
		txtPrice.setEditable(true);
		txtPrice.setBackground(null);
		add(txtPrice);
		
		JLabel lblNewLabel_3 = new JLabel("Available:");
		lblNewLabel_3.setBounds(674, 135, 57, 13);
		add(lblNewLabel_3);
		
		JTextField txtAvailable = new JTextField();
		txtAvailable.setBounds(735, 135, 39, 19);
		txtAvailable.setEditable(true);
		txtAvailable.setBorder(null);
		txtAvailable.setBackground(null);
		add(txtAvailable);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(true);
		textArea.setBounds(331, 348, 1008, 186);
		add(textArea);
		
		
		JLabel lblSave = new JLabel("");
		lblSave.setIcon(new ImageIcon(ProductGUI.class.getResource("/icons/save.png")));
		lblSave.setBounds(699, 264, 32, 39);
		lblSave.setEnabled(true);
		
		txtTitle.setBorder(new LineBorder(Color.black,1));
		txtPrice.setBorder(new LineBorder(Color.black,1));
		txtAvailable.setBorder(new LineBorder(Color.black,1));
		
		JLabel lblNewLabel = new JLabel("WARNING contains ");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel.setBounds(674, 325, 114, 13);
		add(lblNewLabel);
		
		JTextField txtIngredient = new JTextField();
		txtIngredient.setBackground(null);
		txtIngredient.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtIngredient.setBounds(791, 322, 114, 19);
		add(txtIngredient);
		txtIngredient.setColumns(10);
				
		add(lblSave);
		
		MouseListener ms = new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getSource().equals(lblBack)) {
					hideMe();
					parent.changePanel(new StoreGUI(parent,user));
				}
				else if(e.getSource().equals(lblImg)){
					//opens a file chooser to select an image
					JFileChooser chooser = new JFileChooser();
				    FileNameExtensionFilter filter = new FileNameExtensionFilter(
				        "JPG, PNG & GIF Images", "jpg", "gif", "png");
				    chooser.setFileFilter(filter);
				    int returnVal = chooser.showOpenDialog(parent);
				    if(returnVal == JFileChooser.APPROVE_OPTION) {
				            File selected = chooser.getSelectedFile();
				    }
				}
				else if (e.getSource().equals(lblSave)) {
					//checks if he pharmacist filled all the fields.
					if(!(txtTitle.getText().isEmpty() || txtPrice.getText().isEmpty() || txtAvailable.getText().isEmpty())){
						//creates a new object Product
						Product pr = new Product(Integer.valueOf(txtId.getText()),Float.valueOf(txtPrice.getText()), txtTitle.getText(), Integer.valueOf(txtAvailable.getText()), "", textArea.getText(),txtIngredient.getText());
						pr.addProduct();
						
						txtTitle.setEditable(false);
						txtPrice.setEditable(false);
						txtAvailable.setEditable(false);
						textArea.setEditable(false);
						parent.changePanel(new StoreGUI(parent,user));
					}
					else {
						JOptionPane.showMessageDialog(parent,"please fill out all required fields","Unsuccesful",JOptionPane.WARNING_MESSAGE);  
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
		
		lblBack.addMouseListener(ms);
		lblSave.addMouseListener(ms);
		lblImg.addMouseListener(ms);
		
		try {
			//changes the dimensions of the image
			ImageIcon originalIcon = new ImageIcon();
			Image originalImage = originalIcon.getImage();
			Image modImage = originalImage.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
						
			JLabel lblImage = new JLabel();
			lblImage.setBounds(25, 10, 150, 150);
			lblImage.setIcon(new ImageIcon(modImage));
			lblImg.setIcon(new ImageIcon(modImage));
		}catch(Exception e) {
				System.out.println(e);
		}
		lblImg.setBorder(new LineBorder(Color.black,1));
		
		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_1.setBounds(674, 198, 45, 13);
		add(lblNewLabel_1);
		
		txtId = new JTextField();
		txtId.setFont(new Font("Times New Roman", Font.BOLD, 15));
		txtId.setBackground(null);
		txtId.setBounds(721, 196, 96, 19);
		add(txtId);
		txtId.setColumns(10);
		
	}
	public void hideMe() {
		this.setVisible(false);
	}
}
