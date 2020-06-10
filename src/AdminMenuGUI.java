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
		
		JLabel lblSupply = new JLabel("");
		lblSupply.setIcon(new ImageIcon(AdminMenuGUI.class.getResource("/icons/supply.png")));
		lblSupply.setBounds(638, 124, 261, 238);
		add(lblSupply);
		
		JLabel lblNewLabel_1_1 = new JLabel("Supply Report");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(713, 372, 116, 21);
		add(lblNewLabel_1_1);
		
		JLabel lblStore = new JLabel("");
		lblStore.setIcon(new ImageIcon(AdminMenuGUI.class.getResource("/icons/storemng.png")));
		lblStore.setBounds(1049, 125, 261, 238);
		add(lblStore);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Store Management");
		lblNewLabel_1_1_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_1_1_1.setBounds(1117, 373, 155, 21);
		add(lblNewLabel_1_1_1);
		
		MouseListener ms = new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getSource().equals(lblOrders)) {
					mainFrame.changePanel(new AdminOrderHistory(mainFrame, user));
				}
				else if(e.getSource().equals(lblStore)) {
					mainFrame.changePanel(new StoreGUI(mainFrame, user));
				}
				else if (e.getSource().equals(lblSupply)) {
					new Supply(mainFrame);
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
		lblStore.addMouseListener(ms);
		lblSupply.addMouseListener(ms);

	}	
}
