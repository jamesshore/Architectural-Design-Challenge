
public class Transaction {

	private TransactionElement _element = null;
	
	public void commit() {
		assert (_element != null);
		_element.commit();
	}

	public void add(TransactionElement element) {
		_element = element;		
	}

	public boolean willSave() {
		// TODO Auto-generated method stub
		return false;
	}

}
