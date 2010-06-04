import java.io.IOException;


public abstract class TransactionOperation {
	public abstract void commit(Configuration configuration) throws IOException;
}
