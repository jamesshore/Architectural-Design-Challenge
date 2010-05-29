package mocks;

import static org.junit.Assert.*;

import java.io.IOException;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JMock.class)
public class _Rot13StringTest {
	private Mockery _mockery = new JUnit4Mockery();
	private FileSystem _fileSystem;
		
	@Before
	public void setup() {
		_fileSystem = _mockery.mock(FileSystem.class);
	}
	
	@Test
	public void transform() {
		checkTransform("nz", "am");
		checkTransform("am", "nz");
		checkTransform("NZ", "AM");
		checkTransform("AM", "NZ");
		checkTransform(".&)", ".&)");
	}
	
	@Test
	public void saveAs() throws IOException {
		Rot13String string = new Rot13StringImpl("abc", _fileSystem);

		_mockery.checking(new Expectations() {{
			oneOf (_fileSystem).writeFile("filename", "abc");
		}});

		string.saveAs("filename");
	}

	@Test
	public void equalsAndHashCode() {
		Rot13String string1a = new Rot13StringImpl("abc", _fileSystem);
		Rot13String string1b = new Rot13StringImpl("abc", _fileSystem);
		Rot13String string2 = new Rot13StringImpl("def", _fileSystem);

		assertEquals(string1a, string1b);
		assertFalse(string1a.equals(string2));
		assertEquals(string1a.hashCode(), string1b.hashCode());
	}
	
	@Test
	public void getString() {
		assertEquals("abc", new Rot13StringImpl("abc", _fileSystem).getString());
	}

	private void checkTransform(String expected, String original) {
		Rot13String string = new Rot13StringImpl(original, _fileSystem);
		string.transform();
		assertEquals(new Rot13StringImpl(expected, _fileSystem), string);
	}
}
