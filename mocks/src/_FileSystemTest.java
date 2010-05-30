

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class _FileSystemTest {
	private PersistenceMechanism _fileSystem;
	private String _filename = "foo.txt";

	@Before
	public void setup() {
		_fileSystem = new FileSystem(Configuration.test());
	}
	
	@After
	public void teardown() {
		_fileSystem.delete(_filename);
	}

	@Test
	public void exists() throws IOException {
		assertFalse("file should not exist", _fileSystem.exists(_filename));
		_fileSystem.overwrite(_filename, "contents");
		assertTrue("file should exist", _fileSystem.exists(_filename));
	}

	@Test
	public void delete() throws IOException {
		_fileSystem.overwrite(_filename, "foo");
		_fileSystem.delete(_filename);
		assertFalse("file should be deleted", _fileSystem.exists(_filename));
	}

	@Test
	public void overwrite() throws IOException {
		_fileSystem.overwrite(_filename, "foo");
		assertEquals("foo", _fileSystem.read(_filename));
	}
	
	@Test
	public void read_shouldHandleLineBreaksProperly() throws IOException {
		_fileSystem.overwrite(_filename, "foo\nbar");
		assertEquals("foo\nbar", _fileSystem.read(_filename));
	}
	
	@Test
	public void everythingShouldUseConfiguredWorkingDirectory() throws IOException {
		File fullPath = new File(Configuration.test().workingDirectory(), _filename);
		fullPath.delete();
		_fileSystem.overwrite(_filename, "junk");
		assertTrue("should write file to test's working directory", fullPath.exists());
	}
}
