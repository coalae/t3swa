package FactoryMethodPattern;

/**Das Interface dient zur Abstraktion der Klasse und gew�hrt die n�tige Flexibilit�t zur Laufzeit.
 * 
 * @author Sandra Gabriela Hofmarcher
 *
 */
public interface IPropertyReader {
	
	/**Methode um die benoetigten Zugangsdaten aus einer Datei zu lesen
	 * 
	 * @return IProperties
	 */
	public IProperties readProperties();

}
