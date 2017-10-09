package testing;

import static org.junit.Assert.*;

import java.util.List;

//import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import server.logic.model.Item;
import server.logic.tables.ItemTable;
//import server.logic.tables.LoanTable;


public class ItemTableTest {
	ItemTable itemTable;
	
	@Before
	public void creatingItemTableTest() {
		itemTable = ItemTable.getInstance();
		
	}
	
	@Test
	public void getItemTableTest() {
		String[] ISBNList = new String[]{"9781442668584","9781442616899","9781442667181"};

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
	
	@Test
	public void createItemTest() {
		List<Item> itemList = itemTable.getItemTable();
 		assertEquals(true, itemTable.createitem("9781611687910"));
 		String ISBN = itemList.get(itemList.size() - 1).getISBN();
 		assertEquals("9781611687910", ISBN);
	}
	
	@Test
	public void createItemFailTest() {
		//List<Item> itemList = itemTable.getItemTable();
 		assertEquals(false, itemTable.createitem("1111111111111"));
 		//assertEquals("1111111111111", ISBN);
	}
	
	@Test
	public void deleteAllTest() {
		ItemTable items = ItemTable.getInstance();
		assertEquals(true, items.lookup("9781611687910", "1"));
		items.deleteAll("9781611687910");
		assertEquals(false, items.lookup("9781611687910", "1"));
		
	}
	
	@Test
	public void deleteItemFailTest() {
		assertEquals("Active Loan Exists", itemTable.delete("9781442668584", "1"));
		//"Active Loan Exists"
		
		assertEquals("The Item Does Not Exist", itemTable.delete("1111111111111", "1"));
	    //"The Item Does Not Exist
	}
	
	@Test 
	public void deleteItemTest() {
		assertEquals("success", itemTable.delete(itemTable.getItemTable().get(itemTable.getItemTable().size() - 1).getISBN(), itemTable.getItemTable().get(itemTable.getItemTable().size() - 1).getCopynumber()));
	}

}
