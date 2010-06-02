import java.io.*;


public class UI {
	private FileSystem _fileSystem;
	private Console _console;
	
	public UI(FileSystem fileSystem, PrintStream output) {
		_fileSystem = fileSystem;
		_console = new Console(output);
	}

	public void go(Transaction transaction, String[] args) throws IOException {
		CommandLine commandLine = new CommandLine(args);
		
		try {
			Rot13String string = Rot13String.load(_fileSystem, commandLine.inputFilename());
			string.transform();
			_console.write(string.toString());
			string.saveAs(transaction, commandLine.outputFilename());
		}
		catch (IOException e) {
			_console.write(e.getLocalizedMessage());
		}
	}

}
