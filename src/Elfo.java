package src;

public class Elfo extends Criatura implements UsuarioLagoSagrado {

	private int inteligencia;
	private int arco;
	private int coraza;

	public Elfo(String ID, String nombre, int inteligencia, int arco, int coraza) {
		super(ID, nombre);
		this.inteligencia = inteligencia;
		this.arco = arco;
		this.coraza = coraza;
		calcularPoderOfensivo();
		calcularCapacidadDefensiva();
	}

	protected void calcularPoderOfensivo() {
		setPoderOfensivo((inteligencia * (arco * arco)) / 5);
	}

	protected void calcularCapacidadDefensiva() {
		setCapacidadDefensiva((inteligencia * (coraza * coraza)) / 10);
	}

	public void atacar(Criatura defensor) {
		defensor.defender(this);

		if ((getSalud() - 3) >= 0) {
			setSalud(getSalud() - 3);
		} else {
			setSalud(0);
		}

		if ((arco - 1) >= 2) {
			arco = arco - 1;
		} else {
			arco = 2;
		}

		if ((coraza - 1) >= 1) {
			coraza = coraza - 1;
		} else {
			coraza = 1;
		}

		calcularPoderOfensivo();
		calcularCapacidadDefensiva();
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

		if ((arco - 1) >= 2) {
			arco = arco - 1;
		} else {
			arco = 2;
		}

		if ((coraza - 1) >= 1) {
			coraza = coraza - 1;
		} else {
			coraza = 1;
		}

		calcularPoderOfensivo();
		calcularCapacidadDefensiva();
	}

	public void visitarLagoSagrado(LagoSagrado lago) {
		setVisitas(getVisitas() + 1);

		if ((getSalud() + (3 * getVisitas())) <= 10) {
			setSalud(getSalud() + (3 * getVisitas()));
		} else {
			setSalud(10);
		}

		if ((arco + (getVisitas() / 2)) <= 5) {
			arco = arco + (getVisitas() / 2);
		} else {
			arco = 5;
		}

		if ((coraza + (getVisitas() / 2)) <= 5) {
			coraza = coraza + (getVisitas() / 2);
		} else {
			coraza = 5;
		}

		calcularPoderOfensivo();
		calcularCapacidadDefensiva();
		lago.anadirVisita(this);
	}

	public String toString() {
		return "E:{" + getID() + "," + getNombre() + ",I" + inteligencia + ",A" + arco + ",C" + coraza + "}";
	}

	public String toStringExtendido() {
		return "E:{" + getID() + "," + getNombre() + ",I" + inteligencia + ",A" + arco + ",C" + coraza + ","
				+ getPoderOfensivo() + "," + getCapacidadDefensiva() + "," + getSalud() + "}";
	}
}