

import static org.junit.Assert.*;

import java.io.*;

import org.junit.After;
import org.junit.Test;


public class __EndToEndTest {
	private Configuration _configuration = Configuration.test();
	private FileSystem _fileSystem = new FileSystemImpl(Configuration.test());
	private String _inputFile = "in.txt";
	private String _outputFile = "out.txt";
	
	@After
	public void teardown() {
		_fileSystem.deleteFile(_inputFile);
		_fileSystem.deleteFile(_outputFile);		
	}
	
	@Test
	public void endToEnd() throws IOException {
		String inputText = "The dog barks at midnight.";
		_fileSystem.writeFile(_inputFile, inputText);
		
		String expectedOutput = "Gur qbt onexf ng zvqavtug.";
		ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();
		
		UI.main(new String[] { _inputFile, _outputFile }, new PrintStream(consoleOutput), _configuration);
		assertEquals(expectedOutput, _fileSystem.readFile(_outputFile));
		assertEquals(expectedOutput, consoleOutput.toString());
	}
}
