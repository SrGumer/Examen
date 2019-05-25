package EJERCICIOS_CLASE;

import java.io.FileWriter;
import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class GestorDepartamentos {

	private final String[] DPTOS={"ventas", "facturacion", "informatica"};
	private final long[] FACT={80000, 100000, 50000};
	private String nomfich;
	
	public GestorDepartamentos(String nomfich){
		this.nomfich=nomfich;
	}
	
	public void crearXML(){
		Element root;
		Document doc=new Document(root=new Element("departamentos"));
		for (int i = 0; i < FACT.length; i++) {
			Element Departamento=new Element("departamento");
			Departamento.setAttribute("nombre", DPTOS[i]);
			Departamento.addContent(new Element("facturacion").setText(String.valueOf(FACT[i])));
			root.addContent(Departamento);
		}
		XMLOutputter serializador= new XMLOutputter(Format.getPrettyFormat());
		try {
			serializador.output(doc, new FileWriter("deptos.xml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		GestorDepartamentos gestor = new GestorDepartamentos("deptos.xml");
		gestor.crearXML();
		
	}
}
