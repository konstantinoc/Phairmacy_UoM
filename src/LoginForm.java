import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class LoginForm extends JFrame {

	private JPanel contentPane;
	private final Color firstColor = Color.DARK_GRAY;
	private final Color formColor = new Color(196, 219, 191);
	private final Color btnColor = new Color(23, 128 ,0);
	private final Color color2 = Color.BLACK;
	private final String appName = "Phairmacy";
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new LoginForm();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginForm() {
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 882, 490);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setTitle(appName);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 438, 453);
		panel.setBackground(firstColor);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(new ImageIcon(LoginForm.class.getResource("/icons/logo.png")));
		lblNewLabel.setBounds(41, 55, 343, 341);
		panel.add(lblNewLabel);
		
		changePanel(new Login());
	}
	
	public void changePanel(JPanel panel) {
		panel.setBounds(436, 0, 432, 453);
		contentPane.add(panel);
		repaint();
	}
	
	class Login extends JPanel {
		private JTextField txtUsername;
		private JPasswordField txtPassword;
		private JLabel lblLoginError;
		
		/**
		 * Create the panel.
		 */
		public Login() {
			
			setBackground(formColor);
			setLayout(null);
			
			JLabel lblUsername = new JLabel("Username");
			lblUsername.setForeground(color2);
			lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblUsername.setBounds(45, 86, 67, 13);
			add(lblUsername);
			
			txtUsername = new JTextField("");
			txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txtUsername.setBounds(45, 109, 292, 17);
			txtUsername.setBackground(formColor);
			txtUsername.setForeground(Color.BLACK);
			txtUsername.setBorder(null);
			add(txtUsername);
			txtUsername.setColumns(10);
			
			JSeparator separator = new JSeparator();
			separator.setBounds(45, 127, 292, 7);
			add(separator);
			
			JLabel lblPassword = new JLabel("Password");
			lblPassword.setForeground(color2);
			lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblPassword.setBounds(45, 144, 67, 13);
			add(lblPassword);
			
			txtPassword = new JPasswordField("");
			txtPassword.setBounds(45, 167, 292, 17);
			txtPassword.setBackground(formColor);
			txtPassword.setForeground(Color.BLACK);
			txtPassword.setBorder(null);
			add(txtPassword);
			
			JSeparator separator_1 = new JSeparator();
			separator_1.setBounds(45, 185, 292, 7);
			add(separator_1);
			
			JButton btnLogin = new JButton("Login");
			btnLogin.setBackground(btnColor);
			btnLogin.setForeground(color2);
			btnLogin.setFont(new Font("Tahoma", Font.BOLD, 13));
			btnLogin.setBorder(null);
			btnLogin.setBounds(45, 208, 292, 28);
			add(btnLogin);
			
			JLabel lblDontHaveAn = new JLabel("Don't have an account?");
			lblDontHaveAn.setForeground(color2);
			lblDontHaveAn.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblDontHaveAn.setBounds(139, 246, 118, 13);
			add(lblDontHaveAn);
			
			lblLoginError = new JLabel("");
			lblLoginError.setForeground(Color.RED);
			lblLoginError.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblLoginError.setBounds(45, 185, 292, 13);
			add(lblLoginError);
			
			MouseListener mouseListener = new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getSource().equals(btnLogin))
						lblLoginError.setText(loginUser());
					else if (e.getSource().equals(lblDontHaveAn)) {
						changePanel(new Signup());
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
					if (e.getSource().equals(btnLogin)){
						btnLogin.setBackground(color2);
						btnLogin.setForeground(Color.WHITE);
					}
					else if(e.getSource().equals(lblDontHaveAn)) {
						lblDontHaveAn.setFont(new Font("Tahoma", Font.ITALIC, 11));
					}
					setCursor(new Cursor(Cursor.HAND_CURSOR));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					if(e.getSource().equals(btnLogin)) {
						btnLogin.setBackground(btnColor);
						btnLogin.setForeground(color2);
					}
					else if(e.getSource().equals(lblDontHaveAn)) {
						lblDontHaveAn.setFont(new Font("Tahoma", Font.PLAIN, 11));
					}
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}

			};
			
			btnLogin.addMouseListener(mouseListener);
			lblDontHaveAn.addMouseListener(mouseListener);
		}
		
		public void hideMe() {
			this.setVisible(false);
		}
		
		private String loginUser() {
			DB_Connection objDB = new DB_Connection();
			Connection connection = objDB.get_connection();
			PreparedStatement ps = null;
			try {
				String query = "SELECT id FROM users WHERE username = \"" + txtUsername.getText().trim() + "\" AND password = \"" + txtPassword.getText() + "\";";
				ps = connection.prepareStatement(query);
				ResultSet rs = ps.executeQuery();
				
				if (rs.next() == false) {
					return "Username and password dont match at any entry";
				}
				new MainFrame(new User(rs.getInt("id")));
				dispose();
			}catch (Exception e) {
				System.out.println(e);
			}
			return "Something going wrong! Please try again later";
		}
	}

	class Signup extends JPanel {
		
		private JTextField txtUsername_1;
		private JPasswordField txtPassword_1;
		private JPasswordField txtConfirmPassword_1;
		private JTextField txtEmail_1;
		private JLabel lblUsernameError;
		private JLabel lblPasswordError;
		private JLabel lblNotMatchError;
		private JLabel lblEmailError;
		private JLabel lblError;
		private JButton btnSignup;
		
		private JCheckBox ckbxAgree;
		
		/**
		 * Create the panel.
		 */
		public Signup() {
			setVisible(true);
			setLayout(null);
			setBackground(formColor);
			setBounds(438, 0, 438, 453);
			
			JLabel lblUsername_1 = new JLabel("Username");
			lblUsername_1.setForeground(color2);
			lblUsername_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblUsername_1.setBounds(45, 86, 67, 13);
			add(lblUsername_1);
			
			txtUsername_1 = new JTextField();
			txtUsername_1.setForeground(Color.BLACK);
			txtUsername_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txtUsername_1.setColumns(10);
			txtUsername_1.setBorder(null);
			txtUsername_1.setBackground(formColor);
			txtUsername_1.setBounds(45, 109, 292, 17);
			add(txtUsername_1);
			
			JSeparator separator_2 = new JSeparator();
			separator_2.setBounds(45, 127, 292, 7);
			add(separator_2);
			
			JLabel lblPassword_1 = new JLabel("Password");
			lblPassword_1.setForeground(color2);
			lblPassword_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblPassword_1.setBounds(45, 144, 67, 13);
			add(lblPassword_1);
			
			txtPassword_1 = new JPasswordField();
			txtPassword_1.setForeground(Color.BLACK);
			txtPassword_1.setBorder(null);
			txtPassword_1.setBackground(formColor);
			txtPassword_1.setBounds(45, 167, 292, 17);
			add(txtPassword_1);
			
			JSeparator separator_1_1 = new JSeparator();
			separator_1_1.setBounds(45, 185, 292, 7);
			add(separator_1_1);
			
			JLabel lblPassword_1_1 = new JLabel("Confirm Password");
			lblPassword_1_1.setForeground(color2);
			lblPassword_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblPassword_1_1.setBounds(45, 202, 101, 13);
			add(lblPassword_1_1);
			
			txtConfirmPassword_1 = new JPasswordField();
			txtConfirmPassword_1.setForeground(Color.BLACK);
			txtConfirmPassword_1.setBorder(null);
			txtConfirmPassword_1.setBackground(formColor);
			txtConfirmPassword_1.setBounds(45, 225, 292, 17);
			add(txtConfirmPassword_1);
			
			JSeparator separator_1_1_1 = new JSeparator();
			separator_1_1_1.setBounds(45, 243, 292, 7);
			add(separator_1_1_1);
			
			JLabel lblEmail = new JLabel("Email");
			lblEmail.setForeground(color2);
			lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblEmail.setBounds(45, 260, 67, 13);
			add(lblEmail);
			
			txtEmail_1 = new JTextField();
			txtEmail_1.setForeground(Color.BLACK);
			txtEmail_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txtEmail_1.setColumns(10);
			txtEmail_1.setBorder(null);
			txtEmail_1.setBackground(formColor);
			txtEmail_1.setBounds(45, 282, 292, 17);
			add(txtEmail_1);
			
			JSeparator separator_2_1 = new JSeparator();
			separator_2_1.setBounds(45, 300, 292, 7);
			add(separator_2_1);
			
			btnSignup = new JButton("SignUp");
			btnSignup.setForeground(color2);
			btnSignup.setFont(new Font("Tahoma", Font.BOLD, 13));
			btnSignup.setBorder(null);
			btnSignup.setBackground(btnColor);
			btnSignup.setBounds(45, 356, 292, 28);
			add(btnSignup);
			
			JLabel lblAlreadyHaveAn = new JLabel("Already have an account?");
			lblAlreadyHaveAn.setForeground(color2);
			lblAlreadyHaveAn.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblAlreadyHaveAn.setBounds(132, 394, 159, 13);
			add(lblAlreadyHaveAn);
			
			ckbxAgree = new JCheckBox("I agree to " + appName + " Term of Use and Privacy Policy");
			ckbxAgree.setFont(new Font("Tahoma", Font.PLAIN, 9));
			ckbxAgree.setForeground(Color.BLACK);
			ckbxAgree.setBackground(formColor);
			ckbxAgree.setBounds(41, 313, 296, 21);
			add(ckbxAgree);
			
			
			lblUsernameError = new JLabel("");
			lblUsernameError.setForeground(Color.RED);
			lblUsernameError.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblUsernameError.setBounds(45, 127, 292, 13);
			add(lblUsernameError);
			
			lblPasswordError = new JLabel("");
			lblPasswordError.setForeground(Color.RED);
			lblPasswordError.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblPasswordError.setBounds(45, 185, 292, 13);
			add(lblPasswordError);
			
			lblNotMatchError = new JLabel("");
			lblNotMatchError.setForeground(Color.RED);
			lblNotMatchError.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblNotMatchError.setBounds(45, 243, 292, 13);
			add(lblNotMatchError);
			
			lblEmailError = new JLabel("");
			lblEmailError.setForeground(Color.RED);
			lblEmailError.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblEmailError.setBounds(45, 300, 292, 13);
			add(lblEmailError);
			
			lblError = new JLabel("");
			lblError.setForeground(Color.RED);
			lblError.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblError.setBounds(45, 333, 292, 13);
			add(lblError);
			
			MouseListener mouseListener = new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					if (e.getSource().equals(btnSignup)) {
						signupUser();
					}
					else if (e.getSource().equals(lblAlreadyHaveAn)) {
						changePanel(new Login());
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
					// TODO Auto-generated method stub
					if(e.getSource().equals(btnSignup)) {
						btnSignup.setBackground(color2);
						btnSignup.setForeground(Color.WHITE);
					}
					else if(e.getSource().equals(lblAlreadyHaveAn)) {
						lblAlreadyHaveAn.setFont(new Font("Tahoma", Font.ITALIC, 11));
					}
					setCursor(new Cursor(Cursor.HAND_CURSOR));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					if(e.getSource().equals(btnSignup)) {
						btnSignup.setBackground(btnColor);
						btnSignup.setForeground(color2);
					}
					else if(e.getSource().equals(lblAlreadyHaveAn)) {
						lblAlreadyHaveAn.setFont(new Font("Tahoma", Font.PLAIN, 11));
					}
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}
					
			};
			
			btnSignup.addMouseListener(mouseListener);
			lblAlreadyHaveAn.addMouseListener(mouseListener);
		}
		
		public void hideMe() {
			this.setVisible(false);
		}
		
		private void signupUser() {
			lblUsernameError.setText("");
			lblPasswordError.setText("");
			lblNotMatchError.setText("");
			lblEmailError.setText("");
			lblError.setText("");
			
			if (!txtPassword_1.getText().trim().equals(txtConfirmPassword_1.getText().trim())){
				lblNotMatchError.setText("Those passwords didn't macth. Try again!");
			}
			else if(txtPassword_1.getText().trim().length() < 8) {
				lblPasswordError.setText("Password must at least 8 characters!");
			}
			else if(!txtEmail_1.getText().contains("@")) {
				lblEmailError.setText("Enter a valid email");
			}
			else if(!ckbxAgree.isSelected()) {
				lblError.setText("Please accept Term of Use");
			}
			else {
				
				DB_Connection objDB = new DB_Connection();
				Connection connection = objDB.get_connection();
				PreparedStatement ps = null;
				try {
					String query = "INSERT INTO pharmacy.users (username, password) VALUES ('" + txtUsername_1.getText().trim() + "','" + txtPassword_1.getText().trim() + "');";
					ps = connection.prepareStatement(query);
					ps.executeUpdate();
					
					query = "SELECT id FROM pharmacy.users WHERE username = \""+ txtUsername_1.getText()+"\";";
					ps = connection.prepareStatement(query);
					ResultSet rs = ps.executeQuery();
					
					int id = -1;
					while(rs.next()) {
						id = rs.getInt("id");
					}
					
					query = "INSERT INTO pharmacy.customers (user_id, email, isPharmacist) VALUES (" + id + ",'" + txtEmail_1.getText().trim() + "','" + 0 +"');";
					ps = connection.prepareStatement(query);
					ps.executeUpdate();
					
					new MainFrame(new User(id));
					dispose();
					
				}catch (Exception e) {
					System.out.println(e);
					if (e.toString().contains("username")) {
						lblUsernameError.setText("Username already exist");
					}
					else if (e.toString().contains("email")) {
						lblEmailError.setText("Email already exist");
					}
					
				}
			}
		}
	}

}




