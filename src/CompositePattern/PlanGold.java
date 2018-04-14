package CompositePattern;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Die Klasse PlanGold wird aus Plan abgeleitet und definiert den monatlichen Preis, den man fuer eine 
 * Produkt Subscription bezahlen muss.
 * Die Klasse implementiert die Interfaces Serializable und IOfferComponent (letzteres ist Teil des CompositePattern).
 * @author Cordula Eggerth
 *
 */
public class PlanGold extends Plan implements Serializable, IOfferComponent {

	/**
	 * Instanzvariablen
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Konstruktor
	 * @param monthlyRate
	 */
	public PlanGold(double monthlyRate) {
		super(monthlyRate);		 
	}
	
	
	/**
	 * Implementierung der getOfferComponentType Methode des Interface IOfferComponent, 
	 * womit ermittelt wird, welche Angebotskomponente es ist. 
	 */
	@Override
	public ArrayList<String> getOfferComponentType() {
		ArrayList<String> offerComponentType = new ArrayList<String> ();
		offerComponentType.add("PlanGold");
		return offerComponentType;
	}
	
}
