package mocks;

import static org.junit.Assert.*;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JMock.class)
public class _Rot13StringTest {
	Mockery context = new JUnit4Mockery();
		
	@Test
	public void transform() {
		assertTransform("nz", "am");
		assertTransform("am", "nz");
		assertTransform("NZ", "AM");
		assertTransform("AM", "NZ");
		assertTransform(".&)", ".&)");
	}
	
	@Test
	public void saveTo() {
		final FileSystem fileSystem = context.mock(FileSystem.class);
		Rot13String string = new Rot13String("abc", fileSystem);

		context.checking(new Expectations() {{
			oneOf (fileSystem).saveFile("filename", "abc");
		}});

		string.saveTo("filename");		
	}

	private void assertTransform(String expected, String original) {
		Rot13String string = new Rot13String(original);
		assertEquals(new Rot13String(expected), string.transform());
	}

	@Test
	public void equalsAndHashCode() {
		Rot13String string1a = new Rot13String("abc");
		Rot13String string1b = new Rot13String("abc");
		Rot13String string2 = new Rot13String("def");

		assertEquals(string1a, string1b);
		assertFalse(string1a.equals(string2));
		assertEquals(string1a.hashCode(), string1b.hashCode());
	}
	
	@Test
	public void testToString() {
		assertEquals("Rot13String [foo]", new Rot13String("foo").toString());
	}
}
