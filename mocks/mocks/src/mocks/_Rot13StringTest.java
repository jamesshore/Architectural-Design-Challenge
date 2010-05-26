package mocks;

import static org.junit.Assert.*;

import org.junit.Test;


public class _Rot13StringTest {

	@Test
	public void transform() {
		Rot13String string = new Rot13String("abc");
		assertEquals("transformed string", "nop", string.transform());
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
}
