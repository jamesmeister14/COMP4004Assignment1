package testing;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import server.logic.handler.model.Output;
import server.logic.handler.OutputHandler;
import server.logic.model.Item;
import server.logic.model.Loan;
import server.logic.model.Title;
import server.logic.model.User;
import server.logic.tables.ItemTable;
import server.logic.tables.LoanTable;
import server.logic.tables.TitleTable;
import server.logic.tables.UserTable;

public class OutputHandlerTest {
	UserTable userTable;
	OutputHandler outputhandler;
	TitleTable titleTable;
	ItemTable itemTable;
	LoanTable loanTable;
	
	
	@Before 
	public void setup() throws Exception {
		userTable = UserTable.getInstance();
		outputhandler = new OutputHandler();
		titleTable = TitleTable.getInstance();
		itemTable = ItemTable.getInstance();
	}
	
	@Test
	public void createUserTest() {
		Output jamie = outputhandler.createUser("jamie@carleton.ca,jamie");
		
		assertEquals(2, jamie.getState());
		assertEquals("Success!", jamie.getOutput());
		
		List<User> users = userTable.getUserTable();
		assertEquals("jamie@carleton.ca", users.get(users.size()-1).getUsername());
		assertEquals("jamie", users.get(users.size()-1).getPassword());
	}
	
	@Test 
	public void isNumberTest() {
		//pass
		assertEquals(true, outputhandler.isNumber("123456789"));
 		//fail
		assertEquals(false, outputhandler.isNumber("x"));
		assertEquals(false, outputhandler.isNumber("@"));
		
	}
	
	@Test 
	public void isIntegerTest() {
		//pass
		assertEquals(true, outputhandler.isInteger("1234567890123"));
 		//fail
		assertEquals(false, outputhandler.isInteger("123456789012"));
		assertEquals(false, outputhandler.isInteger("x"));
		assertEquals(false, outputhandler.isInteger("@"));
		
	}
	
	@Test
	public void createTitleTest() {
		//pass
		Output jamie = outputhandler.createTitle("1234567890123,The old man and the Sea");
		
		assertEquals(2, jamie.getState());
		assertEquals("Success!", jamie.getOutput());
		
		List<Title> titles = titleTable.getTitleTable();
		assertEquals("1234567890123", titles.get(titles.size()-1).getISBN());
		assertEquals("The old man and the Sea", titles.get(titles.size()-1).getBooktitle());
		//fail
		Output jamie2 = outputhandler.createTitle("The old man and the Sea");
		
		assertEquals(5, jamie2.getState());
		assertEquals("Your input should in this format:'ISBN,title',ISBN should be a 13-digit number", jamie2.getOutput());
	}
	
	@Test
	public void createItemTest() {
		//pass
		Output jamie = outputhandler.createItem("1234567890123");
		List<Item> items = itemTable.getItemTable();
		
		assertEquals(2, jamie.getState());
		assertEquals("Success!", jamie.getOutput());
		assertEquals("1234567890123", items.get(items.size()-1).getISBN());
		
		//fail
		Output jamie2 = outputhandler.createItem("123456789012");
		assertEquals(6, jamie2.getState());
		assertEquals("Your input should in this format:'ISBN',ISBN should be a 13-digit number", jamie2.getOutput());
	}
	
	@Test
	public void DeleteUserTest(){
		//pass
		Output jamie = outputhandler.deleteUser("Sun@carleton.ca");
		assertEquals(2, jamie.getState());
		assertEquals("Success!", jamie.getOutput());
		
		//fail
		Output jamie2 = outputhandler.deleteUser("1234567890123");
		assertEquals(7, jamie2.getState());
		assertEquals("Your input should in this format:'useremail'", jamie2.getOutput());
	}
	
	@Test
	public void DeleteTitleTest(){
		//pass
		Output jamie = outputhandler.deleteTitle("9781317594277");
		assertEquals(2, jamie.getState());
		assertEquals("Success!", jamie.getOutput());
		
		//fail
		Output jamie2 = outputhandler.deleteTitle("The act in context");
		assertEquals(8, jamie2.getState());
		assertEquals("Your input should in this format:'ISBN',ISBN should be a 13-digit number", jamie2.getOutput());
		
	}
	
	@Test
	public void DeleteItemTest(){
		//pass
		Output jamie = outputhandler.deleteItem("9781611687910,1");
		assertEquals(2, jamie.getState());
		assertEquals("Success!", jamie.getOutput());
		
		//fail
		Output jamie2 = outputhandler.deleteItem("9781317594277");
		assertEquals(9, jamie2.getState());
		assertEquals("Your input should in this format:'ISBN,copynumber',ISBN should be a 13-digit number", jamie2.getOutput());
		
	}
	
	@Test
	public void borrowTest() {
		//pass
		Output jamie = outputhandler.borrow("Zhibo@carleton.ca,1234567890123,0");
		assertEquals(3, jamie.getState());
		assertEquals("Copynumber Invalid!", jamie.getOutput());
		
		//fail
		Output jamie1 = outputhandler.borrow("chico@carleton.ca,1234567890123,0");
		assertEquals(10, jamie1.getState());
		assertEquals("The User Does Not Exist!", jamie1.getOutput());
		Output jamie2 = outputhandler.borrow("Zhibo@carleton.ca,");
		assertEquals(10, jamie2.getState());
		assertEquals("Your input should in this format:'useremail,ISBN,copynumber'", jamie2.getOutput());
		Output jamie3 = outputhandler.borrow("Zhibo@carleton.ca,1234567890123,1");
		assertEquals(3, jamie3.getState());
		assertEquals("Copynumber Invalid!", jamie3.getOutput());
	
	}
	
	@Test
	public void renewTest() {
		//pass
		Output jamie = outputhandler.renew("Michelle@carleton.ca,9781442668585,1");
		assertEquals(3, jamie.getState());
		assertEquals("Success!", jamie.getOutput());
		//fail
		Output jamie1 = outputhandler.renew("chico@carleton.ca,1234567890123,0");
		assertEquals(11, jamie1.getState());
		assertEquals("The User Does Not Exist!", jamie1.getOutput());
		Output jamie2 = outputhandler.renew("Zhibo@carleton.ca,");
		assertEquals(11, jamie2.getState());
		assertEquals("Your input should in this format:'useremail,ISBN,copynumber'", jamie2.getOutput());
		Output jamie3 = outputhandler.renew("Zhibo@carleton.ca,1234567890123,0");
		assertEquals(3, jamie3.getState());
		assertEquals("Outstanding Fee Exists!", jamie3.getOutput());
		
	}
}
