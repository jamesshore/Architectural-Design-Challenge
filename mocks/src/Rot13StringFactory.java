

import java.io.IOException;

public interface Rot13StringFactory {

	public abstract Rot13String createFromFile(String filename)
			throws IOException;

}