import java.io.File;


public class FileSystem {

	public boolean fileExists(String filename) {
		File file = new File(filename);
		return file.exists();
	}

	public void createFile(String string) {
		// TODO Auto-generated method stub
		
	}

}
