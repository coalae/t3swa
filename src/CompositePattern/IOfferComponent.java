package CompositePattern;

import java.util.ArrayList;

/**
 * Das Interface IOfferComponent ist Teil des CompositePatterns.
 * Es zeigt, welchen Kategorien von Komponenten sich das derzeitige Sortiment zusammensetzt.
 * @author Cordula Eggerth
 *
 */
public interface IOfferComponent {

	/**
	 * Die Methode getOfferComponentType liefert die Kategorie der Angebotskomponente zurueck.
	 * @return
	 */
	public ArrayList<String> getOfferComponentType();
}
