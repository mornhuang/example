package cn.hxex.order.exception;

/**
 * @author galaxy
 *
 * Thrown when the order is not placed above a minimum value
 */
public class OrderMinimumAmountException extends Exception {
	/**
	 * Default constructor
	 */
	public OrderMinimumAmountException() {
		super();
	}

	/**
	 * @param s
	 */
	public OrderMinimumAmountException(String s) {
		super(s);
	}
}
