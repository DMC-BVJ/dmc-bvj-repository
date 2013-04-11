package mk.bvj.dmc.model;

import java.util.List;

/**
 * One Session from the input.
 */
public class Session {

  private int sessionId;
  private String order;
  private List<Transaction> transactions;
  
  /**
   * @return the sessionId
   */
  public int getSessionId() {
    return sessionId;
  }
  
  /**
   * @param sessionId the sessionId to set
   */
  public void setSessionId(int sessionId) {
    this.sessionId = sessionId;
  }
  
  /**
   * @return the order
   */
  public String getOrder() {
    return order;
  }
  
  /**
   * @param order the order to set
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
   * @param transactions the transactions to set
   */
  public void setTransactions(List<Transaction> transactions) {
    this.transactions = transactions;
  }
  
}
