package testing;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import server.logic.handler.model.Output;
import server.logic.handler.OutputHandler;
import server.logic.model.Title;
import server.logic.model.User;
import server.logic.tables.TitleTable;
import server.logic.tables.UserTable;

public class OutputHandlerTest {
	UserTable userTable;
	OutputHandler outputhandler;
	TitleTable titleTable;
	
	
	@Before 
	public void setup() throws Exception {
		userTable = UserTable.getInstance();
		outputhandler = new OutputHandler();
		titleTable = TitleTable.getInstance();
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
		Output jamie = outputhandler.createTitle("1234567890123,The old man and the Sea");
		
		assertEquals(2, jamie.getState());
		assertEquals("Success!", jamie.getOutput());
		
		List<Title> titles = titleTable.getTitleTable();
		assertEquals("1234567890123", titles.get(titles.size()-1).getISBN());
		assertEquals("The old man and the Sea", titles.get(titles.size()-1).getBooktitle());
	}
	
}
