

import java.io.IOException;

public class Rot13StringFileSystemLoader implements Rot13StringLoader {

	private PersistenceMechanism _fileSystem;
	
	public Rot13StringFileSystemLoader(PersistenceMechanism fileSystem) {
		_fileSystem = fileSystem;
	}

	public TransformableString load(String filename) throws IOException {
		return new Rot13String(_fileSystem.read(filename), _fileSystem);
	}

}
