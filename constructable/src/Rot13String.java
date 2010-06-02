import java.io.IOException;


public class Rot13String {

	private String _string; 

	public static Rot13String load(FileSystem fileSystem, String filename) throws IOException {
		return new Rot13String(fileSystem.readFile(filename));
	}
	
	public static Rot13String load(String filename) throws IOException {
		return load(new FileSystem(), filename);
	}
	
	public Rot13String(String string) {
		_string = string;
	}

	public void transform() {
		String result = "";
		for (char c : _string.toCharArray()) {
			if ((c >= 'a' && c <= 'm') || (c >= 'A' && c <= 'M')) result += (char)(c + 13);
			else if ((c >= 'n' && c <= 'z') || (c >= 'N' && c <= 'Z')) result += (char)(c - 13);
			else result += c;
		}
		_string = result;
	}

	public void saveAs(Transaction transaction, final String filename) {
		new FileSystem().createFile(transaction, filename, _string);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_string == null) ? 0 : _string.hashCode());
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
		Rot13String other = (Rot13String) obj;
		if (_string == null) {
			if (other._string != null)
				return false;
		} else if (!_string.equals(other._string))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return _string;
	}
}
