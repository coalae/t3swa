package ProxyPattern;

import java.util.ArrayList;

import Logic.MockupConnection;
import Model.Customer;

/**
 * ProxyCustomerData implementiert das Interface CustomerData und ladet, falls die CustomerList null ist, d.h. lokal nicht angelegt ist, 
 * eine neue Kundenliste mit den aktuellen FastBill Daten in das File. Der Ladevorgang wird von RealCustomerData druchgefuehrt.
 * Die Klasse ist Teil des ProxyPattern.
 * @author Cordula Eggerth
 */
public class ProxyCustomerData implements CustomerData {

	/**
	 * loadCustomerData implementiert die Methode des Interface CustomerData, 
	 * um Customer Daten zu laden.
	 * Falls die customerList Daten enthaelt, werden diese verwendet. 
	 * Falls die customerList keine Daten enthaelt, werden die Daten ueber RealCustomerData angefragt, 
	 * wo der Ladevorgang durchgefuehrt wird.
	 * @return customerList
	 */
	@Override
	public ArrayList<Customer> loadCustomerData() {
		
  		MockupConnection connection = new MockupConnection();
  		ArrayList<Customer> customerList = new ArrayList<Customer>();
  		
  		if(connection.getCustomerList().size()==0){
  			RealCustomerData updatedCustomerData = new RealCustomerData();
  			customerList = updatedCustomerData.loadCustomerData();
  			return customerList;
  		} else {
  			return connection.getCustomerList();
  		}
  		
	}

}
