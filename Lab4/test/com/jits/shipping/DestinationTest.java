package com.jits.shipping;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DestinationTest {
	private Destination d;
	
	@Before
	public void setUp() throws Exception {
		d = new Destination("12 Main St.", "12345");
	}

	@Test
	public void testScanParcel() {
		
		String expected = d.scanParcel(987654567654567L);
		
		assertEquals(true, expected.contains("988"));
		assertEquals(true, expected.contains("987654567654567"));
	}

}
