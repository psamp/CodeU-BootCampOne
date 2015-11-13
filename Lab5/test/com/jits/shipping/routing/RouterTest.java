package com.jits.shipping.routing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.jits.shipping.JitsException;
import com.jits.shipping.routing.GroundRouter;

public class RouterTest {
	
	Router router;
	
	@Before
	public void setUp() {
		router = new GroundRouter();
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none(); 

	@Test
	public void testLookUpDistributionCenter() throws JitsException {
		String expectedGA = "DistCtr: DC1 Raleigh ";
		String actualGA = router.locateByZip("30331").location();
		
		String expectedWI = "DistCtr: DC2 Kansas City ";
		String actualWI = router.locateByZip("53004").location();
		
		String expectedID = "DistCtr: DC3 Denver ";
		String actualID = router.locateByZip("85014").location();
		
		String expectedHI = "DistCtr: NA N/A ";
		String actualHI = router.locateByZip("96716").location();
		
		assertEquals(expectedGA, actualGA);
		assertEquals(expectedWI, actualWI);
		assertEquals(expectedID, actualID);
		assertEquals(expectedHI, actualHI);
	}

}
