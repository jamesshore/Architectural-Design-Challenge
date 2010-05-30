

import static org.junit.Assert.*;

import java.io.IOException;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JMock.class)
public class _Rot13StringFactoryTest {
	private Mockery _mockery = new JUnit4Mockery();

	@Test
	public void createFromFile() throws IOException {
		final FileSystem fileSystem = _mockery.mock(FileSystem.class);
		Rot13StringFactory factory = new Rot13StringFactoryImpl(fileSystem);
		
		_mockery.checking(new Expectations() {{
			oneOf (fileSystem).readFile("filename"); will(returnValue("abc"));
		}});

		Rot13String string = factory.createFromFile("filename");
		assertEquals(new Rot13StringImpl("abc", fileSystem), string);
	}
}
