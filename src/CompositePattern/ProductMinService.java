package CompositePattern;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * ProductMinService wird von Product abgeleitet. 
 * @author Cordula Eggerth
 *
 */
public class ProductMinService extends Product implements Serializable, IOfferComponent {

	/**
	 * Instanzvariablen
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Konstruktor
	 * @param productId
	 * @param name
	 * @param description
	 * @param vatPercent
	 * @param plan
	 */
	public ProductMinService(int productId, String name, String description, double vatPercent, Plan plan) {
		super(productId, name, description, vatPercent, plan);
	}


	/**
	 * Implementierung der getOfferComponentType Methode des Interface IOfferComponent, 
	 * womit ermittelt wird, welche Angebotskomponente es ist. 
	 */
	@Override
	public ArrayList<String> getOfferComponentType() {
		ArrayList<String> offerComponentType = new ArrayList<String> ();
		offerComponentType.add("ProductMinService");
		return offerComponentType;
	}
	
}
