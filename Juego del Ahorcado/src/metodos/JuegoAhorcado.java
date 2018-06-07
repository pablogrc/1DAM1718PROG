package metodos;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.SwingUtilities;

import parteGrafica.Main;

public class JuegoAhorcado {

	ArrayList<String> palabras = new ArrayList<String>();
	String palabraAdivinar = "";
	char[] palabraJugador;
	int errores = 0;

	public void LeerPalabras() throws IOException {
		BufferedReader bf = new BufferedReader(new FileReader("palabras.txt"));
		while (bf.ready()) {
			String Linea = bf.readLine();
			palabras.add(Linea);

		}

	}

	public void IniciarJuego() {
		Random r = new Random();
		int posicion = r.nextInt(palabras.size());
		palabraAdivinar = palabras.get(posicion);
		palabraJugador = new char[palabraAdivinar.length()];

		for (int i = 0; i < palabraAdivinar.length(); i++) {
			palabraJugador[i] = '-';
		}

	}

	public String MostrarPalabra() {
		return String.valueOf(palabraJugador);
	}


	public boolean comprobarLetra(char c) {
		boolean acierto = false;

		for (int i = 0; i < palabraJugador.length; i++) {
			if (palabraAdivinar.charAt(i) == c) {
				palabraJugador[i] = palabraAdivinar.charAt(i);
				acierto = true;
			}

		}
		if (acierto==false) {
			errores++;
		}
		return acierto;
	}
	public void Jugar() {
		try {
			LeerPalabras();
		} catch (IOException e) {

			e.printStackTrace();
		}
		IniciarJuego();
		System.out.println(MostrarPalabra());
		do {
			
			System.out.println("Introducir caracter");
			Scanner teclado = new Scanner(System.in);

			char c = teclado.next().charAt(0);

			if(!comprobarLetra(c))
			{
				mostrarVentana();
			}
			System.out.println(MostrarPalabra());
		} while (errores <=10);
	}

	public void mostrarVentana(){
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run() {
				Main m=new Main(errores);
				m.setVisible(true);
			}
		});
	}
	public static void main(String[] args) {
		
		JuegoAhorcado j = new JuegoAhorcado();
		j.Jugar();
		

	}
}
