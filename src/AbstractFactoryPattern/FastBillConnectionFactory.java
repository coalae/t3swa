package AbstractFactoryPattern;

import Logic.FastBillConnection;
import Logic.IConnection;

/**
 * FastBillConnectionFactory implementiert das Interface IConnectionFactory und dient zur Erzeugung einer 
 * fastbillconnection. Es ist Teil des Abstract Factory Pattern.
 * @author Cordula Eggerth
 */
public class FastBillConnectionFactory implements IConnectionFactory {

	/**
	 * createConnection implementiert die Methode aus dem Interface IConnectionFactory zur Erzeugung 
	 * einer connection - hier als FastBillConnection.
	 */
	@Override
	public IConnection createConnection() {
		return new FastBillConnection();
	}
}
