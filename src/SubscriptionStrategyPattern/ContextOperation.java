package SubscriptionStrategyPattern;

import Model.Subscription;

/**
 * Die Klasse ermoegtlicht die Ausfuerhung der Methoden des SubscriptionStrategyPattern je gewaehlter Strategie bzw. derzeitigen State
 * @author Sandra Gabriela Hofmarcher
 *
 */

public class ContextOperation {
	
	/**
	 * Variable fuer den Zugriff auf das Interface ISubscriptionState
	 */
	private ISubscriptionState strategy;
	

	/**
	 * set-Methode fuer strategy
	 * @param strategy
	 */
	public void setStrategy(ISubscriptionState strategy){
		this.strategy = strategy;
	}
	
	/**
	 * get-Methode fuer strategy
	 * @return strategy
	 */
	public ISubscriptionState getStrategy(){
		return this.strategy;
	}
	
	/**
	 * Methode um das Verhalten des States zu delegieren, wenn die Subscription gestartet wird
	 */
	void startSubscription(Subscription subscription){
		strategy.startSubscription(subscription);
	}
    
	/**
	 * Methode um das Verhalten des States zu delegieren, wenn die Subscription abgebrochen wird
	 */
	void cancelSubscription(Subscription subscription){
		strategy.cancelSubscription(subscription);
	}
	
	/**
	 * Methode um das Verhalten des States zu delegieren, wenn die Zahlung eingangen ist
	 */
	void markPaymentReceived(Subscription subscription){
		strategy.markPaymentReceived(subscription);
	}
	
	/**
	 * Methode um das Verhalten des States zu delegieren, wenn die Zahlung noch nicht eingegangen ist
	 */
	void markPaymentPending(Subscription subscription){
		strategy.markPaymentPending(subscription);
	}

	
}
