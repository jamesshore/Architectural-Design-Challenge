import static org.junit.Assert.*;
import java.io.File;
import org.junit.Test;



public class _ConfigurationTest {

	@Test
	public void production() {
		assertEquals(new File("."), Configuration.production().workingDirectory());
	}
	
	@Test
	public void test() {
		File expected = new File(System.getProperty("java.io.tmpdir"));
		assertEquals(expected, Configuration.test().workingDirectory());
	}
	
}
