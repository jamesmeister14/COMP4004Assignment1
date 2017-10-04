package testing;

import static org.junit.Assert.*;

import org.junit.Before;

import server.logic.model.Item;

import org.junit.Test;



public class ItemTest {
	Item item;
	
	@Before
	public void creatingItemTest() {
		item = new Item(0,"9998887776665","1");
		
	}
	
	@Test
	public void ToStringTest() {
		String output = item.toString();
		assertEquals("[0,9998887776665,1]",output);
		
	}
	
	//getters
	@Test
	public void itemIDGetterTest() {
		int itemID = item.getItemid();
		assertEquals(0,itemID);
		
	}
	@Test
	public void ISBNGetterTest() {
		String isbNum = item.getISBN();
		assertEquals("9998887776665",isbNum);
		
	}
	@Test
	public void copynumberGetterTest() {
		String copyNum = item.getCopynumber();
		assertEquals("1",copyNum);
		
	}
	
	//setters
	//and since we know that the getters work from the above test we can use them here
	@Test
	public void itemIDSetterTest() {
		item.setItemid(1);
		assertEquals(1,item.getItemid());
		
	}
	@Test
	public void ISBNSetterTest() {
		item.setISBN("1112223334445");;
		assertEquals("1112223334445",item.getISBN());
		
	}
	@Test
	public void copynumberSetterTest() {
		item.setCopynumber("2");
		assertEquals("2",item.getCopynumber());
	}
	
	
}
