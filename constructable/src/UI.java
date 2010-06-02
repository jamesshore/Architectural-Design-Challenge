import java.io.*;


public class UI {
	private Console _console;
	
	public UI(PrintStream output) {
		_console = new Console(output);
	}

	public void processFiles(Transaction transaction, Rot13String string, String outputFilename) {
		string.transform();
		_console.write(string.toString());
		string.saveAs(transaction, outputFilename);
	}

}
