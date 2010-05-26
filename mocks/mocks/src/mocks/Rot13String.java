package mocks;

public class Rot13String {

	private String _string;

	public Rot13String(String string) {
		_string = string;
	}

	public String transform() {
		String result = "";
		for (int i = 0; i < _string.length(); i++) {
			result += transformChar(_string.charAt(i));
		}
		return result;
	}

	private char transformChar(char c) {
		if ((c >= 'a' && c <= 'm') || (c >= 'A' && c <= 'M')) return (char)(c + 13);
		if ((c >= 'n' && c <= 'z') || (c >= 'N' && c <= 'Z')) return (char)(c - 13);
		return c;
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
}
