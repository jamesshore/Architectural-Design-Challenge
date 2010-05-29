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
	private FileSystem _fileSystem = new FileSystemImpl();
	private String inputFile = "in.txt";
	private String outputFile = "out.txt";
	
	@After
	public void teardown() {
		if (_fileSystem.fileExists(inputFile)) _fileSystem.deleteFile(inputFile);
		if (_fileSystem.fileExists(outputFile)) _fileSystem.deleteFile(outputFile);		
	}
	
	@Test
	public void endToEnd() throws IOException {
		String inputText = "The dog barks at midnight.";
		_fileSystem.writeFile(inputFile, inputText);
		
		String expectedOutput = "Gur qbt onexf ng zvqavtug.";
		ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();
		
		UI.main(new String[] { inputFile, outputFile }, new PrintStream(consoleOutput));
		assertEquals(expectedOutput, _fileSystem.readFile(outputFile));
		assertEquals(expectedOutput, consoleOutput.toString());
	}
	
	@Test
	public void toDo() {
		fail("Need to implement test vs. production config switch.");
	}
}
