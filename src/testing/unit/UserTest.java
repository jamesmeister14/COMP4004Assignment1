package testing.unit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import server.logic.model.User;

public class UserTest {
	User user;
	
	@Before
	public void creatingUserTest() {
		user = new User(0,"jamie","banana");
	}
	
	@Test
	public void ToStringTest() {
		String output = user.toString();
		assertEquals("[0,jamie,banana]",output);
	}
	
	//getters
	@Test
	public void userIDGetterTest() {
		int userID = user.getUserid();
		assertEquals(0,userID);
	}
	@Test
	public void usernameGetterTest() {
		String copyNum = user.getUsername();
		assertEquals("jamie",copyNum);
	}
	@Test
	public void passwordGetterTest() {
		String isbNum = user.getPassword();
		assertEquals("banana",isbNum);
		
	}
	
	
	//setters
	//and since we know that the getters work from the above test we can use them here
	@Test
	public void userIDSetterTest() {
		user.setUserid(1);
		assertEquals(1,user.getUserid());
	}
	@Test
	public void copynumberSetterTest() {
		user.setUsername("james");;
		assertEquals("james",user.getUsername());
	}
	@Test
	public void passwordSetterTest() {
		user.setPassword("apples");
		assertEquals("apples",user.getPassword());
		
	}
	

}
