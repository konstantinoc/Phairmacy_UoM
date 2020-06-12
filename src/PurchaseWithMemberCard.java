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

	// calculate the dicound from coupon
	public float discountFromCoupon(String code) {
		float disc = 0;
		DB_Connection objDB = new DB_Connection();
		Connection connection = objDB.get_connection();
		PreparedStatement ps = null;
		try {
			//checks if the coupon is in coupons table in database.
			String query = "SELECT * FROM coupons WHERE c_code = \"" + code + "\";";
			ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			int flag = 1;
			while(rs.next() && flag == 1) {
				//for all products in cart
				for(int i=0; i<user.getCart().getProductList().size(); i++) {
					Product current = user.getCart().getProductList().get(i);
					//checks if the products in the cart relates with the code.
					if(current.getId() == rs.getInt("product_id")) {
						//checks if the qty of the product in the cart is more than the minimum qty.
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
	
	//checks if the code is valid and if its belong to the user.
	public boolean codeIsValid(String code) {
		DB_Connection objDB = new DB_Connection();
		Connection connection = objDB.get_connection();
		PreparedStatement ps = null;
		try {
			//checks if the coupons is in the coupons table in arraylist.
			String query = "SELECT * FROM coupons WHERE c_code = \"" + code + "\";";
			ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next() == false)
				return false;
			//checks if the coupon belong to the user.
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
	
	//calculates the total cost.
	public float[] calculateTotal(boolean isRedeemPoints, boolean isDelivery, String code) {
		float[] total= {0,0,0,0,0};
		total[0] = user.getCart().calculateTotal();
		if(codeIsValid(code))
			total[2] = discountFromCoupon(code);
		else {
			total[2] = (float) 0.00;
		}
		//if customer selected to redeem his points
		if(isRedeemPoints)
			total[1] = calculateDiscoundFromPoints(total[2]);
		//if customer selected to get the product via delivery.
		if(isDelivery) {
			if (user.getSub() == 0)
				total[3] = (float) 2.50;
			else {
				total[3] = (float) 0.00;
			}
		}
			
		//adds total cost of the cart,deducts the discound from coupon and points and adds the delivery fee.
		total[4] = (float)(total[0] - total[1] - total[2] + total[3]);
		this.total = total;
		return total;
	}
	
	/** 
	 * calculates the discount from delivery.
	 * one point is equal with 0.10 cents
	 */
	public float calculateDiscoundFromPoints(float disc) {
		float total = user.getCart().calculateTotal();
		int usedPoints = (int) ((total - disc)  / 0.10);
		return (float) (usedPoints * 0.10);
	}

	public void callPaymentGui() {
		this.pmnt = new PaymentGUI(this);
		gui.getMainFrame().changePanel(pmnt);
	}
	
	// makes the finals checks
	public void checkout() {
		Cart cart = user.getCart();
		
		// for each product in cart
		for(int i=0; i<cart.cartSize(); i++) {
			Product currentProduct = cart.getProductList().get(i);
			int currentQty = cart.getQtyList().get(i);
			
			boolean has_allergie = false;
			//check if the user has allergy in any product. 
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
					//checks if the user need delivery but the store didnt support delivery to his location.
					if(user.getPostal().startsWith("54") && gui.getCkDelivery().isSelected() || !gui.getCkDelivery().isSelected()) {
						if(gui.getRdbtnCard().isSelected() || gui.getRdbtnCash().isSelected()) {
							//if user select to pay with credit card.
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
		//gets the result of the the payment if customer want to pay with credit card 
		if(gui.getRdbtnCard().isSelected()) {
			result = this.pmnt.getResult(); 
		}
		//if payment completed successfully.
		if (result == 1) {
			Cart cart = user.getCart();
			
			//gets the current date.
			Date d = new Date();  
		    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
		    String date = formatter.format(d);
			
		    Order order = null;
		    int id = 0;
		    
		    //gets the payment method.
			int pmntMethod = 0;
			if(gui.getRdbtnCard().isSelected()) {
				pmntMethod = 1;
			}
			int delivery = 0;
			if(gui.getCkDelivery().isSelected()) {
				delivery = 1;
			}
			//for all products in cart.
			for (int i=0; i<user.getCart().cartSize(); i++) {
				Product currentProduct = cart.getProductList().get(i); 
				int currentQty = cart.getQtyList().get(i);
				/**
				 * for the first product creates an object order creating the new id for the order.
				 * for the next order gets the current id and creates the order.
				 */
				if(i==0) {
					order = new Order(user.getId(), currentProduct.getId(), currentQty, date, currentProduct.getPrice() * currentQty, delivery ,pmntMethod );
					id = order.getId();
				}else {
					order = new Order(id,user.getId(), currentProduct.getId(), currentQty, date, currentProduct.getPrice() * currentQty, delivery ,pmntMethod, 0 );
				}
				
				//adds the order to the database
				order.addOrder();
				
				//update the product qty and updates the databse.
				currentProduct.setQty(currentProduct.getQty() - currentQty);
				currentProduct.editQty();
			}
			/**
			 * adds the points from the purchase to the user
			 * updates the customer's data.
			 * clears the cart.
			 */
			user.setPoints((int) (user.getPoints() + (this.total[4] / 1) - (this.total[1] / 0.10)));
			user.updateCustomerData();
			user.getCart().removeAll();
			gui.getMainFrame().RefreshNavMenu();
			try {
				//if user used a coupon removes it from his available coupons.
				coupon o = new coupon(gui.getTxtCoupon().getText());
				if(total[2] > 0.00)
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
