package mocks;

import java.io.File;

public class Configuration {

	private File _workingDirectory;

	public static Configuration production() {
		return new Configuration(".");
	}
	
	public static Configuration test() {
		return new Configuration("temp");
	}
	
	private Configuration(String workingDirectory) {
		_workingDirectory = new File(workingDirectory);
	}

	public File workingDirectory() {
		return _workingDirectory;
	}

}
