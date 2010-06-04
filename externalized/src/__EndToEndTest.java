import static org.junit.Assert.*;
import java.io.*;
import org.junit.*;



public class __EndToEndTest {
	private Configuration _config = Configuration.test();
	private FileSystem _fileSystem = new FileSystem();
	private String _inputFile = "in.txt";
	private String _outputFile = "out.txt";

	@Before
	public void setup() throws IOException {
		_fileSystem = new FileSystem();
		Transaction tx = new Transaction(_config);
		_fileSystem.createFile(tx, "in.txt", "The dog barks at midnight.");
		tx.commit();
	}
	
	@After
	public void teardown() throws IOException {
		Transaction tx = new Transaction(_config);
		_fileSystem.deleteFile(tx, _inputFile);
		_fileSystem.deleteFile(tx, _outputFile);
		tx.commit();
	}
	
	@Test
	public void smokeTest() throws IOException {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		
		UI.main(Configuration.test(), new PrintStream(output), new String[] { _inputFile, _outputFile });
		assertEquals("Gur qbt onexf ng zvqavtug.", _fileSystem.readFile(Configuration.test(), _outputFile));
	}
}
