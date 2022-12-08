package application;

import java.util.ArrayList;

public class Modele {

	private final ArrayList<Observateur> obs = new ArrayList<>();

	public Modele() {

	}

	public void ajouterObservateur(Observateur o) {
		obs.add(o);
	}

	public void notifierObservateur() {
		for (Observateur o : obs) {
			o.reagir();
		}
	}

}
