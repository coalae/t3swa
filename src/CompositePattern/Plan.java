package CompositePattern;

import java.io.Serializable;

/**
 * Die Klasse Plan definiert den monatlichen Preis, den man fuer eine 
 * Produkt Subscription bezahlen muss.
 * @author Cordula Eggerth
 *
 */
public class Plan implements Serializable {

	/**
	 * Instanzvariablen
	 */
	private static final long serialVersionUID = 1L;
	private double monthlyRate;   // monatlich zu bezahlender Preis fuer das Produkt  

	/**
	 * Konstruktor
	 * @param monthlyRate
	 */
	/**
	 */
	public Plan(double monthlyRate) {
		this.monthlyRate = monthlyRate;
	}
	
	/**
	 * GET- UND SET-METHODEN
	 */
	
	/**
	 * get monthlyRate
	 * @return monthlyRate
	 */
	public double getMonthlyRate() {
		return monthlyRate;
	}

	/**
	 * set monthlyRate
	 * @param monthlyRate
	 */
	public void setMonthlyRate(double monthlyRate) {
		this.monthlyRate = monthlyRate;
	}
	
}
