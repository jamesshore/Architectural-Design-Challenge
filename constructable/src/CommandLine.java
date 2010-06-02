
public class CommandLine {

	private String[] _args;
	
	public CommandLine(String[] args) {
		_args = args;
	}

	public boolean isValid() {
		return _args.length == 2;
	}
	
	public String inputFilename() {
		return _args[0];
	}

	public String outputFilename() {
		return _args[1];
	}

}
