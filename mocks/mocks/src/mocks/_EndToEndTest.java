package mocks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;


public class _EndToEndTest {
	private Configuration _configuration = Configuration.test();
	private FileSystem _fileSystem = new FileSystemImpl(Configuration.test());
	private String inputFile = "in.txt";
	private String outputFile = "out.txt";
	
	@After
	public void teardown() {
		_fileSystem.deleteFile(inputFile);
		_fileSystem.deleteFile(outputFile);		
	}
	
	@Test
	public void endToEnd() throws IOException {
		String inputText = "The dog barks at midnight.";
		_fileSystem.writeFile(inputFile, inputText);
		
		String expectedOutput = "Gur qbt onexf ng zvqavtug.";
		ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();
		
		UI.main(new String[] { inputFile, outputFile }, new PrintStream(consoleOutput), _configuration);
		assertEquals(expectedOutput, _fileSystem.readFile(outputFile));
		assertEquals(expectedOutput, consoleOutput.toString());
	}
	
	@Test
	@Ignore
	public void toDo() {
		fail("Need to implement test vs. production config switch.");
	}
}
