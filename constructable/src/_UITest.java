import static org.junit.Assert.*;
import java.io.*;
import org.junit.*;

public class _UITest {
	private ByteArrayOutputStream _output;
	private PrintStream _print;
	
	@Before
	public void setup() {
		_output = new ByteArrayOutputStream();
		_print = new PrintStream(_output);
	}
	
	@Test
	public void go() throws IOException {
		FileSystem fileSystemStub = new FileSystem() {
			public String readFile(String filename) { return "abc"; }
		};
		
		Transaction transaction = new Transaction();
		String[] args = new String[] { "in.txt", "out.txt" };
		UI ui = new UI(fileSystemStub, _print);
		ui.go(transaction, args);
		
		assertEquals("console output", "nop", _output.toString());
		assertTrue("should save transformed file", transaction.hasOperation(new FileSystem.CreateOperation("out.txt", "nop")));
	}
	
	@Test
	public void go_shouldHandleExceptionGracefully() throws IOException {
		FileSystem fileSystemStub = new FileSystem() {
			public String readFile(String filename) throws IOException { throw new IOException("error message!"); }
		};
		
		UI ui = new UI(fileSystemStub, _print);
		ui.go(new Transaction() {}, new String[] { "in", "out" });
		
		assertEquals("console output", "error message!", _output.toString());
	}
	
	@Test
	public void go_shouldDisplayUsageForBadCommandLine() throws IOException {
		UI ui = new UI(new FileSystem(), _print);
		ui.go(new Transaction(), new String[] {});
		
		assertEquals("console output", UI.USAGE, _output.toString());
	}
}
