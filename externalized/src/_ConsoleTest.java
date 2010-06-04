import static org.junit.Assert.*;
import java.io.*;
import org.junit.Test;

public class _ConsoleTest {

	@Test
	public void write() {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		Console console = new Console(new PrintStream(output));
		console.write("blah");
		assertEquals("blah", output.toString());
	}
}
