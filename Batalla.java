import java.util.ArrayList;
import java.util.ListIterator;

public class Batalla {

	private int i, j, n = 0, puntosGanados, numLuchas = 0, numJug;
	private boolean todasMuertas, fin, j0, j1, j2, j3;
	private Jugador jugadorAtacante, jugadorDefensor;
	private Criatura criaturaAtacante, criaturaDefensora;
	private String resultadoBatalla, jugadoresIniciales, criaturasIniciales, finBatalla, puntosConseguidos,
			resultadoLucha = "", criaturasFinales, jugadoresFinales;

	public Batalla(Bosque bosque, Jugadores jugadores, LagoSagrado lago, TemploMaldito templo, boolean reparto,
			String nombreFichero, int numLinea, boolean partida, int numBatalla) {

		// Asignacion Criaturas (sin reparto)

		if (reparto == false) {
			if (bosque.getNumCriaturas() < jugadores.getNumJugadores()) {
				return;
			}

			for (i = 0; i < 3; i++) {

				for (j = 0; j < jugadores.getNumJugadores(); j++) {
					if (bosque.getNumCriaturas() == 0) {
						break;
					}
					Criatura c = bosque.getAleatoria();
					jugadores.getJugador(j).anadirCriatura(c);
				}
			} // Con reparto
		} else {

			new Reparto(nombreFichero, numLinea, bosque, jugadores);

		}

		// Escritura informacion inicial de la batalla

		for (i = 0; i < jugadores.getNumJugadores(); i++) {

			for (j = 0; j < jugadores.getJugador(i).getNumCriaturas(); j++) {

				if (j == 0) {

					criaturasIniciales = "(" + jugadores.getJugador(i).getCriatura(j).getID() + ","
							+ jugadores.getJugador(i).getCriatura(j).getSalud() + ")";
				} else {
					criaturasIniciales = criaturasIniciales + ",(" + jugadores.getJugador(i).getCriatura(j).getID()
							+ "," + jugadores.getJugador(i).getCriatura(j).getSalud() + ")";
				}

			}

			if (i == 0) {
				jugadoresIniciales = jugadores.getJugador(i).getID() + ":{" + criaturasIniciales + "}";
			} else {
				jugadoresIniciales = jugadoresIniciales + "," + jugadores.getJugador(i).getID() + ":{"
						+ criaturasIniciales + "}";
			}
		}

		resultadoBatalla = "BATALLA_" + numBatalla + " " + jugadoresIniciales;

		// Comprobacion jugadores inactivos

		for (i = 0; i < jugadores.getNumJugadores(); i++) {

			jugadores.getJugador(i).setInactividad(true);

			jugadores.getJugador(i).setInactividad(false);
			todasMuertas = true;

			for (j = 0; j < jugadores.getJugador(i).getNumCriaturas(); j++) {
				if (jugadores.getJugador(i).getCriatura(j).getSalud() > 0) {
					todasMuertas = false;
					break;
				}
			}
			if (todasMuertas == true) {
				jugadores.getJugador(i).setInactividad(true);
			}
		}

		// Lista jugadores activos

		ArrayList<Jugador> jugadoresActivos = new ArrayList<Jugador>();

		for (i = 0; i < jugadores.getNumJugadores(); i++) {
			if (jugadores.getJugador(i).getInactividad() == false) {
				jugadoresActivos.add(jugadores.getJugador(i));
			}
		}

		// Batalla

		while (fin == false) {

			// Hay 0 jugadores? Se termina la batalla

			if (jugadoresActivos.size() == 0) {
				fin = true;
				finBatalla = "  FIN BATALLA: No hay jugadores activos.";

				for (i = 0; i < jugadores.getNumJugadores(); i++) {
					if (i == 0) {
						puntosConseguidos = "  PUNTOS CONSEGUIDOS: " + jugadores.getJugador(i).getID() + "=0";
					} else {
						puntosConseguidos = puntosConseguidos + "," + jugadores.getJugador(i).getID() + "=0";
					}
				}
				resultadoBatalla = resultadoBatalla + "\n" + resultadoLucha + "\n" + finBatalla + "\n"
						+ puntosConseguidos + "\n";
				break;
			}

			// Comprobacion jugadores activos

			numJug = 0;
			j0 = false;
			j1 = false;
			j2 = false;
			j3 = false;

			// Ver cuales estan inactivos
			for (Jugador j : jugadoresActivos) {
				for (Criatura c : j.getCriaturas()) {
					if (c.getSalud() <= 0) {
						j.setInactividad(true);
					} else {
						j.setInactividad(false);
						break;
					}
				}
				// Los inactivos marcarlos para eliminar
				if (j.getInactividad() == true) {
					switch (numJug) {
						case 0:
							j0 = true;
							break;
						case 1:
							j1 = true;
							break;
						case 2:
							j2 = true;
							break;
						case 3:
							j3 = true;
							break;
					}
				}
				numJug++;
			}

			// Eliminar los inactivos

			if (j3 == true) {
				jugadoresActivos.remove(3);
			}
			if (j2 == true) {
				jugadoresActivos.remove(2);
			}
			if (j1 == true) {
				jugadoresActivos.remove(1);
			}
			if (j0 == true) {
				jugadoresActivos.remove(0);
			}

			// Hay 0 jugadores? Se termina la batalla

			if (jugadoresActivos.size() == 0) {
				fin = true;
				finBatalla = "  FIN BATALLA: No hay jugadores activos.";

				for (i = 0; i < jugadores.getNumJugadores(); i++) {
					if (i == 0) {
						puntosConseguidos = "  PUNTOS CONSEGUIDOS: " + jugadores.getJugador(i).getID() + "=0";
					} else {
						puntosConseguidos = puntosConseguidos + "," + jugadores.getJugador(i).getID() + "=0";
					}
				}

				resultadoBatalla = resultadoBatalla + "\n" + resultadoLucha + "\n" + finBatalla + "\n"
						+ puntosConseguidos + "\n";
				;
				break;
			}

			// Hay 1 jugador? Se le dan puntos y se termina la batalla

			if (jugadoresActivos.size() == 1) {

				fin = true;
				puntosGanados = 0;
				for (Criatura c : jugadoresActivos.get(0).getCriaturas()) {
					if (c.getSalud() > 0) {
						puntosGanados = puntosGanados + 2;
					}
				}

				jugadoresActivos.get(0).setPuntos(jugadoresActivos.get(0).getPuntos() + puntosGanados);
				finBatalla = "  FIN BATALLA: Solo hay un jugador activo.";

				for (i = 0; i < jugadores.getNumJugadores(); i++) {

					if (jugadores.getJugador(i).getID().equals(jugadoresActivos.get(0).getID())) {

						if (i == 0) {
							puntosConseguidos = "  PUNTOS CONSEGUIDOS: " + jugadores.getJugador(i).getID() + "="
									+ puntosGanados;
						} else {
							puntosConseguidos = puntosConseguidos + "," + jugadores.getJugador(i).getID() + "="
									+ puntosGanados;
						}
					} else {

						if (i == 0) {
							puntosConseguidos = "  PUNTOS CONSEGUIDOS: " + jugadores.getJugador(i).getID() + "=0";
						} else {
							puntosConseguidos = puntosConseguidos + "," + jugadores.getJugador(i).getID() + "=0";
						}
					}
				}
				resultadoBatalla = resultadoBatalla + "\n" + resultadoLucha + "\n" + finBatalla + "\n"
						+ puntosConseguidos + "\n";
				;
				break;
			}

			// Asignar jugadores

			if (n == 0) {
				jugadorAtacante = jugadoresActivos.get(0);
				jugadorAtacante.setAtaco(true);
				jugadorDefensor = jugadoresActivos.get(1);
				n++;
			} else {

				ListIterator<Jugador> it = jugadoresActivos.listIterator();

				while (it.hasNext()) {

					Jugador j = it.next();

					if (j.getAtaco() == true) {
						j.setAtaco(false);
						if (it.hasNext() == false) {
							jugadorAtacante = jugadoresActivos.get(0);
							jugadorAtacante.setAtaco(true);
							jugadorDefensor = jugadoresActivos.get(1);
							continue;
						} else {
							jugadorAtacante = it.next();
							jugadorAtacante.setAtaco(true);
						}
						if (it.hasNext() == false) {
							jugadorDefensor = jugadoresActivos.get(0);
							continue;
						} else {
							jugadorDefensor = it.next();
							continue;
						}
					}
					if (it.hasNext() == false) {
						jugadorAtacante = jugadoresActivos.get(0);
						jugadorAtacante.setAtaco(true);
						jugadorDefensor = jugadoresActivos.get(1);
					}

				}
			}

			/*
			 * switch (jugadoresActivos.size()) { case 2: if (n >= 2) { n = 0; } if (n == 1)
			 * { jugadorAtacante = jugadoresActivos.get(0); jugadorDefensor =
			 * jugadoresActivos.get(1); n++; } else { jugadorAtacante =
			 * jugadoresActivos.get(1); jugadorDefensor = jugadoresActivos.get(0); n++; }
			 * 
			 * break; case 3: if (n >= 3) { n = 0; } jugadorAtacante =
			 * jugadoresActivos.get(n); if (n == 2) { jugadorDefensor =
			 * jugadoresActivos.get(0); n = 0; } else { jugadorDefensor =
			 * jugadoresActivos.get(n + 1); n++; } break; case 4: if (n >= 4) { n = 0; }
			 * jugadorAtacante = jugadoresActivos.get(n); if (n == 3) { jugadorDefensor =
			 * jugadoresActivos.get(0); n = 0; } else { jugadorDefensor =
			 * jugadoresActivos.get(n + 1); n++; } break; }
			 */
			jugadorAtacante.ordenarPO();
			jugadorDefensor.ordenarCD();

			// Asignacion criatura atacante/defensora

			for (i = 0; i < jugadorAtacante.getNumCriaturas(); i++) {
				if (jugadorAtacante.getCriatura(i).getSalud() > 0) {
					criaturaAtacante = jugadorAtacante.getCriatura(i);
					break;
				}
			}

			for (i = 0; i < jugadorDefensor.getNumCriaturas(); i++) {
				if (jugadorDefensor.getCriatura(i).getSalud() > 0) {
					criaturaDefensora = jugadorDefensor.getCriatura(i);
					break;
				}

			}

			// Escribir estado inicial de la lucha

			if (numLuchas == 0) {
				resultadoLucha = "  LUCHA " + (numLuchas + 1) + ": " + jugadorAtacante.getID() + "-" + criaturaAtacante
						+ " --> " + jugadorDefensor.getID() + "-" + criaturaDefensora + "\n";
			} else {
				resultadoLucha = resultadoLucha + "  LUCHA " + (numLuchas + 1) + ": " + jugadorAtacante.getID() + "-"
						+ criaturaAtacante + " --> " + jugadorDefensor.getID() + "-" + criaturaDefensora + "\n";
			}

			// Lucha

			criaturaAtacante.atacar(criaturaDefensora);

			// Escribir resultados de la lucha

			for (i = 0; i < jugadores.getNumJugadores(); i++) {

				for (j = 0; j < jugadores.getJugador(i).getNumCriaturas(); j++) {

					if (j == 0) {

						criaturasFinales = "(" + jugadores.getJugador(i).getCriatura(j).getID() + ","
								+ jugadores.getJugador(i).getCriatura(j).getSalud() + ")";
					} else {
						criaturasFinales = criaturasFinales + ",(" + jugadores.getJugador(i).getCriatura(j).getID()
								+ "," + jugadores.getJugador(i).getCriatura(j).getSalud() + ")";
					}

				}

				if (i == 0) {
					jugadoresFinales = "  " + jugadores.getJugador(i).getID() + ":{" + criaturasFinales + "}";
				} else {
					jugadoresFinales = jugadoresFinales + "," + jugadores.getJugador(i).getID() + ":{"
							+ criaturasFinales + "}";
				}
			}

			resultadoLucha = resultadoLucha + jugadoresFinales + "\n";

			numLuchas++;

			// En caso de terminar por numLuchas
			if (numLuchas == 10) {

				finBatalla = "  FIN BATALLA: Ya se han producido 10 luchas.";

				// Vemos jugadores activos
				for (Jugador j : jugadoresActivos) {
					for (Criatura c : j.getCriaturas()) {
						if (c.getSalud() <= 0) {
							j.setInactividad(true);
						} else {
							j.setInactividad(false);
							break;
						}
					}

					// Si esta activo, se le dan puntos
					if (j.getInactividad() == false) {
						puntosGanados = 0;
						for (Criatura c : j.getCriaturas()) {
							if (c.getSalud() > 0) {
								puntosGanados = puntosGanados + 1;
							}
						}
						j.setPuntos(j.getPuntos() + puntosGanados);

					}

					for (i = 0; i < jugadores.getNumJugadores(); i++) {
						if (i == 0) {
							puntosConseguidos = "  PUNTOS CONSEGUIDOS: " + jugadores.getJugador(i).getID() + "="
									+ (jugadores.getJugador(i).getPuntos()
											- jugadores.getJugador(i).getPuntosAnteriores());
						} else {
							puntosConseguidos = puntosConseguidos + "," + jugadores.getJugador(i).getID() + "="
									+ (jugadores.getJugador(i).getPuntos()
											- jugadores.getJugador(i).getPuntosAnteriores());
						}
					}
				}
				resultadoBatalla = resultadoBatalla + "\n" + resultadoLucha + "\n" + finBatalla + "\n"
						+ puntosConseguidos + "\n";
				// Se termina la batalla
				fin = true;
			}
		}

		// Fin batalla, criaturas no neutralizadas a recuperarse

		for (i = 0; i < jugadores.getNumJugadores(); i++)

		{
			for (Criatura c : jugadores.getJugador(i).getCriaturas()) {
				if (c.getSalud() > 0) {
					if (c instanceof UsuarioLagoSagrado) {
						((UsuarioLagoSagrado) c).visitarLagoSagrado(lago);
					} else {
						((UsuarioTemploMaldito) c).visitarTemploMaldito(templo);
					}
				}
			}
		}

		// Criaturas al bosque

		for (i = 0; i < jugadores.getNumJugadores(); i++) {
			for (Criatura c : jugadores.getJugador(i).getCriaturas()) {
				bosque.anadirCriatura(c);
			}
			for (j = jugadores.getJugador(i).getNumCriaturas(); j > 0; j--) {
				jugadores.getJugador(i).eliminarCriatura(j - 1);
			}
		}
	}

	public String getResultado() {
		return resultadoBatalla;
	}
}