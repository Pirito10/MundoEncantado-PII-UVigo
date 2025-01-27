import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Bosque {

	private ArrayList<Criatura> criaturas = new ArrayList<Criatura>();

	public Bosque() {
	}

	public Bosque(String nombreFichero) {
		leerFichero(nombreFichero);
	}

	public void ordenarPO() {
		Collections.sort(criaturas, Criatura.criaturaPorPO);
	}

	public boolean leerFichero(String nombreFichero) {
		Scanner entrada = null;
		String linea;

		try {
			entrada = new Scanner(new FileInputStream(nombreFichero));
		} catch (FileNotFoundException e) {
			return true;
		}

		while (entrada.hasNextLine()) {
			linea = entrada.nextLine();

			String[] partes = linea.split(",");

			char tipoCriatura = partes[0].charAt(0);

			partes[0] = partes[0].substring(3);

			partes[partes.length - 1] = partes[partes.length - 1].substring(0, partes[partes.length - 1].length() - 1); // Borrar
																														// ultimo
																														// }

			for (Criatura c : criaturas) {
				if (c.getID().equals(partes[0])) {
					criaturas.remove(c);
					break;
				}
			}

			switch (tipoCriatura) {
				case 'N':
					criaturas.add(new Ninfa(partes[0], partes[1], Integer.parseInt(partes[2].substring(1)),
							Integer.parseInt(partes[3].substring(1)), Integer.parseInt(partes[4].substring(1)),
							Integer.parseInt(partes[5].substring(1)), Integer.parseInt(partes[6].substring(1))));
					break;

				case 'O':
					criaturas.add(new Orco(partes[0], partes[1], Integer.parseInt(partes[2].substring(1)),
							Integer.parseInt(partes[3].substring(1)), Integer.parseInt(partes[4].substring(1))));
					break;

				case 'B':
					criaturas.add(new Bruja(partes[0], partes[1], Integer.parseInt(partes[2].substring(1)),
							Integer.parseInt(partes[3].substring(1)), Integer.parseInt(partes[4].substring(1)),
							Integer.parseInt(partes[5].substring(1))));
					break;
				case 'E':
					criaturas.add(new Elfo(partes[0], partes[1], Integer.parseInt(partes[2].substring(1)),
							Integer.parseInt(partes[3].substring(1)), Integer.parseInt(partes[4].substring(1))));
					break;
			}
		}
		entrada.close();
		return false;
	}

	public ArrayList<Criatura> getListaCriaturas() {
		return criaturas;
	}

	public Criatura getAleatoria() {
		Random rand = new Random();
		int num = rand.nextInt(criaturas.size());
		Criatura criatura = criaturas.get(num);
		criaturas.remove(num);
		return criatura;
	}

	public Criatura getCriatura(String ID) {
		for (Criatura c : criaturas) {
			if (c.getID().equals(ID)) {
				return c;
			}
		}
		return null;
	}

	public int getNumCriaturas() {
		return criaturas.size();
	}

	public boolean anadirCriatura(Criatura criatura) {
		for (Criatura c : criaturas) {
			if (c.getID().equals(criatura.getID())) {
				return true;
			}
		}
		criaturas.add(criatura);
		return false;
	}

	public boolean borrarCriatura(String ID) {
		for (Criatura c : criaturas) {
			if (c.getID().equals(ID)) {
				criaturas.remove(c);
				return false;
			}
		}
		return true;
	}

	public boolean volcarCriaturas(String nombreFichero) {
		Collections.sort(criaturas);

		PrintWriter salida;

		try {
			salida = new PrintWriter(nombreFichero);
		} catch (FileNotFoundException e) {
			return true;
		}

		for (Criatura c : criaturas) {
			salida.println(c);
		}

		salida.close();

		return false;
	}

	public boolean lucha(String IDAtacante, String IDDefensora) {
		Criatura criaturaAtacante = null;
		Criatura criaturaDefensora = null;
		for (Criatura c : criaturas) {
			if (c.getID().equals(IDAtacante)) {
				criaturaAtacante = c;
				break;
			}
		}

		for (Criatura c : criaturas) {
			if (c.getID().equals(IDDefensora)) {
				criaturaDefensora = c;
				break;
			}
		}

		if ((criaturaAtacante != null) && (criaturaDefensora != null)) {
			criaturaAtacante.atacar(criaturaDefensora);
			return false;
		} else {
			return true;
		}
	}

	public boolean visitarLugarSagrado(String ID, LagoSagrado lago, TemploMaldito templo) {
		for (Criatura c : criaturas) {
			if (c.getID().equals(ID)) {
				if (c instanceof UsuarioLagoSagrado) {
					((UsuarioLagoSagrado) c).visitarLagoSagrado(lago);
				} else {
					((UsuarioTemploMaldito) c).visitarTemploMaldito(templo);
				}
				return false;
			}
		}
		return true;
	}

	public boolean comprobarTodasNeutralizadas() {
		for (Criatura c : criaturas) {
			if (c.getSalud() > 0) {
				return false;
			}
		}
		return true;
	}
}