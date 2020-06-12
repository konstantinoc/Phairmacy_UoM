import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddCouponGUI extends JPanel {
	private JTextField txtProductId;
	private JTextField txtDiscount;
	private JTextField txtMin;
	private JTextField txtTitle;
	private JTextField txtPoints;
	private JTextField txtCode;

	/**
	 * Create the panel.
	 * pharmacist can add coupon though this panel.
	 */
	public AddCouponGUI(MainFrame mainFrame) {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Product ID");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel.setBounds(587, 140, 111, 13);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Discount");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_1.setBounds(587, 177, 111, 13);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Minimum Qty");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_2.setBounds(587, 219, 111, 13);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Title");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_3.setBounds(587, 252, 111, 13);
		add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Points");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_4.setBounds(587, 289, 111, 13);
		add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Code");
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_5.setBounds(587, 331, 111, 13);
		add(lblNewLabel_5);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnConfirm.setBounds(612, 373, 186, 39);
		add(btnConfirm);
		
		txtProductId = new JTextField();
		txtProductId.setBounds(702, 138, 96, 19);
		add(txtProductId);
		txtProductId.setColumns(10);
		
		txtDiscount = new JTextField();
		txtDiscount.setColumns(10);
		txtDiscount.setBounds(702, 175, 96, 19);
		add(txtDiscount);
		
		txtMin = new JTextField();
		txtMin.setColumns(10);
		txtMin.setBounds(702, 217, 96, 19);
		add(txtMin);
		
		txtTitle = new JTextField();
		txtTitle.setColumns(10);
		txtTitle.setBounds(702, 250, 96, 19);
		add(txtTitle);
		
		txtPoints = new JTextField();
		txtPoints.setColumns(10);
		txtPoints.setBounds(702, 287, 96, 19);
		add(txtPoints);
		
		txtCode = new JTextField();
		txtCode.setColumns(10);
		txtCode.setBounds(702, 329, 96, 19);
		add(txtCode);
		
		MouseListener ms = new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				//checks if all the fields filled.
				if(txtCode.getText().isEmpty() || txtProductId.getText().isEmpty() || txtDiscount.getText().isEmpty() || 
						txtMin.getText().isEmpty() || txtTitle.getText().isEmpty()) {
					JOptionPane.showMessageDialog(mainFrame, "Please fill all field", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else {
					//creates new a new object coupons  
					coupon c = new coupon(txtCode.getText(), Integer.valueOf(txtProductId.getText()), Float.valueOf(txtDiscount.getText()), 
							Integer.valueOf(txtMin.getText()), txtTitle.getText(), Integer.valueOf(txtPoints.getText()));
					int r = c.addCoupon();
					//checks if the code is already exist.
					if(r==1)
						JOptionPane.showMessageDialog(mainFrame, "Coupon added succesful", "Succesful", JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(mainFrame, "Code already exist use somthing else!", "Error", JOptionPane.ERROR_MESSAGE);
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
		btnConfirm.addMouseListener(ms);
	}
}
