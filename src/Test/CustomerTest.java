package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import CompositePattern.Plan;
import Model.Customer;

public class CustomerTest {

	@Test
	public void testCustomer() {
		Customer mrTest = new Customer(123, "Type", "TestIncorp.", "Testion");
		
		assertEquals(mrTest.getCustomerType(), "Type");
		assertEquals(mrTest.getLastname(), "Testion");
		assertEquals(mrTest.getOrganization(), "TestIncorp");
	}
	
	//wenn Methode fertig, dann testen ;)
	@Test
	public void testSubscribe(Plan plan){}

}
