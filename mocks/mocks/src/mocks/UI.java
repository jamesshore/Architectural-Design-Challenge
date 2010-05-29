package mocks;

import java.io.IOException;
import java.io.PrintStream;

public class UI {
	private static String _inputFileName;
	private static String _outputFileName;
	private Console _console;
	private Rot13StringFactory _stringFactory;
	
	public UI(PrintStream out) {
		this(new ConsoleImpl(out), new Rot13StringFactoryImpl());
	}
	
	public UI(Console console, Rot13StringFactory stringFactory) {
		_console = console;
		_stringFactory = stringFactory;
	}

	public void go(String inputFilename, String outputFilename) {
		try {
			Rot13String string = _stringFactory.createFromFile(inputFilename);
			string.transform();
			string.saveTo(outputFilename);
			_console.write(string.getString());
		}
		catch (IOException e) {
			_console.write(e.getLocalizedMessage());
		}
	}
	
	private static void processArgs(String[] args, PrintStream out) {
		if (args.length != 2) printUsageAndExit(out);		
		_inputFileName = args[0];
		_outputFileName = args[1];
		if (_inputFileName == null || _outputFileName == null) printUsageAndExit(out);                        
	}

	private static void printUsageAndExit(PrintStream out) {
		new ConsoleImpl(out).write("Usage info goes here");
		System.exit(1);		
	}
	
	public static void main(String[] args) {
		main(args, System.out);
	}

	public static void main(String[] args, PrintStream out) {
		processArgs(args, out);
		new UI(out).go(_inputFileName, _outputFileName);
	}
}
