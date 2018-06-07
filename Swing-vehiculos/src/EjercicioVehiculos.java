import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class EjercicioVehiculos extends JFrame implements ActionListener {

	JTextField t;
	JTextField t2;
	JComboBox<String> combo = null;
	JComboBox<String> combo2 = null;
	JButton boton2;
	JButton boton3;
	
	DefaultComboBoxModel modelo = new DefaultComboBoxModel();	
	String[]tabla2= {"Marca","Modelo", "Motor", "Potencia"};
	DefaultTableModel modeloTabla = new DefaultTableModel(tabla2, 0);
JTable tabla= null;

	public EjercicioVehiculos() {
		super("Lista de vehículos con JTable");
		Container c = getContentPane();
		
		c.setLayout(new GridLayout(5,1));
		c.setPreferredSize(new Dimension(500, 400));
		
		JPanel p = new JPanel();
		p.setLayout(new FlowLayout());
		
		JPanel p1 = new JPanel();
		p1.setLayout(new FlowLayout());
		
		JPanel p2 = new JPanel();
		p2.setLayout(new FlowLayout());
		
		JPanel p3 = new JPanel();
		p3.setLayout(new FlowLayout());
		
		JPanel p4 = new JPanel();
		p4.setLayout(new FlowLayout());
		
		
		c.add(p);
		c.add(p1);
		c.add(p2);
		c.add(p3);
		c.add(p4);
		
		p.add(new JLabel("Marca:"));
		
		p1.add(new JLabel("Modelo:"));
		
		p2.add(new JLabel("Motor:"));
		
		p3.add(new JLabel("Potencia:"));
	
		JLabel l = new JLabel("");
		p1.add(l);
		
		t = new JTextField();
		t.setPreferredSize(new Dimension(200, 20));
		p1.add(t);

		JButton boton = new JButton("Agregar");
		p4.add(boton);
		boton.addActionListener(this);
		
		String[]motor= {"Electrico","Hibrido", "Diesel", "Gasolina"};
		combo = new JComboBox<>(motor);
		combo.setPreferredSize(new Dimension(200, 20));
		p2.add(combo);

	
		combo2 = new JComboBox<>();
		combo2.setPreferredSize(new Dimension(200, 20));
		p.add(combo2);


		boton2 = new JButton("Eliminar");
		p4.add(boton2);
		boton2.addActionListener(this);
		
		boton3 = new JButton("Limpiar");
		p4.add(boton3);
		boton3.addActionListener(this);
		
		boton3 = new JButton("Guardar");
		p4.add(boton3);
		boton3.addActionListener(this);
		
		t2 = new JTextField();
		t2.setPreferredSize(new Dimension(200, 20));
		p3.add(t2);
		
		String[]tabla2= {"Marca","Modelo", "Motor", "Potencia"};
		DefaultTableModel modelo = new DefaultTableModel(tabla2, 0);
		 tabla = new JTable(modelo);
		JScrollPane panelTabla = new JScrollPane(tabla);
		
		p4.add(panelTabla);
		cargarDatos();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();

	}

	public static void main(String[] args) {
		EjercicioVehiculos ventana = new EjercicioVehiculos();
		ventana.setVisible(true);
	}
	
	public void añadir(){
		String marca = (String) combo.getSelectedItem();
		String modelo = t.getText();
		String motor = (String) combo.getSelectedItem();
		String potencia = t2.getText();
		
		String datos[] = {marca, modelo, motor, potencia};
		modeloTabla.addRow(datos);
		tabla.setModel(modeloTabla);
		
	}
		public class recibirTabla implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent a) {
			JButton c = (JButton)a.getSource();
			añadir();
				
			}
			
		}
	
		public void cargarDatos() {
			BufferedReader entrada;
			try {
				entrada = new BufferedReader(new FileReader("marcas.txt"));

				while (entrada.ready()) {
					String linea = entrada.readLine();
					modelo.addElement(linea);
					
				}
				combo2.setModel(modelo);
				entrada.close();
			} catch (IOException i) {

				i.printStackTrace();
			}

		}
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();

		 if (b.getText().equals("Agregar")) {
			añadir();
			
			
			} else if (b.getText().equals("Guardar")) {
				JFileChooser fc = new JFileChooser();
				int resultado = fc.showSaveDialog(this);
				if (resultado == JFileChooser.APPROVE_OPTION) {
					File f = fc.getSelectedFile();
				try {
					BufferedWriter salida = new BufferedWriter(new FileWriter(f));
					for (int i = 0; i < modelo.getSize(); i++) {
						salida.write(modelo.getElementAt(i)+"\n");
					}
					salida.close();
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
		
		}
	}
	}
}


