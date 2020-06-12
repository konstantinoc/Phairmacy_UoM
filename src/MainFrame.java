import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;

public class MainFrame extends JFrame {

	private JPanel panel;
	private JPanel panel_1;
	private Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	
	private User user;
	/**
	 * Create the frame.
	 * 
	 */
	public MainFrame(User user) {
		this.user = user;
		
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 800);
		getContentPane().setLayout(null);
		setVisible(true);
				
		panel_1 = new NavMenu();
		panel_1.setBounds(0, 0, (int) screen.getWidth(), 80);
		getContentPane().add(panel_1);
		
		panel = new JPanel();
		panel.setBounds(0, 77, (int) screen.getWidth(), (int) screen.getHeight()-140);
		getContentPane().add(panel);
		panel.setLayout(new CardLayout(0, 0));
		if (user.getIsPharmacist() == 0)
			changePanel(new StoreGUI(this,user));	
		else
			changePanel(new AdminMenuGUI(this, user));
	}
	
	//refreshes the navigation menu
	public void RefreshNavMenu() {
		this.remove(panel_1);
		this.panel_1 = new NavMenu();
		panel_1.setBounds(0, 0, (int) screen.getWidth(), 80);
		getContentPane().add(panel_1);
	}
	public MainFrame getMe() {
		return this;
	}
	
	//changes the 2nd panel of the Frame
	public void changePanel(JPanel child) {
		this.panel.removeAll();
		child.setPreferredSize(new Dimension((int) screen.getWidth(), (int) screen.getHeight()-140));
		this.panel.add(child);
		repaint();
		revalidate();
	}
	
	class NavMenu extends JPanel {
		/**
		 * Create the panel.
		 * is the first panel of the frame
		 * is a navigation menu
		 */
		public NavMenu() {
			setBackground(Color.DARK_GRAY);
			setLayout(null);
			
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			
			JLabel toProfile = new JLabel("");
			toProfile.setIcon(new ImageIcon(NavMenu.class.getResource("/icons/user.png")));
			toProfile.setBounds((int) (screenSize.getWidth()-50)-270, 10, 32, 64);
			add(toProfile);
			
			JLabel toStore = new JLabel("");
			toStore.setIcon(new ImageIcon(NavMenu.class.getResource("/icons/shop.png")));
			toStore.setBounds((int) (screenSize.getWidth()-50)-180, 10, 32, 64);
			add(toStore);
			
			JLabel toCart = new JLabel("");
			toCart.setIcon(new ImageIcon(NavMenu.class.getResource("/icons/cart.png")));
			toCart.setBounds((int) (screenSize.getWidth()-50)-90, 10, 32, 64);
			add(toCart);
			
			JLabel logout = new JLabel("");
			logout.setIcon(new ImageIcon(NavMenu.class.getResource("/icons/logout.png")));
			logout.setBounds((int) (screenSize.getWidth()-50), 10, 32, 64);
			add(logout);
			
			JLabel points = new JLabel(String.valueOf(user.getPoints()));
			points.setIcon(new ImageIcon(NavMenu.class.getResource("/icons/points.png")));
			points.setFont(new Font("SansSerif", Font.BOLD, 15));
			points.setForeground(Color.WHITE);
			points.setBounds((int) (screenSize.getWidth()-50)-400, 10, 80, 64);
			add(points);
			
			JLabel toMenu = new JLabel("");
			toMenu.setIcon(new ImageIcon(NavMenu.class.getResource("/icons/menu.png")));
			toMenu.setBounds((int) (screenSize.getWidth()-50)-270, 10, 50, 64);
			toMenu.setVisible(false);
			add(toMenu);
			
			//pharmacist has different menu from the customer
			if(user.getIsPharmacist() == 1) {
				toCart.setVisible(false);
				toProfile.setBounds((int) (screenSize.getWidth()-50)-90, 10, 32, 64);
				points.setVisible(false);
				toMenu.setVisible(true);
			}
		
			MouseListener mouseListener = new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getSource().equals(logout)) {
						dispose();
						new LoginForm();
					}
					else if(e.getSource().equals(toProfile)){
						changePanel(new ProfileGUI(getMe(), user));
					}
					else if(e.getSource().equals(toStore)) {
						changePanel(new StoreGUI(getMe(),user));
					}
					else if(e.getSource().equals(toCart)) {
						changePanel(new CartGUI(getMe(),user));
					}
					else if(e.getSource().equals(toMenu)) {
						changePanel(new AdminMenuGUI(getMe(),user));
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
					setCursor(new Cursor(Cursor.HAND_CURSOR));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}
				
			};
			toProfile.addMouseListener(mouseListener);
			toCart.addMouseListener(mouseListener);
			toStore.addMouseListener(mouseListener);
			logout.addMouseListener(mouseListener);
			toMenu.addMouseListener(mouseListener);
		}	
	}
}
