package mk.bvj.dmc.model;

import org.junit.Test;

/**
 * Test for VectorModel class.
 */
public class VectorModelTest {

  @Test
  public void testGetVectorHeader() {
    VectorModel vector = new VectorModel();
    String header = vector.getVectorHeader(true);

    System.out.println(header);
  }

}
