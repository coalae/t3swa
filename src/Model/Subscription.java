package Model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * Die Klasse Subscription bildet das model fuer das Abonnement eines Products.
 * 
 * @author Cordula Eggerth (Model-Grundlagen) und Martin Regenfelder (Umsetzung, Uberarbeitung und Ausbau)
 *
 */
public class Subscription implements Serializable {

	/**
	 * Instanzvariablen
	 *
	 */
	private static final long serialVersionUID = 1L;
	private UUID id;
	private Date validUntil; // Datum bis wann die Subscription gueltig ist
	private int productId; // Subscription bezieht sich jeweils auf 1 Product
							// und davon 1 Plan (anderer Plan des Products ist
							// null gesetzt)
	private String subscriptionStatus; // subscriptionStatus: active, inactive,
										// oder suspended
	private int customerID;

	public Subscription(UUID id, Date validUntil, int productId, String subscriptionStatus, int customerID) {
		this.id = id;
		this.validUntil = validUntil;
		this.productId = productId;
		this.subscriptionStatus = subscriptionStatus;
		this.customerID = customerID;
	}

	public Subscription() {
	}

	/**
	 * GET- UND SET-METHODEN
	 */

	/**
	 * get id der Subcription
	 * 
	 * @return id
	 */
	public UUID getId() {
		return id;
	}

	/**
	 * set id der Subscription
	 * 
	 * @param id
	 */
	public void setId(UUID id) {
		this.id = id;
	}

	/**
	 * get validUntil (Gueltigkeitsdatum) der Subscription
	 * 
	 * @return validUntil
	 */
	public Date getValidUntil() {
		return validUntil;
	}

	/**
	 * set validUntil (Gueltigkeitsdatum) der Subscription
	 * 
	 * @param validUntil
	 */
	public void setValidUntil(Date validUntil) {
		this.validUntil = validUntil;
	}

	/**
	 * get productId des Products, fuer das die Subscription gemacht wird
	 * 
	 * @return productId
	 */
	public int getProductId() {
		return productId;
	}

	/**
	 * set productId des Products, fuer das die Subscription gemacht wird
	 * 
	 * @param remoteProductId
	 */
	public void setProductId(int remoteProductId) {
		this.productId = remoteProductId;
	}

	/**
	 * get subscriptionStatus
	 * 
	 * @return subscriptionStatus
	 */
	public String getSubscriptionStatus() {
		Date d = new Date();
		if (this.validUntil.getTime() < d.getTime())
			return "inactive";
		else
			return "active";
	}

	/**
	 * set subscriptionStatus
	 * 
	 * @param subscriptionStatus
	 */
	public void setSubscriptionStatus(String subscriptionStatus) {
		this.subscriptionStatus = subscriptionStatus;

	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

}
