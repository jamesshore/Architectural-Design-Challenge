import java.io.IOException;


public class Transaction {

	private TransactionOperation _element = null;
	
	public void commit(Configuration configuration) throws IOException {
		if (_element == null) return;
		_element.commit(configuration);
	}

	public void add(TransactionOperation element) {
		_element = element;
	}

	public boolean hasOperation(TransactionOperation operation) {
		if (_element == null) return false; 
		return _element.equals(operation);
	}
}
