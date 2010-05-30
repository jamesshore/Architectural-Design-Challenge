

import java.io.IOException;

public interface PersistenceMechanism {
	public void overwrite(String filename, String contents) throws IOException;
	public boolean exists(String string);
	public void delete(String filename);
	public String read(String filename) throws IOException;
}
