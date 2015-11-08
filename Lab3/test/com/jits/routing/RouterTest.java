package com.jits.routing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.jits.shipping.JitsException;
import com.jits.shipping.Parcel;
import com.jits.shipping.Warehouse;

public class RouterTest {
	
	private Router router;
	
	@Before 
	public void setUp() throws Exception {
		router = new Router();
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none(); 

	@Test
	public void testLookUpDistributionCenter() throws JitsException {
		String expectedGA = "Raleigh";
		String actualGA = router.distributionCenterLookup("GA").getCity();
		
		String expectedWI = "Kansas City";
		String actualWI = router.distributionCenterLookup("WI").getCity();
		
		String expectedID = "Denver";
		String actualID = router.distributionCenterLookup("ID").getCity();
		
		String expectedHI = "N/A";
		String actualHI = router.distributionCenterLookup("HI").getCity();
		
		assertEquals(expectedGA, actualGA);
		assertEquals(expectedWI, actualWI);
		assertEquals(expectedID, actualID);
		assertEquals(expectedHI, actualHI);
	}
	
	@Test
		public void testRouteParcel() throws JitsException {
		Warehouse warehouse = new Warehouse();
		Parcel parcel = warehouse.newParcel("89687676564|GRD|12345|30313|5.09|5|4.45|7.86|&*^89786|XYZ|426 Marietta St.");
		
		router.routeParcel(parcel);
		String expectedOne = "Whse DistCtr: DC1 Raleigh ";
		String actualOne = parcel.getLocation();
		assertEquals(expectedOne, actualOne);
		
		router.routeParcel(parcel);
		String expectedTwo = "Whse DistCtr: DC1 Raleigh Dest: 426 Marietta St. 30313";
		String actualTwo = parcel.getLocation();
		assertEquals(expectedTwo, actualTwo);
	}

}
