
public class UI {

	public void go(Transaction transaction, Rot13String string) {
		string.transform();
		string.saveAs(transaction, "foo.txt");
	}

}
