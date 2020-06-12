import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PaymentGUI extends JPanel {
	
	private int result;
	private JButton btnSuccesful;
	private JButton btnUnsuccesful;
	private JButton btnCancel;
	private PurchaseWithMemberCard pwmc;
	/**
	 * Create the panel for the payment.
	 * just 3 buttons.
	 */
	public PaymentGUI(PurchaseWithMemberCard pwmc) {
		this.pwmc = pwmc;
		setLayout(null);
		
		btnSuccesful = new JButton("Succesful");
		btnSuccesful.setBounds(628, 132, 193, 23);
		add(btnSuccesful);
		
		btnUnsuccesful = new JButton("Unsucceful");
		btnUnsuccesful.setBounds(628, 168, 193, 23);
		add(btnUnsuccesful);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(628, 202, 193, 23);
		add(btnCancel);
		
		ActionListener al = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setResult(e);
			}
			
		};
		
		btnSuccesful.addActionListener(al);
		btnUnsuccesful.addActionListener(al);
		btnCancel.addActionListener(al);
	}
	
	//returns which button user pressed
	public void setResult(ActionEvent e) {
		if (e.getSource().equals(btnSuccesful)) {
			this.result = 1;
		}
		else if (e.getSource().equals(btnUnsuccesful)) {
			this.result = -1;
		}
		else if (e.getSource().equals(btnCancel)) {
			this.result = 2;
		}
		pwmc.completeOrder();

	}
	
	public int getResult() {
		return this.result;
	}
}
