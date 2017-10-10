package testing.unit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import server.logic.model.Title;

public class TitleTest {
	Title title;
	
	@Before
	public void creatingTitleTest() {
		title = new Title("9998887776665","Old man and the Sea");
		
	}
	
	@Test
	public void ToStringTest() {
		String output = title.toString();
		assertEquals("[9998887776665,Old man and the Sea]",output);
		
	}
	
	//getters
	@Test
	public void ISBNGetterTest() {
		String isbNum = title.getISBN();
		assertEquals("9998887776665",isbNum);
		
	}
	@Test
	public void booktitleGetterTest() {
		String booktitle = title.getBooktitle();
		assertEquals("Old man and the Sea",booktitle);
		
	}
	
	//setters
	//and since we know that the getters work from the above test we can use them here
	@Test
	public void ISBNSetterTest() {
		title.setISBN("1112223334445");;
		assertEquals("1112223334445",title.getISBN());
		
	}
	@Test
	public void booktitleSetterTest() {
		title.setBooktitle("2");
		assertEquals("2",title.getBooktitle());
	}

}
