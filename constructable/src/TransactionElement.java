import java.io.IOException;


public interface TransactionElement {
	public void commit() throws IOException;
}
