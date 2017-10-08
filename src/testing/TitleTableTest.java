package testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

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

}
