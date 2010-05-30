import static org.junit.Assert.*;

import org.junit.Test;



public class _FileSystemTest {

	@Test
	public void fileExists() {
		FileSystem fileSystem = new FileSystem();
		assertFalse(fileSystem.fileExists("foo"));
	}
	
	@Test
	public void createFile() {
		FileSystem fileSystem = new FileSystem();
		assertFalse(fileSystem.fileExists("foo.txt"));
		fileSystem.createFile("foo.txt");
		assertTrue(fileSystem.fileExists("foo.txt"));
	}
}
