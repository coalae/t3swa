package Logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;

import CompositePattern.Product;
import Model.Customer;
import Model.Invoice;
import Model.Subscription;
import iteratorPattern.InvoiceIterator;
import iteratorPattern.SubscriptionIterator;

/**
 * Die Klasse MockupConnection implementiert das Interface IConnection, das die
 * Methoden, die in den Connections angeboten werden, vorgibt. Die Klasse wird
 * im Abstract Factory Pattern verwendet.
 * 
 * @author Cordula Eggerth (Products, Plans, Customers) und Martin Regenfelder
 *         (Invoices, Payments, Subscriptions)
 *
 */
public class MockupConnection implements Serializable, IConnection, SubscriptionIterator, InvoiceIterator {

	/**
	 * Instanzvariablen
	 */
	private static final long serialVersionUID = 1L;
	private FastBillConnection fastBillConnection;

	public MockupConnection() {
		this.fastBillConnection = new FastBillConnection();
	}

	/**
	 * writeCurrentCustomerListIntoFile schreibt die aktuelle Customer List (von
	 * FastBill) in ein File.
	 */
	public void writeCurrentCustomerListIntoFile() {
		String filePath = "customerList.ser";

		ArrayList<Customer> filedCustomerList = fastBillConnection.getCustomerList();

		try {
			FileOutputStream fileOutput = new FileOutputStream(filePath);
			ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
			objectOutput.writeObject(filedCustomerList);
			objectOutput.close();
			fileOutput.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Customer getCustomerPerID(int id) {
		ArrayList<Customer> customerList = getCustomerList();
		for (Customer customer : customerList) {
			if (customer.getId() == id)
				return customer;
		}
		return null;
	}

	/**
	 * getCustomerList() holt die Customer List, die in einem File bereit liegt.
	 * Es wird hier keine Connection zu FastBill aufgebaut.
	 * 
	 * @return customerList (aus dem file)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Customer> getCustomerList() {
		ArrayList<Customer> customerList = new ArrayList<Customer>();
		String filePath = "customerList.ser";
		File file = new File(filePath);
		if (file.exists() && file.canRead()) {
			try {
				FileInputStream fileInput = new FileInputStream(filePath);
				ObjectInputStream objectInput = new ObjectInputStream(fileInput);

				if (objectInput != null) {
					customerList = (ArrayList<Customer>) objectInput.readObject();
					objectInput.close();
					fileInput.close();
				}

			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}

		return customerList;
	}

	/**
	 * createCustomer() fuegt einen Customer zur serialisierten Liste hinzu. Es
	 * wird hier keine Connection zu FastBill aufgebaut, sondern nur mit der
	 * serialisierten Customer Liste in customerList.ser gearbeitet.
	 */
	@Override
	public void createCustomer(Customer customer) {
		ArrayList<Customer> currentCustomerList = getCustomerList();
		String filePath = "customerList.ser";

		int maxExistingId = 0;
		for (int i = 0; i < currentCustomerList.size(); i++) {
			if (currentCustomerList.get(i).getId() > maxExistingId) {
				maxExistingId = currentCustomerList.get(i).getId();
			}
		}
		maxExistingId++;
		customer.setId(maxExistingId);
		currentCustomerList.add(customer);

		try {
			FileOutputStream fileOutput = new FileOutputStream(filePath);
			ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
			objectOutput.writeObject(currentCustomerList);
			objectOutput.close();
			fileOutput.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * writeCurrentProductListIntoFile schreibt die angebotene Produkt Liste
	 * (von FastBill) in ein File.
	 */
	public void writeCurrentProductListIntoFile() {
		String filePath = "productList.ser";

		ArrayList<Product> filedProductList = fastBillConnection.getProductList();

		try {
			FileOutputStream fileOutput = new FileOutputStream(filePath);
			ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
			objectOutput.writeObject(filedProductList);
			objectOutput.close();
			fileOutput.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * getProductList() holt die Product List, die in einem File bereit liegt.
	 * Es wird hier keine Connection zu FastBill aufgebaut.
	 * 
	 * @return productList (aus dem file)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Product> getProductList() {
		ArrayList<Product> productList = new ArrayList<Product>();
		String filePath = "productList.ser";
		File file = new File(filePath);
		if (file.exists() && file.canRead()) {
			try {
				FileInputStream fileInput = new FileInputStream(filePath);
				ObjectInputStream objectInput = new ObjectInputStream(fileInput);

				if (objectInput != null) {
					productList = (ArrayList<Product>) objectInput.readObject();
					objectInput.close();
					fileInput.close();
				}

			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}

		return productList;
	}

	/**
	 * getProductPerId liefert ein Produkt anhand der ID
	 * 
	 * @return Product product
	 */
	@Override
	public Product getProductPerId(int id) {
		ArrayList<Product> productList = getProductList();

		for (Product product : productList) {
			if (product.getProductId() == id)
				return product;
		}
		return null;
	}

	/**
	 * getAllSubscriptions liefert eine ArrayList aller Subscriptions. Diese
	 * werden aus einem Serialisierten File subscriptionList.ser gelesen
	 * 
	 * @return ArrayList subscriptionList
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Subscription> getAllSubscriptions() {
		ArrayList<Subscription> subscriptionList = new ArrayList<Subscription>();
		String filePath = "subscriptionList.ser";
		File file = new File(filePath);
		if (file.exists() && file.canRead()) {
			try {
				FileInputStream fileInput = new FileInputStream(filePath);
				ObjectInputStream objectInput = new ObjectInputStream(fileInput);

				if (objectInput != null) {
					subscriptionList = (ArrayList<Subscription>) objectInput.readObject();
					objectInput.close();
					fileInput.close();
				}

			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}

		if (subscriptionList.size() == 0) {
			createMockupSubscriptionData();
			return getAllSubscriptions();
		}
		return subscriptionList;
	}

	/**
	 * getSubscriptionsOfCustomer liefert alle Subscriptions eines Customers
	 * 
	 * @param customer
	 * @return ArrayList subscriptionList
	 */
	@Override
	public ArrayList<Subscription> getSubscriptionsOfCustomer(Customer customer) {
		ArrayList<Subscription> subscriptionList = getAllSubscriptions();
		ArrayList<Subscription> customersSubList = new ArrayList<>();
		for (Subscription subscription : subscriptionList) {
			if (subscription.getCustomerID() == customer.getId()) {
				customersSubList.add(subscription);
			}
		}

		return customersSubList;
	}

	/**
	 * Hier wird eine ArrayList lokal im File subscriptionList.ser gespeichert
	 * 
	 * @param subscriptionList
	 */

	public void storeSubscriptionList(ArrayList<Subscription> subscriptionList) {

		String filePath = "subscriptionList.ser";
		try {
			FileOutputStream fileOutput = new FileOutputStream(filePath);
			ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
			objectOutput.writeObject(subscriptionList);
			objectOutput.close();
			fileOutput.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * addSubscription fügt eine neue Subscription hinzu
	 * 
	 * @param subscription
	 * @return boolean wasAdded
	 */
	@Override
	public void updateSubscription(Subscription subscription) {
		ArrayList<Subscription> subscriptionList = getAllSubscriptions();

		for (int i = 0; i < subscriptionList.size(); i++) {
			if (subscriptionList.get(i).getId().equals(subscription.getId())) {
				subscriptionList.set(i, subscription);
				break;
			}
		}

		storeSubscriptionList(subscriptionList);

	}

	public void storeInvoiceList(ArrayList<Invoice> invoiceList) {

		String filePath = "invoiceList.ser";
		try {
			FileOutputStream fileOutput = new FileOutputStream(filePath);
			ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
			objectOutput.writeObject(invoiceList);
			objectOutput.close();
			fileOutput.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * loadInvoiceList liest aus dem lokalen File invoiceList.ser eine ArrayList
	 * 
	 * @return ArrayList invoiceList
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Invoice> loadInvoiceList() {
		ArrayList<Invoice> invoiceList = new ArrayList<Invoice>();
		String filePath = "invoiceList.ser";
		File file = new File(filePath);
		if (file.exists() && file.canRead()) {
			try {
				FileInputStream fileInput = new FileInputStream(filePath);
				ObjectInputStream objectInput = new ObjectInputStream(fileInput);

				if (objectInput != null) {
					invoiceList = (ArrayList<Invoice>) objectInput.readObject();
					objectInput.close();
					fileInput.close();
				}

			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}

		return invoiceList;
	}

	/**
	 * addSubscription fügt eine neue Subscription hinzu
	 * 
	 * @param subscription
	 * @return boolean wasAdded
	 */
	@Override
	public boolean addSubscription(Subscription subscription) {

		ArrayList<Subscription> subscriptionList = getAllSubscriptions();
		subscriptionList.add(subscription);
		storeSubscriptionList(subscriptionList);
		return false;
	}

	/**
	 * deleteSubscription löscht eine bestehende Subscription
	 * 
	 * @param subscription
	 * @return boolean wasDeleted
	 */
	@Override
	public boolean deleteSubscription(Subscription subscription) {
		ArrayList<Subscription> subscriptionList = getAllSubscriptions();
		boolean success = false;
		for (int i = 0; i < subscriptionList.size(); i++) {
			if (subscriptionList.get(i).getId().equals(subscription.getId())) {
				subscriptionList.remove(i);
				success = true;
				break;
			}
		}
		storeSubscriptionList(subscriptionList);
		return success;
	}

	/**
	 * updateSubscription kann den status einer Subscription updaten
	 * 
	 * @param subscription
	 */
	@Override
	public Subscription getSubscriptionPerId(UUID id) {
		ArrayList<Subscription> subscriptionList = getAllSubscriptions();
		for (Subscription subscription : subscriptionList) {
			if (subscription.getId().equals(id))
				return subscription;
		}
		return null;
	}

	/**
	 * Dient der erstellung von Mockup-Daten für Subscriptions. Aus diesen
	 * werden dann in weiterer Folge Invoices erzeugt.
	 */
	public void createMockupSubscriptionData() {
		// Laden von customerList & productlist
		ArrayList<Product> productList = getProductList();
		ArrayList<Customer> customerList = getCustomerList();

		// Wenn eine der beiden Listen null ist, return
		if (productList == null || customerList == null)
			return;
		ArrayList<Subscription> subscriptionList = new ArrayList<>();

		// Aktuelles Datum + 30 tage
		// Date date = new Date();
		// Calendar cal = Calendar.getInstance();
		// cal.setTime(date);
		// cal.add(Calendar.DATE, 30);
		// date = cal.getTime();

		for (Customer c : customerList) {
			// Subscription #1
			Subscription subscription = new Subscription(UUID.randomUUID(), new Date(),
					productList.get(0).getProductId(), "inactive", c.getId());
			subscriptionList.add(subscription);
			// Subscription #2
			Subscription subscription2 = new Subscription(UUID.randomUUID(), new Date(),
					productList.get(0).getProductId(), "inactive", c.getId());
			subscriptionList.add(subscription2);
			// Subscription #3
			Subscription subscription3 = new Subscription(UUID.randomUUID(), new Date(),
					productList.get(0).getProductId(), "inactive", c.getId());
			subscriptionList.add(subscription3);
			// Subscription #4
			Subscription subscription4 = new Subscription(UUID.randomUUID(), new Date(),
					productList.get(0).getProductId(), "inactive", c.getId());
			subscriptionList.add(subscription4);
		}

		storeSubscriptionList(subscriptionList);

	}

	/**
	 * Versucht erst Invoices aus dem serialisierten File zu lesen, und legt
	 * neue Invoices an wenn keine gefunden werden.
	 */
	@Override
	public ArrayList<Invoice> getAllInvoices() {
		ArrayList<Invoice> invoiceList = loadInvoiceList();
		HashMap<UUID, Invoice> map = new HashMap<>();
		boolean wasEmpty = false;
		if (invoiceList.size() == 0)
			wasEmpty = true;
		for (Invoice invoice : invoiceList) {
			map.put(invoice.getSubscriptionID(), invoice);
		}

		ArrayList<Subscription> subscriptionList = getAllSubscriptions();
		for (Subscription subscription : subscriptionList) {
			if (map.get(subscription.getId()) == null && subscription.getSubscriptionStatus().equals("inactive")) {
				Invoice invoice = new Invoice(UUID.randomUUID(), subscription.getCustomerID(),
						subscription.getProductId(), new Date(), subscription.getId());
				invoiceList.add(invoice);
			}
		}
		if (wasEmpty) {
			storeInvoiceList(invoiceList);
		}
		return invoiceList;
	}

	/**
	 * getInvoicePerSubscriptionID liefert eine Invoice anhand der dazugehörigen
	 * SubscriptionId
	 * 
	 * @param id
	 * @return Invoice invoice
	 */
	@Override
	public Invoice getInvoicePerSubscriptionID(UUID id) {
		ArrayList<Invoice> invoiceList = getAllInvoices();

		for (Invoice invoice : invoiceList) {
			if (id.equals(invoice.getSubscriptionID())) {
				return invoice;
			}
		}
		return null;
	}

	/**
	 * getAllInvoicesOfCustomer liefert eine ArrayList aller Invoices eines
	 * Customers
	 * 
	 * @param customer
	 * @return ArrayList invoiceList
	 */
	@Override
	public ArrayList<Invoice> getAllInvoicesOfCustomer(Customer customer) {
		ArrayList<Invoice> invoiceList = getAllInvoices();
		ArrayList<Invoice> customersInvoices = new ArrayList<>();

		for (Invoice invoice : invoiceList) {
			if (invoice.getCustomerId() == customer.getId())
				customersInvoices.add(invoice);
		}
		return customersInvoices;
	}

	/**
	 * payInvoice Bezahlt eine bestimmte Invoice
	 * 
	 * @param invoice
	 */
	@Override
	public void payInvoice(Invoice invoice) {
		// Set invoice to paid
		invoice.setPaid(true);
		invoice.setInvoicePaidDate(new Date());
		updateInvoice(invoice);
		Subscription subscription = getSubscriptionPerId(invoice.getSubscriptionID());
		Date subscriptionExpireDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(subscriptionExpireDate);
		cal.add(Calendar.DATE, 30);
		subscription.setValidUntil(cal.getTime());
		updateSubscription(subscription);

	}

	/**
	 * updateInvoice dient dem aktualisieren einer bestimmten Invoice
	 * 
	 * @param invoice
	 */
	@Override
	public void updateInvoice(Invoice invoice) {
		ArrayList<Invoice> invoiceList = loadInvoiceList();
		for (int i = 0; i < invoiceList.size(); i++) {
			if (invoiceList.get(i).getInvoiceId().equals(invoice.getInvoiceId())) {
				invoiceList.set(i, invoice);
				break;
			}
		}
		storeInvoiceList(invoiceList);

	}
	/**
	 * Liefert ArrayList<Invoice> als Iterator
	 */
	@Override
	public Iterator<Invoice> createInvoiceIterator() {
		return getAllInvoices().iterator();
	}
	
	/**
	 * Liefert ArrayList<Subscription> als Iterator
	 */
	@Override
	public Iterator<Subscription> createSubscriptionIterator() {
		return getAllSubscriptions().iterator();
	}
	
	
	public static void main (String args[]){
		
		
		MockupConnection mcon = new MockupConnection();
		InvoiceIterator invoiceIterator = mcon;
		SubscriptionIterator subscriptionIterator = mcon;
		Iterator<Invoice> invoice = invoiceIterator.createInvoiceIterator();
		Iterator<Subscription> subscription = subscriptionIterator.createSubscriptionIterator();
		while(invoice.hasNext()){
			Invoice inv = (Invoice) invoice.next();
			System.out.println(inv.getInvoiceId());
			System.out.println(inv.getSubscriptionID());
		}
		while(subscription.hasNext()){
			Subscription sub = (Subscription) subscription.next();
			System.out.println(sub.getSubscriptionStatus());
			System.out.println(sub.getCustomerID());
		}
		
	}
	
}
