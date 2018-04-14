package Model;

import java.io.Serializable;
import CompositePattern.Plan;
import ObserverPattern.SubjectStateObserver;
import ObserverPattern.SubscriptionStateSubject;

/**
 * Model Klasse des Customer.
 * Sie enthaelt die Instanzvariablen des Customer (abgestimmt auf die notwendigen Instanzvariablen, 
 * die durch die FastBill API vorgegeben werden) und die Get- und Set-Methoden fuer Customer.
 * @author Cordula Eggerth
 *
 */
public class Customer implements Serializable {
	
	/**
	 * Instanzvariablen
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String customerType;
	private String organization; 
	private String lastname;
	
	/**
	 * Konstruktor
	 * @param id
	 * @param customerType
	 * @param organization
	 * @param lastname
	 */
	public Customer(int id, String customerType, String organization, String lastname) {
		this.id = id;
		this.customerType = customerType;
		this.organization = organization;
		this.lastname = lastname;
	}

	/**
	 * GET- UND SET-METHODEN
	 */
	
	/**
	 * get Id des Customer
	 * @return id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * set Id des Customer
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * get Customer Type
	 * @return customerType
	 */
	public String getCustomerType() {
		return customerType;
	}
	
	/**
	 * set Customer Type
	 * @param customerType
	 */
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	
	/**
	 * get Organization (Firma) des Customer
	 * @return organization
	 */
	public String getOrganization() {
		return organization;
	}
	
	/** set Organization (Firma) des Customer
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	
	/** 
	 * get Lastname (Nachname) des Customers
	 * @return lastname
	 */
	public String getLastname() {
		return lastname;
	}
	
	/**
	 * set Lastname (Nachname) des Customers
	 * @param lastname
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	//vorerst einmal angelegt für das ObserverPattern - muss noch bearbeitet werden 
	public void subscribe(Plan plan){
		
		
		Subscription s = new Subscription();
		SubscriptionStateSubject subObserver = new SubscriptionStateSubject(s.getId());
		SubjectStateObserver customerObserver = new SubjectStateObserver();
		subObserver.addObserver(customerObserver);
		
	}

}
