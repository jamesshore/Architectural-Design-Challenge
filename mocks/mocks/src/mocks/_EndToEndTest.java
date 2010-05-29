package mocks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.Test;


public class _EndToEndTest {

	private FileSystem _fileSystem = new FileSystemImpl();
	
	@Test
	public void endToEnd() throws IOException {
		String inputFile = "in.txt";
		String outputFile = "out.txt";
		
		String inputText = "The dog barks at midnight.";
		String expectedOutput = "Gur qbt onexf ng zvqavtug.";
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			_fileSystem.writeFile(inputFile, inputText);
			UI.main(new String[] { inputFile, outputFile }, new PrintStream(out));
			assertEquals(expectedOutput, _fileSystem.readFile(outputFile));
			assertEquals(expectedOutput, out.toString());
		}
		finally {
			if (_fileSystem.fileExists(inputFile)) _fileSystem.deleteFile(inputFile);
			if (_fileSystem.fileExists(outputFile)) _fileSystem.deleteFile(outputFile);
		}
	}
	
	@Test
	public void toDo() {
		fail("Need to implement test vs. production config switch.");
	}
}
