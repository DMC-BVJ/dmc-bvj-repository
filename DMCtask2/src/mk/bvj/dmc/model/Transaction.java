package mk.bvj.dmc.model;

/**
 * One Transaction from the input file.
 */
public class Transaction {

	private Long sessionId;

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

	private int bStep;
	private String onlineStatus;
	private String availability;

	private String customerId;
	private int maxVal;
	private int customerScore;
	private int accountLifetime;
	private int payments;
	private int age;
	private int address;
	private int lastOrder;

	private String order;

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
	 * @return the startHour
	 */
	public int getStartHour() {
		return startHour;
	}

	/**
	 * @param startHour
	 *            the startHour to set
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
	 * @param startWeekday
	 *            the startWeekday to set
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
	 * @param duration
	 *            the duration to set
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
	 * @param cCount
	 *            the cCount to set
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
	 * @param cMinPrice
	 *            the cMinPrice to set
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
	 * @param cMaxPrice
	 *            the cMaxPrice to set
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
	 * @param cSumPrice
	 *            the cSumPrice to set
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
	 * @param bCount
	 *            the bCount to set
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
	 * @param bMinPrice
	 *            the bMinPrice to set
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
	 * @param bMaxPrice
	 *            the bMaxPrice to set
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
	 * @param bSumPrice
	 *            the bSumPrice to set
	 */
	public void setbSumPrice(double bSumPrice) {
		this.bSumPrice = bSumPrice;
	}

	/**
	 * @return the bStep
	 */
	public int getbStep() {
		return bStep;
	}

	/**
	 * @param bStep
	 *            the bStep to set
	 */
	public void setbStep(int bStep) {
		this.bStep = bStep;
	}

	/**
	 * @return the onlineStatus
	 */
	public String getOnlineStatus() {
		return onlineStatus;
	}

	/**
	 * @param onlineStatus
	 *            the onlineStatus to set
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
	 * @param availability
	 *            the availability to set
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
	 * @param customerId
	 *            the customerId to set
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
	 * @param maxVal
	 *            the maxVal to set
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
	 * @param customerScore
	 *            the customerScore to set
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
	 * @param accountLifetime
	 *            the accountLifetime to set
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
	 * @param payments
	 *            the payments to set
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
	 * @param age
	 *            the age to set
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
	 * @param address
	 *            the address to set
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
	 * @param lastOrder
	 *            the lastOrder to set
	 */
	public void setLastOrder(int lastOrder) {
		this.lastOrder = lastOrder;
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

}
