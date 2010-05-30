package mocks;

import static org.junit.Assert.*;

import java.io.IOException;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JMock.class)
public class _UITest {
	private Mockery _mockery = new JUnit4Mockery();
	
	@Test
	public void go_happyPath() throws IOException {
		final String inputFile = "input.txt";
		final String outputFile = "output.txt";

		final Console console = _mockery.mock(Console.class);
		final Rot13StringFactory stringFactory = _mockery.mock(Rot13StringFactory.class);
		final Rot13String string = _mockery.mock(Rot13String.class);
		_mockery.checking(new Expectations() {{
			oneOf (stringFactory).createFromFile(inputFile); will(returnValue(string));
			oneOf (string).transform();
			oneOf (string).saveAs(outputFile);
			oneOf (string).getString(); will(returnValue("nop"));
			oneOf (console).write("nop");
		}});
		
		UI ui = new UI(console, stringFactory);
		ui.go(new String[] {inputFile, outputFile});
	}
	
	@Test
	public void go_printsUsageWhenCommandLineIsBad() throws IOException {
		fail("to do");
	}
	
}
