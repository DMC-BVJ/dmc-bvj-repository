package mk.bvj.dmc.learner;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Map;

import mk.bvj.dmc.model.Session;

import org.junit.Test;

public class TransactionsReaderTest {

  @Test
  public void testRun() throws IOException {
    TransactionsReader reader = new TransactionsReader();
    
    String fileName = "C:/Users/vladimir/Desktop/DMC2013_task/transact_train.txt";
    Map<Long, Session> sessions = reader.run(fileName);
    
    assertEquals(50000, sessions.size());
  }

}
