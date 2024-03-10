public class Bruja extends Criatura implements UsuarioTemploMaldito {

	private int sabiduria;
	private int magia;
	private int baston;
	private int vestido;

	public Bruja(String ID, String nombre, int sabiduria, int magia, int baston, int vestido) {
		super(ID, nombre);
		this.sabiduria = sabiduria;
		this.magia = magia;
		this.baston = baston;
		this.vestido = vestido;
		calcularPoderOfensivo();
		calcularCapacidadDefensiva();
	}

	protected void calcularPoderOfensivo() {
		setPoderOfensivo((sabiduria + magia) * baston / 5);
	}

	protected void calcularCapacidadDefensiva() {
		setCapacidadDefensiva((sabiduria + magia) * vestido / 10);
	}

	public void atacar(Criatura defensor) {
		defensor.defender(this);

		if ((getSalud() - 2) >= 0) {
			setSalud(getSalud() - 2);
		} else {
			setSalud(0);
		}

		if ((baston - 1) >= 1) {
			baston = baston - 1;
		} else {
			baston = 1;
		}

		calcularPoderOfensivo();
	}

	public void defender(Criatura atacante) {
		if ((atacante.getPoderOfensivo() - getCapacidadDefensiva()) > 0) {
			setSalud(getSalud() - (atacante.getPoderOfensivo() - getCapacidadDefensiva()));
		}

		if ((getSalud() - 2) >= 0) {
			setSalud(getSalud() - 2);
		} else {
			setSalud(0);
		}

		if ((vestido - 1) >= 1) {
			vestido = vestido - 1;
		} else {
			vestido = 1;
		}

		calcularCapacidadDefensiva();
	}

	public void visitarTemploMaldito(TemploMaldito templo) {
		setVisitas(getVisitas() + 1);

		if ((baston + ((2 + getVisitas()) / 2)) <= 10) {
			baston = baston + ((2 + getVisitas()) / 2);
		} else {
			baston = 10;
		}

		if ((vestido + (getVisitas() / 2)) <= 10) {
			vestido = vestido + (getVisitas() / 2);
		} else {
			vestido = 10;
		}

		calcularPoderOfensivo();
		calcularCapacidadDefensiva();
		templo.anadirVisita(this);
	}

	public String toString() {
		return "B:{" + getID() + "," + getNombre() + ",S" + sabiduria + ",M" + magia + ",B" + baston + ",V" + vestido
				+ "}";
	}

	public String toStringExtendido() {
		return "B:{" + getID() + "," + getNombre() + ",S" + sabiduria + ",M" + magia + ",B" + baston + ",V" + vestido
				+ "," + getPoderOfensivo() + "," + getCapacidadDefensiva() + "," + getSalud() + "}";
	}
}