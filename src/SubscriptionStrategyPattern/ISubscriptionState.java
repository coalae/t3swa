package SubscriptionStrategyPattern;

import Model.Subscription;

public interface ISubscriptionState {

	void startSubscription(Subscription subscription);
    
	void cancelSubscription(Subscription subscription);
	
	void markPaymentReceived(Subscription subscription);
	
	void markPaymentPending(Subscription subscription);

}
