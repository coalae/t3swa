package CompositePattern;

import java.io.Serializable;

/**
 * Die Klasse Product ist die model Ober-Klasse fuer Produkte.
 * Davon werden 3 Produkte abgeleitet.
 * @author Cordula Eggerth
 *
 */
public class Product implements Serializable {
	
	/**
	 * Instanzvariablen
	 */
	private static final long serialVersionUID = 1L;
	private int productId;
	private String name;   
	private String description;
	private double vatPercent; // umsatzsteuer in prozent	
	private Plan plan;     // 1 product hat jeweils 2 plans, aus denen 1 ausgewaehlt wird bei erstellung
	
	/**
	 * Konstruktor
	 * @param productId
	 * @param name
	 * @param description
	 * @param vatPercent
	 * @param plan
	 */
	public Product(int productId, String name, String description, double vatPercent, Plan plan) {
		this.productId = productId;
		this.name = name;
		this.description = description;
		this.vatPercent = vatPercent;
		this.plan = plan;
	}	

	/**
	 * GET- UND SET-METHODEN 
	 */
	
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

	/**
	 * get name 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * set name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/** 
	 * get description
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * set description
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * get vatPercent
	 * @return vatPercent
	 */
	public double getVatPercent() {
		return vatPercent;
	}

	/** set VatPercent
	 * @param vatPercent
	 */
	public void setVatPercent(double vatPercent) {
		this.vatPercent = vatPercent;
	}

	/**
	 * get plan
	 * @return plan
	 */
	public Plan getPlan() {
		return plan;
	}

	/**
	 * set plan
	 * @param plan
	 */
	public void setPlan(Plan plan) {
		this.plan = plan;
	}
	
}
