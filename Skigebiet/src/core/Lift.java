package core;

import java.util.LinkedList;
import java.util.Queue;

public class Lift extends Thread {

	private Queue<Kabine> kabinen;
	private Warteschlange ws;
	private Uhr uhr;
	private int leereSitze;
	private int persBef�rdert;
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
			try {
				// Jede Minute kommt eine Kabine an
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			Kabine kabine = kabineF�llen();
			kabine.fahrLos();

		}
	}

	private Kabine kabineF�llen() {
		Kabine kabine = kabinen.poll();
		System.out.println("WS: " + ws.getWs().size());
		for (int i = 0; i < 4; i++) {
			Skifahrer s = ws.getWs().poll();
			if (s == null) {
				continue;
			}
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

	public void addToPersBef�rdert(int value) {
		persBef�rdert += value;
	}

	public void addToLifteGesamtGefahren(int value) {
		lifteGesamtGefahren += value;
	}

	public int getLeereSitze() {
		return leereSitze;
	}

	public int getPersBef�rdert() {
		return persBef�rdert;
	}

	public int getLifteGesamtGefahren() {
		return lifteGesamtGefahren;
	}

}
