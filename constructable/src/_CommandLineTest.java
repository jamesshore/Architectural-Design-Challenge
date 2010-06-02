import static org.junit.Assert.*;
import org.junit.Test;



public class _CommandLineTest {

	@Test
	public void happyPath() {
		CommandLine commandLine = new CommandLine(new String[] { "in.txt", "out.txt" });
		
		assertEquals("in.txt", commandLine.inputFilename());
		assertEquals("out.txt", commandLine.outputFilename());
	}
	
	@Test
	public void unhappyPath() {
		CommandLine commandLine = new CommandLine(new String[] {});
		assertFalse("sadness and despair", commandLine.isValid());
	}
}
