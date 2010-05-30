

import java.io.PrintStream;

public class ConsoleDisplay implements Display {

	private PrintStream _out;
		
	public ConsoleDisplay(PrintStream out) {
		_out = out;
	}

	public void write(String string) {
		_out.print(string);
	}

}
