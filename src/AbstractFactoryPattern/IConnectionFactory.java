package AbstractFactoryPattern;

import Logic.IConnection;

/**
 * IConnectionFactory ist das Interface, das als Grundlage fuer die Erzeugung einer Factory dient.
 * Es ist Teil des Abstract Factory Patterns.
 * @author Cordula Eggerth
 */
public interface IConnectionFactory {

	/**
	 * createConnection erzeugt entweder eine FastBillConnection (mit Verwendung der FastBillAPI) oder 
	 * eine MockupConnection (mit Verwendung der Daten aus dem File bzw. Serialisierung) 
	 * @return IConnection
	 */
	public IConnection createConnection();

}
