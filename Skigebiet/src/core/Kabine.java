package core;

import java.util.HashSet;
import java.util.Set;

public class Kabine extends Thread {

	private int nr;
	private Set<Skifahrer> insassen;
	private Lift lift;

	public Kabine(int nr, Lift lift) {
		this.lift = lift;
		this.nr = nr;
		insassen = new HashSet<Skifahrer>();
	}

	@Override
	public void run() {
		try {
			lift.addToLeereSitze(4 - insassen.size());
			lift.addToPersBefördert(insassen.size());
			lift.addToLifteGesamtGefahren(1);
			// Kabine braucht 8 Minuten hoch
			Thread.sleep(800);
			ausladen();
			// Kabine braucht 8 Minuten runter
			Thread.sleep(800);
			lift.getKabinen().add(new Kabine(nr, lift));
			this.stop();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ausladen();
	}

	private void ausladen() {
		insassen.clear();

	}

	public int getNr() {
		return nr;
	}

	public void setNr(int nr) {
		this.nr = nr;
	}

	public Set<Skifahrer> getInsassen() {
		return insassen;
	}

	public void setInsassen(Set<Skifahrer> insassen) {
		this.insassen = insassen;
	}

	public void setzeRein(Skifahrer s) {
		insassen.add(s);

	}

	public void fahrLos() {

		System.out.println("Kabine " + nr + " fährt mit " + insassen.size()
				+ " los");
		this.start();

	}

}
