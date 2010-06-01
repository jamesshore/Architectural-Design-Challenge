
public class UI {

	public void go(Transaction transaction, Rot13String string, String outputFilename) {
		string.transform();
		string.saveAs(transaction, outputFilename);
	}

}
