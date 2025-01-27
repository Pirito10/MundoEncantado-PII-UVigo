package src;

public class Orco extends Criatura implements UsuarioTemploMaldito {

	private int fuerza; // Atributos propios
	private int garrote;
	private int escudo;

	public Orco(String ID, String nombre, int fuerza, int garrote, int escudo) { // Constructor
		super(ID, nombre);
		this.fuerza = fuerza;
		this.garrote = garrote;
		this.escudo = escudo;
		calcularPoderOfensivo();
		calcularCapacidadDefensiva();
	}

	protected void calcularPoderOfensivo() { // Metodos propios
		setPoderOfensivo((fuerza + garrote) / 5);
	}

	protected void calcularCapacidadDefensiva() {
		setCapacidadDefensiva((fuerza + escudo) / 20);
	}

	public void atacar(Criatura defensor) {
		defensor.defender(this);

		if ((getSalud() - 1) >= 0) {
			setSalud(getSalud() - 1);
		} else {
			setSalud(0);
		}

		if ((garrote - 3) >= 0) {
			garrote = garrote - 3;
		} else {
			garrote = 0;
		}

		calcularPoderOfensivo();
	}

	public void defender(Criatura atacante) {
		if ((atacante.getPoderOfensivo() - getCapacidadDefensiva()) > 0) {
			setSalud(getSalud() - (atacante.getPoderOfensivo() - getCapacidadDefensiva()));
		}

		if ((getSalud() - 3) >= 0) {
			setSalud(getSalud() - 3);
		} else {
			setSalud(0);
		}

		if ((escudo - 3) >= 0) {
			escudo = escudo - 3;
		} else {
			escudo = 0;
		}

		calcularCapacidadDefensiva();
	}

	public void visitarTemploMaldito(TemploMaldito templo) {
		setVisitas(getVisitas() + 1);

		if ((garrote + (2 * getVisitas())) <= 90) {
			garrote = garrote + (2 * getVisitas());
		} else {
			garrote = 90;
		}

		if ((escudo + getVisitas()) <= 90) {
			escudo = escudo + getVisitas();
		} else {
			escudo = 90;
		}

		calcularPoderOfensivo();
		calcularCapacidadDefensiva();
		templo.anadirVisita(this);
	}

	public String toString() {
		return "O:{" + getID() + "," + getNombre() + ",F" + fuerza + ",G" + garrote + ",E" + escudo + "}";
	}

	public String toStringExtendido() {
		return "O:{" + getID() + "," + getNombre() + ",F" + fuerza + ",G" + garrote + ",E" + escudo + ","
				+ getPoderOfensivo() + "," + getCapacidadDefensiva() + "," + getSalud() + "}";
	}
}