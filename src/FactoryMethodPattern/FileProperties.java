package FactoryMethodPattern;

/**Diese Klasse speichert die ausgelesenen Benutzerdaten f�r die API-Schnittstelle.
 * Bewusst wurden keine set-Methoden erzeugt, um die Benutzerdaten vor Korruption zu bewahren.
 * Das Objekt ist immutable.
 * 
 * @author Sandra Gabriela Hofmarcher
 *
 */
public class FileProperties implements IProperties {
	
	/**Benutzername bzw. E-Mailadresse f�r FastBill
	 * 
	 */
	private String userMail;
	
	/**ID bzw. API-Key f�r FastBill
	 * 
	 */
	private String id;
	
	/**Der Konstruktor erh�lt die Variablen userMail und id �ber das Interface IProperties
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
