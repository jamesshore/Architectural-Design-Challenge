

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Test;

public class _ConsoleTest {

	@Test
	public void write() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Display console = new ConsoleDisplay(new PrintStream(out));
		console.write("foo");
		assertEquals("foo", out.toString());
	}
}
