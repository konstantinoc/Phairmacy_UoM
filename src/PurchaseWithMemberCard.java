import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PurchaseWithMemberCard {
	private User user;
	
	public PurchaseWithMemberCard(User user) {
		this.user = user;		
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
			String query = "SELECT * FROM coupons WHERE code = \"" + code + "\";";
			ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
				
			while(rs.next()) {
				for(int i=0; i<user.getCart().getProductList().size(); i++) {
					Product current = user.getCart().getProductList().get(i);
					if(current.getId().equals(rs.getString("product_id"))) {
						if(user.getCart().getQtyList().get(i) >= rs.getInt("minimum_qty")) {
							disc += current.getPrice() * user.getCart().getQtyList().get(i) * rs.getFloat("discount");
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
			String query = "SELECT * FROM coupons WHERE code = \"" + code + "\";";
			ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next() == false)
				return false;
			return true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public float[] calculateTotal(boolean isRedeemPoints, boolean isDelivery, String code) {
		float[] total= {0,0,0,0,0};
		total[0] = user.getCart().calculateTotal();
		if(isRedeemPoints)
			total[1] = (float) 2.50;
		if(codeIsValid(code))
			total[2] = discountFromCoupon(code);
		else
			total[2] = (float) 0.00;
		if(isDelivery)
			total[3] = (float) 2.50;
		
		total[4] = total[0] - total[1] - total[2] + total[3];
		return total;
	}
}
