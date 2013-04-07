package mk.bvj.dmc.interfaces;

/**
 * @author Boris
 *
 */
public interface Reviewer {

	/**
	 * For a transaction returns the probability predicting if it leads to an order.
	 * @param transaction The transaction
	 * @return value between [0,1]
	 */
	public double submitTransaction(String[] transaction);
	
	/**
	 * Called up after the last transaction of a session. The passed parameter is a string with
	 * the value “y” if the last session led to a purchase, and with the value “n” if not
	 * @param result
	 */
	public void submitOutcome(String result);
}
