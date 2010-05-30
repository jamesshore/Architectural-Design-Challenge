import static org.junit.Assert.*;

import org.junit.Test;



public class _FileSystemTest {

	@Test
	public void fileExists() {
		FileSystem fileSystem = new FileSystem();
		assertFalse(fileSystem.fileExists("foo"));
	}
}
