package ObserverPattern;

import java.util.Observable;
import java.util.UUID;
/**
 * Die Klasse SubscriptionStateSubject speichert sowohl die ID, als auch den Status der jeweiligen Subscription.
 * Sie erweitert die Klasse Observable und meldet an SubjectStateObserver, wenn sich der Status aendert.
 * @author Sandra Gabriela Hofmarcher
 *
 */
public class SubscriptionStateSubject extends Observable  {

	/**
	 * subID ist die ID der Subscription
	 */
	UUID subID;
	
	/**
	 * Status der Subscription
	 */
	String subState= null;

	/**
	 * Konstruktor
	 * @param subID
	 */
	public SubscriptionStateSubject(UUID subID){
		this.subID = subID;
	}
	
	
	/**
	 * Methode um die SubscriptionID zu erhalten
	 * @return subState
	 */
	public String getState(){
		return this.subState;	
	}
	
	/**
	 * Methode um den Status zu aendern. Gleichzeitig setzt diese Funktion ein Aenderungs-Flag und benachrichtigt die Observer
	 * @param subState
	 */
	public void setState(String subState){
		this.subState = subState;
		setChanged();
		notifyObservers();
	}

}
