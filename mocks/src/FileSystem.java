

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileSystem implements PersistenceMechanism {

	private Configuration _configuration;
	
	public FileSystem(Configuration configuration) {
		_configuration = configuration;
	}

	@Override
	public String read(String filename) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(fullPath(filename)));
		try {
			String result = "";
			int character = 0;
			while ((character = reader.read()) != -1) {
				result += (char)character;
			}
			return result;
		}
		finally {
			reader.close();
		}		
	}
	
	@Override
	public void overwrite(String filename, String contents) throws IOException {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(fullPath(filename)));
			writer.write(contents);
		}
		finally {
			writer.close();
		}
	}

	@Override
	public void delete(String filename) {
		fullPath(filename).delete();		
	}
	
	@Override
	public boolean exists(String filename) {
		return fullPath(filename).exists();
	}

	private File fullPath(String filename) {
		return new File(_configuration.workingDirectory(), filename);
	}
}
