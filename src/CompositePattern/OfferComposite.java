package CompositePattern;

import java.util.ArrayList;

/**
 * OfferComposite implementiert das Interface IOfferComponent und ist Teil des CompositePatterns.
 * Die Klasse bietet die Moeglichkeit, das Angebot der Firma gesamt oder teilweise aufzulisten, 
 * wodurch ein OfferComposite durch Hinzufuegen der einzelnen Bestandteile (Products und Plans) 
 * gebildet wird.
 * Die Klasse bietet Methoden zum Hinzufuegen und Entfernen von IOfferComponent-Objekten.
 * @author Cordula Eggerth
 * 
 */
public class OfferComposite implements IOfferComponent {

	/**
	 * Instanzvariable 
	 */
	private ArrayList<IOfferComponent> offerComponentList;
	
	/**
	 * Konstruktor
	 */
	public OfferComposite () {
		this.offerComponentList = new ArrayList<IOfferComponent>();
	}
	
	/**
	 * GET -UND SET-METHODEN
	 */
	
	/**
	 * get offerComponentList
	 * @return offerComponentList
	 */
	public ArrayList<IOfferComponent> getOfferComponentList() {
		return offerComponentList;
	}

	/** 
	 * set offerComponentList
	 * @param offerComponentList
	 */
	public void setOfferComponentList(ArrayList<IOfferComponent> offerComponentList) {
		this.offerComponentList = offerComponentList;
	}	
	
	/** 
	 * Implementierung der Methode getOfferComponentType aus dem Interface IOfferComponent.
	 * Sie gibt eine Liste aller OfferComponentTypes aus, die gerade im Angebot vorhanden sind.
	 */
	@Override
	public ArrayList<String> getOfferComponentType() {
		
		ArrayList<String> offerComponentType = new ArrayList<String> ();
		
		for(int i=0;i<this.offerComponentList.size();i++){
			offerComponentType.add(offerComponentList.get(i).getOfferComponentType().get(0));
		}
		
		return offerComponentType;
	}
	
	/**
	 * addOfferComponent fuegt eine offerComponent zur offerCompositeList hinzu
	 * @param offerComponent
	 */
	public void addOfferComponent(IOfferComponent offerComponent){
		offerComponentList.add(offerComponent);
	}

	/** 
	 * removeOfferComponent loescht eine offerComponent aus der offerCompositeList
	 * @param offerComponent
	 */
	public void removeOfferComponent(IOfferComponent offerComponent){
		offerComponentList.remove(offerComponent);
	}
	
	
	/**
	 * getChild liefert anhand eines uebergebenen componentType die jeweilige IOfferComponent in
	 * der offerComponentList.
	 * @param componentType
	 */
	public IOfferComponent getChild(String componentType) {
		for(int i=0;i<this.offerComponentList.size();i++) {
			if(this.offerComponentList.get(i).getOfferComponentType().get(0).equals(componentType)){
				return this.offerComponentList.get(i);
			}
		}
		return null;
	}

}
