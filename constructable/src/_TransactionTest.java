import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;



public class _TransactionTest {
	
	@Test
	public void commit() throws IOException {
		final int[] arrayHack = {0};   // sure wish Java had closures...
		Transaction transaction = new Transaction();
		transaction.add(new TransactionOperation() { public void commit() {   // ...and decent anonymous function syntax.
			arrayHack[0] = 1;
		}});
		
		assertEquals("should not execute transaction element immediately", 0, arrayHack[0]);
		transaction.commit();
		assertEquals("should execute transaction element on commit", 1, arrayHack[0]);		
	}
	
	@Test
	public void commit_shouldFailGracefullyWhenNoOperations() throws IOException {
		new Transaction().commit(); // should not throw exception
	}
	
	@Test
	public void hasOperation() {
		Transaction transaction = new Transaction();
		assertFalse("should fail properly when no operations pending", transaction.hasOperation(new TestOperation()));
		transaction.add(new TestOperation());
		assertTrue("should check operations", transaction.hasOperation(new TestOperation()));
	}

	private static class TestOperation implements TransactionOperation {
		public void commit() {}
		public boolean equals(Object o) { return (o instanceof TestOperation); }
	}
}
