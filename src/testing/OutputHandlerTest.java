package testing;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import server.logic.handler.model.Output;
import server.logic.handlers.OutputHandler;
import server.logic.model.User;
import server.logic.tables.UserTable;

public class OutputHandlerTest {
	UserTable userTable;
	OutputHandler outputhandler;
	
	@Before 
	public void setup() throws Exception {
		userTable = UserTable.getInstance();
		outputhandler = new OutputHandler();
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
	
	
}
