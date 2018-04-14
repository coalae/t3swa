package ProxyPattern;

import java.util.ArrayList;

import Model.Customer;

/**
 * Das Interface CustomerData gibt die Methode loadCustomerData vor, die von RealCustomerData und 
 * ProxyCustomerData implementiert werden.
 * Es ist Teil des ProxyPattern. 
 * @author Cordula Eggerth
 *
 */
public interface CustomerData {
	
	/**
	 * loadCustomerData dient dazu, die Customer Daten zu laden.
	 * @return ArrayList Customer
	 */
	public ArrayList<Customer> loadCustomerData();
		
}
