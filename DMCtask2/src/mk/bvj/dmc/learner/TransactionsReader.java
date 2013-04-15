package mk.bvj.dmc.learner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import mk.bvj.dmc.model.Session;
import mk.bvj.dmc.model.Transaction;

public class TransactionsReader{
	
	public Map<Long, Session> run(String filename) throws IOException {
		Map<Long, Session> sessions = new HashMap<Long, Session>();
		
		File file = new File(filename);
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = null;
		while((line = br.readLine()) != null){
			Transaction transaction = parseTransaction(line);
			Session session = sessions.get(transaction.getSessionId()); 
			if (session == null) {
				session = new Session();
				session.setSessionId(transaction.getSessionId());
				session.setOrder(transaction.getOrder());
			}
			session.addTransaction(transaction);
		}
		
		return sessions;
	}

	private Transaction parseTransaction(String line) {
		String[] vals = line.split("|");
		Transaction transaction = new Transaction();
		transaction.setSessionId(Long.valueOf(vals[0].trim()));
		transaction.setStartHour(Integer.valueOf(vals[1].trim()));
		transaction.setStartWeekday(Integer.valueOf(vals[2].trim()));
		transaction.setcCount(Integer.valueOf(vals[3].trim()));
		Double cMinPrice = vals[4].trim().equals("?") ? -1 : Double.valueOf(vals[4].trim());
		transaction.setcMinPrice(cMinPrice);
		Double cMaxPrice = vals[5].trim().equals("?") ? -1 : Double.valueOf(vals[5].trim());
		transaction.setcMaxPrice(cMaxPrice);
		Double cSumPrice = vals[6].trim().equals("?") ? -1 : Double.valueOf(vals[6].trim());
		transaction.setcSumPrice(cSumPrice);
		transaction.setbCount(Integer.valueOf(vals[7].trim()));
		Double bMinPrice = vals[8].trim().equals("?") ? -1 : Double.valueOf(vals[8].trim());
		transaction.setbMinPrice(bMinPrice);
		Double bMaxPrice = vals[9].trim().equals("?") ? -1 : Double.valueOf(vals[9].trim());
		transaction.setbMaxPrice(bMaxPrice);
		Double bSumPrice = vals[10].trim().equals("?") ? -1 : Double.valueOf(vals[10].trim());
		transaction.setbSumPrice(bSumPrice);
		Integer bStep = vals[11].trim().equals("?") ? -1 : Integer.valueOf(vals[11].trim());
		transaction.setbStep(bStep);
		transaction.setOnlineStatus(vals[12].trim());
		transaction.setAvailability(vals[13].trim());
		transaction.setCustomerId(vals[14].trim());
		Integer maxVal = vals[15].trim().equals("?") ? -1 : Integer.valueOf(vals[15].trim());
		transaction.setMaxVal(maxVal);
		Integer customerScore = vals[16].trim().equals("?") ? -1 : Integer.valueOf(vals[16].trim());
		transaction.setCustomerScore(customerScore);
		Integer accountLifetime = vals[17].trim().equals("?") ? -1 : Integer.valueOf(vals[17].trim());
		transaction.setAccountLifetime(accountLifetime);
		Integer payments = vals[18].trim().equals("?") ? -1 : Integer.valueOf(vals[18].trim());
		transaction.setPayments(payments);
		Integer age = vals[19].trim().equals("?") ? -1 : Integer.valueOf(vals[19].trim());
		transaction.setAge(age);
		Integer address = vals[20].trim().equals("?") ? -1 : Integer.valueOf(vals[20].trim());
		transaction.setAddress(address);
		Integer lastOrder = vals[21].trim().equals("?") ? -1 : Integer.valueOf(vals[21].trim());
		transaction.setLastOrder(lastOrder);
		String order = vals[22].trim();
		assert(order.equalsIgnoreCase("y") || order.equalsIgnoreCase("n"));
		transaction.setOrder(order);
		
		return transaction;
	}

}
