package testing.unit;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import server.logic.model.Loan;
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
	public void createLoanTest() {
		//pass
		assertEquals("success", loanTable.createloan(4, "9781442668584", "1", new Date()));
		
		//fail
		assertEquals("The Item is Not Available", loanTable.createloan(1, "9781442667181", "1", new Date()));
		assertEquals("ISBN Invalid", loanTable.createloan(0, "1234567890123", "1", new Date()));
		assertEquals("Copynumber Invalid", loanTable.createloan(0, "9781442668584", "0", new Date()));
		assertEquals("Outstanding Fee Exists", loanTable.createloan(0, "9781442668584", "1", new Date()));
		
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
		//pass
		assertEquals(true, loanTable.checkLoan("9781442668584", "100"));
		assertEquals(true, loanTable.checkLoan("1111111111111", "1"));
		//fail
		assertEquals(false, loanTable.checkLoan("9781442668584", "1"));
	}
	
	@Test
	public void checkLoanSingleParameterTest() {
		//pass
		assertEquals(true, loanTable.checkLoan("1111111111111"));
		//fail
		assertEquals(false, loanTable.checkLoan("9781442668584"));
	}
	
	@Test
	public void checkUserTest() {
		//pass
		//this test failed once because the date was off by milliseconds
		//every other time it ran fine
		assertEquals(true, loanTable.checkUser(3));
		//fail
		assertEquals(false, loanTable.checkUser(0));
	}
	
	@Test
	public void checkLimitTest() {
		//pass
		assertEquals(true, loanTable.checkLimit(0));
		assertEquals(true, loanTable.checkLimit(324));
		//fail, need to get to maxed borrowed items
		List<Loan> Loans = loanTable.getLoanTable();
		Loan l1 = new Loan(0,"0000000000000","1",new Date(), "0");
		Loans.add(l1);
		Loan l2 = new Loan(0,"2222222222222","1",new Date(), "0");
		Loans.add(l2);
		Loan l3 = new Loan(0,"3333333333333","1",new Date(), "0");
		Loans.add(l3);
		Loan l4 = new Loan(0,"4444444444444","1",new Date(), "0");
		Loans.add(l4);
		assertEquals(false,loanTable.checkLimit(0));
	}
	
	@Test
	public void lookLimitTest() {
		//pass
		assertEquals(true, loanTable.looklimit(3));
		//fail
		assertEquals(false, loanTable.looklimit(0));
	}
	
	@Test
	public void returnItemTest() {
		List<Loan> Loans = loanTable.getLoanTable();
		assertEquals("success", loanTable.returnItem(Loans.get(loanTable.getLoanTable().size()-1).getUserid(), Loans.get(loanTable.getLoanTable().size()-1).getIsbn(), Loans.get(loanTable.getLoanTable().size()-1).getCopynumber(), new Date()));
		assertEquals("The Loan Does Not Exist", loanTable.returnItem(1, "1111111111111", "1", new Date()));
	}
	
	@Test
	public void renewItemTest() {
		
		List<Loan> Loans = loanTable.getLoanTable();
		assertEquals("success", loanTable.renewal(Loans.get(loanTable.getLoanTable().size()-1).getUserid(), Loans.get(loanTable.getLoanTable().size()-1).getIsbn(), Loans.get(loanTable.getLoanTable().size()-1).getCopynumber(), new Date()));
		
		assertEquals("The loan does not exist", loanTable.renewal(1233,"1111111111111","1",new Date()));
		assertEquals("Outstanding Fee Exists", loanTable.renewal(0,"4444444444444","1",new Date()));
	}
}
