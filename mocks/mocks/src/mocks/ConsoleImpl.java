package mocks;

import java.io.PrintStream;

public class ConsoleImpl implements Console {

	private PrintStream _out;
		
	public ConsoleImpl(PrintStream out) {
		_out = out;
	}

	public void write(String string) {
		_out.print(string);
	}

}
