import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class FileSystem {

	public boolean fileExists(String filename) {
		File file = new File(filename);
		return file.exists();
	}

	public void createFile(String filename, String contents) throws IOException {
		File file = new File(filename);
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		try {
			
		}
		finally {
			writer.close();
		}
	}

	public void deleteFile(String filename) {
		File file = new File(filename);
		file.delete();		
	}

	public String readFile(String filename) {
		// TODO Auto-generated method stub
		return null;
	}

}
