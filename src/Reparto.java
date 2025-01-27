import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Reparto {

	public Reparto(String nombreFichero, int numLinea, Bosque bosque, Jugadores jugadores) {
		Scanner entrada = null;
		String linea;

		int i = 0, j;

		try {
			entrada = new Scanner(new FileInputStream(nombreFichero));
		} catch (FileNotFoundException e) {
			System.out.println("\nNo existe el fichero " + nombreFichero);
			System.exit(-1);
		}

		do {
			linea = entrada.nextLine();
			i++;
		} while (i <= numLinea);

		String[] partes = linea.split("},");

		for (i = 0; i < partes.length; i++) {
			partes[i] = partes[i].substring(partes[i].indexOf("{") + 1).replace("}", "");
		}

		for (i = 0; i < jugadores.getNumJugadores(); i++) {
			String[] subpartes = partes[i].split(",");

			for (j = 0; j < subpartes.length; j++) {
				Criatura c = bosque.getCriatura(subpartes[j]);
				jugadores.getJugador(i).anadirCriatura(c);
			}
		}
	}
}