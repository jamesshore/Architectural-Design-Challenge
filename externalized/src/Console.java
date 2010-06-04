import java.io.PrintStream;


public class Console {

	private PrintStream _output;
	
	public Console(PrintStream output) {
		_output = output;
	}

	public void write(String string) {
		_output.print(string);
	}

}
