package src;

import java.util.ArrayList;
import java.util.Collections;

public class Jugador {

	private String ID; // Atributos
	private String nombre;
	private int puntos = 0;
	private int puntosAnteriores = 0;
	private boolean inactivo = false;
	private boolean ataco = false;
	private ArrayList<Criatura> criaturas = new ArrayList<Criatura>();

	public Jugador(String ID, String nombre) { // Constructor
		this.ID = ID;
		this.nombre = nombre;
	}

	public String getID() { // Getters
		return ID;
	}

	public String getNombre() {
		return nombre;
	}

	public int getPuntos() {
		return puntos;
	}

	public int getPuntosAnteriores() {
		return puntosAnteriores;
	}

	public boolean getInactividad() {
		return inactivo;
	}

	public boolean getAtaco() {
		return ataco;
	}

	public int getNumCriaturas() {
		return criaturas.size();
	}

	public Criatura getCriatura(int n) {
		return criaturas.get(n);
	}

	public ArrayList<Criatura> getCriaturas() {
		return criaturas;
	}

	public void setID(String ID) {
		this.ID = ID;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPuntos(int puntos) {
		puntosAnteriores = this.puntos;
		this.puntos = puntos;
	}

	public void setPuntosAnteriores(int puntos) {
		puntosAnteriores = puntos;
	}

	public void setInactividad(boolean inactivo) {
		this.inactivo = inactivo;
	}

	public void setAtaco(boolean ataco) {
		this.ataco = ataco;
	}

	public void anadirCriatura(Criatura criatura) {
		criaturas.add(criatura);
	}

	public void eliminarCriatura(Criatura criatura) {
		criaturas.remove(criatura);
	}

	public void eliminarCriatura(int n) {
		criaturas.remove(n);
	}

	public void ordenarPO() {
		Collections.sort(criaturas, Criatura.criaturaPorPO);
	}

	public void ordenarCD() {
		Collections.sort(criaturas, Criatura.criaturaPorCD);
	}

	public String toString() {
		return ID + ":{" + nombre + "}";
	}
}