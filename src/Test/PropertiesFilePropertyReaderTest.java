package Test;

import org.junit.Test;
import static org.junit.Assert.*;

import FactoryMethodPattern.IProperties;
import FactoryMethodPattern.IPropertyReader;
import FactoryMethodPattern.PropertiesFilePropertyReader;

public class PropertiesFilePropertyReaderTest {
	
	

	@Test
	public void testReadProperties() {

		IPropertyReader reader = new PropertiesFilePropertyReader();
		IProperties iprop = reader.readProperties();
		assertEquals(iprop.getUserMail(), "morri.kocht@gmail.com");
		assertEquals(iprop.getID(), "2d60347a07aa9b4196dab08f510321bfmqdln9fxgK2BuL4wzCfSpRYGhKibdtBl");
		
	}



}
