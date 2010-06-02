import static org.junit.Assert.*;
import java.io.*;
import org.junit.Test;

public class _UITest {

	@Test
	public void transformsAndSavesFile() throws IOException {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		Transaction transaction = new Transaction();

		Rot13String string = new Rot13String("abc");
		UI ui = new UI(new PrintStream(output));
		ui.go(transaction, string, "out.txt");
		
		assertEquals(new Rot13String("nop"), string);
		assertTrue("UI should save transformed file", transaction.hasOperation(new FileSystem.CreateOperation("out.txt", "nop")));
		assertEquals("console output", "nop", output.toString());
	}
}
