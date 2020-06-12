import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.JOptionPane;

public class PurchaseWithMemberCard {
	private User user;
	private PurchaseWithMemberCardGUI gui;
	private PaymentGUI pmnt;
	private float[] total;
	
	public PurchaseWithMemberCard(User user, PurchaseWithMemberCardGUI gui) {
		this.user = user;	
		this.gui = gui;
	}

	public User getUser() {
		return user;
	}

	
	public float discountFromCoupon(String code) {
		float disc = 0;
		DB_Connection objDB = new DB_Connection();
		Connection connection = objDB.get_connection();
		PreparedStatement ps = null;
		try {
			String query = "SELECT * FROM coupons WHERE c_code = \"" + code + "\";";
			ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			int flag = 1;
			while(rs.next() && flag == 1) {
				for(int i=0; i<user.getCart().getProductList().size(); i++) {
					Product current = user.getCart().getProductList().get(i);
					if(current.getId() == rs.getInt("product_id")) {
						if(user.getCart().getQtyList().get(i) >= rs.getInt("minimum_qty")) {
							disc += current.getPrice() * user.getCart().getQtyList().get(i) * rs.getFloat("discount");
						}
						else {
							int confirm = JOptionPane.showConfirmDialog(gui,"To use this coupon for " + current.getName() + " you have to add in cart at least " + rs.getInt("minimum_qty") + " pieces\n Do you want to go back to cart?");  
							if(confirm == JOptionPane.YES_OPTION){  
								gui.getMainFrame().changePanel(new CartGUI(gui.getMainFrame(),user));
								flag = 0;
							}
						}
						break;
					}
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return disc;
	}
	
	public boolean codeIsValid(String code) {
		DB_Connection objDB = new DB_Connection();
		Connection connection = objDB.get_connection();
		PreparedStatement ps = null;
		try {
			String query = "SELECT * FROM coupons WHERE c_code = \"" + code + "\";";
			ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next() == false)
				return false;
			query = "SELECT * FROM customer_coupon WHERE customer_id = " + user.getId() + " AND coupon_id = " + rs.getInt("id")  + ";";
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			if (rs.next() == false) {
				JOptionPane.showMessageDialog(gui, "You dont own this coupon. You can get it at coupon tab in your Profile.", "Error", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			return true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public float[] calculateTotal(boolean isRedeemPoints, boolean isDelivery, String code) {
		float[] total= {0,0,0,0,0};
		total[0] = user.getCart().calculateTotal();
		if(codeIsValid(code))
			total[2] = discountFromCoupon(code);
		else {
			total[2] = (float) 0.00;
		}
		if(isRedeemPoints)
			total[1] = calculateDiscoundFromPoints(total[2]);
		if(isDelivery) {
			if (user.getSub() == 0)
				total[3] = (float) 2.50;
			else {
				total[3] = (float) 0.00;
			}
		}
			
		
		total[4] = (float)(total[0] - total[1] - total[2] + total[3]);
		this.total = total;
		return total;
	}
	
	public float calculateDiscoundFromPoints(float disc) {
		float total = user.getCart().calculateTotal();
		int usedPoints = (int) ((total - disc)  / 0.10);
		return (float) (usedPoints * 0.10);
	}

	public void callPaymentGui() {
		this.pmnt = new PaymentGUI(this);
		gui.getMainFrame().changePanel(pmnt);
	}
	
	public void checkout() {
		Cart cart = user.getCart();
		for(int i=0; i<cart.cartSize(); i++) {
			Product currentProduct = cart.getProductList().get(i);
			int currentQty = cart.getQtyList().get(i);
			
			boolean has_allergie = false;
			for(String a:user.getAllergies()) {
				if(a.equals(currentProduct.getIngredient())) {
					has_allergie = true;
					break;
				}
			}
			int confirm = 0 ;
			if(has_allergie){
				confirm = JOptionPane.showConfirmDialog(gui,"You have an allergie to the product " + currentProduct.getName() + "\nDo you want to continue? NOT RECOMMENDED", "",JOptionPane.ERROR_MESSAGE);  
			}
			if((confirm == JOptionPane.YES_OPTION) || (!has_allergie)){
				if(currentProduct.getQty() < currentQty) {
					JOptionPane.showMessageDialog(gui, "ORDER UNSUCCESFUL\nNo enough quantity in stock!\nAvailable:" + currentProduct.getQty(),"Unsuccesful operation", JOptionPane.ERROR_MESSAGE);
					gui.getMainFrame().changePanel(new CartGUI(gui.getMainFrame(),user));
					break;
				}
				else {
					if(user.getPostal().startsWith("54")) {
						if(gui.getRdbtnCard().isSelected() || gui.getRdbtnCash().isSelected()) {
							if(gui.getRdbtnCard().isSelected())
								callPaymentGui();
							completeOrder();
						}else {
							JOptionPane.showMessageDialog(gui, "Please choose payment method", "", JOptionPane.WARNING_MESSAGE);
						}
					}else {
						JOptionPane.showMessageDialog(gui, "We dont support delivery at your location.Sorry!", "", JOptionPane.WARNING_MESSAGE);
						gui.getMainFrame().changePanel(new CartGUI(gui.getMainFrame(),user));
					}
				}
			}
			else
				gui.getMainFrame().changePanel(new CartGUI(gui.getMainFrame(), user));
		}
	}
	
	public void completeOrder() {
		int result = 1;
		if(gui.getRdbtnCard().isSelected()) {
			result = this.pmnt.getResult(); 
		}
		if (result == 1) {
			Cart cart = user.getCart();
			
			Date d = new Date();  
		    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
		    String date = formatter.format(d);
			
		    Order order = null;
		    int id = 0;
		    
			int pmntMethod = 0;
			if(gui.getRdbtnCard().isSelected()) {
				pmntMethod = 1;
			}
			int delivery = 0;
			if(gui.getCkDelivery().isSelected()) {
				delivery = 1;
			}
			for (int i=0; i<user.getCart().cartSize(); i++) {
				Product currentProduct = cart.getProductList().get(i); 
				int currentQty = cart.getQtyList().get(i);
				if(i==0) {
					order = new Order(user.getId(), currentProduct.getId(), currentQty, date, currentProduct.getPrice() * currentQty, delivery ,pmntMethod );
					id = order.getId();
				}else {
					order = new Order(id,user.getId(), currentProduct.getId(), currentQty, date, currentProduct.getPrice() * currentQty, delivery ,pmntMethod, 0 );
				}
					
				order.addOrder();
				
				currentProduct.setQty(currentProduct.getQty() - currentQty);
				currentProduct.editQty();
			}
			user.setPoints((int) (user.getPoints() + (this.total[4] / 1) - (this.total[1] / 0.10)));
			user.updateCustomerData();
			user.getCart().removeAll();
			gui.getMainFrame().RefreshNavMenu();
			try {
				coupon o = new coupon(gui.getTxtCoupon().getText());
				System.out.println("sff " + o.getId());
				o.useCoupon(user.getId());
			}catch(Exception e){
				e.printStackTrace();
			}
			JOptionPane.showMessageDialog(gui, "ORDER SUCCESFUL","Succesful operation", JOptionPane.INFORMATION_MESSAGE);
			gui.getMainFrame().changePanel(new StoreGUI(gui.getMainFrame(),user));
		}
		else if ((result == -1)|| result == 2){
			JOptionPane.showMessageDialog(gui, "ORDER UNSUCCESFUL","Unsuccesful operation", JOptionPane.ERROR_MESSAGE);
			gui.getMainFrame().changePanel(new CartGUI(gui.getMainFrame(),user));
		}
		
	}	
}
