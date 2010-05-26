package mocks;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class FileSystemImpl implements FileSystem {

	@Override
	public void createFile(String filename, String contents) throws IOException {
		File file = new File(filename);
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		try {
			writer.write(contents);
		}
		finally {
			writer.close();
		}
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
	public String readFile(String filename) throws IOException {
		File file = new File(filename);
		BufferedReader reader = new BufferedReader(new FileReader(file));
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
}
