package testing;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import server.logic.tables.ItemTable;

public class ItemTableTest {
	ItemTable itemTable;
	
	@Before
	public void creatingItemTableTest() {
		itemTable = ItemTable.getInstance();
		
	}
	
	@Test
	public void getItemTableTest() {
		String[] ISBNList = new String[]{"9781442668584","9781442616899","9781442667181","9781611687910"};

		for(int i = 0; i < ISBNList.length; i++){
			assertEquals(ISBNList[i], itemTable.getItemTable().get(i).getISBN());
			assertEquals("1", itemTable.getItemTable().get(i).getCopynumber());
			assertEquals(i, itemTable.getItemTable().get(i).getItemid());
		}
	}
	
	@Test
	public void lookupItemTableTest() {
		assertEquals(true, itemTable.lookup("9781442668584", "1"));
		
	}
	@Test
	public void lookupItemTableFailTest() {
		assertEquals(false, itemTable.lookup("1111111111111", "1"));
		assertEquals(false, itemTable.lookup("9781442668584", "2"));
		
	}

}
