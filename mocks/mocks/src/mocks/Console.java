package mocks;

import java.io.PrintStream;

public class Console {

	private PrintStream _out;
	
	public Console() {
		this(System.out);
	}
	
	public Console(PrintStream out) {
		_out = out;
	}

	public void write(String string) {
		_out.print(string);
	}

}
