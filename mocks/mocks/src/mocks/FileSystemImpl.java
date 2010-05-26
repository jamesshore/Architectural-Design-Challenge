package mocks;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class FileSystemImpl implements FileSystem {

	@Override
	public void createFile(String filename, String contents) throws IOException {
		File file = new File(filename);
		Writer writer = new BufferedWriter(new FileWriter(file));
	}

	@Override
	public boolean fileExists(String filename) {
		return new File(filename).exists();
	}

	@Override
	public void deleteFile(String filename) {
		File file = new File(filename);
		file.delete();		
	}

	@Override
	public String readFile(String filename) {
		// TODO Auto-generated method stub
		return null;
	}
}
