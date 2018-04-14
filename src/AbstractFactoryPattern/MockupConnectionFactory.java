package AbstractFactoryPattern;

import Logic.IConnection;
import Logic.MockupConnection;

/**
 * MockupConnectionFactory implementiert das Interface IConnectionFactory und dient zur Erzeugung einer 
 * mockupconnection. Es ist Teil des Abstract Factory Pattern.
 * @author Cordula Eggerth
 */
public class MockupConnectionFactory implements IConnectionFactory {
	
	/**
	 * createConnection implementiert die Methode aus dem Interface IConnectionFactory zur Erzeugung 
	 * einer connection - hier als MockupConnection.
	 */
	@Override
	public IConnection createConnection() {
		return new MockupConnection();
	}

}
