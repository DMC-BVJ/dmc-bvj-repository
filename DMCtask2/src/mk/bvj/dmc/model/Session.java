package mk.bvj.dmc.model;

import java.util.ArrayList;
import java.util.List;

/**
 * One Session from the input.
 */
public class Session {

	private Long sessionId;
	private String order;// boolean?
	private List<Transaction> transactions;

	/**
	 * @return the sessionId
	 */
	public Long getSessionId() {
		return sessionId;
	}

	/**
	 * @param sessionId
	 *            the sessionId to set
	 */
	public void setSessionId(Long sessionId) {
		this.sessionId = sessionId;
	}

	/**
	 * @return the order
	 */
	public String getOrder() {
		return order;
	}

	/**
	 * @param order
	 *            the order to set
	 */
	public void setOrder(String order) {
		this.order = order;
	}

	/**
	 * @return the transactions
	 */
	public List<Transaction> getTransactions() {
		return transactions;
	}

	/**
	 * @param transactions
	 *            the transactions to set
	 */
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	/**
	 * Add a transaction.
	 * @param transaction the transaction
	 */
	public void addTransaction(Transaction transaction) {
		if (transactions == null) {
			transactions = new ArrayList<Transaction>();
		}
		transactions.add(transaction);
	}
}
