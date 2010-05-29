package mocks;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;


public class _EndToEndTest {

	private FileSystem _fileSystem = new FileSystemImpl();
	
	@Test
	public void endToEnd() throws IOException {
		String inputFile = "in.txt";
		String outputFile = "out.txt";
		try {
			_fileSystem.writeFile(inputFile, "The dog barks at midnight.");
			UI.main(new String[] { inputFile, outputFile });
			assertEquals("Gur qbt onexf ng zvqavtug.", _fileSystem.readFile(outputFile));
		}
		finally {
			if (_fileSystem.fileExists(inputFile)) _fileSystem.deleteFile(inputFile);
			if (_fileSystem.fileExists(outputFile)) _fileSystem.deleteFile(outputFile);
		}
	}
	
	@Test
	public void toDo() {
		fail("(Also need to implement test vs. production config switch.)");
	}
}
