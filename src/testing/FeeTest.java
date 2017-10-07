package testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import server.logic.model.Fee;

public class FeeTest {

	Fee fee;
	
	@Before
	public void creatingfeeTest() {
		fee = new Fee(0,20);
		
	}
	
	@Test
	public void ToStringTest() {
		String output = fee.toString();
		assertEquals("[0,20]",output);
		
	}
	
	//getters
	@Test
	public void feeIDGetterTest() {
		int feeID = fee.getUserid();
		assertEquals(0,feeID);
		
	}
	@Test
	public void feeGetterTest() {
		int cost = fee.getFee();
		assertEquals(20,cost);
		
	}
	
	//setters
	//and since we know that the getters work from the above test we can use them here
	@Test
	public void feeIDSetterTest() {
		fee.setUserid(2);
		assertEquals(2,fee.getUserid());
		
	}
	@Test
	public void feeSetterTest() {
		fee.setFee(30);
		assertEquals(30,fee.getFee());
		
	}
	

}
