

import static org.junit.Assert.*;

import java.io.*;

import org.junit.After;
import org.junit.Test;


public class __EndToEndTest {
	private Configuration _configuration = Configuration.test();
	private PersistenceMechanism _fileSystem = new FileSystem(Configuration.test());
	private String _inputFile = "in.txt";
	private String _outputFile = "out.txt";
	
	@After
	public void teardown() {
		_fileSystem.delete(_inputFile);
		_fileSystem.delete(_outputFile);		
	}
	
	@Test
	public void endToEnd() throws IOException {
		String inputText = "The dog barks at midnight.";
		_fileSystem.overwrite(_inputFile, inputText);
		
		String expectedOutput = "Gur qbt onexf ng zvqavtug.";
		ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();
		
		UI.main(new String[] { _inputFile, _outputFile }, new PrintStream(consoleOutput), _configuration);
		assertEquals(expectedOutput, _fileSystem.read(_outputFile));
		assertEquals(expectedOutput, consoleOutput.toString());
	}
}
