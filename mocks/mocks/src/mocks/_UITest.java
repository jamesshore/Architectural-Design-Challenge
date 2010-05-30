package mocks;

import java.io.IOException;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JMock.class)
public class _UITest {
	private Mockery _mockery = new JUnit4Mockery();
	private Rot13StringFactory _stringFactory;
	private Console _console;
	private UI _ui;

	@Before
	public void setup() {
		_console = _mockery.mock(Console.class);
		_stringFactory = _mockery.mock(Rot13StringFactory.class);		
		_ui = new UI(_console, _stringFactory);
	}
	
	@Test
	public void go_happyPath() throws IOException {
		final String inputFile = "input.txt";
		final String outputFile = "output.txt";

		final Rot13String string = _mockery.mock(Rot13String.class);
		_mockery.checking(new Expectations() {{
			oneOf (_stringFactory).createFromFile(inputFile); will(returnValue(string));
			oneOf (string).transform();
			oneOf (string).saveAs(outputFile);
			oneOf (string).getString(); will(returnValue("nop"));
			oneOf (_console).write("nop");
		}});
		
		_ui.go(new String[] {inputFile, outputFile});
	}
	
	@Test
	public void go_printsUsageWhenCommandLineIsBad() throws IOException {
		_mockery.checking(new Expectations() {{
			oneOf (_console).write(UI.USAGE);
		}});
		
		_ui.go(new String[] {});
	}
	
}
