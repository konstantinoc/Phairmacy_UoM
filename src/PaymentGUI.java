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
	 * Create the panel.
	 */
	public PaymentGUI(PurchaseWithMemberCard pwmc) {
		this.pwmc = pwmc;
		setLayout(null);
		
		btnSuccesful = new JButton("Succesful");
		btnSuccesful.setBounds(174, 56, 193, 23);
		add(btnSuccesful);
		
		btnUnsuccesful = new JButton("Unsucceful");
		btnUnsuccesful.setBounds(174, 92, 193, 23);
		add(btnUnsuccesful);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(174, 126, 193, 23);
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
	
	public void setResult(ActionEvent e) {
		if (e.getSource().equals(btnSuccesful)) {
			this.result = 1;
		}
		else if (e.getSource().equals(btnUnsuccesful)) {
			this.result = -1;
		}
		else if (e.getSource().equals(btnCancel)) {
			this.result = 0;
		}
		pwmc.completeOrder();
	}
	
	public int getResult() {
		return this.result;
	}
}
