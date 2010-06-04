import java.io.*;


public class UI {
	public static final String USAGE = "Blah, blah, usage, etc.";
	
	private Configuration _config;
	private FileSystem _fileSystem;
	private Console _console;
	
	public UI(Configuration config, FileSystem fileSystem, PrintStream output) {
		_config = config;
		_fileSystem = fileSystem;
		_console = new Console(output);
	}

	public void go(Transaction transaction, String[] args) throws IOException {
		CommandLine commandLine = new CommandLine(args);
		if (!commandLine.isValid()) {
			_console.write(USAGE);
			return;
		}
		
		try {
			Rot13String string = Rot13String.load(_config, _fileSystem, commandLine.inputFilename());
			string.transform();
			_console.write(string.toString());
			string.saveAs(transaction, commandLine.outputFilename());
		}
		catch (IOException e) {
			_console.write(e.getLocalizedMessage());
		}
	}

	public static void main(String[] args) throws IOException {
		main(Configuration.production(), System.out, args);
	}
	
	public static void main(Configuration config, PrintStream out, String[] args) throws IOException {
		Transaction transaction = new Transaction(config);
		UI ui = new UI(config, new FileSystem(), out);
		ui.go(transaction, args);
		transaction.commit();
	}

}
