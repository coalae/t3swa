package SubscriptionStrategyPattern;

import Model.Subscription;

/**
 * Diese Klasse ist Teil des SubscriptionStrategyPatterns und setzt den Status je nach Event bzw. aufgerufener Methode
 * @author Sandra Gabriela Hofmarcher
 *
 */

public class ActiveSubscriptionState implements ISubscriptionState {


	@Override
	public void cancelSubscription(Subscription subscription) {
		subscription.setSubscriptionStatus("inactive");
		
	}

	@Override
	public void markPaymentReceived(Subscription subscription) {
		subscription.setSubscriptionStatus("active");
		
	}

	@Override
	public void markPaymentPending(Subscription subscription) {
		subscription.setSubscriptionStatus("suspended");
		
	}

	@Override
	public void startSubscription(Subscription subscription) {
		subscription.setSubscriptionStatus("active");
		
	}

}
