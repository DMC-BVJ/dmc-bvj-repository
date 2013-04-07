package mk.bvj.dmc.tool;

import static org.junit.Assert.*;

import mk.bvj.dmc.interfaces.Reviewer;

import org.junit.Test;

/**
 * @author Boris
 * Test class for DMCTool
 */
public class DMCToolTest {

	@Test
	public void testReviewerInterfaceImplemented() {
		assertTrue(new DMCTool() instanceof Reviewer);
	}

}
