package mk.bvj.dmc.model;

import java.lang.reflect.Field;
import java.text.DecimalFormat;

/**
 * Model class representing the vector used for training the classifier.
 * This model contains the features from the input and can include additional derived features.
 */
public class VectorModel {
  
  private final static String DELIMITER = "|";

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
  
  private int[] bStep = new int[5];
  private double onlineStatus;
  private int[] availability = new int[7];
  
  private String customerId;
  private int maxVal;
  private int customerScore;
  private int accountLifetime;
  private int payments;
  private int age;
  private int address;
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
   * Ger the header for the vector.
   * 
   * @param withOutcome if true, the outcome column name will be added
   * @return header for the vector
   */
  public String getVectorHeader(boolean withOutcome) {
    StringBuffer s = new StringBuffer();
    
    Field[] fields = this.getClass().getDeclaredFields();
    for (int i = 0; i < fields.length - 2; i++) {
      if (fields[i].getName().equals("DELIMITER")) {
        continue;
      } else if (fields[i].getName().equals("bStep")) {
        s.append("bStep1" + DELIMITER);
        s.append("bStep2" + DELIMITER);
        s.append("bStep3" + DELIMITER);
        s.append("bStep4" + DELIMITER);
        s.append("bStep5" + DELIMITER);
        continue;
      } else if (fields[i].getName().equals("availability")) {
        s.append("availability1" + DELIMITER);
        s.append("availability2" + DELIMITER);
        s.append("availability3" + DELIMITER);
        s.append("availability4" + DELIMITER);
        s.append("availability5" + DELIMITER);
        s.append("availability6" + DELIMITER);
        s.append("availability7" + DELIMITER);
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
    DecimalFormat format = new DecimalFormat("#.###");
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
    s.append(bStep[0]);
    s.append(DELIMITER);
    s.append(bStep[1]);
    s.append(DELIMITER);
    s.append(bStep[2]);
    s.append(DELIMITER);
    s.append(bStep[3]);
    s.append(DELIMITER);
    s.append(bStep[4]);
    s.append(DELIMITER);
    s.append(format.format(onlineStatus));
    s.append(DELIMITER);
    s.append(availability[0] + DELIMITER);
    s.append(availability[1] + DELIMITER);
    s.append(availability[2] + DELIMITER);
    s.append(availability[3] + DELIMITER);
    s.append(availability[4] + DELIMITER);
    s.append(availability[5] + DELIMITER);
    s.append(availability[6] + DELIMITER);
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
  public int[] getbStep() {
    return bStep;
  }

  /**
   * @param bStep the bStep to set
   */
  public void setbStep(int[] bStep) {
    this.bStep = bStep;
  }

  /**
   * @return the onlineStatus
   */
  public double getOnlineStatus() {
    return onlineStatus;
  }

  /**
   * @param onlineStatus the onlineStatus to set
   */
  public void setOnlineStatus(double onlineStatus) {
    this.onlineStatus = onlineStatus;
  }

  /**
   * @return the availability
   */
  public int[] getAvailability() {
    return availability;
  }

  /**
   * @param availability the availability to set
   */
  public void setAvailability(int[] availability) {
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
  public int getAddress() {
    return address;
  }

  /**
   * @param address the address to set
   */
  public void setAddress(int address) {
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
  
}
