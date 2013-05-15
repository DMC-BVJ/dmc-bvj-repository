package mk.bvj.dmc.model;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Model class representing the vector used for training the classifier.
 * This model contains the features from the input and can include additional derived features.
 */
public class VectorModel {

  private static Map<String, Double> minValuesRegistered = new HashMap<String, Double>();
  private static Map<String, Double> maxValuesRegistered = new HashMap<String, Double>();
  
  private static Map<String, Double> minValuesUnregistered = new HashMap<String, Double>();
  private static Map<String, Double> maxValuesUnregistered = new HashMap<String, Double>();
  static {
    minValuesRegistered.put("duration", new Double(0));
    maxValuesRegistered.put("duration", new Double(21553.323));
    minValuesRegistered.put("cCount", new Double(0));
    maxValuesRegistered.put("cCount", new Double(200));
    minValuesRegistered.put("cMinPrice", new Double(0));
    maxValuesRegistered.put("cMinPrice", new Double(2799.99));
    minValuesRegistered.put("cMaxPrice", new Double(0));
    maxValuesRegistered.put("cMaxPrice", new Double(6999.99));
    minValuesRegistered.put("cSumPrice", new Double(0));
    maxValuesRegistered.put("cSumPrice", new Double(76239.34));
    minValuesRegistered.put("bCount", new Double(0));
    maxValuesRegistered.put("bCount", new Double(108));
    minValuesRegistered.put("bMinPrice", new Double(0));
    maxValuesRegistered.put("bMinPrice", new Double(6999.99));
    minValuesRegistered.put("bMaxPrice", new Double(0));
    maxValuesRegistered.put("bMaxPrice", new Double(6999.99));
    minValuesRegistered.put("bSumPrice", new Double(0));
    maxValuesRegistered.put("bSumPrice", new Double(10429.83));
    minValuesRegistered.put("maxValue", new Double(0));
    maxValuesRegistered.put("maxValue", new Double(50000));
    minValuesRegistered.put("customerScore", new Double(0));
    maxValuesRegistered.put("customerScore", new Double(638));
    minValuesRegistered.put("accountLifetime", new Double(0));
    maxValuesRegistered.put("accountLifetime", new Double(600));
    minValuesRegistered.put("payments", new Double(0));
    maxValuesRegistered.put("payments", new Double(868));
    minValuesRegistered.put("age", new Double(0));
    maxValuesRegistered.put("age", new Double(99));
    minValuesRegistered.put("lastOrder", new Double(0));
    maxValuesRegistered.put("lastOrder", new Double(738));
    
    minValuesUnregistered.put("duration", new Double(0));
    maxValuesUnregistered.put("duration", new Double(21580.092));
    minValuesUnregistered.put("cCount", new Double(0));
    maxValuesUnregistered.put("cCount", new Double(200));
    minValuesUnregistered.put("cMinPrice", new Double(0));
    maxValuesUnregistered.put("cMinPrice", new Double(5999.99));
    minValuesUnregistered.put("cMaxPrice", new Double(0));
    maxValuesUnregistered.put("cMaxPrice", new Double(5999.99));
    minValuesUnregistered.put("cSumPrice", new Double(0));
    maxValuesUnregistered.put("cSumPrice", new Double(115742));
    minValuesUnregistered.put("bCount", new Double(0));
    maxValuesUnregistered.put("bCount", new Double(62));
    minValuesUnregistered.put("bMinPrice", new Double(0));
    maxValuesUnregistered.put("bMinPrice", new Double(5999.99));
    minValuesUnregistered.put("bMaxPrice", new Double(0));
    maxValuesUnregistered.put("bMaxPrice", new Double(5999.99));
    minValuesUnregistered.put("bSumPrice", new Double(0));
    maxValuesUnregistered.put("bSumPrice", new Double(23116.88));
  }
  
  private final static String DELIMITER = ",";
  private boolean registered = false;

  private int sessionNo;
  
  private int startHour;
  private int startWeekday;
  private double duration; 
  
  private int cCount;
  private double cMinPrice;
  private double cMaxPrice;
  private double cSumPrice;
  
  private int bCount;
  private double bMinPrice;
  private double bMaxPrice;
  private double bSumPrice;
  
  private String bStep;
  private String onlineStatus;
  private String availability;
  
  private String customerId;
  private int maxVal;
  private int customerScore;
  private int accountLifetime;
  private int payments;
  private int age;
  private String address;
  private int lastOrder;
  
  private int order;

  /**
   * Get the vector as string.
   * 
   * @param withOutcome if true, the outcome will be added to the vector 
   * @return the vector as string
   */
  public String getVectorAsString(boolean withOutcome) {
    String vector = this.toString();
    
    if (withOutcome) {
      vector += DELIMITER + order;
    }
    
    return vector;
  }
  
  /**
   * Get the vector as array with values.
   * 
   * @param withOutcome if true, the outcome will be added to the vector 
   * @return the vector as string
   */
  public double[] getVectorAsValueArray(boolean withOutcome, boolean normalized) {
    List<Double> vectorValues = new ArrayList<Double>();
    
    vectorValues.add(normalizeValue("duration", duration, normalized));
    vectorValues.add(normalizeValue("cCount", cCount, normalized));
    vectorValues.add(normalizeValue("cMinPrice", cMinPrice, normalized));
    vectorValues.add(normalizeValue("cMaxPrice", cMaxPrice, normalized));
    vectorValues.add(normalizeValue("cSumPrice", cSumPrice, normalized));
    vectorValues.add(normalizeValue("bCount", bCount, normalized));
    vectorValues.add(normalizeValue("bMinPrice", bMinPrice, normalized));
    vectorValues.add(normalizeValue("bMaxPrice", bMaxPrice, normalized));
    vectorValues.add(normalizeValue("bSumPrice", bSumPrice, normalized));
    
    // bstep
    vectorValues.add("bStep_missing".equals(bStep) ? new Double(1) : new Double(0));
    vectorValues.add("bStep_1".equals(bStep) ? new Double(1) : new Double(0));
    vectorValues.add("bStep_2".equals(bStep) ? new Double(1) : new Double(0));
    vectorValues.add("bStep_3".equals(bStep) ? new Double(1) : new Double(0));
    vectorValues.add("bStep_4".equals(bStep) ? new Double(1) : new Double(0));
    vectorValues.add("bStep_5".equals(bStep) ? new Double(1) : new Double(0));
    
    // online status
    vectorValues.add("online_missing".equals(onlineStatus) ? new Double(1) : new Double(0));
    vectorValues.add("online_yes".equals(onlineStatus) ? new Double(1) : new Double(0));
    vectorValues.add("online_no".equals(onlineStatus) ? new Double(1) : new Double(0));

    if (registered) {
      vectorValues.add(normalizeValue("maxValue", maxVal, normalized));
      vectorValues.add(normalizeValue("customerScore", customerScore, normalized));
      vectorValues.add(normalizeValue("accountLifetime", accountLifetime, normalized));
      vectorValues.add(normalizeValue("payments", payments, normalized));
      vectorValues.add(normalizeValue("age", age, normalized));
    
      // address
      vectorValues.add("address_mr".equals(address) ? new Double(1) : new Double(0));
      vectorValues.add("address_mrs".equals(address) ? new Double(1) : new Double(0));
      vectorValues.add("address_company".equals(address) ? new Double(1) : new Double(0));
      vectorValues.add(normalizeValue("lastOrder", lastOrder, normalized));
    }
  
    if (withOutcome) {
      vectorValues.add(new Double(order));
    }
    
    double[] result = new double[vectorValues.size()];
    int i = 0;
    for (Double value : vectorValues) {
      result[i++] = value.doubleValue();
    }
    return result;
  }
  
  private Double normalizeValue(String name, double value, boolean normalized) {
    if (!normalized) {
      return value;
    }
    
    double minValue = 0;
    double maxValue = 0;
    if (registered) {
      if (minValuesRegistered.containsKey(name)) {
        minValue = minValuesRegistered.get(name);
      }
      if (maxValuesRegistered.containsKey(name)) {
        maxValue = maxValuesRegistered.get(name);
      }
    } else {
      if (minValuesUnregistered.containsKey(name)) {
        minValue = minValuesRegistered.get(name);
      }
      if (maxValuesUnregistered.containsKey(name)) {
        maxValue = maxValuesRegistered.get(name);
      }
    }
    
    double normalizedValue = (value - minValue) / (maxValue - minValue);
    if (normalizedValue < 0) {
      normalizedValue = 0;
    } else if (normalizedValue > 1) {
      normalizedValue = 1;
    }
    
    return new Double(normalizedValue);
  }
  
  /**
   * Get the header for the vector.
   * 
   * @param withOutcome if true, the outcome column name will be added
   * @return header for the vector
   */
  public String getVectorHeader(boolean withOutcome) {
    StringBuffer s = new StringBuffer();
    
    Field[] fields = this.getClass().getDeclaredFields();
    for (int i = 0; i < fields.length - 2; i++) {
      if (fields[i].getName().equals("DELIMITER") ||
          fields[i].getName().equals("registered") ||
          fields[i].getName().equals("minValues") ||
          fields[i].getName().equals("maxValues")) {
        continue;
      }
      
      s.append(fields[i].getName());
      s.append(DELIMITER);
    }
    s.append(fields[fields.length - 2].getName());
    
    if (withOutcome) {
      s.append(DELIMITER);
      s.append(fields[fields.length - 1].getName());
    }
    
    return s.toString();
  }
  
  @Override
  public String toString() {
    DecimalFormatSymbols symbols = new DecimalFormatSymbols();
    symbols.setDecimalSeparator('.');
    DecimalFormat format = new DecimalFormat("#.###", symbols);
    StringBuffer s = new StringBuffer();
    
    s.append(sessionNo);
    s.append(DELIMITER);
    s.append(startHour);
    s.append(DELIMITER);
    s.append(startWeekday);
    s.append(DELIMITER);
    s.append(format.format(duration));
    s.append(DELIMITER);
    s.append(cCount);
    s.append(DELIMITER);
    s.append(format.format(cMinPrice));
    s.append(DELIMITER);
    s.append(format.format(cMaxPrice));
    s.append(DELIMITER);
    s.append(format.format(cSumPrice));
    s.append(DELIMITER);
    s.append(bCount);
    s.append(DELIMITER);
    s.append(format.format(bMinPrice));
    s.append(DELIMITER);
    s.append(format.format(bMaxPrice));
    s.append(DELIMITER);
    s.append(format.format(bSumPrice));
    s.append(DELIMITER);
    s.append(bStep);
    s.append(DELIMITER);
    s.append(onlineStatus);
    s.append(DELIMITER);
    s.append(availability + DELIMITER);
    s.append(customerId);
    s.append(DELIMITER);
    s.append(maxVal);
    s.append(DELIMITER);
    s.append(customerScore);
    s.append(DELIMITER);
    s.append(accountLifetime);
    s.append(DELIMITER);
    s.append(payments);
    s.append(DELIMITER);
    s.append(age);
    s.append(DELIMITER);
    s.append(address);
    s.append(DELIMITER);
    s.append(lastOrder);
    
    return s.toString();
  }

  /**
   * @return the sessionNo
   */
  public int getSessionNo() {
    return sessionNo;
  }

  /**
   * @param sessionNo the sessionNo to set
   */
  public void setSessionNo(int sessionNo) {
    this.sessionNo = sessionNo;
  }

  /**
   * @return the startHour
   */
  public int getStartHour() {
    return startHour;
  }

  /**
   * @param startHour the startHour to set
   */
  public void setStartHour(int startHour) {
    this.startHour = startHour;
  }

  /**
   * @return the startWeekday
   */
  public int getStartWeekday() {
    return startWeekday;
  }

  /**
   * @param startWeekday the startWeekday to set
   */
  public void setStartWeekday(int startWeekday) {
    this.startWeekday = startWeekday;
  }

  /**
   * @return the duration
   */
  public double getDuration() {
    return duration;
  }

  /**
   * @param duration the duration to set
   */
  public void setDuration(double duration) {
    this.duration = duration;
  }

  /**
   * @return the cCount
   */
  public int getcCount() {
    return cCount;
  }

  /**
   * @param cCount the cCount to set
   */
  public void setcCount(int cCount) {
    this.cCount = cCount;
  }

  /**
   * @return the cMinPrice
   */
  public double getcMinPrice() {
    return cMinPrice;
  }

  /**
   * @param cMinPrice the cMinPrice to set
   */
  public void setcMinPrice(double cMinPrice) {
    this.cMinPrice = cMinPrice;
  }

  /**
   * @return the cMaxPrice
   */
  public double getcMaxPrice() {
    return cMaxPrice;
  }

  /**
   * @param cMaxPrice the cMaxPrice to set
   */
  public void setcMaxPrice(double cMaxPrice) {
    this.cMaxPrice = cMaxPrice;
  }

  /**
   * @return the cSumPrice
   */
  public double getcSumPrice() {
    return cSumPrice;
  }

  /**
   * @param cSumPrice the cSumPrice to set
   */
  public void setcSumPrice(double cSumPrice) {
    this.cSumPrice = cSumPrice;
  }

  /**
   * @return the bCount
   */
  public int getbCount() {
    return bCount;
  }

  /**
   * @param bCount the bCount to set
   */
  public void setbCount(int bCount) {
    this.bCount = bCount;
  }

  /**
   * @return the bMinPrice
   */
  public double getbMinPrice() {
    return bMinPrice;
  }

  /**
   * @param bMinPrice the bMinPrice to set
   */
  public void setbMinPrice(double bMinPrice) {
    this.bMinPrice = bMinPrice;
  }

  /**
   * @return the bMaxPrice
   */
  public double getbMaxPrice() {
    return bMaxPrice;
  }

  /**
   * @param bMaxPrice the bMaxPrice to set
   */
  public void setbMaxPrice(double bMaxPrice) {
    this.bMaxPrice = bMaxPrice;
  }

  /**
   * @return the bSumPrice
   */
  public double getbSumPrice() {
    return bSumPrice;
  }

  /**
   * @param bSumPrice the bSumPrice to set
   */
  public void setbSumPrice(double bSumPrice) {
    this.bSumPrice = bSumPrice;
  }

  /**
   * @return the bStep
   */
  public String getbStep() {
    return bStep;
  }

  /**
   * @param bStep the bStep to set
   */
  public void setbStep(String bStep) {
    this.bStep = bStep;
  }

  /**
   * @return the onlineStatus
   */
  public String getOnlineStatus() {
    return onlineStatus;
  }

  /**
   * @param onlineStatus the onlineStatus to set
   */
  public void setOnlineStatus(String onlineStatus) {
    this.onlineStatus = onlineStatus;
  }

  /**
   * @return the availability
   */
  public String getAvailability() {
    return availability;
  }

  /**
   * @param availability the availability to set
   */
  public void setAvailability(String availability) {
    this.availability = availability;
  }

  /**
   * @return the customerId
   */
  public String getCustomerId() {
    return customerId;
  }

  /**
   * @param customerId the customerId to set
   */
  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  /**
   * @return the maxVal
   */
  public int getMaxVal() {
    return maxVal;
  }

  /**
   * @param maxVal the maxVal to set
   */
  public void setMaxVal(int maxVal) {
    this.maxVal = maxVal;
  }

  /**
   * @return the customerScore
   */
  public int getCustomerScore() {
    return customerScore;
  }

  /**
   * @param customerScore the customerScore to set
   */
  public void setCustomerScore(int customerScore) {
    this.customerScore = customerScore;
  }

  /**
   * @return the accountLifetime
   */
  public int getAccountLifetime() {
    return accountLifetime;
  }

  /**
   * @param accountLifetime the accountLifetime to set
   */
  public void setAccountLifetime(int accountLifetime) {
    this.accountLifetime = accountLifetime;
  }

  /**
   * @return the payments
   */
  public int getPayments() {
    return payments;
  }

  /**
   * @param payments the payments to set
   */
  public void setPayments(int payments) {
    this.payments = payments;
  }

  /**
   * @return the age
   */
  public int getAge() {
    return age;
  }

  /**
   * @param age the age to set
   */
  public void setAge(int age) {
    this.age = age;
  }

  /**
   * @return the address
   */
  public String getAddress() {
    return address;
  }

  /**
   * @param address the address to set
   */
  public void setAddress(String address) {
    this.address = address;
  }

  /**
   * @return the lastOrder
   */
  public int getLastOrder() {
    return lastOrder;
  }

  /**
   * @param lastOrder the lastOrder to set
   */
  public void setLastOrder(int lastOrder) {
    this.lastOrder = lastOrder;
  }

  /**
   * @return the order
   */
  public int getOrder() {
    return order;
  }

  /**
   * @param order the order to set
   */
  public void setOrder(int order) {
    this.order = order;
  }

  /**
   * @return the registered
   */
  public boolean isRegistered() {
    return registered;
  }

  /**
   * @param registered the registered to set
   */
  public void setRegistered(boolean registered) {
    this.registered = registered;
  }
  
}
