package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Model.Invoice;

public class InvoiceTest {

	@Test
	public void testPaid() {
		Invoice myInvoice = new Invoice();
		myInvoice.setPaid(true);
		assertEquals(myInvoice.isPaid(), true);
	}

}
