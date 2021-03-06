package com.jits.routing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.jits.shipping.JitsException;

public class RouterTest {
	
	private Router router;
	
	@Before 
	public void setUp() throws Exception {
		router = new Router();
	}
//
//	@Test
//	public void testLookUpDistributionCenter() throws JitsException {
//		String expectedGA = "Raleigh";
//		String actualGA = router.lookUpDistributionCenter("GA").getCity();
//		
//		String expectedWI = "Kansas City";
//		String actualWI = router.lookUpDistributionCenter("WI").getCity();
//		
//		String expectedID = "Denver";
//		String actualID = router.lookUpDistributionCenter("ID").getCity();
//		
//		String expectedHI = "N/A";
//		String actualHI = router.lookUpDistributionCenter("HI").getCity();
//		
//		assertEquals(expectedGA, actualGA);
//		assertEquals(expectedWI, actualWI);
//		assertEquals(expectedID, actualID);
//		assertEquals(expectedHI, actualHI);
//	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none(); 
	
	@Test
	public void testInvalidStateRequest() throws JitsException {

		try {
			router.lookUpDistributionCenter("why");
			throw new JitsException();
			
			
		} catch(JitsException e) {
			System.out.println(e.getMessage());
		}
		
	}

}
