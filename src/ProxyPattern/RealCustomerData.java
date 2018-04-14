package ProxyPattern;

import java.util.ArrayList;

import Logic.MockupConnection;
import Model.Customer;

/**
 * RealCustomerData ladet (bzw. schreibt) neue Kundendaten in ein File, falls die Kundenliste aus dem File leer ist, damit in der Connection 
 * Daten angezeigt werden koennen. Dieser Ladevorgang, der von RealCustomerData durchgefuehrt wird, wird von ProxyCustomerData 
 * angefragt. 
 * Die Klasse RealCustomerData implementiert das Interface CustomerData.
 * Die Klasse ist Teil des ProxyPattern.
 * @author Cordula Eggerth
 */
public class RealCustomerData implements CustomerData {

	/**
	 * loadCustomerData implementiert die Methode des Interface CustomerData, 
	 * um Customer Daten zu laden.
	 * @return customerList
	 */
	@Override
	public ArrayList<Customer> loadCustomerData() {
		
  		MockupConnection connection = new MockupConnection();
  		
  		connection.writeCurrentCustomerListIntoFile();  // falls noch keine Liste im File angelegt, dann schreibt aktuelle Liste

      	ArrayList<Customer> customerList=connection.getCustomerList();	
		return customerList;
	}
	
}
