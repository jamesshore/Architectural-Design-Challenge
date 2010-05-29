package mocks;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;


public class _ConfigurationTest {

	@Test
	public void whenInProductionMode() {
		assertEquals(new File("."), Configuration.production().workingDirectory());
	}
	
	@Test
	public void whenInTestMode() {
		String tempDir = System.getProperty("java.io.tmpdir");
		assertEquals(new File(tempDir), Configuration.test().workingDirectory());
	}
}
