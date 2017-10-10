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
		assertEquals(1, feeTable.getFeeTable().get(1).getUserid());
		assertEquals(6, feeTable.getFeeTable().get(1).getFee());
		
	}
	
	@Test
	public void lookupFeeTableTest() {
		assertEquals(true, feeTable.lookup(20));
		
	}
	@Test
	public void lookupFeeTableFailTest() {
		assertEquals(false, feeTable.lookup(1));
		
	}
	
	@Test
	public void CheckUsertest() {
		//pass
		assertEquals(true, feeTable.checkuser(0));
		assertEquals(true, feeTable.checkuser(1));
		//pass
		assertEquals(false, feeTable.checkuser(2));
				
	}
	
	@Test
	public void applyfeeTest() {
		feeTable.applyfee(1, 1000000);
		//since (1000000/60000)-overdue + current fee = 16
		assertEquals(17, feeTable.getFeeTable().get(1).getFee());
		
	}
	
	@Test
	public void payFineTest() {
		//pass
		assertEquals("success",feeTable.payfine(0));
		//fail
		assertEquals("Borrowing Items Exist",feeTable.payfine(20));
	}
	
}
