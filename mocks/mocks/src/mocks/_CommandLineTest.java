package mocks;

import static org.junit.Assert.*;

import org.junit.Test;


public class _CommandLineTest {

	@Test
	public void valid() {
		CommandLine commandLine = new CommandLine(new String[] {"foo", "bar"});
		assertTrue("two args should be valid", commandLine.valid());

		commandLine = new CommandLine(new String[] {});
		assertFalse("everything else should be invalid", commandLine.valid());	
	}
	
	@Test
	public void filenames() {
		CommandLine commandLine = new CommandLine(new String[] {"input", "output"});
		assertEquals("input", commandLine.inputFilename());
		assertEquals("output", commandLine.outputFilename());
	}
}
