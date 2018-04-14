package iteratorPattern;

import java.util.Iterator;
/**
 * Interface für Collections of Subscriptions
 * @author Martin
 *
 */
public interface SubscriptionIterator {
	
	@SuppressWarnings("rawtypes")
	public Iterator createSubscriptionIterator();

}




