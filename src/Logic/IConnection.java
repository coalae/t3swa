package Logic;

import java.util.ArrayList;
import java.util.UUID;

import CompositePattern.Product;
import Model.Customer;
import Model.Invoice;
import Model.Subscription;

/**
 * Das Interface IConnection gibt die Methode vor, die in den Connections
 * angeboten werden. Die Klasse wird im Abstract Factory Pattern verwendet.
 * 
 * @author Cordula Eggerth (Products, Plans, Customers) und Martin Regenfelder (Invoices, Payments, Subscriptions)
 *
 */
public interface IConnection {

	/**
	 * getCustomerList liefert eine Liste der bestehenden Customers.
	 * 
	 * @return ArrayList Customer
	 */
	public ArrayList<Customer> getCustomerList();

	/**
	 * getCustomerPerID liefert einen Customer anhand seiner Id
	 * @param id
	 * @return Customer customer
	 */
	public Customer getCustomerPerID(int id);

	/**
	 * createCustomer legt einen neuen Kunde, der als Parameter uebergeben
	 * wurde, an.
	 * 
	 * @param customer
	 */
	public void createCustomer(Customer customer);

	/**
	 * getProductList liefert eine Liste der angebotenen Products.
	 * 
	 * @return ArrayList Customer
	 */
	public ArrayList<Product> getProductList();
	
	
	/**
	 * getProductPerId liefert ein Produkt anhand der ID
	 * @return Product product
	 */
	public Product getProductPerId(int id);

	// Methoden für Subscriptions

	/**
	 * getAllSubscriptions liefert eine ArrayList aller Subscriptions
	 * 
	 * @return ArrayList subscriptionList
	 */
	public ArrayList<Subscription> getAllSubscriptions();

	/**
	 * getSubscriptionsOfCustomer liefert alle Subscriptions eines Customers
	 * 
	 * @param customer
	 * @return ArrayList subscriptionList
	 */
	public ArrayList<Subscription> getSubscriptionsOfCustomer(Customer customer);

	/**
	 * addSubscription fügt eine neue Subscription hinzu
	 * 
	 * @param subscription
	 * @return boolean wasAdded
	 */
	public boolean addSubscription(Subscription subscription);

	/**
	 * deleteSubscription löscht eine bestehende Subscription
	 * 
	 * @param subscription
	 * @return boolean wasDeleted
	 */
	public boolean deleteSubscription(Subscription subscription);

	/**
	 * getSubscriptionPerId liefert eine Subscription anhand ihrer UUID
	 * 
	 * @param id
	 * @return Subscription subscription
	 */
	public Subscription getSubscriptionPerId(UUID id);

	/**
	 * updateSubscription kann den status einer Subscription updaten
	 * 
	 * @param subscription
	 */
	public void updateSubscription(Subscription subscription);

	// Methoden für Invoices

	/**
	 * getAllInvoices liefert eine ArrayList aller Invoices
	 * 
	 * @return ArrayList invoiceList
	 */
	public ArrayList<Invoice> getAllInvoices();

	/**
	 * getAllInvoicesOfCustomer liefert eine ArrayList aller Invoices eines
	 * Customers
	 * 
	 * @param customer
	 * @return ArrayList invoiceList
	 */
	public ArrayList<Invoice> getAllInvoicesOfCustomer(Customer customer);

	/**
	 * payInvoice Bezahlt eine bestimmte Invoice
	 * 
	 * @param invoice
	 */
	public void payInvoice(Invoice invoice);

	/**
	 * updateInvoice dient dem aktualisieren einer bestimmten Invoice
	 * @param invoice
	 */
	public void updateInvoice(Invoice invoice);

	/**
	 * getInvoicePerSubscriptionID liefert eine Invoice anhand der dazugehörigen SubscriptionId
	 * @param id
	 * @return Invoice invoice
	 */
	public Invoice getInvoicePerSubscriptionID(UUID id);

}
