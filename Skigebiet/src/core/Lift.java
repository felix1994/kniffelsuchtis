package core;

import java.util.LinkedList;
import java.util.Queue;

public class Lift extends Thread {

	private Queue<Kabine> kabinen;
	private Warteschlange ws;
	private Uhr uhr;
	private int leereSitze;
	private int persBefördert;
	private int lifteGesamtGefahren;

	public Lift(Warteschlange ws, Uhr uhr) {
		leereSitze = 0;
		this.uhr = uhr;
		this.ws = ws;
		kabinen = new LinkedList<Kabine>();
		for (int i = 1; i < 31; i++) {
			kabinen.add(new Kabine(i, this));
		}
	}

	@Override
	public void run() {
		while (uhr.getUhrzeit() < 1600) {
			// System.out.println("Es ist " + uhr.getUhrzeit() + " Uhr");
			try {
				// Jede Minute kommt eine Kabine an
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			Kabine kabine = kabineFüllen();
			kabine.fahrLos();
			System.out.println("WS: " + ws.getWs().size());
			// System.err.println(kabinen.size());
		}
	}

	private Kabine kabineFüllen() {
		Kabine kabine = kabinen.poll();
		for (int i = 0; i < 4; i++) {
			Skifahrer s = ws.getWs().poll();
			if (s == null)
				return kabine;
			kabine.setzeRein(s);
		}
		return kabine;

	}

	public Queue<Kabine> getKabinen() {
		return kabinen;
	}

	public void addToLeereSitze(int value) {
		leereSitze += value;
	}

	public void addToPersBefördert(int value) {
		persBefördert += value;
	}

	public void addToLifteGesamtGefahren(int value) {
		lifteGesamtGefahren += value;
	}

	public int getLeereSitze() {
		return leereSitze;
	}

	public int getPersBefördert() {
		return persBefördert;
	}

	public int getLifteGesamtGefahren() {
		return lifteGesamtGefahren;
	}

}
