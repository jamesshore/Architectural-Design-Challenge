import static org.junit.Assert.*;

import java.io.*;

import org.junit.*;



public class _FileSystemTest {
	private String _filename = "foo.txt";
	private Configuration _config = Configuration.test();
	private FileSystem _fileSystem;

	@Before
	public void setup() {
		_fileSystem = new FileSystem();
	}
	
	@After
	public void teardown() throws IOException {
		deleteFile(_filename);
	}
	
	@Test
	public void fileExists() {
		assertFalse("file should not exist", _fileSystem.fileExists(_config, _filename));
	}
	
	@Test
	public void createFile() throws IOException {
		assertFalse("assume file doesn't exist", _fileSystem.fileExists(_config, _filename));
		createFileContaining("foo");
		assertTrue("file should be created", _fileSystem.fileExists(_config, _filename));
	}
	
	@Test
	public void createFile_overwritesExistingFile() throws IOException {
		createFileContaining("foo");
		createFileContaining("bar");
		assertEquals("bar", _fileSystem.readFile(_config, _filename));
	}

	@Test
	public void createFile_obeysConfiguration_thusEverythingDoes() throws IOException {
		Transaction tx = new Transaction();
		_fileSystem.createFile(tx, "foo", "contents");
		tx.commit(Configuration.test());
		
		File fooPath = new File(Configuration.test().workingDirectory(), "foo");
		assertTrue("'foo' should be created in test working directory", fooPath.exists());
	}
	
	@Test
	public void deleteFile() throws IOException {
		createFileContaining("foo");
		assertTrue("assume file exists", _fileSystem.fileExists(_config, _filename));
		deleteFile(_filename);
		assertFalse("file should be deleted", _fileSystem.fileExists(_config, _filename));
	}
	
	@Test
	public void deleteFile_failsSilentlyIfFileDoesNotExist() throws IOException {
		deleteFile(_filename);
		deleteFile(_filename);
	}
	
	@Test
	public void readFile() throws IOException {
		createFileContaining("contents");
		assertEquals("contents", _fileSystem.readFile(_config, _filename));
	}
	
	@Test
	public void createOperation_isTestable() {
		Transaction transaction = new Transaction();
		_fileSystem.createFile(transaction, "filename", "contents");
		assertTrue("create operation should be testable", transaction.hasOperation(new FileSystem.CreateOperation("filename", "contents")));
	}

	private void createFileContaining(String contents) throws IOException {
		Transaction transaction = new Transaction();
		_fileSystem.createFile(transaction, _filename, contents);
		transaction.commit(Configuration.test());
	}

	private void deleteFile(String filename) throws IOException {
		Transaction transaction = new Transaction();
		_fileSystem.deleteFile(transaction, filename);
		transaction.commit(Configuration.test());
	}
}
