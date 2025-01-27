import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Jugadores {

	private ArrayList<Jugador> jugadores = new ArrayList<Jugador>();

	public Jugadores() {
	}

	public Jugadores(String nombreFichero) {
		leerFichero(nombreFichero);
	}

	public boolean leerFichero(String nombreFichero) {
		Scanner entrada = null;
		String linea;
		boolean anadido;

		try {
			entrada = new Scanner(new FileInputStream(nombreFichero));
		} catch (FileNotFoundException e) {
			return true;
		}

		while (entrada.hasNextLine()) {
			anadido = false;

			linea = entrada.nextLine();

			String[] partes = linea.split(":");

			partes[partes.length - 1] = partes[partes.length - 1].substring(0, partes[partes.length - 1].length() - 1); // Borrar
																														// ultimo
																														// '}'

			for (Jugador j : jugadores) {
				if (j.getID().equals(partes[0])) {
					j.setID(partes[0]);
					j.setNombre(partes[1].substring(1, partes[1].length()));
					j.setPuntos(0);
					j.setPuntosAnteriores(0);
					anadido = true;
				}
			}

			if (anadido == false) {
				jugadores.add(new Jugador(partes[0], partes[1].substring(1, partes[1].length())));
			}
		}
		entrada.close();
		return false;
	}

	public int getNumJugadores() {
		return jugadores.size();
	}

	public Jugador getJugador(int n) {
		return jugadores.get(n);
	}

	public Jugador getGanador() {
		for (Jugador j : jugadores) {
			if (j.getPuntos() >= 10) {
				return j;
			}
		}
		return null;
	}

	public boolean anadirJugador(Jugador jugador) {
		for (Jugador j : jugadores) {
			if (j.getID().equals(jugador.getID())) {
				return true;
			}
		}
		jugadores.add(jugador);
		return false;
	}

	public boolean borrarJugador(String ID) {
		for (Jugador j : jugadores) {
			if (j.getID().equals(ID)) {
				jugadores.remove(j);
				return false;
			}
		}
		return true;
	}

	public boolean volcarJugadores(String nombreFichero) {
		PrintWriter salida;

		try {
			salida = new PrintWriter(nombreFichero);
		} catch (FileNotFoundException e) {
			return true;
		}

		for (Jugador j : jugadores) {
			salida.println(j);
		}

		salida.close();

		return false;
	}

	public boolean comprobarDiezPuntos() {
		for (Jugador j : jugadores) {
			if (j.getPuntos() >= 10) {
				return true;
			}
		}
		return false;
	}

	public String toString() { // Representar coleccion
		String resultado = ""; // Variable que se retornara con la informacion de las ninfas

		if (jugadores.size() == 0) { // Si la lista esta vacia, se informa de ello y no se hace nada mas
			resultado = "\nNo hay jugadores";
		}

		for (Jugador j : jugadores) { // Por cada ninfa de la lista, se guarda su informacion en una linea
			resultado += j + "\n";
		}

		return resultado;
	}
}