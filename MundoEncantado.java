
public class MundoEncantado {

	public static void main(String[] args) { // Main

		boolean reparto = false, partida = false, salidaFichero = false;

		// Si hay modo instrucciones:

		if (args[0].equals("-i")) {
			if (args[2].equals("-o")) {
				salidaFichero = true;
			}
			new Instrucciones(args[1], salidaFichero, args[3]);
			return;
		}

		// Si hay modo normal, se crea el bosque y los jugadores, y se leen sus ficheros

		Bosque bosque = new Bosque(args[3]);

		Jugadores jugadores = new Jugadores(args[1]);

		// Se crean los lugares sagrados

		LagoSagrado lago = new LagoSagrado();

		TemploMaldito templo = new TemploMaldito();

		// Se comprueba si hay reparto
		if (args.length > 4) {

			if (args[4].equals("-r")) {
				reparto = true;
			}
		}

		// Se comprueba si hay volcado del resultado en fichero
		if (args.length > 6) {

			if (args[6].equals("-o")) {
				partida = true;
			}
		}

		// Se ejecuta la partida

		if (reparto == false && partida == false) {
			new Partida(bosque, jugadores, lago, templo, reparto, null, partida, null, false);
		} else {
			if (reparto == false && partida == true) {
				new Partida(bosque, jugadores, lago, templo, reparto, null, partida, args[7], false);
			} else {
				if (reparto == true && partida == false) {
					new Partida(bosque, jugadores, lago, templo, reparto, args[5], partida, null, false);
				} else {
					new Partida(bosque, jugadores, lago, templo, reparto, args[5], partida, args[7], false);

				}
			}
		}

	}
}