package mk.bvj.dmc.interfaces;


/**
 * Classifier interface.
 */
public interface DMCClassifier {

  public double predict(double[] example, boolean registered);
}
