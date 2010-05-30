import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;



public class _FileSystemTest {
	private String _filename = "foo.txt";
	private FileSystem _fileSystem;

	@Before
	public void setup() {
		_fileSystem = new FileSystem();
	}
	
	@Test
	public void fileExists() {
		assertFalse("file should not exist", _fileSystem.fileExists("foo"));
	}
	
	@Test
	public void createFile() throws IOException {
		assertFalse("assume file doesn't exist", _fileSystem.fileExists(_filename));
		_fileSystem.createFile(_filename);
		assertTrue("file should be created", _fileSystem.fileExists(_filename));
	}
	
	@Test
	public void deleteFile() throws IOException {
		_fileSystem.createFile(_filename);
		assertTrue("assume file exists", _fileSystem.fileExists(_filename));
		_fileSystem.deleteFile(_filename);
		assertFalse("file should be deleted", _fileSystem.fileExists(_filename));
	}
}
