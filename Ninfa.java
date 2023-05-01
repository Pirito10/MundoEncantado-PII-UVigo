
public class Ninfa extends Criatura implements UsuarioLagoSagrado {

	private int divinidad; // Atributos propios
	private int velocidad;
	private int engano;
	private int varita;
	private int armadura;

	public Ninfa(String ID, String nombre, int divinidad, int varita, int velocidad, int engano, int armadura) { // Constructor
		super(ID, nombre);
		this.divinidad = divinidad;
		this.velocidad = velocidad;
		this.engano = engano;
		this.varita = varita;
		this.armadura = armadura;
		calcularPoderOfensivo();
		calcularCapacidadDefensiva();
	}

	protected void calcularPoderOfensivo() { // Metodos propios
		setPoderOfensivo(divinidad * (velocidad + engano) + varita / 100);
	}

	protected void calcularCapacidadDefensiva() {
		setCapacidadDefensiva(divinidad * (velocidad + engano) + armadura / 200);
	}

	public void atacar(Criatura defensor) {

		defensor.defender(this); // Se llama a la otra criatura a defenderse

		if ((getSalud() - 2) >= 0) {
			setSalud(getSalud() - 2); // Se restan dos puntos de vida por la lucha
		} else {
			setSalud(0);
		}

		if ((varita - 5) >= 0) {
			varita = varita - 5;
		} else {
			varita = 0;
		}
		calcularPoderOfensivo();
	}

	public void defender(Criatura atacante) {

		if ((atacante.getPoderOfensivo() - getCapacidadDefensiva()) > 0) {
			setSalud(getSalud() - (atacante.getPoderOfensivo() - getCapacidadDefensiva()));
		}

		if ((getSalud() - 2) >= 0) {
			setSalud(getSalud() - 2); // Se restan dos puntos de vida por la lucha
		} else {
			setSalud(0);
		}

		if ((armadura - 5) >= 0) {
			armadura = armadura - 5;
		} else {
			armadura = 0;
		}
		calcularCapacidadDefensiva();
	}

	public void visitarLagoSagrado(LagoSagrado lago) {

		setVisitas(getVisitas() + 1);
		if ((getSalud() + (2 + getVisitas() * 2)) <= 10) {
			setSalud(getSalud() + (2 + getVisitas() * 2));
		} else {
			setSalud(10);
		}

		if ((varita + (getVisitas() * 3)) <= 1000) {
			varita = varita + (getVisitas() * 3);
		} else {
			varita = 1000;
		}

		if ((armadura + (getVisitas() * 3)) <= 1000) {
			armadura = armadura + (getVisitas() * 3);
		} else {
			armadura = 1000;
		}

		calcularPoderOfensivo();
		calcularCapacidadDefensiva();
		lago.anadirVisita(this);
	}

	public String toString() {
		return "N:{" + getID() + "," + getNombre() + ",D" + divinidad + ",V" + varita + ",R" + velocidad + ",E" + engano
				+ ",A" + armadura + "}";
	}

	public String toStringExtendido() {
		return "N:{" + getID() + "," + getNombre() + ",D" + divinidad + ",V" + varita + ",R" + velocidad + ",E" + engano
				+ ",A" + armadura + "," + getPoderOfensivo() + "," + getCapacidadDefensiva() + "," + getSalud() + "}";
	}

}