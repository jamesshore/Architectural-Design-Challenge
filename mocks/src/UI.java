

import java.io.IOException;
import java.io.PrintStream;

public class UI {
	protected static final String USAGE = "Usage: blah-blah <input> <output>";

	private Display _display;
	private Rot13StringLoader _stringFactory;
	
	public UI(PrintStream out, Configuration configuration) {
		this(new ConsoleDisplay(out), new Rot13StringFileSystemLoader(new FileSystem(configuration)));
	}
	
	public UI(Display display, Rot13StringLoader stringFactory) {
		_display = display;
		_stringFactory = stringFactory;
	}

	public void go(String[] args) {
		try {
			CommandLine commandLine = new CommandLine(args);
			if (!commandLine.valid()) {
				_display.write(USAGE);
				return;
			}
			
			TransformableString string = _stringFactory.load(commandLine.inputFilename());
			string.transform();
			string.writeTo(_display);
			string.saveAs(commandLine.outputFilename());
		}
		catch (IOException e) {
			_display.write(e.getLocalizedMessage());
		}
	}
		
	public static void main(String[] args) {
		main(args, System.out, Configuration.production());
	}

	public static void main(String[] args, PrintStream out, Configuration configuration) {
		new UI(out, configuration).go(args);
	}
}
