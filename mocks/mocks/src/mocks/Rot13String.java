package mocks;

import java.io.IOException;

public interface Rot13String {

	public abstract void transform();

	public abstract void saveTo(String filename) throws IOException;

	public abstract String getString();

}