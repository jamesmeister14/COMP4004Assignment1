package testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import server.logic.tables.UserTable;

public class UserTableTest {

	UserTable userTable;
	
	@Before
	public void creatingUserTableTest() {
		userTable = UserTable.getInstance();
		
	}
	
	@Test
	public void getUserTableTest() {
		String[] passwordList=new String[]{"Zhibo","Yu","Michelle","Kevin","Sun"};
    	String[] usernameList=new String[]{"Zhibo@carleton.ca","Yu@carleton.ca","Michelle@carleton.ca","Kevin@carleton.ca","Sun@carleton.ca"};
    	
		for(int i = 0; i < passwordList.length; i++){
			assertEquals(passwordList[i], userTable.getUserTable().get(i).getPassword());
			assertEquals(usernameList[i], userTable.getUserTable().get(i).getUsername());
			assertEquals(i, userTable.getUserTable().get(i).getUserid());
		}
	}
	
	@Test
	public void lookupUserTableTest() {
		assertEquals(true, userTable.lookup(1));
		
	}
	@Test
	public void lookupUserTableFailTest() {
		assertEquals(false, userTable.lookup(-1));
		assertEquals(false, userTable.lookup(5));
		
	}

}
