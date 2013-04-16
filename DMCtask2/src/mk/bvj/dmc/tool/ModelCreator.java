package mk.bvj.dmc.tool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mk.bvj.dmc.model.Session;
import mk.bvj.dmc.model.Transaction;
import mk.bvj.dmc.model.VectorModel;

/**
 * Create a vector model from each transaction.
 */
public class ModelCreator {

  public Map<Long, VectorModel> createVectorModels(Map<Long, Session> sessions) {
    
    Map<Long, VectorModel> models = new HashMap<Long, VectorModel>();
    
    // for each session create a vector model
    for (Map.Entry<Long, Session> entry : sessions.entrySet()) {
      VectorModel vector = new VectorModel();
      
      Session session = entry.getValue();
      
      if (session.getTransactions().size() == 0) {
        continue;
      }
      
      vector.setSessionNo(entry.getKey().intValue());
      vector.setStartHour(session.getTransactions().get(0).getStartHour());
      vector.setStartWeekday(session.getTransactions().get(0).getStartWeekday());
      vector.setDuration(getDuration(session));
      
      vector.setcCount(getCCount(session));
      vector.setcMinPrice(getCMinPrice(session));
      vector.setcMaxPrice(getCMaxPrice(session));
      vector.setcSumPrice(getCSumPrice(session));
      
      vector.setbCount(getBCount(session));
      vector.setbMinPrice(getBMinPrice(session));
      vector.setbMaxPrice(getBMaxPrice(session));
      vector.setbSumPrice(getBSumPrice(session));
      
      vector.setbStep(getBStep(session));
      vector.setOnlineStatus(getOnlineStatus(session));
      vector.setAvailability(getAvailability(session));
      
      vector.setCustomerId(getCustomerId(session));
      vector.setMaxVal(getMaxVal(session));
      vector.setCustomerScore(getCustomerScore(session));
      vector.setAccountLifetime(getAccountLifetime(session));
      vector.setPayments(getPayments(session));
      vector.setAge(getAge(session));
      vector.setAddress(getAddress(session));
      vector.setLastOrder(getLastOrder(session));
      
      if ("y".equals(session.getTransactions().get(0).getOrder())) {
        vector.setOrder(1);
      } else {
        vector.setOrder(0);
      }
      
      models.put(entry.getKey(), vector);
    }
    
    return models;
  }
  
  /**
   * At the moment, for the duration the value is taken from the last transaction.
   * 
   * @param session .
   * @return duration
   */
  private double getDuration(Session session) {
    List<Transaction> transactions = session.getTransactions();
    return transactions.get(transactions.size() - 1).getDuration();
  }
  
  private int getCCount(Session session) {
    List<Transaction> transactions = session.getTransactions();
    return transactions.get(transactions.size() - 1).getcCount();
  }
  
  /**
   * cMinPrice.
   * Get the minimum value.
   * For missing value set 0
   */
  private double getCMinPrice(Session session) {
    double minPrice = -1;
    
    for (Transaction transaction : session.getTransactions()) {
      if (minPrice < 0 || transaction.getcMinPrice() < minPrice) {
        minPrice = transaction.getcMinPrice();
      }
    }
    
    // handle missing value
    if (minPrice < 0) {
      minPrice = 0;
    }
    
    return minPrice;
  }
  
  /**
   * cMaxPrice.
   * Get the maximum value.
   * For missing value set 0
   */
  private double getCMaxPrice(Session session) {
    double maxPrice = -1;
    
    for (Transaction transaction : session.getTransactions()) {
      if (transaction.getcMaxPrice() >= 0 && transaction.getcMaxPrice() > maxPrice) {
        maxPrice = transaction.getcMaxPrice();
      }
    }
    
    // handle missing value
    if (maxPrice < 0) {
      maxPrice = 0;
    }
    
    return maxPrice;
  }
  
  /**
   * cSumPrice.
   * Get the maximum value.
   * For missing value set 0
   */
  private double getCSumPrice(Session session) {
    double sumPrice = -1;
    
    for (Transaction transaction : session.getTransactions()) {
      if (transaction.getcSumPrice() >= 0 && transaction.getcSumPrice() > sumPrice) {
        sumPrice = transaction.getcSumPrice();
      }
    }
    
    // handle missing value
    if (sumPrice < 0) {
      sumPrice = 0;
    }
    
    return sumPrice;
  }
  
  private int getBCount(Session session) {
    List<Transaction> transactions = session.getTransactions();
    return transactions.get(transactions.size() - 1).getbCount();
  }
  
  /**
   * bMinPrice.
   * Get the minimum value.
   * For missing value set 0
   */
  private double getBMinPrice(Session session) {
    double minPrice = -1;
    
    for (Transaction transaction : session.getTransactions()) {
      if (minPrice < 0 || transaction.getbMinPrice() < minPrice) {
        minPrice = transaction.getbMinPrice();
      }
    }
    
    // handle missing value
    if (minPrice < 0) {
      minPrice = 0;
    }
    
    return minPrice;
  }
  
  /**
   * bMaxPrice.
   * Get the maximum value.
   * For missing value set 0
   */
  private double getBMaxPrice(Session session) {
    double maxPrice = -1;
    
    for (Transaction transaction : session.getTransactions()) {
      if (transaction.getbMaxPrice() >= 0 && transaction.getbMaxPrice() > maxPrice) {
        maxPrice = transaction.getbMaxPrice();
      }
    }
    
    // handle missing value
    if (maxPrice < 0) {
      maxPrice = 0;
    }
    
    return maxPrice;
  }
  
  /**
   * bSumPrice.
   * Get the maximum value.
   * For missing value set 0
   */
  private double getBSumPrice(Session session) {
    double sumPrice = -1;
    
    for (Transaction transaction : session.getTransactions()) {
      if (transaction.getbSumPrice() >= 0 && transaction.getbSumPrice() > sumPrice) {
        sumPrice = transaction.getbSumPrice();
      }
    }
    
    // handle missing value
    if (sumPrice < 0) {
      sumPrice = 0;
    }
    
    return sumPrice;
  }
  
  /**
   * bStep.
   * Get the last not missing value
   * For missing value set middle value (3)
   */
  private int getBStep(Session session) {
    int value = 0;
    
    for (Transaction transaction : session.getTransactions()) {
      if (transaction.getbStep() > 0) {
        value = transaction.getbStep();
      }
    }
    
    // handle missing value
    if (value == 0) {
      value = 3;
    }
    
    return value;
  }
  
  /**
   * onlineStatus.
   * Get the ratio of 'y' relative to all available non missing values (Sum(all yes) / Sum(all yes + all no))
   * For missing value set 0.5
   */
  private double getOnlineStatus(Session session) {
    double value = 0;
    double count = 0;
    
    for (Transaction transaction : session.getTransactions()) {
      if (!"?".equals(transaction.getOnlineStatus())) {
        count ++;
        if ("y".equals(transaction.getOnlineStatus())) {
          value ++;
        }
      }
    }
    
    // handle missing value 
    if (count == 0) {
      value = 0.5d;
    } else {
      value = value / count;
    }
    
    return value;
  }
  
  /**
   * availability.
   * Get the maximum value from a predefined ordering.
   * For missing value set the middle value
   */
  private int getAvailability(Session session) {
    Map<String, Integer> valuesMap = new HashMap<String, Integer>();
    valuesMap.put("completely not orderable", new Integer(1));
    valuesMap.put("completely not determinable", new Integer(2));
    valuesMap.put("mainly not orderable", new Integer(3));
    valuesMap.put("mixed", new Integer(4));
    valuesMap.put("mainly not determinable", new Integer(5));
    valuesMap.put("mainly orderable", new Integer(6));
    valuesMap.put("completely orderable", new Integer(7));
    
    int availability = 0; 
    
    for (Transaction transaction : session.getTransactions()) {
      if (!"?".equals(transaction.getAvailability()) && valuesMap.containsKey(transaction.getAvailability())) {
        int value = valuesMap.get(transaction.getAvailability());
        if (value > availability) {
          availability = value;
        }
      }
    }
    
    // handle missing values
    if (availability == 0) {
      availability = 4; // mixed
    }
    
    return availability;
  }

  /**
   * customerId.
   * Same value everywhere, take the first encountered.
   * For missing value set 0 as string
   */
  private String getCustomerId(Session session) {
    String customerId = "0";
    
    for (Transaction transaction : session.getTransactions()) {
      if (!"?".equals(transaction.getCustomerId())) {
        customerId = transaction.getCustomerId();
        break;
      }
    }
    
    return customerId;
  }
  
  /**
   * maxVal.
   * Same value everywhere, take the first encountered.
   * For missing value set 50000
   */
  private int getMaxVal(Session session) {
    int value = -1;
    
    for (Transaction transaction : session.getTransactions()) {
      if (transaction.getMaxVal() > value) {
        value = transaction.getMaxVal();
        break;
      }
    }
    
    // handle missing values
    if (value < 0) {
      value = 50000;
    }
    
    return value;
  }
  
  /**
   * customerScore.
   * Same value everywhere, take the first encountered.
   * For missing value set 0
   */
  private int getCustomerScore(Session session) {
    int value = -1;
    
    for (Transaction transaction : session.getTransactions()) {
      if (transaction.getCustomerScore() > value) {
        value = transaction.getCustomerScore();
        break;
      }
    }
    
    // handle missing values
    if (value < 0) {
      value = 0;
    }
    
    return value;
  }
  
  /**
   * accountLifetime.
   * Same value everywhere, take the first encountered.
   * For missing value set 0
   */
  private int getAccountLifetime(Session session) {
    int value = -1;
    
    for (Transaction transaction : session.getTransactions()) {
      if (transaction.getAccountLifetime() > value) {
        value = transaction.getAccountLifetime();
        break;
      }
    }
    
    // handle missing values
    if (value < 0) {
      value = 0;
    }
    
    return value;
  }
  
  /**
   * payments.
   * Same value everywhere, take the first encountered.
   * For missing value set 0
   */
  private int getPayments(Session session) {
    int value = -1;
    
    for (Transaction transaction : session.getTransactions()) {
      if (transaction.getPayments() > value) {
        value = transaction.getPayments();
        break;
      }
    }
    
    // handle missing values
    if (value < 0) {
      value = 0;
    }
    
    return value;
  }
  
  /**
   * age.
   * Same value everywhere, take the first encountered.
   * For missing value set 0
   */
  private int getAge(Session session) {
    int value = -1;
    
    for (Transaction transaction : session.getTransactions()) {
      if (transaction.getAge() > value) {
        value = transaction.getAge();
      }
    }
    
    // handle missing values
    if (value < 0) {
      value = 0;
    }
    
    return value;
  }
  
  /**
   * address.
   * Same value everywhere, take the first encountered.
   * For missing value set 0
   */
  private int getAddress(Session session) {
    int value = -1;
    
    for (Transaction transaction : session.getTransactions()) {
      if (transaction.getAddress() > value) {
        value = transaction.getAddress();
        break;
      }
    }
    
    // handle missing values
    if (value < 0) {
      value = 0;
    }
    
    return value;
  }
  
  /**
   * lastOrder.
   * Same value everywhere, take the first encountered.
   * For missing value set 0
   */
  private int getLastOrder(Session session) {
    int value = -1;
    
    for (Transaction transaction : session.getTransactions()) {
      if (transaction.getLastOrder() > value) {
        value = transaction.getLastOrder();
        break;
      }
    }
    
    // handle missing values
    if (value < 0) {
      value = 0;
    }
    
    return value;
  }
  
}
