package testing;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import server.logic.model.Title;
import server.logic.tables.TitleTable;

public class TitleTableTest {
	TitleTable titleTable;
	
	@Before
	public void creatingtitleTableTest() {
		titleTable = TitleTable.getInstance();
		
	}
	
	@Test
	public void getTitleTableTest() {
		String[] ISBNList=new String[]{"9781442668584","9781442616899","9781442667181","9781611687910","9781317594277"};
    	String[] titlenameList=new String[]{"By the grace of God","Dante's lyric poetry ","Courtesy lost","Writing for justice","The act in context"};
    	
		for(int i = 0; i < ISBNList.length; i++){
			assertEquals(ISBNList[i], titleTable.getTitleTable().get(i).getISBN());
			assertEquals(titlenameList[i], titleTable.getTitleTable().get(i).getBooktitle());
		}
	}
	
	@Test
	public void lookupTitleTableTest() {
		assertEquals(true, titleTable.lookup("9781442668584"));
		
	}
	@Test
	public void lookupTitleTableFailTest() {
		assertEquals(false, titleTable.lookup("1111111111111"));
		
	}
	
	@Test
	public void createTitleTest() {
		List<Title> titleList = titleTable.getTitleTable();
		assertEquals(true, titleTable.createtitle("2222222222222", "The old man and the seeaa"));
 		assertEquals("2222222222222", titleList.get(titleList.size()-1).getISBN());
 		assertEquals("The old man and the seeaa", titleList.get(titleList.size()-1).getBooktitle());
	}
	
	@Test
	public void deleteTitleTest() {
		//"success"
		for (int i = 5; i < titleTable.getTitleTable().size(); i++) {
			 assertEquals("success", titleTable.delete(titleTable.getTitleTable().get(i).getISBN()));
		}
		//"Active Loan Exists"
		assertEquals("Active Loan Exists", titleTable.delete(titleTable.getTitleTable().get(0).getISBN()));

		//"The Title Does Not Exist"
		assertEquals("The Title Does Not Exist", titleTable.delete("1111111111111"));
		
		//assertEquals("The Title Does Not Exist", titleTable.delete("9781442616899"));
	}

}
