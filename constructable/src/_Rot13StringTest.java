import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Ignore;
import org.junit.Test;



public class _Rot13StringTest {

	@Test
	public void load() throws IOException {
		FileSystem fileSystem = new FileSystem();
		String filename = "in.txt";
		try {
			fileSystem.createFile(filename, "abc");
			assertEquals(new Rot13String("abc"), Rot13String.load(filename));
		}
		finally {
			fileSystem.deleteFile(filename);
		}
	}
	
	@Test
	public void transform() {
		checkTransform("ab", "no");
		checkTransform("no", "ab");
		checkTransform("AB", "NO");
		checkTransform("NO", "AB");
		checkTransform(" '&].", " '&].");
	}
	
	@Test
	public void saveAs() throws IOException {
		FileSystem fileSystem = new FileSystem();
		String filename = "out.txt";
		try {
			Rot13String string = new Rot13String("abc");
			string.saveAs(filename);
			assertEquals("abc", fileSystem.readFile(filename));
		}
		finally {
			fileSystem.deleteFile(filename);
		}
	}
	
	@Test
	public void todo() {
		fail("next: transaction object");
		fail("be sure to remove throws declaration on saveAs()");
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
