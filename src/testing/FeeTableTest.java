package testing;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import server.logic.tables.FeeTable;

public class FeeTableTest {

FeeTable feeTable;
	
	@Before
	public void creatingFeeTableTest() {
		feeTable = FeeTable.getInstance();
		
	}
	
	@Test
	public void getFeeTableTest() {
		assertEquals(0, feeTable.getFeeTable().get(0).getUserid());
		assertEquals(5, feeTable.getFeeTable().get(0).getFee());
		
	}
	
	@Test
	public void lookupFeeTableTest() {
		assertEquals(true, feeTable.lookup(20));
		
	}
	@Test
	public void lookupFeeTableFailTest() {
		assertEquals(false, feeTable.lookup(0));
		
	}
	
	@Test
	public void CheckUsertest() {
		//pass
		assertEquals(true, feeTable.checkuser(0));
		//pass
		assertEquals(false, feeTable.checkuser(1));
				
	}
	
	@Test
	public void applyfeeTest() {
		feeTable.applyfee(0, 1000000);
		//since (1000000/60000)-overdue + current fee = 16
		assertEquals(16, feeTable.getFeeTable().get(0).getFee());
		
	}

	
}
