import static org.junit.Assert.*;
import java.io.*;
import org.junit.*;



public class __EndToEndTest {
	private FileSystem _fileSystem = new FileSystem();
	private String _inputFile = "in.txt";
	private String _outputFile = "out.txt";

	@Before
	public void setup() throws IOException {
		_fileSystem = new FileSystem();
		Transaction transaction = new Transaction();
		_fileSystem.createFile(transaction, "in.txt", "The dog barks at midnight.");
		transaction.commit();
	}
	
	@After
	public void teardown() {
		_fileSystem.deleteFile(_inputFile);
		_fileSystem.deleteFile(_outputFile);
	}
	
	@Test
	public void smokeTest() throws IOException {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		
		UI.main(new String[] { _inputFile, _outputFile });
		assertEquals("Gur qbt onexf ng zvqavtug.", _fileSystem.readFile(_outputFile));
	}
}
