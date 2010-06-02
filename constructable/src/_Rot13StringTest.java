import static org.junit.Assert.*;
import java.io.IOException;
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

	@Test
	public void load() throws IOException {
		FileSystem fileSystem = new FileSystem();
		String filename = "in.txt";
		try {
			Transaction transaction = new Transaction();
			fileSystem.createFile(transaction, filename, "abc");
			transaction.commit();
			
			
			FileSystem stub = new FileSystem() {
				public String readFile(String filename) { return "foo"; } 
			};
			Rot13String loaded = Rot13String.load(stub, filename);
			
			
			assertEquals(new Rot13String("abc"), loaded);
			
			
		} finally {
			fileSystem.deleteFile(filename);
		}
	}

	@Test
	public void saveAs() {
		Transaction transaction = new Transaction();
		Rot13String string = new Rot13String("abc");
		string.saveAs(transaction, "out.txt");
		assertTrue("should queue up save operation", transaction.hasOperation(new FileSystem.CreateOperation("out.txt", "abc")));
	}

	@Test
	public void toString_() {
		assertEquals("yoyo", new Rot13String("yoyo").toString());
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

	private void checkTransform(String a, String b) {
		Rot13String original = new Rot13String(a);
		Rot13String expected = new Rot13String(b);
		original.transform();
		assertEquals(expected, original);
	}

}
