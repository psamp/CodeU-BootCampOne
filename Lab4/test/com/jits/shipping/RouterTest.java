package com.jits.shipping;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.jits.parcel.JitsException;
import com.jits.shipping.GroundRouter;

public class RouterTest {
	
	private GroundRouter router;
	
	@Before 
	public void setUp() throws Exception {
		router = new GroundRouter();
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none(); 

	@Test
	public void testLookUpDistributionCenter() throws JitsException {
		String expectedGA = "DC1";
		String actualGA = router.distributionCenterLookup("30331").getDestination();
		
		String expectedWI = "DC2";
		String actualWI = router.distributionCenterLookup("53004").getDestination();
		
		String expectedID = "DC3";
		String actualID = router.distributionCenterLookup("85014").getDestination();
		
		String expectedHI = "NA";
		String actualHI = router.distributionCenterLookup("96716").getDestination();
		
		assertEquals(expectedGA, actualGA);
		assertEquals(expectedWI, actualWI);
		assertEquals(expectedID, actualID);
		assertEquals(expectedHI, actualHI);
	}

}
