import java.io.IOException;


public class Transaction {

	private TransactionElement _element = null;
	
	public void commit() throws IOException {
		assert (_element != null);
		_element.commit();
	}

	public void add(TransactionElement element) {
		_element = element;		
	}

	public boolean willSave() {
		return _element != null;
	}

}
