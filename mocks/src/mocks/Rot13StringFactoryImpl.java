package mocks;

import java.io.IOException;

public class Rot13StringFactoryImpl implements Rot13StringFactory {

	private FileSystem _fileSystem;
	
	public Rot13StringFactoryImpl(FileSystem fileSystem) {
		_fileSystem = fileSystem;
	}

	public Rot13String createFromFile(String filename) throws IOException {
		return new Rot13StringImpl(_fileSystem.readFile(filename), _fileSystem);
	}

}
