import static org.junit.Assert.*;

import org.junit.Test;



public class _Rot13StringTest {

	@Test
	public void transform() {
		fail("to do");
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
