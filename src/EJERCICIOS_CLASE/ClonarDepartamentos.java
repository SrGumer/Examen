package EJERCICIOS_CLASE;

import java.io.FileWriter;
import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class ClonarDepartamentos {

	public static void main(String[] args) {
		
		SAXBuilder builder = new SAXBuilder();
		try {
			Document doc = builder.build("deptos.xml");
			XMLOutputter serializador = new XMLOutputter(Format.getPrettyFormat());
			
			Element depto = new Element("departamento");
			depto.setAttribute("nombre","personal");
			depto.addContent(new Element ("facturacion").setText("45000"));
			Element root = doc.getRootElement();
			root.addContent(depto);
			serializador.output(doc, new FileWriter("deptos2.xml"));
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
