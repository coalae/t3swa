package ConnectionStrategyPattern;

import AbstractFactoryPattern.FastBillConnectionFactory;
import AbstractFactoryPattern.IConnectionFactory;
import Logic.IConnection;

/**
 * FastBillConnectionStrategy implementiert das Interface IConnectionStrategy. Es legt eine neue
 * FastBillConnection an und gibt diese an die aufrufende Klasse zurueck. Die Klasse ist Teil des
 * ConnectionStrategyPattern. 
 * @author Cordula Eggerth
 *
 */
public class FastBillConnectionStrategy implements IConnectionStrategy {

	/**
	 * execute legt eine neue FastBillConnection an und gibt diese zurueck.
	 * @param mode
	 * @return IConnection
	 */
	@Override
	public IConnection execute(String mode) {
		
  		IConnectionFactory factory = null;
		factory = new FastBillConnectionFactory();
  		IConnection connection = factory.createConnection();
  		return connection;
  		
	}

}
