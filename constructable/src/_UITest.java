import static org.junit.Assert.*;
import java.io.*;
import org.junit.*;

public class _UITest {
	private ByteArrayOutputStream _output;
	private UI _ui;
	
	@Before
	public void setup() {
		_output = new ByteArrayOutputStream();
		_ui = new UI(new PrintStream(_output));
	}
	
	@Test
	public void processFiles() throws IOException {
		Transaction transaction = new Transaction();
		Rot13String inputString = new Rot13String("abc");
		
		_ui.processFiles(transaction, inputString, "out.txt");
		
		assertEquals(new Rot13String("nop"), inputString);
		assertTrue("UI should save transformed file", transaction.hasOperation(new FileSystem.CreateOperation("out.txt", "nop")));
		assertEquals("console output", "nop", _output.toString());
	}
}
