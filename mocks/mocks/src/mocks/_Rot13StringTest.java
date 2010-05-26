package mocks;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

public class _Rot13StringTest {

	@Test
	public void transform() {
		assertTransform("nz", "am");
		assertTransform("am", "nz");
		assertTransform("NZ", "AM");
		assertTransform("AM", "NZ");
		assertTransform(".&)", ".&)");
	}
	
	@Test
	@Ignore
	public void saveTo() {
//		Rot13String string = new Rot13String(original);
//		string.saveTo("directory");
		
		fail("mock-based assert goes here");
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
