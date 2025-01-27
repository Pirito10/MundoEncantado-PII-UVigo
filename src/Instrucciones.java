import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Instrucciones {

	public Instrucciones(String nombreFicheroInstrucciones, boolean salidaFichero, String nombreSalida) {

		Bosque bosque = new Bosque();
		Jugadores jugadores = new Jugadores();
		LagoSagrado lago = new LagoSagrado();
		TemploMaldito templo = new TemploMaldito();

		Scanner entrada = null;
		String linea;
		boolean error;

		try {
			entrada = new Scanner(new FileInputStream(nombreFicheroInstrucciones));
		} catch (FileNotFoundException e) {
			System.out.println("\nNo existe el fichero " + nombreFicheroInstrucciones);
			System.exit(-1);
		}

		PrintWriter salida = null;

		if (salidaFichero == true) {
			try {
				salida = new PrintWriter(nombreSalida);
			} catch (FileNotFoundException e) {
			}
		}

		while (entrada.hasNextLine()) {
			linea = entrada.nextLine();

			if (linea.isEmpty() == true) {
				continue;
			}

			if (linea.charAt(0) == '#') {
				continue;
			}

			String[] partes = linea.split(" ");

			String instruccion = partes[0];

			switch (instruccion) {
				case "CargarJugadores":
					error = cargarJugadores(partes[1], jugadores);
					if (error == false) {
						if (salidaFichero == true) {
							salida.println(partes[0] + " " + partes[1] + ": OK.");
						} else {
							System.out.println(partes[0] + " " + partes[1] + ": OK.");
						}
					} else {
						if (salidaFichero == true) {
							salida.println(partes[0] + " " + partes[1] + ": FAIL.");
						} else {
							System.out.println(partes[0] + " " + partes[1] + ": FAIL.");
						}
					}
					break;

				case "CrearJugador":
					String nombre = "";
					for (int i = 0; i < partes.length - 2; i++) {
						if (i == 0) {
							nombre = nombre + partes[i + 2];
						} else {
							nombre = nombre + " " + partes[i + 2];
						}
					}
					error = crearJugador(partes[1], nombre, jugadores);
					if (error == false) {
						if (salidaFichero == true) {
							salida.println(partes[0] + " " + partes[1] + " " + nombre + ": OK.");
						} else {
							System.out.println(partes[0] + " " + partes[1] + " " + nombre + ": OK.");
						}
					} else {
						if (salidaFichero == true) {
							salida.println(partes[0] + " " + partes[1] + " " + nombre + ": FAIL.");
						} else {
							System.out.println(partes[0] + " " + partes[1] + " " + nombre + ": FAIL.");
						}
					}
					break;

				case "BorrarJugador":
					error = borrarJugador(partes[1], jugadores);
					if (error == false) {
						if (salidaFichero == true) {
							salida.println(partes[0] + " " + partes[1] + ": OK.");
						} else {
							System.out.println(partes[0] + " " + partes[1] + ": OK.");
						}
					} else {
						if (salidaFichero == true) {
							salida.println(partes[0] + " " + partes[1] + ": FAIL.");
						} else {
							System.out.println(partes[0] + " " + partes[1] + ": FAIL.");
						}
					}
					break;

				case "VolcarJugadores":
					error = volcarJugadores(partes[1], jugadores);
					if (error == false) {
						if (salidaFichero == true) {
							salida.println(partes[0] + " " + partes[1] + ": OK.");
						} else {
							System.out.println(partes[0] + " " + partes[1] + ": OK.");
						}
					} else {
						if (salidaFichero == true) {
							salida.println(partes[0] + " " + partes[1] + ": FAIL.");
						} else {
							System.out.println(partes[0] + " " + partes[1] + ": FAIL.");
						}
					}
					break;

				case "CargarCriaturas":
					error = cargarCriaturas(partes[1], bosque);
					if (error == false) {
						if (salidaFichero == true) {
							salida.println(partes[0] + " " + partes[1] + ": OK.");
						} else {
							System.out.println(partes[0] + " " + partes[1] + ": OK.");
						}
					} else {
						if (salidaFichero == true) {
							salida.println(partes[0] + " " + partes[1] + ": FAIL.");
						} else {
							System.out.println(partes[0] + " " + partes[1] + ": FAIL.");
						}
					}
					break;

				case "CrearNinfa":
					error = crearNinfa(partes[1], partes[2], Integer.parseInt(partes[3]), Integer.parseInt(partes[4]),
							Integer.parseInt(partes[5]), Integer.parseInt(partes[6]), Integer.parseInt(partes[7]),
							bosque);
					if (error == false) {
						if (salidaFichero == true) {
							salida.println(
									partes[0] + " " + partes[1] + " " + partes[2] + " " + partes[3] + " " + partes[4]
											+ " " + partes[5] + " " + partes[6] + " " + partes[7] + ": OK.");
						} else {
							System.out.println(partes[0] + " " + partes[1] + " " + partes[2] + " " + partes[3] + " "
									+ partes[4] + " " + partes[5] + " " + partes[6] + " " + partes[7] + ": OK.");
						}
					} else {
						if (salidaFichero == true) {
							salida.println(
									partes[0] + " " + partes[1] + " " + partes[2] + " " + partes[3] + " " + partes[4]
											+ " " + partes[5] + " " + partes[6] + " " + partes[7] + ": FAIL.");
						} else {
							System.out.println(partes[0] + " " + partes[1] + " " + partes[2] + " " + partes[3] + " "
									+ partes[4] + " " + partes[5] + " " + partes[6] + " " + partes[7] + ": FAIL.");
						}
					}
					break;

				case "CrearOrco":
					error = crearOrco(partes[1], partes[2], Integer.parseInt(partes[3]), Integer.parseInt(partes[4]),
							Integer.parseInt(partes[5]), bosque);
					if (error == false) {
						if (salidaFichero == true) {
							salida.println(
									partes[0] + " " + partes[1] + " " + partes[2] + " " + partes[3] + " " + partes[4]
											+ " " + partes[5] + ": OK.");
						} else {
							System.out.println(partes[0] + " " + partes[1] + " " + partes[2] + " " + partes[3] + " "
									+ partes[4] + " " + partes[5] + ": OK.");
						}
					} else {
						if (salidaFichero == true) {
							salida.println(
									partes[0] + " " + partes[1] + " " + partes[2] + " " + partes[3] + " " + partes[4]
											+ " " + partes[5] + ": FAIL.");
						} else {
							System.out.println(partes[0] + " " + partes[1] + " " + partes[2] + " " + partes[3] + " "
									+ partes[4] + " " + partes[5] + ": FAIL.");
						}
					}
					break;

				case "CrearBruja":
					error = crearBruja(partes[1], partes[2], Integer.parseInt(partes[3]), Integer.parseInt(partes[4]),
							Integer.parseInt(partes[5]), Integer.parseInt(partes[6]), bosque);
					if (error == false) {
						if (salidaFichero == true) {
							salida.println(
									partes[0] + " " + partes[1] + " " + partes[2] + " " + partes[3] + " " + partes[4]
											+ " " + partes[5] + " " + partes[6] + ": OK.");
						} else {
							System.out.println(partes[0] + " " + partes[1] + " " + partes[2] + " " + partes[3] + " "
									+ partes[4] + " " + partes[5] + " " + partes[6] + ": OK.");
						}
					} else {
						if (salidaFichero == true) {
							salida.println(
									partes[0] + " " + partes[1] + " " + partes[2] + " " + partes[3] + " " + partes[4]
											+ " " + partes[5] + " " + partes[6] + ": FAIL.");
						} else {
							System.out.println(partes[0] + " " + partes[1] + " " + partes[2] + " " + partes[3] + " "
									+ partes[4] + " " + partes[5] + " " + partes[6] + ": FAIL.");
						}
					}
					break;

				case "CrearElfo":
					error = crearElfo(partes[1], partes[2], Integer.parseInt(partes[3]), Integer.parseInt(partes[4]),
							Integer.parseInt(partes[5]), bosque);
					if (error == false) {
						if (salidaFichero == true) {
							salida.println(
									partes[0] + " " + partes[1] + " " + partes[2] + " " + partes[3] + " " + partes[4]
											+ " " + partes[5] + ": OK.");
						} else {
							System.out.println(partes[0] + " " + partes[1] + " " + partes[2] + " " + partes[3] + " "
									+ partes[4] + " " + partes[5] + ": OK.");
						}
					} else {
						if (salidaFichero == true) {
							salida.println(
									partes[0] + " " + partes[1] + " " + partes[2] + " " + partes[3] + " " + partes[4]
											+ " " + partes[5] + ": FAIL.");
						} else {
							System.out.println(partes[0] + " " + partes[1] + " " + partes[2] + " " + partes[3] + " "
									+ partes[4] + " " + partes[5] + ": FAIL.");
						}
					}
					break;

				case "BorrarCriatura":
					error = borrarCriatura(partes[1], bosque);
					if (error == false) {
						if (salidaFichero == true) {
							salida.println(partes[0] + " " + partes[1] + ": OK.");
						} else {
							System.out.println(partes[0] + " " + partes[1] + ": OK.");
						}
					} else {
						if (salidaFichero == true) {
							salida.println(partes[0] + " " + partes[1] + ": FAIL.");
						} else {
							System.out.println(partes[0] + " " + partes[1] + ": FAIL.");
						}
					}
					break;

				case "VolcarCriaturas":
					error = volcarCriaturas(partes[1], bosque);
					if (error == false) {
						if (salidaFichero == true) {
							salida.println(partes[0] + " " + partes[1] + ": OK.");
						} else {
							System.out.println(partes[0] + " " + partes[1] + ": OK.");
						}
					} else {
						if (salidaFichero == true) {
							salida.println(partes[0] + " " + partes[1] + ": FAIL.");
						} else {
							System.out.println(partes[0] + " " + partes[1] + ": FAIL.");
						}
					}
					break;

				case "MostrarCriatura":
					Criatura criatura = mostrarCriatura(partes[1], bosque);
					if (criatura != null) {
						if (salidaFichero == true) {
							salida.println(partes[0] + " " + partes[1] + ": " + criatura.toStringExtendido());
						} else {
							System.out.println(partes[0] + " " + partes[1] + ": " + criatura.toStringExtendido());
						}
					} else {
						if (salidaFichero == true) {
							salida.println(partes[0] + " " + partes[1] + ": FAIL.");
						} else {
							System.out.println(partes[0] + " " + partes[1] + ": FAIL.");
						}
					}
					break;

				case "Atacar":
					error = atacar(partes[1], partes[2], bosque);
					if (error == false) {
						if (salidaFichero == true) {
							salida.println(partes[0] + " " + partes[1] + " " + partes[2] + ": OK.");
						} else {
							System.out.println(partes[0] + " " + partes[1] + " " + partes[2] + ": OK.");
						}
					} else {
						if (salidaFichero == true) {
							salida.println(partes[0] + " " + partes[1] + " " + partes[2] + ": FAIL.");
						} else {
							System.out.println(partes[0] + " " + partes[1] + " " + partes[2] + ": FAIL.");
						}
					}
					break;

				case "VisitarLugarSagrado":
					error = visitarLugarSagrado(partes[1], bosque, lago, templo);
					if (error == false) {
						if (salidaFichero == true) {
							salida.println(partes[0] + " " + partes[1] + ": OK.");
						} else {
							System.out.println(partes[0] + " " + partes[1] + ": OK.");
						}
					} else {
						if (salidaFichero == true) {
							salida.println(partes[0] + " " + partes[1] + ": FAIL.");
						} else {
							System.out.println(partes[0] + " " + partes[1] + ": FAIL.");
						}
					}
					break;

				case "GenerarAsignacionCriaturas":
					error = generarAsignacionCriaturas(Integer.parseInt(partes[1]), partes[2], bosque, jugadores);
					if (error == false) {
						if (salidaFichero == true) {
							salida.println(partes[0] + " " + partes[1] + " " + partes[2] + ": OK.");
						} else {
							System.out.println(partes[0] + " " + partes[1] + " " + partes[2] + ": OK.");
						}
					} else {
						if (salidaFichero == true) {
							salida.println(partes[0] + " " + partes[1] + " " + partes[2] + ": FAIL.");
						} else {
							System.out.println(partes[0] + " " + partes[1] + " " + partes[2] + ": FAIL.");
						}
					}
					break;

				case "JugarPartida":
					error = jugarPartida(partes[1], partes[2], bosque, jugadores, lago, templo);
					if (error == false) {
						if (salidaFichero == true) {
							salida.println(partes[0] + " " + partes[1] + " " + partes[2] + ": OK.");
						} else {
							System.out.println(partes[0] + " " + partes[1] + " " + partes[2] + ": OK.");
						}
					} else {
						if (salidaFichero == true) {
							salida.println(partes[0] + " " + partes[1] + " " + partes[2] + ": FAIL.");
						} else {
							System.out.println(partes[0] + " " + partes[1] + " " + partes[2] + ": FAIL.");
						}
					}
					break;

				case "CriaturaMasOfensiva":

					if (bosque.getNumCriaturas() == 0) {
						if (salidaFichero == true) {
							salida.println(partes[0] + ": FAIL.");
						} else {
							System.out.println(partes[0] + ": FAIL.");
						}
					} else {
						Criatura c = criaturaMasOfensiva(bosque);

						if (salidaFichero == true) {
							salida.println(partes[0] + ": " + c.toStringExtendido());
						} else {
							System.out.println(partes[0] + ": " + c.toStringExtendido());
						}
					}
					break;
			}
		}
		salida.close();
	}

	public boolean cargarJugadores(String nombreFichero, Jugadores jugadores) {
		boolean error = jugadores.leerFichero(nombreFichero);
		return error;
	}

	public boolean crearJugador(String ID, String nombre, Jugadores jugadores) {
		boolean error = jugadores.anadirJugador(new Jugador(ID, nombre));
		return error;
	}

	public boolean borrarJugador(String ID, Jugadores jugadores) {
		boolean error = jugadores.borrarJugador(ID);
		return error;
	}

	public boolean volcarJugadores(String nombreFichero, Jugadores jugadores) {
		boolean error = jugadores.volcarJugadores(nombreFichero);
		return error;
	}

	public boolean cargarCriaturas(String nombreFichero, Bosque bosque) {
		boolean error = bosque.leerFichero(nombreFichero);
		return error;
	}

	public boolean crearNinfa(String ID, String nombre, int divinidad, int velocidad, int engano, int varita,
			int armadura, Bosque bosque) {
		boolean error = bosque.anadirCriatura(new Ninfa(ID, nombre, divinidad, velocidad, engano, varita, armadura));
		return error;
	}

	public boolean crearOrco(String ID, String nombre, int fuerza, int garrote, int escudo, Bosque bosque) {
		boolean error = bosque.anadirCriatura(new Orco(ID, nombre, fuerza, garrote, escudo));
		return error;
	}

	public boolean crearBruja(String ID, String nombre, int sabiduria, int magia, int baston, int vestido,
			Bosque bosque) {
		boolean error = bosque.anadirCriatura(new Bruja(ID, nombre, sabiduria, magia, baston, vestido));
		return error;
	}

	public boolean crearElfo(String ID, String nombre, int inteligencia, int arco, int coraza, Bosque bosque) {
		boolean error = bosque.anadirCriatura(new Elfo(ID, nombre, inteligencia, arco, coraza));
		return error;
	}

	public boolean borrarCriatura(String ID, Bosque bosque) {
		boolean error = bosque.borrarCriatura(ID);
		return error;
	}

	public boolean volcarCriaturas(String nombreFichero, Bosque bosque) {
		boolean error = bosque.volcarCriaturas(nombreFichero);
		return error;
	}

	public Criatura mostrarCriatura(String ID, Bosque bosque) {
		Criatura criatura = bosque.getCriatura(ID);
		return criatura;
	}

	public boolean atacar(String IDAtacante, String IDDefensora, Bosque bosque) {
		boolean error = bosque.lucha(IDAtacante, IDDefensora);
		return error;
	}

	public boolean visitarLugarSagrado(String ID, Bosque bosque, LagoSagrado lago, TemploMaldito templo) {
		boolean error = bosque.visitarLugarSagrado(ID, lago, templo);
		return error;
	}

	public boolean generarAsignacionCriaturas(int num, String nombreFichero, Bosque bosque, Jugadores jugadores) {
		if (jugadores.getNumJugadores() == 0) {
			return true;
		}

		if (bosque.getNumCriaturas() < jugadores.getNumJugadores()) {
			return true;
		}

		int i, j, k, criaturasPorJugador;
		PrintWriter salida;
		Random rand = new Random();
		int numRandom;

		try {
			salida = new PrintWriter(nombreFichero);
		} catch (FileNotFoundException e) {
			return true;
		}

		criaturasPorJugador = bosque.getNumCriaturas() / jugadores.getNumJugadores();

		ArrayList<Criatura> listaCriaturas = bosque.getListaCriaturas();

		for (i = 0; i < num; i++) {
			for (j = 0; j < jugadores.getNumJugadores(); j++) {
				if (j == 0) {
					salida.println(jugadores.getJugador(j).getID() + ":{");
				} else {
					salida.println("," + jugadores.getJugador(j).getID() + ":{");
				}

				for (k = 0; k < criaturasPorJugador; k++) {
					numRandom = rand.nextInt(bosque.getNumCriaturas());

					if (k == 0) {
						Criatura criatura = listaCriaturas.get(numRandom);
						listaCriaturas.remove(numRandom);
						salida.println(criatura.getID());
					} else {
						Criatura criatura = listaCriaturas.get(numRandom);
						listaCriaturas.remove(numRandom);
						salida.println("," + criatura.getID());
					}
				}

				salida.println("}");
			}
			salida.println("\n");
		}
		return false;
	}

	public boolean jugarPartida(String nombreFicheroReparto, String nombreFicheroPartida, Bosque bosque,
			Jugadores jugadores, LagoSagrado lago, TemploMaldito templo) {
		boolean error = false;
		new Partida(bosque, jugadores, lago, templo, true, nombreFicheroReparto, true, nombreFicheroPartida, error);
		return true;
	}

	public Criatura criaturaMasOfensiva(Bosque bosque) {
		bosque.ordenarPO();
		Criatura criatura = bosque.getListaCriaturas().get(0);
		return criatura;
	}
}