package com.jits.shipping.routing;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.jits.shipping.JitsException;
import com.jits.shipping.routing.GroundRouter;

public class RouterTest {
	
	@Rule
	public ExpectedException thrown = ExpectedException.none(); 

	@Test
	public void testLookUpDistributionCenter() throws JitsException {
		String expectedGA = "DC1";
		String actualGA = GroundRouter.groundLocationLookup("30331").getDestination();
		
		String expectedWI = "DC2";
		String actualWI = GroundRouter.groundLocationLookup("53004").getDestination();
		
		String expectedID = "DC3";
		String actualID = GroundRouter.groundLocationLookup("85014").getDestination();
		
		String expectedHI = "NA";
		String actualHI = GroundRouter.groundLocationLookup("96716").getDestination();
		
		assertEquals(expectedGA, actualGA);
		assertEquals(expectedWI, actualWI);
		assertEquals(expectedID, actualID);
		assertEquals(expectedHI, actualHI);
	}

}