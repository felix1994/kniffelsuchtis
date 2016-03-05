package core;

import java.util.Map;
import java.util.Random;
import java.util.Set;

public class Skifahrer {

	private int id;
	private int ankunft;
	private int ende;
	private Map<Integer, Set<Skifahrer>> wsInput;
	private Random random = new Random();

	public Skifahrer(int id, int ankunft, Map<Integer, Set<Skifahrer>> wsInput) {
		this.wsInput = wsInput;
		this.id = id;
		this.ankunft = ankunft;
		this.ende = ende;
		ende = berechneEnde(ankunft);
		berechneFahrzeiten();
	}

	private int berechneEnde(int uhrzeit) {
		int[] stunden = { 100, 200, 300, 400, 500, 600, 700 };
		int[] minuten = { 0, 10, 20, 30, 40, 50 };
		int ende = uhrzeit + stunden[random.nextInt(7)]
				+ minuten[random.nextInt(6)];
		return ende;
	}

	private void berechneFahrzeiten() {
		int uhrzeit = ankunft;
		while (uhrzeit < ende) {
			// Eine Abfahrt dauert 6 - 11 Minuten
			uhrzeit += random.nextInt(6) + 6;

			int rest = uhrzeit % 100;
			if (rest >= 60) {
				uhrzeit += 40;
			}
			System.out.println(uhrzeit);
			wsInput.get(uhrzeit).add(this);
		}
	}
}
