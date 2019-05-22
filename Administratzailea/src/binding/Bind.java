package binding;
import java.io.File;
import java.io.StringReader;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
public class Bind {
	
	public Bind() {
		
	}
	
	public Erabiltzaileak bindErabiltzaileak(String xmlCode) {
		Erabiltzaileak erabiltzaileak = null;
		
		try {

			//File f = new File("erabiltzaileak.xml");
			StringReader string = new StringReader(xmlCode);
			JAXBContext jc = JAXBContext.newInstance(Erabiltzaileak.class);

			Unmarshaller ju = jc.createUnmarshaller();
			erabiltzaileak = (Erabiltzaileak) ju.unmarshal(string);
			//System.out.println(erabiltzaileak);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
		return erabiltzaileak;
	}
	
	/*public static void main(String[] args) {
		System.out.println("HASI");
		Bind b = new Bind();
		b.bindErabiltzaileak();
		System.out.println("AMAITU");
	}*/
}
