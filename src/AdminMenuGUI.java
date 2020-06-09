import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AdminMenuGUI extends JPanel {
	private MainFrame mainFrame;
	private User user;
	/**
	 * Create the panel.
	 */
	public AdminMenuGUI(MainFrame mainFrame, User user) {
		this.mainFrame = mainFrame;
		this.user = user;
		
		setLayout(null);
		
		JLabel lblOrders = new JLabel("");
		lblOrders.setIcon(new ImageIcon(AdminMenuGUI.class.getResource("/icons/orders256.png")));
		lblOrders.setBounds(207, 125, 261, 238);
		add(lblOrders);
		
		JLabel lblNewLabel_1 = new JLabel("Orders");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_1.setBounds(282, 373, 74, 21);
		add(lblNewLabel_1);
		
		MouseListener ms = new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getSource().equals(lblOrders)) {
					mainFrame.changePanel(new AdminOrderHistory(mainFrame, user));
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
		lblOrders.addMouseListener(ms);

	}	
}
