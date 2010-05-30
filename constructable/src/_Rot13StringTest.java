import static org.junit.Assert.*;

import org.junit.Test;



public class _Rot13StringTest {

	@Test
	public void transform() {
		checkTransform("ab", "no");
		checkTransform("no", "ab");
		checkTransform("AB", "NO");
		checkTransform("NO", "AB");
		checkTransform(" '&].", " '&].");
	}

	private void checkTransform(String a, String b) {
		Rot13String original = new Rot13String(a);
		Rot13String expected = new Rot13String(b);
		original.transform();
		assertEquals(expected, original);
	}
	
	@Test
	public void equals() {
		Rot13String string1a = new Rot13String("foo");
		Rot13String string1b = new Rot13String("foo");
		Rot13String string2 = new Rot13String("bar");
		
		assertEquals(string1a, string1b);
		assertFalse(string1a.equals(string2));
		assertEquals(string1a.hashCode(), string1b.hashCode());
	}
}
