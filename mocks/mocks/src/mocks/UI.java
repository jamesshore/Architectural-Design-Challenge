package mocks;

import java.io.IOException;

public class UI {
	private String _inputFileName;
	private String _outputFileName;
	private Console _console = new Console();
	
	public void go(String[] args) {
		try {
			processArgs(args);
			
			Rot13StringFactory stringFactory = new Rot13StringFactory();
			Rot13String string = stringFactory.createFromFile(_inputFileName);
			string.transform();
			string.saveTo(_outputFileName);
		}
		catch (IOException e) {
			_console.write(e.getLocalizedMessage());
		}
	}
	
	private void processArgs(String[] args) {
		if (args.length != 2) printUsageAndExit();		
		_inputFileName = args[0];
		_outputFileName = args[1];
		if (_inputFileName == null || _outputFileName == null) printUsageAndExit();                        
	}

	private void printUsageAndExit() {
		_console.write("Usage info goes here");
		System.exit(1);		
	}
	
	public static void main(String[] args) {
		new UI().go(args);
	}
}
