package testing;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;


import server.logic.tables.LoanTable;

public class LoanTableTest {

	LoanTable loanTable;
	Date date;
	SimpleDateFormat format1;
	@Before
	public void creatingLoanTableTest() {
		loanTable = LoanTable.getInstance();
		date = new Date();
		format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
	}
	
	@Test
	public void getLoanTableTest() {

		assertEquals(0, loanTable.getLoanTable().get(0).getUserid());
		assertEquals("9781442668584", loanTable.getLoanTable().get(0).getIsbn());
		assertEquals("1", loanTable.getLoanTable().get(0).getCopynumber());
		assertEquals(format1.format(date), format1.format(loanTable.getLoanTable().get(0).getDate()));
		assertEquals("0", loanTable.getLoanTable().get(0).getRenewstate());
		
	}
	
	@Test
	public void lookupLoanTableTest() {
		assertEquals(true, loanTable.lookup("9781442668584","1"));
		
	}
	@Test
	public void lookupLoanTableFailTest() {
		assertEquals(false, loanTable.lookup("1111111111111", "1"));
		assertEquals(false, loanTable.lookup("9781442668584", "2"));
		
	}
	
	@Test
	public void checkLoanTest() {
		assertEquals(true, loanTable.checkLoan("9781442668584", "100"));
		assertEquals(true, loanTable.checkLoan("1111111111111", "1"));
		
		assertEquals(false, loanTable.checkLoan("9781442668584", "1"));
	}
	
	
}
