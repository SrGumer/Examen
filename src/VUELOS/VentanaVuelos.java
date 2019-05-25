package VUELOS;

import java.awt.BorderLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.omg.PortableServer.ServantRetentionPolicyValue;

public class VentanaVuelos extends JFrame{

	private JPanel panel;
	private DefaultListModel destinos;
	private Document doc;
	private JList destinosVuelos;	
	public VentanaVuelos(){
		SAXBuilder builder = new SAXBuilder();
		try {
			doc = builder.build("vuelos.xml");
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		dibujar();
		//eventos();
		pack();
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void dibujar(){
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		panel = new JPanel();
		this.add(new JLabel("Haz doble click en el destino deseado"));
		destinos = new DefaultListModel<>();
		cargarDestinos();	
		destinosVuelos = new JList<>(destinos);
		this.add(destinosVuelos);
	}
	
	public void cargarDestinos(){
		Element root = doc.getRootElement();
		List hijos = root.getChildren();
		Iterator <Element> it = hijos.iterator();
		while(it.hasNext()){
				Element hijo = it.next();	
				if(destinos.contains(hijo.getChildText("destino"))){
					
				}
				else{
				destinos.addElement(hijo.getChildText("destino"));
				}
		}
		System.out.println(destinos);
	}
	public void eventos(){
		destinosVuelos.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	public static void main(String[] args) {
		new VentanaVuelos();
	}

}
