package testing.unit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


import java.text.SimpleDateFormat;
import java.util.Date;

import server.logic.model.Loan;

public class LoanTest {

	Loan loan;
	Date date;
	SimpleDateFormat format1;
	
	@Before
	public void creatingLoanTest() {
		//loan = new Loan(0,"9998887776665","1");
		date = new Date();
		format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		loan = new Loan(0, "9998887776665", "1", date, "0");
		
		
	}
	
	@Test
	public void ToStringTest() {
		String output = loan.toString();
		assertEquals("[0,9998887776665,1," + format1.format(date) + ",0]",output);
		
	}
	
	//getters
	@Test
	public void loanIDGetterTest() {
		int loanID = loan.getUserid();
		assertEquals(0,loanID);
		
	}
	@Test
	public void ISBNGetterTest() {
		String isbNum = loan.getIsbn();
		assertEquals("9998887776665",isbNum);
		
	}
	@Test
	public void copynumberGetterTest() {
		String copyNum = loan.getCopynumber();
		assertEquals("1",copyNum);
		
	}
	@Test
	public void dateGetterTest() {
		Date loandate = loan.getDate();
		assertEquals(date,loandate);
		
	}
	@Test
	public void renewstateGetterTest() {
		String renew = loan.getRenewstate();
		assertEquals("0",renew);
		
	}
	
	//setters
	//and since we know that the getters work from the above test we can use them here
	@Test
	public void loanIDSetterTest() {
		loan.setUserid(1);
		assertEquals(1,loan.getUserid());
		
	}
	@Test
	public void ISBNSetterTest() {
		loan.setIsbn("9998887776665");
		assertEquals("9998887776665",loan.getIsbn());
		
	}
	@Test
	public void copynumberSetterTest() {
		loan.setCopynumber("2");
		assertEquals("2",loan.getCopynumber());
		
	}
	@Test
	public void dateSetterTest() {
		loan.setDate(date);
		assertEquals(date,loan.getDate());
		
	}
	@Test
	public void renewstateSetterTest() {
		loan.setRenewstate("2");
		assertEquals("2",loan.getRenewstate());
		
	}

}
