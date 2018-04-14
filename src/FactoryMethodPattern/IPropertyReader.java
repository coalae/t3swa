package FactoryMethodPattern;

/**Das Interface dient zur Abstraktion der Klasse und gewährt die nötige Flexibilität zur Laufzeit.
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
