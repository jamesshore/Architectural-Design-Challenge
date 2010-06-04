import java.io.IOException;


public abstract class TransactionOperation {
	private Configuration _config;

	public void setConfiguration(Configuration config) {
		_config = config;
	}
	
	public Configuration configuration() {
		return _config;
	}

	public abstract void commit() throws IOException;
}
