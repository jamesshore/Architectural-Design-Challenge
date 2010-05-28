package mocks;

import java.io.IOException;

public class Rot13StringFactory {

	private FileSystem _fileSystem;
	
	public Rot13StringFactory(FileSystem fileSystem) {
		_fileSystem = fileSystem;
	}

	public Rot13String createFromFile(String filename) throws IOException {
		return new Rot13String(_fileSystem.readFile(filename));
	}

}
