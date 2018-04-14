package FactoryMethodPattern;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**Die Klasse liest die benoetigten Benutzerdaten aus der Datei aus und uebergibt sie der Schnittstelle IProperties
 * 
 * @author Sandra Gabriela Hofmarcher
 *
 */

public class PropertiesFilePropertyReader implements IPropertyReader {

	/**
	 * Name bzw Path der zu lesenden Datei, in welcher die ben�tigten Benutzerdaten vermerkt sind
	 */
	private String fileName= "config.properties";
	
	
	@Override
	public IProperties readProperties() {
		
		IProperties iprop = null;
		InputStream input = null;
		
		
		try{
			Properties prop = new Properties();	
			input = new FileInputStream(fileName);
			
			prop.load(input);
			
			iprop = new FileProperties(prop.getProperty("email"), prop.getProperty("key"));
			
		} catch(IOException e){
			e.printStackTrace();
		}finally{
			if(input != null){
				try{
					input.close();
				}catch (IOException e){
					e.printStackTrace();
				}
			}
		}
		return iprop;
	}

}
