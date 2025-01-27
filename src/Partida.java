package src;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Partida {

	private int numLinea = 0, numLineas = 0, numBatalla = 1;
	private boolean fin = false;
	private String puntuaciones = "";

	public Partida(Bosque bosque, Jugadores jugadores, LagoSagrado lago, TemploMaldito templo, boolean reparto,
			String nombreFicheroReparto, boolean partida, String nombreFicheroPartida, boolean error) {

		// Si hay reparto, calcular cuantas lineas hay
		if (reparto == true) {
			Scanner entrada = null;

			try {
				entrada = new Scanner(new FileInputStream(nombreFicheroReparto));
			} catch (FileNotFoundException e) {
				error = true;
				System.out.println("\nNo existe el fichero " + nombreFicheroReparto);
				System.exit(-1);
			}

			while (entrada.hasNextLine()) {
				entrada.nextLine();
				numLineas++;
			}
		}

		PrintWriter salida = null;

		// Si hay partida, crear fichero
		if (partida == true) {
			try {
				salida = new PrintWriter(nombreFicheroPartida);
			} catch (FileNotFoundException e) {
				error = true;
			}
		}

		while (fin == false) {
			// Se celebra una batalla
			Batalla batalla = new Batalla(bosque, jugadores, lago, templo, reparto, nombreFicheroReparto, numLinea,
					partida, numBatalla);

			if (partida == true) {
				salida.println(batalla.getResultado());
			} else {
				System.out.println(batalla.getResultado());
			}
			numBatalla++;

			// Si hubo reparto, actualizar la linea
			if (reparto == true) {

				if (numLinea == numLineas - 1) {
					numLinea = 0;
				} else {
					numLinea++;
				}
			}

			// Se comprueba si quedan criaturas
			if (bosque.comprobarTodasNeutralizadas() == true) {
				fin = true;
			}

			// Se comprueba si hay ganador
			if (jugadores.comprobarDiezPuntos() == true) {
				fin = true;
			}

			if (fin == true) {
				if (partida == true) {
					salida.println("VISITAS A LOS LUGARES SAGRADOS:\n  Lago Sagrado: " + lago.mostrarVisitas()
							+ "\n  Templo Maldito: " + templo.mostrarVisitas() + "\n\nPUNTUACIONES:");
				} else {
					System.out.println("VISITAS A LOS LUGARES SAGRADOS:\n  Lago Sagrado: " + lago.mostrarVisitas()
							+ "\n  Templo Maldito: " + templo.mostrarVisitas() + "\n\nPUNTUACIONES:");
				}

				for (int i = 0; i < jugadores.getNumJugadores(); i++) {
					if (jugadores.getJugador(i).equals(jugadores.getGanador())) {
						puntuaciones = puntuaciones + "  " + jugadores.getJugador(i).getNombre() + " ("
								+ jugadores.getJugador(i).getID() + ") = " + jugadores.getJugador(i).getPuntos()
								+ " (VENCEDOR)" + "\n";
					} else {
						puntuaciones = puntuaciones + "  " + jugadores.getJugador(i).getNombre() + " ("
								+ jugadores.getJugador(i).getID() + ") = " + jugadores.getJugador(i).getPuntos() + "\n";
					}
				}
				if (partida == true) {
					salida.println(puntuaciones);
				} else {
					System.out.println(puntuaciones);
				}
			}
		}
		if (partida == true) {
			salida.close();
		}
	}
}