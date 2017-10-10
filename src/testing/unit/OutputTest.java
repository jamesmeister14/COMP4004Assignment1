package testing.unit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import server.logic.handler.model.Output;

public class OutputTest {
	Output output;
	@Before
	public void setup() throws Exception {
		output = new Output("WAITING", 0);
	}
	
	@Test
	public void ToStringTest() {
		String result = output.toString();
		assertEquals("[WAITING,0]",result);
		
	}
	
	//getters
	@Test
	public void outputGetterTest() {
		String otput = output.getOutput();
		assertEquals("WAITING",otput);
		
	}
	@Test
	public void stateGetterTest() {
		int state = output.getState();
		assertEquals(0,state);
		
	}
	
	
	//setters
	//and since we know that the getters work from the above test we can use them here
	
	@Test
	public void outputSetterTest() {
		output.setOutput("FINISHWAITING");
		assertEquals("FINISHWAITING",output.getOutput());
		
	}
	@Test
	public void stateSetterTest() {
		output.setState(1);
		assertEquals(1, output.getState());
		
	}
	
}
