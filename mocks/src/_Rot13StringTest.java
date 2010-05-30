

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
	private PersistenceMechanism _fileSystem;
		
	@Before
	public void setup() {
		_fileSystem = _mockery.mock(PersistenceMechanism.class);
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
		TransformableString string = new Rot13String("abc", _fileSystem);

		_mockery.checking(new Expectations() {{
			oneOf (_fileSystem).overwrite("filename", "abc");
		}});

		string.saveAs("filename");
	}

	@Test
	public void equals_and_hashCode() {
		TransformableString string1a = new Rot13String("abc", _fileSystem);
		TransformableString string1b = new Rot13String("abc", _fileSystem);
		TransformableString string2 = new Rot13String("def", _fileSystem);

		assertEquals(string1a, string1b);
		assertFalse(string1a.equals(string2));
		assertEquals(string1a.hashCode(), string1b.hashCode());
	}
	
	@Test
	public void writeTo() {
		TransformableString string = new Rot13String("foo", _fileSystem);

		final Display display = _mockery.mock(Display.class);
		_mockery.checking(new Expectations() {{
			oneOf (display).write("foo");
		}});
		string.writeTo(display);
	}

	private void checkTransform(String expected, String original) {
		TransformableString string = new Rot13String(original, _fileSystem);
		string.transform();
		assertEquals(new Rot13String(expected, _fileSystem), string);
	}
}
