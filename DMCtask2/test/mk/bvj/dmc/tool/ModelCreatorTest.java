package mk.bvj.dmc.tool;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import mk.bvj.dmc.learner.TransactionsReader;
import mk.bvj.dmc.model.Session;
import mk.bvj.dmc.model.VectorModel;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test the creation of vector models.
 */
public class ModelCreatorTest {

  @Test
  public void testModelCreation() throws IOException {
    // create sessions first
    TransactionsReader reader = new TransactionsReader();
    String fileName = "C:/Users/vladimir/Desktop/DMC2013_task/transact_class.txt";
    Map<Long, Session> sessions = reader.run(fileName);
    
    // run the model creator 
    ModelCreator modelCreator = new ModelCreator();
    Map<Long, VectorModel> models = modelCreator.createVectorModels(sessions, true);
    assertEquals(5111, models.size());
    
    // save the models to output file 
    String outputFileName = "C:/Users/vladimir/Desktop/DMC2013_task/vectors_toclassify_unreg_nomissing.txt";
    PrintWriter writer = new PrintWriter(outputFileName);
    
    boolean printHeader = true;
    for (Map.Entry<Long, VectorModel> entry : models.entrySet()) {
      VectorModel vector = entry.getValue();
      if (printHeader) {
        writer.println(vector.getVectorHeader(false));
        printHeader = false;
      }
      
      if (!vector.isRegistered()) {
        writer.println(vector.getVectorAsString(false));
      }
    }
    writer.flush();
    writer.close();
  }

}
