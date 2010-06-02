
public class CommandLine {

	private String[] _args;
	
	public CommandLine(String[] args) {
		_args = args;
	}

	public boolean isValid() {
		return true;
	}
	
	public String inputFilename() {
		return _args[0];
	}

	public String outputFilename() {
		return _args[1];
	}

}
