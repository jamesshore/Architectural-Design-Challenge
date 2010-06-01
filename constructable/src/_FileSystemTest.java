import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.*;



public class _FileSystemTest {
	private String _filename = "foo.txt";
	private FileSystem _fileSystem;

	@Before
	public void setup() {
		_fileSystem = new FileSystem();
	}
	
	@After
	public void teardown() {
		_fileSystem.deleteFile(_filename);
	}
	
	@Test
	public void fileExists() {
		assertFalse("file should not exist", _fileSystem.fileExists(_filename));
	}
	
	@Test
	public void createFile() throws IOException {
		assertFalse("assume file doesn't exist", _fileSystem.fileExists(_filename));
		createFile("foo");
		assertTrue("file should be created", _fileSystem.fileExists(_filename));
	}
	
	@Test
	public void createFile_overwritesExistingFile() throws IOException {
		createFile("foo");
		createFile("bar");
		assertEquals("bar", _fileSystem.readFile(_filename));
	}
	
	@Test
	public void deleteFile() throws IOException {
		createFile("foo");
		assertTrue("assume file exists", _fileSystem.fileExists(_filename));
		_fileSystem.deleteFile(_filename);
		assertFalse("file should be deleted", _fileSystem.fileExists(_filename));
	}
	
	@Test
	public void deleteFile_failsSilentlyIfFileDoesNotExist() {
		_fileSystem.deleteFile(_filename);
		_fileSystem.deleteFile(_filename);
	}
	
	@Test
	public void readFile() throws IOException {
		createFile("contents");
		assertEquals("contents", _fileSystem.readFile(_filename));
	}
	
	@Test
	public void createOperation_isTestable() {
		Transaction transaction = new Transaction();
		_fileSystem.createFile(transaction, "filename", "contents");
		assertTrue("create operation should be testable", transaction.hasOperation(new FileSystem.CreateOperation("filename", "contents")));
	}

	private void createFile(String contents) throws IOException {
		Transaction transaction = new Transaction();
		_fileSystem.createFile(transaction, _filename, contents);
		transaction.commit();
	}
}
