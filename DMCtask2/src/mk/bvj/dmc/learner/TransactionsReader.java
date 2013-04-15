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
		// skip first line (header)
		br.readLine();
		while((line = br.readLine()) != null){
			Transaction transaction = parseTransaction(line);
			Session session = sessions.get(transaction.getSessionId()); 
			if (session == null) {
				session = new Session();
				session.setSessionId(transaction.getSessionId());
				session.setOrder(transaction.getOrder());
				sessions.put(session.getSessionId(), session);
			}
			session.addTransaction(transaction);
		}
		
		br.close();
		return sessions;
	}

	private Transaction parseTransaction(String line) {
		String[] vals = line.split("\\|");
		Transaction transaction = new Transaction();
		transaction.setSessionId(Long.valueOf(vals[0].trim()));
		transaction.setStartHour(Integer.valueOf(vals[1].trim()));
		transaction.setStartWeekday(Integer.valueOf(vals[2].trim()));
		transaction.setDuration(Double.valueOf(vals[3].trim()));
		transaction.setcCount(Integer.valueOf(vals[4].trim()));
		Double cMinPrice = vals[5].trim().equals("?") ? -1 : Double.valueOf(vals[5].trim());
		transaction.setcMinPrice(cMinPrice);
		Double cMaxPrice = vals[6].trim().equals("?") ? -1 : Double.valueOf(vals[6].trim());
		transaction.setcMaxPrice(cMaxPrice);
		Double cSumPrice = vals[7].trim().equals("?") ? -1 : Double.valueOf(vals[7].trim());
		transaction.setcSumPrice(cSumPrice);
		transaction.setbCount(Integer.valueOf(vals[8].trim()));
		Double bMinPrice = vals[9].trim().equals("?") ? -1 : Double.valueOf(vals[9].trim());
		transaction.setbMinPrice(bMinPrice);
		Double bMaxPrice = vals[10].trim().equals("?") ? -1 : Double.valueOf(vals[10].trim());
		transaction.setbMaxPrice(bMaxPrice);
		Double bSumPrice = vals[11].trim().equals("?") ? -1 : Double.valueOf(vals[11].trim());
		transaction.setbSumPrice(bSumPrice);
		Integer bStep = vals[12].trim().equals("?") ? -1 : Integer.valueOf(vals[12].trim());
		transaction.setbStep(bStep);
		transaction.setOnlineStatus(vals[13].trim());
		transaction.setAvailability(vals[14].trim());
		transaction.setCustomerId(vals[15].trim());
		Integer maxVal = vals[16].trim().equals("?") ? -1 : Integer.valueOf(vals[16].trim());
		transaction.setMaxVal(maxVal);
		Integer customerScore = vals[17].trim().equals("?") ? -1 : Integer.valueOf(vals[17].trim());
		transaction.setCustomerScore(customerScore);
		Integer accountLifetime = vals[18].trim().equals("?") ? -1 : Integer.valueOf(vals[18].trim());
		transaction.setAccountLifetime(accountLifetime);
		Integer payments = vals[19].trim().equals("?") ? -1 : Integer.valueOf(vals[19].trim());
		transaction.setPayments(payments);
		Integer age = vals[20].trim().equals("?") ? -1 : Integer.valueOf(vals[20].trim());
		transaction.setAge(age);
		Integer address = vals[21].trim().equals("?") ? -1 : Integer.valueOf(vals[21].trim());
		transaction.setAddress(address);
		Integer lastOrder = vals[22].trim().equals("?") ? -1 : Integer.valueOf(vals[22].trim());
		transaction.setLastOrder(lastOrder);
		String order = vals[23].trim();
		assert(order.equalsIgnoreCase("y") || order.equalsIgnoreCase("n"));
		transaction.setOrder(order);
		
		return transaction;
	}

}
