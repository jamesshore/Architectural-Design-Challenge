import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class FileSystem {

	public boolean fileExists(String filename) {
		File file = new File(filename);
		return file.exists();
	}

	public void createFile(Transaction transaction, final String filename, final String contents) {
		transaction.add(new CreateOperation(filename, contents));
	}

	public void deleteFile(String filename) {
		File file = new File(filename);
		file.delete();		
	}

	public String readFile(String filename) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(filename));
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

	public static class CreateOperation implements TransactionOperation {
		private final String _contents;
		private final String _filename;

		CreateOperation(String filename, String contents) {
			_contents = contents;
			_filename = filename;
		}

		public void commit() throws IOException {
			BufferedWriter writer = new BufferedWriter(new FileWriter(_filename));
			try {
				writer.write(_contents);
			}
			finally {
				writer.close();
			}
		}
	}

}
