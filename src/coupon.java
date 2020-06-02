
public class coupon {
	private int id;
	private String code;
	private int product_id;
	private float discount;
	private int minimum_qty;
	private String description;
	/**
	 * @param id
	 * @param code
	 * @param product_id
	 * @param discount
	 * @param minimum_qty
	 * @param description
	 */
	public coupon(int id, String code, int product_id, float discount, int minimum_qty, String description) {
		this.id = id;
		this.code = code;
		this.product_id = product_id;
		this.discount = discount;
		this.minimum_qty = minimum_qty;
		this.description = description;
	}
	
	
}
