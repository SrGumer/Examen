package EJERCICIOS_CLASE;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class DepartamentosMas50000 {

	
	public static void main(String[] args) {
	
		SAXBuilder builder = new SAXBuilder();
		try {
			Document doc = builder.build("deptos2.xml");
			Element root = doc.getRootElement();
			Element root2 = new Element("departamentos");
			List hijos = root.getChildren();
			Iterator<Element> it = hijos.iterator();
			while(it.hasNext()){
				Element hijo = it.next();
				if(Integer.parseInt(hijo.getChild("facturacion").getText())>50000){
					it.remove();
					hijo.detach();
					root2.addContent(hijo);
					//root2.addContent(hijo.clone());
				}
			}
			Document doc2 = new Document(root2);
			XMLOutputter serializador = new XMLOutputter(Format.getPrettyFormat());
			serializador.output(doc2, System.out);
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			serializador.output(hijos, System.out);
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
