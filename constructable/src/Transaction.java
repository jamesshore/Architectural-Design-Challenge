import java.io.IOException;


public class Transaction {

	private TransactionOperation _element = null;
	
	public void commit() throws IOException {
		assert (_element != null);
		_element.commit();
	}

	public void add(TransactionOperation element) {
		_element = element;		
	}

	public boolean hasOperation(TransactionOperation operation) {
		if (_element == null) return false; 
		return _element.equals(operation);
	}

}
