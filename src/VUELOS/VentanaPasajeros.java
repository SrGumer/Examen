package VUELOS;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VentanaPasajeros extends JFrame {

	private JComboBox<Integer> cmbPasajeros;
	private JPanel panel;
	private JButton btnGuardar;
	private JTextField[] textos;
	
	private VentanaPasajeros(){
		dibujar();
		eventos();
		pack();
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void dibujar(){
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		this.add(new JLabel("Introduce cantidad de pasajeros"));
		this.add(cmbPasajeros = new JComboBox<>(new Integer[] {1,2,3,4,5,6,7,8,9,10}));
		panel = new JPanel(new GridLayout(0, 2));
		this.add(panel);
		this.add(btnGuardar = new JButton("GUARDAR"));
		btnGuardar.setVisible(false);
	}
	
	private void eventos(){
		cmbPasajeros.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				panel.removeAll();
				int n = (int) cmbPasajeros.getSelectedItem();
				textos = new JTextField[n];
				for (int i = 0; i < textos.length; i++) {
					panel.add(new JLabel("Pasajero "+(i+1)));
					panel.add(textos[i] = new JTextField());
				}
				btnGuardar.setVisible(true);
				repaint();
				revalidate();
				pack();
			}	
		});
		
		btnGuardar.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				boolean textosValidos = true;
				String[] pasajeros = new String[textos.length];
				for (int i = 0; i < pasajeros.length; i++) {
					if(textos[i].getText().equals("")){
						textosValidos = false;
						textos[i].setBackground(Color.RED);
					}
					else{
						pasajeros[i] = textos[i].getText();
						textos[i].setBackground(Color.WHITE);
					}
				}
				if(textosValidos){
					guardarPasajeros(pasajeros);
				}
				else{
					JOptionPane.showMessageDialog(null, "No seras un genio, verdad?");
				}
			}
		});
	}
	
	private void guardarPasajeros(String[] pasajeros){
		try{
			PrintWriter pw = new PrintWriter("pasajeros.txt");
			for (int i = 0; i < pasajeros.length; i++) {
				pw.println(pasajeros[i]);
			}
			pw.close();
			JOptionPane.showMessageDialog(null, "Guardado correctamente");
			panel.removeAll();
			btnGuardar.setVisible(false);
			pack();
		}catch(IOException e){
			JOptionPane.showMessageDialog(null, "Error al gaurdar el archivo");
		}
	}
	
public static void main(String[] args) {
	
	new VentanaPasajeros();
	
}
}