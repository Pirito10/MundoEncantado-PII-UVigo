package src;

import java.util.Comparator;

public abstract class Criatura implements Comparable<Criatura> { // Clase padre de las criaturas

	private String ID; // Atributos comunes
	private String nombre;
	private int salud = 10;
	private int poderOfensivo;
	private int capacidadDefensiva;
	private int numVisitas = 0;

	protected Criatura(String ID, String nombre) {
		this.ID = ID;
		this.nombre = nombre;
	}

	protected abstract void calcularPoderOfensivo(); // Metodos comunes

	protected abstract void calcularCapacidadDefensiva();

	protected abstract void atacar(Criatura defensor);

	protected abstract void defender(Criatura atacante);

	public abstract String toString();

	public abstract String toStringExtendido();

	public void setID(String ID) { // Setters
		this.ID = ID;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setSalud(int salud) {
		this.salud = salud;
	}

	public void setPoderOfensivo(int poderOfensivo) {
		this.poderOfensivo = poderOfensivo;
	}

	public void setCapacidadDefensiva(int capacidadDefensiva) {
		this.capacidadDefensiva = capacidadDefensiva;
	}

	public void setVisitas(int numVisitas) {
		this.numVisitas = numVisitas;
	}

	public String getID() { // Getters
		return ID;
	}

	public String getNombre() {
		return nombre;
	}

	public int getSalud() {
		return salud;
	}

	public int getPoderOfensivo() {
		return poderOfensivo;
	}

	public int getCapacidadDefensiva() {
		return capacidadDefensiva;
	}

	public int getVisitas() {
		return numVisitas;
	}

	public int compareTo(Criatura criatura) { // Metodo para comparar dos criaturas segun su ID

		int resultadoComparacion = this.getID().compareTo(criatura.getID()); // Se hace la comparacion entre los
																				// ID de ambas criaturas

		if (resultadoComparacion < 0) { // Si el ID de la primera criatura es superior (alfabeticamente)...
			return -1; // Se devuelve un numero positivo
		} else if (resultadoComparacion > 0) { // Si el ID de la primera ninfa es inferior...
			return 1; // Se devuelve un numero negativo
		} else { // Si los IDs coinciden...
			return this.getNombre().compareTo(criatura.getNombre()); // Se hace la comparacion de los nombres y se
																		// devolvera un numero en funcion de que nombre
																		// es mayor
		}
	}

	public static Comparator<Criatura> criaturaPorPO = new Comparator<Criatura>() { // Metodo para la
																					// implementacion de la
																					// interfaz Comparator

		public int compare(Criatura c1, Criatura c2) { // Metodo para comparar dos criaturas segun su fortaleza
			int PO1 = c1.getPoderOfensivo(), PO2 = c2.getPoderOfensivo(); // Variables para almacenar las
																			// fortalezas de cada criatura

			if (PO1 > PO2) { // Si la fortaleza de la primera criatura es mayor...
				return -1; // Se devuelve un numero negativo
			} else if (PO1 < PO2) { // Si la fortaleza de la primera criatura es menor...
				return 1; // Se devuelve un numero positivo
			} else { // Si las fortalezas coinciden...
				return c1.compareTo(c2); // Se hace la comparacion entre los nombres y se devolvera un numero en funcion
											// de su orden alfabetico, o en caso de tener igual nombre, se comparan los
											// ID's y se devolvera un numero en funcion de que ID en mayor
			}
		}
	};

	public static Comparator<Criatura> criaturaPorCD = new Comparator<Criatura>() {

		public int compare(Criatura c1, Criatura c2) {
			int CD1 = c1.getCapacidadDefensiva(), CD2 = c2.getCapacidadDefensiva();

			if (CD1 > CD2) {
				return -1;
			} else if (CD1 < CD2) {
				return 1;
			} else {
				return c1.compareTo(c2);
			}
		}
	};
}