import java.io.IOException;


public interface TransactionOperation {
	public void commit() throws IOException;
}
