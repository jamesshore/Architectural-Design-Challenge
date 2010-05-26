package mocks;

import java.io.IOException;

public interface FileSystem {

	public void createFile(String filename, String contents) throws IOException;
	public boolean fileExists(String string);
	public void deleteFile(String filename);
	public String readFile(String filename) throws IOException;

}
