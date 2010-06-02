import java.io.*;


public class UI {
	private Console _console;
	
	public UI(PrintStream output) {
		_console = new Console(output);
	}

	public void go(Transaction transaction, String[] args) throws IOException {
		CommandLine commandLine = new CommandLine(args);
		
		Rot13String string = Rot13String.load(new FileSystem(), commandLine.inputFilename());
		string.transform();
		_console.write(string.toString());
		string.saveAs(transaction, commandLine.outputFilename());
	}

}
