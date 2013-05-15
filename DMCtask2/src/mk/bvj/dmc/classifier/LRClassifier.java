package mk.bvj.dmc.classifier;

import mk.bvj.dmc.interfaces.DMCClassifier;

/**
 * Logistic Regression classifier.
 */
public class LRClassifier implements DMCClassifier {

  private double[] betaRegistered;
  private double[] betaUnregistered;
  
  /**
   * Constructor.
   * @param betaRegistered 
   */
  public LRClassifier(double[] betaRegistered, double[] betaUnregistered) {
    this.betaRegistered = betaRegistered;
    this.betaUnregistered = betaUnregistered;
  }
  
  @Override
  public double predict(double[] example, boolean registered) {
    double[] weights;
    if (registered) {
      weights = betaRegistered;
    } else {
      weights = betaUnregistered;
    }
    
    double eta = 0.0d;
    int i = 0;
    for (double attributeValue : example) {
      eta += weights[i] * attributeValue;
      i++;
    }
    eta += weights[weights.length - 1];
    double prediction = Math.exp(eta) / (1 + Math.exp(eta));
    
    if (prediction < 0.5) {
      return 0;
    } else {
      return 1;
    }
  }

}
