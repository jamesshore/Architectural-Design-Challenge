import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Test;



public class _UITest {

	@Test
	public void transformsAndSavesFile() throws IOException {
		UI ui = new UI();
		Transaction transaction = new Transaction();
		Rot13String string = new Rot13String("abc");
		ui.go(transaction, string, "out.txt");
		
		assertEquals(new Rot13String("nop"), string);
		assertTrue("UI should save transformed file", transaction.hasOperation(new FileSystem.CreateOperation("out.txt", "nop")));
	}
}
