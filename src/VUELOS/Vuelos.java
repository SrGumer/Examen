package VUELOS;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class Vuelos {

	private Document doc;
	
	public Vuelos(String nomFich){
		SAXBuilder builder = new SAXBuilder();
		try {
			doc = builder.build(nomFich);		
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> destinosDesde(String origen){
		ArrayList<String> destinos = new ArrayList<String>();
		Element root = doc.getRootElement();
		List hijos = root.getChildren();
		Iterator <Element> it = hijos.iterator();
		while(it.hasNext()){
				Element hijo = it.next();
				if(hijo.getChildText("origen").equals(origen)){
					destinos.add(hijo.getChildText("destino"));
				}
		}		
		return destinos;	
	}
	
	public void vuelosPosterioresA(String horaMinima){
		Element root = doc.getRootElement();
		List hijos = root.getChildren();
		Iterator<Element> it = hijos.iterator();

		String[] horaMin = horaMinima.split(":");
		while(it.hasNext()){
			Element hijo = it.next();
			String[] horas;
			horas=hijo.getChildText("hora").split(":");
			if(Integer.parseInt(horas[0]) == Integer.parseInt(horaMin[0]) && Integer.parseInt(horas[1]) > Integer.parseInt(horaMin[1])){
				System.out.println(hijo.getChildText("origen") + " - " + hijo.getChildText("destino"));
			
			}else if(Integer.parseInt(horas[0]) > Integer.parseInt(horaMin[0])){
				System.out.println(hijo.getChildText("origen") + " - " + hijo.getChildText("destino"));
			}			
		}
	}
	
	public void verVuelo(Element eVuelo){
		XMLOutputter serializador = new XMLOutputter(Format.getPrettyFormat());
		try {
			serializador.output(eVuelo, System.out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Vuelos v1 = new Vuelos("vuelos.xml");
		System.out.println(v1.destinosDesde("Bilbao"));
		v1.vuelosPosterioresA("12:00");
		v1.verVuelo(v1.doc.getRootElement().getChild("vuelo"));
	}
}