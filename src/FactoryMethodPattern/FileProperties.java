package FactoryMethodPattern;

/**Diese Klasse speichert die ausgelesenen Benutzerdaten für die API-Schnittstelle.
 * Bewusst wurden keine set-Methoden erzeugt, um die Benutzerdaten vor Korruption zu bewahren.
 * Das Objekt ist immutable.
 * 
 * @author Sandra Gabriela Hofmarcher
 *
 */
public class FileProperties implements IProperties {
	
	/**Benutzername bzw. E-Mailadresse für FastBill
	 * 
	 */
	private String userMail;
	
	/**ID bzw. API-Key für FastBill
	 * 
	 */
	private String id;
	
	/**Der Konstruktor erhält die Variablen userMail und id über das Interface IProperties
	 * 
	 * @param userMail
	 * @param id
	 */
	FileProperties(String userMail, String id){
		this.userMail = userMail;
		this.id = id;
	}

	@Override
	public String getUserMail() {
		return this.userMail;
	}

	@Override
	public String getID() {
		return this.id;
	}



}
