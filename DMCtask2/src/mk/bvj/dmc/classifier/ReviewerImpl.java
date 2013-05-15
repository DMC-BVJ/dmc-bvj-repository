package mk.bvj.dmc.classifier;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import mk.bvj.dmc.interfaces.DMCClassifier;
import mk.bvj.dmc.interfaces.Reviewer;
import mk.bvj.dmc.learner.TransactionsReader;
import mk.bvj.dmc.model.Session;
import mk.bvj.dmc.model.VectorModel;
import mk.bvj.dmc.tool.ModelCreator;

public class ReviewerImpl implements Reviewer {
  
  private double[] registeredModel = 
      new double[]{0.137, -0.157, -0.090, 0.151, -0.035, -0.480, 0.194, -0.022, 0.239, 0.947, 0.938, 0.134, 0.330, -0.187, -1.250, 1.021, -0.161, 0.191, 0.099, -0.021, -0.066, 0.166, -0.049, 0.036, -0.015, 0.008, -0.141, -0.782};
  private double[] unregisteredModel = 
      new double[]{-0.096, -0.187, 0.032, -0.021, 0.057, -0.352, 0.126, 0.082, 0.286, -0.488, 1.310, 0.647, 0.251, -0.149, -1.089, 2.953, -1.217, 0.046, 0.242};
  
  private TransactionsReader transactionsReader = new TransactionsReader();
  private ModelCreator modelCreator = new ModelCreator();
  private DMCClassifier classifier = new LRClassifier(registeredModel, unregisteredModel);
  private StringBuffer buffer = new StringBuffer();

  @Override
  public double submitTransaction(String[] transaction) {
    double prediction = 0;
    
    // add all features in one string
    for (int i = 0; i < transaction.length - 1; i++) {
      buffer.append(transaction[i] + "|");
    }
    buffer.append(transaction[transaction.length - 1]);
    
    // create input stream so the same reader can be used as the first task
    // create sessions (should be only one)
    StringReader stringReader = new StringReader(buffer.toString());
    Map<Long, Session> session;
    try {
      session = transactionsReader.readSessions(stringReader);
    } catch (IOException e) {
      session = new HashMap<Long, Session>();
    } 
    
    // create the vector with all the transformations of the input 
    Map<Long, VectorModel> vectorModel = modelCreator.createVectorModels(session, true);
    if (vectorModel.size() == 1) {
      VectorModel vector = vectorModel.entrySet().iterator().next().getValue();
      double[] vectorAsString = vector.getVectorAsValueArray(false, false);
      
      // run prediction of the vector
      prediction = classifier.predict(vectorAsString, vector.isRegistered());
    }
    
    buffer.append("\n");
    return prediction;
  }

  @Override
  public void submitOutcome(String result) {
    buffer = new StringBuffer();
  }

}
