

import static org.junit.Assert.*;

import java.io.IOException;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JMock.class)
public class _Rot13StringFileSystemLoaderTest {
	private Mockery _mockery = new JUnit4Mockery();

	@Test
	public void createFromFile() throws IOException {
		final PersistenceMechanism fileSystem = _mockery.mock(PersistenceMechanism.class);
		Rot13StringLoader factory = new Rot13StringFileSystemLoader(fileSystem);
		
		_mockery.checking(new Expectations() {{
			oneOf (fileSystem).read("filename"); will(returnValue("abc"));
		}});

		TransformableString string = factory.load("filename");
		assertEquals(new Rot13String("abc", fileSystem), string);
	}
}
