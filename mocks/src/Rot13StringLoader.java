

import java.io.IOException;

public interface Rot13StringLoader {

	public abstract TransformableString load(String filename)
			throws IOException;

}