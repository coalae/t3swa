package ConnectionStrategyPattern;

import AbstractFactoryPattern.IConnectionFactory;
import AbstractFactoryPattern.MockupConnectionFactory;
import Logic.IConnection;

/**
 * MockupConnectionStrategy implementiert das Interface IConnectionStrategy. Es legt eine neue
 * MockupConnection an und gibt diese an die aufrufende Klasse zurueck. Die Klasse ist Teil des
 * ConnectionStrategyPattern. 
 * @author Cordula Eggerth
 *
 */
public class MockupConnectionStrategy implements IConnectionStrategy {
	
	/**
	 * execute legt eine neue MockupConnection an und gibt diese zurueck.
	 * @param mode
	 * @return IConnection
	 */
	@Override
	public IConnection execute(String mode) {

		IConnectionFactory factory = null;
		factory = new MockupConnectionFactory();
  		IConnection connection = factory.createConnection();
  		return connection;
	
	}
}
