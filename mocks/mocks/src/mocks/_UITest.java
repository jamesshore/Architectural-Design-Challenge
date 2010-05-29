package mocks;

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
	public void go() throws IOException {
		final Console console = _mockery.mock(Console.class);
		final Rot13StringFactory stringFactory = _mockery.mock(Rot13StringFactory.class);
		final Rot13String string = _mockery.mock(Rot13String.class);
		_mockery.checking(new Expectations() {{
			oneOf (stringFactory).createFromFile("input.txt"); will(returnValue(string));
			oneOf (string).transform();
			oneOf (string).saveTo("output.txt");
			oneOf (string).getString(); will(returnValue("nop"));
			oneOf (console).write("nop");
		}});
		
		UI ui = new UI(console, stringFactory);
		ui.go("input.txt", "output.txt");
	}
}
