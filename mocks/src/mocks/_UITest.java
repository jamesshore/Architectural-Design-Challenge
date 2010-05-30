package mocks;

import java.io.FileNotFoundException;
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
	private String _inputFile = "input.txt";
	private String _outputFile = "output.txt";
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
		final Rot13String string = _mockery.mock(Rot13String.class);
		_mockery.checking(new Expectations() {{
			oneOf (_stringFactory).createFromFile(_inputFile); will(returnValue(string));
			oneOf (string).transform();
			oneOf (string).saveAs(_outputFile);
			oneOf (string).getString(); will(returnValue("nop"));
			oneOf (_console).write("nop");
		}});
		
		_ui.go(new String[] {_inputFile, _outputFile});
	}
	
	@Test
	public void go_printsUsageWhenCommandLineIsBad() throws IOException {
		_mockery.checking(new Expectations() {{
			oneOf (_console).write(UI.USAGE);
		}});
		
		_ui.go(new String[] {});
	}
	
	@Test
	public void go_handlesIOExceptionNicely() throws IOException {
		_mockery.checking(new Expectations() {{
			oneOf (_stringFactory).createFromFile(_inputFile); will(throwException(new FileNotFoundException("file not found")));
			oneOf (_console).write("file not found");
		}});
		
		_ui.go(new String[] {_inputFile, _outputFile});
	}
	
}
