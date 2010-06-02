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
		createTestFile("in.txt", "abc");

		Transaction transaction = new Transaction();
		
		String[] args = new String[] { "in.txt", "out.txt" };
		_ui.processFiles(transaction, args);
		
		assertEquals("console output", "nop", _output.toString());
		assertTrue("should save transformed file", transaction.hasOperation(new FileSystem.CreateOperation("out.txt", "nop")));
	}

	private void createTestFile(String filename, String contents) throws IOException {
		Transaction transaction = new Transaction();
		new FileSystem().createFile(transaction, filename, contents);
		transaction.commit();
	}
}
