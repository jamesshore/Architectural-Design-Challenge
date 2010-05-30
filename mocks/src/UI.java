

import java.io.IOException;
import java.io.PrintStream;

public class UI {
	protected static final String USAGE = "Usage: blah-blah <input> <output>";

	private Console _console;
	private Rot13StringFactory _stringFactory;
	
	public UI(PrintStream out, Configuration configuration) {
		this(new ConsoleImpl(out), new Rot13StringFactoryImpl(new FileSystemImpl(configuration)));
	}
	
	public UI(Console console, Rot13StringFactory stringFactory) {
		_console = console;
		_stringFactory = stringFactory;
	}

	public void go(String[] args) {
		try {
			CommandLine commandLine = new CommandLine(args);
			if (!commandLine.valid()) {
				_console.write(USAGE);
				return;
			}
			
			Rot13String string = _stringFactory.createFromFile(commandLine.inputFilename());
			string.transform();
			_console.write(string.getString());
			string.saveAs(commandLine.outputFilename());
		}
		catch (IOException e) {
			_console.write(e.getLocalizedMessage());
		}
	}
		
	public static void main(String[] args) {
		main(args, System.out, Configuration.production());
	}

	public static void main(String[] args, PrintStream out, Configuration configuration) {
		new UI(out, configuration).go(args);
	}
}
