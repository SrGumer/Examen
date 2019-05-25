package EJERCICIOS_CLASE;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class XMLFactorial {

	private static long factorial(double num){
		long resultado=1;
		while (num>0){
			resultado=(long) (num*resultado);
			num-=1;
		}
		return resultado;	
	}
	public static void main(String[] args) {
		int resp=0;
		try {
			resp=Integer.parseInt(JOptionPane.showInputDialog(null, "Cuantos quieres?"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		Element root=new Element("factoriales");
		Document doc=new Document(root);
		for (int i = 1; i <= resp; i++) {
			Element hijo=new Element("factorial");
			hijo.setAttribute("de", String.valueOf(i));
			hijo.setText(String.valueOf(factorial(i)));
			root.addContent(hijo);
		}
		XMLOutputter serializador = new XMLOutputter(Format.getPrettyFormat());
		try {
			serializador.output(doc, new FileWriter("fact.xml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List lista=root.getChildren();
		Iterator it = lista.iterator();
		while(it.hasNext()){
			Element hijos=(Element) it.next();
			if(Integer.parseInt(hijos.getText())>99){
				System.out.println(hijos.getAttribute("de"));
			}
		}
	}
}
