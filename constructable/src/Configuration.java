import java.io.File;


public class Configuration {

	public static Configuration production() {
		return new Configuration();
	}

	public static Configuration test() {
		// TODO Auto-generated method stub
		return null;
	}

	public File workingDirectory() {
		return new File(".");
	}

}
