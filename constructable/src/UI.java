import java.io.ByteArrayOutputStream;


public class UI {

	public UI(ByteArrayOutputStream output) {
		// TODO Auto-generated constructor stub
	}

	public void go(Transaction transaction, Rot13String string, String outputFilename) {
		string.transform();
		string.saveAs(transaction, outputFilename);
	}

}
