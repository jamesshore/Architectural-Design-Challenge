package mocks;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class _FileSystemTest {

	private FileSystem _fileSystem = new FileSystemImpl();
	private String _filename = "foo.txt";

	@Test
	public void fileExists() throws IOException {
		try {
			assertFalse("file should not exist", _fileSystem
					.fileExists(_filename));
			_fileSystem.createFile(_filename, "contents");
			assertTrue("file should exist", _fileSystem.fileExists(_filename));
		}
		finally {
			_fileSystem.deleteFile(_filename);
		}
	}

	@Test
	public void deleteFile() throws IOException {
		_fileSystem.createFile(_filename, "foo");
		_fileSystem.deleteFile(_filename);
		assertFalse("file should be deleted", _fileSystem.fileExists(_filename));
	}

	@Test
	public void createFile() throws IOException {
		try {
			_fileSystem.createFile(_filename, "foo");
			String actual = _fileSystem.readFile(_filename);
			assertEquals("foo", actual);
		}
		finally {
			_fileSystem.deleteFile(_filename);
		}
	}
}
