package src;

import java.util.ArrayList;
import java.util.Collections;

public class TemploMaldito {

	private ArrayList<Criatura> libroVisitas = new ArrayList<Criatura>();

	public void anadirVisita(Criatura criatura) {
		if (libroVisitas.size() == 0) {
			libroVisitas.add(criatura);
			return;
		}

		for (Criatura c : libroVisitas) {
			if (c.getID().equals(criatura.getID())) {
				c.setVisitas(criatura.getVisitas());
				return;
			}
		}

		libroVisitas.add(criatura);
		return;
	}

	public String mostrarVisitas() {
		String visitas = "";
		int i = 0;

		Collections.sort(libroVisitas);

		for (Criatura c : libroVisitas) {
			if (i == 0) {
				visitas = c.getID() + "=" + c.getVisitas();
			} else {
				visitas = visitas + ", " + c.getID() + "=" + c.getVisitas();
			}
			i++;
		}

		return "{" + visitas + "}";
	}
}