package mocks;

import java.io.IOException;

public class UI {
	private static String _inputFileName;
	private static String _outputFileName;
	private Console _console = new ConsoleImpl();
	private Rot13StringFactory _stringFactory;
	
	public UI() {
		this(new ConsoleImpl(), new Rot13StringFactoryImpl());
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
	
	public static void main(String[] args) {
		processArgs(args);
		new UI().go(_inputFileName, _outputFileName);
	}
	
	private static void processArgs(String[] args) {
		if (args.length != 2) printUsageAndExit();		
		_inputFileName = args[0];
		_outputFileName = args[1];
		if (_inputFileName == null || _outputFileName == null) printUsageAndExit();                        
	}

	private static void printUsageAndExit() {
		new ConsoleImpl().write("Usage info goes here");
		System.exit(1);		
	}
}
