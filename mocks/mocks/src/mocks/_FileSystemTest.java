package mocks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class _FileSystemTest {
	private FileSystem _fileSystem;
	private String _filename = "foo.txt";

	@Before
	public void setup() {
		_fileSystem = new FileSystemImpl(Configuration.test());
	}
	
	@After
	public void teardown() {
		_fileSystem.deleteFile(_filename);
	}

	@Test
	public void fileExists() throws IOException {
		assertFalse("file should not exist", _fileSystem.fileExists(_filename));
		_fileSystem.writeFile(_filename, "contents");
		assertTrue("file should exist", _fileSystem.fileExists(_filename));
	}

	@Test
	public void deleteFile() throws IOException {
		_fileSystem.writeFile(_filename, "foo");
		_fileSystem.deleteFile(_filename);
		assertFalse("file should be deleted", _fileSystem.fileExists(_filename));
	}

	@Test
	public void writeFile() throws IOException {
		_fileSystem.writeFile(_filename, "foo");
		assertEquals("foo", _fileSystem.readFile(_filename));
	}
	
	@Test
	public void readFile() throws IOException {
		_fileSystem.writeFile(_filename, "foo\nbar");
		assertEquals("foo\nbar", _fileSystem.readFile(_filename));
	}
	
	@Test
	public void everythingShouldUseConfiguredWorkingDirectory() throws IOException {
		File fullPath = new File(Configuration.test().workingDirectory(), _filename);
		fullPath.delete();
		_fileSystem.writeFile(_filename, "junk");
		assertTrue("should write file to test's working directory", fullPath.exists());
	}
}
