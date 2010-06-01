import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Test;



public class _UITest {

	@Test
	public void transformsAndSavesFile() throws IOException {
		UI ui = new UI();
		Transaction transaction = new Transaction();
		Rot13String string = new Rot13String("abc");
		ui.go(transaction, string);
		
		assertEquals(new Rot13String("nop"), string);
	}
}
