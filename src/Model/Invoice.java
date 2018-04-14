package Model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * Model Klasse des Customer.
 * Sie enthaelt die Instanzvariablen des Customer (abgestimmt auf die notwendigen Instanzvariablen, 
 * die durch die FastBill API vorgegeben werden) und die Get- und Set-Methoden fuer Customer.
 * @author Cordula Eggerth (Model-Grundlagen) und Martin Regenfelder (Umsetzung, Uberarbeitung und Ausbau)
 *
 */
public class Invoice implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4995959527497050188L;
	/**
	 * Instanzvariablen
	 */
	private UUID invoiceId; 
	private int customerId; // 1 customer per invoice
	private int productId;  // 1 product per invoice
	private UUID subscriptionID;
	
	private Date invoiceCreationDate; // Datum der Rechnungserstellung
	private Date invoiceDueDate;      // Datum der Faelligkeit der Rechnung
	private Date invoicePaidDate;     // Datum der Zahlung der Rechnung
	private boolean paid;						   // true: bezahlt; false: nicht bezahlt

	public Invoice(){}
	public Invoice(UUID invoiceId, int customerId, int productId, Date invoiceCreationDate,UUID subscriptionID){
		this.invoiceId = invoiceId;
		this.customerId = customerId;
		this.productId = productId;
		this.invoiceCreationDate = invoiceCreationDate;
		this.subscriptionID = subscriptionID;
		//Faelligkeit ist Erstelldatum + 7 tage
		Calendar cal = Calendar.getInstance();
		cal.setTime(invoiceCreationDate);
		cal.add(Calendar.DATE, 7);
		this.invoiceDueDate = cal.getTime();
		this.paid = false;
		
	}
	
	/**
	 * GET- UND SET-METHODEN
	 */
	
	/**
	 * get invoiceId (RechnungsId)
	 * @return 
	 */
	public UUID getInvoiceId() {
		return invoiceId;
	}
	
	/** set invoice Id (RechnungsId)
	 * @param invoiceId
	 */
	public void setInvoiceId(UUID invoiceId) {
		this.invoiceId = invoiceId;
	}
	
	/**
	 * get customerId 
	 * @return customerId
	 */
	public int getCustomerId() {
		return customerId;
	}
	
	/**
	 * set customerId
	 * @param customerId
	 */
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	/**
	 * get productId
	 * @return productId
	 */
	public int getProductId() {
		return productId;
	}
	
	/**
	 * set productId 
	 * @param productId
	 */
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	/** get invoiceCreationDate
	 * @return invoiceCreationDate
	 */
	public Date getInvoiceCreationDate() {
		return invoiceCreationDate;
	}
	
	/**
	 * set invoiceCreationDate
	 * @param invoiceCreationDate
	 */
	public void setInvoiceCreationDate(Date invoiceCreationDate) {
		this.invoiceCreationDate = invoiceCreationDate;
	}
	
	/**
	 * get invoiceDueDate
	 * @return invoiceDueDate
	 */
	public Date getInvoiceDueDate() {
		return invoiceDueDate;
	}
	
	/**
	 * set invoiceDueDate
	 * @param invoiceDueDate
	 */
	public void setInvoiceDueDate(Date invoiceDueDate) {
		this.invoiceDueDate = invoiceDueDate;
	}
	
	/**
	 * get invoicePaidDate
	 * @return invoicePaidDate
	 */
	public Date getInvoicePaidDate() {
		return invoicePaidDate;
	}
	
	/**
	 * set invoicePaidDate
	 * @param invoicePaidDate
	 */
	public void setInvoicePaidDate(Date invoicePaidDate) {
		this.invoicePaidDate = invoicePaidDate;
	}
	
	/**
	 * check, ob Invoice bezahlt wurde
	 * @return paid
	 */
	public boolean isPaid() {
		return paid;
	}
	
	/**
	 * set paid
	 * @param paid
	 */
	public void setPaid(boolean paid) {
		this.paid = paid;
	}
	public UUID getSubscriptionID() {
		return subscriptionID;
	}
	public void setSubscriptionID(UUID subscriptionID) {
		this.subscriptionID = subscriptionID;
	}
	
}


