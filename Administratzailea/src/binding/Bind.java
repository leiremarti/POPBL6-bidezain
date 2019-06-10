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

			StringReader string = new StringReader(xmlCode);
			JAXBContext jc = JAXBContext.newInstance(Erabiltzaileak.class);

			Unmarshaller ju = jc.createUnmarshaller();
			erabiltzaileak = (Erabiltzaileak) ju.unmarshal(string);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
		return erabiltzaileak;
	}
	
}
