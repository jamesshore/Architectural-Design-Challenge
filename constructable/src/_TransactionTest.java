import static org.junit.Assert.*;

import org.junit.Test;



public class _TransactionTest {

	@Test
	public void commit() {
		final int[] arrayHack = {0};   // sure wish Java had closures...
		Transaction transaction = new Transaction();
		transaction.add(new TransactionElement() { public void commit() {   // ...and decent anonymous function syntax.
			arrayHack[0] = 1;
		}});
		
		assertEquals("should not execute transaction element immediately", 0, arrayHack[0]);
		transaction.commit();
		assertEquals("should execute transaction element on commit", 1, arrayHack[0]);		
	}
	
	@Test
	public void willSave() {
		Transaction transaction = new Transaction();
		assertFalse("new transaction should think it does nothing", transaction.willSave());
		transaction.add(new TransactionElement() { public void commit() {} });
		assertTrue("for now, assume any transaction element will cause a save to occur", transaction.willSave());
	}
}
