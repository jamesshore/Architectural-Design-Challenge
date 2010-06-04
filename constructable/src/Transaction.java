import java.io.IOException;


public class Transaction {

	private Configuration _config;
	private TransactionOperation _element = null;
	
	public Transaction(Configuration config) {
		_config = config;
	}
	
	public void commit() throws IOException {
		if (_element == null) return;
		_element.commit();
	}

	public void add(TransactionOperation element) {
		_element = element;
		element.setConfiguration(_config);
	}

	public boolean hasOperation(TransactionOperation operation) {
		if (_element == null) return false; 
		return _element.equals(operation);
	}

	public Object configuration() {
		// TODO Auto-generated method stub
		return null;
	}

}
