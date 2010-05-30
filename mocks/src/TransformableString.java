

import java.io.IOException;

public interface TransformableString {
	public abstract void transform();
	public abstract void saveAs(String filename) throws IOException;
	public abstract void writeTo(Display display);
}