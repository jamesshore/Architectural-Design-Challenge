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
		private final String _filename;
		private final String _contents;

		CreateOperation(String filename, String contents) {
			_filename = filename;
			_contents = contents;
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
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((_contents == null) ? 0 : _contents.hashCode());
			result = prime * result
					+ ((_filename == null) ? 0 : _filename.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			CreateOperation other = (CreateOperation) obj;
			if (_contents == null) {
				if (other._contents != null)
					return false;
			} else if (!_contents.equals(other._contents))
				return false;
			if (_filename == null) {
				if (other._filename != null)
					return false;
			} else if (!_filename.equals(other._filename))
				return false;
			return true;
		}
	}

}
