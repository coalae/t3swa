package FactoryMethodPattern;

/**Das Interface dient zur Abstraktion der Klasse, um die Fexibilität zur Laufzeit zu gewähren.
 * 
 * @author Sandra Gabriela Hofmarcher
 *
 */
public interface IProperties {
	
	/**Methode um die Benutzer- E-Mail zu erhalten
	 * @param email 
	 * 
	 * @return userEmail
	 */
	String getUserMail();
	
	/**Methode um den API-Key zu erhalten
	 * 
	 * @return id bzw. API-Key
	 */
	String getID();

}
