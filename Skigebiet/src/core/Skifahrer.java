package core;

import java.util.Random;

public class Skifahrer extends Thread {

	private boolean inWarteschlange;
	private boolean imLift;
	private Uhr uhr;
	private Random random = new Random();;
	private int ankunftszeit;
	private int heimgehzeit;
	private Warteschlange ws;

	public Skifahrer(Uhr uhr, Warteschlange warteschlange) {
		ws = warteschlange;
		inWarteschlange = true;
		imLift = false;
		this.uhr = uhr;
		ankunftszeit = uhr.getUhrzeit();
		heimgehzeit = berechneHeimgehzeit();
		this.start();
	}

	private int berechneHeimgehzeit() {
		int[] stunden = { 100, 200, 300, 400, 500, 600 };
		int[] minuten = { 0, 10, 20, 30, 40, 50 };

		int stunde = stunden[random.nextInt(6)];
		int min = minuten[random.nextInt(6)];
		int gesamt = ankunftszeit + stunde + min;
		if (gesamt > 1600)
			gesamt = 1600;
		return gesamt;
	}

	@Override
	public void run() {
		while (true) {
			// System.err.println("In WS");
			int zeit = 0;
			// Steht in der Warteschlange
			while (inWarteschlange) {
				sleepi(200);
				zeit += 2;
			}
			ws.addWartezeit(zeit);
			// Sitzt im Lift
			// System.err.println("im Lift");
			while (imLift)
				sleepi(200);

			// Auf der Abfahrt, braucht zwischen 4 und 8 Minuten für eine
			// Abfahrt
			// System.err.println("Fährt los");
			sleepi(random.nextInt(400) + 400);

			// Unten angekommen, wieder in Warteschlange oder ganz heim
			if (uhr.getUhrzeit() >= heimgehzeit) {
				// System.err.println("Geht heim");
				this.stop();
			} else {
				// System.err.println("Stellt sich wieder an");
				ws.getWs().add(this);
				inWarteschlange = true;
				imLift = false;
			}

		}
	}

	private void sleepi(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean isInWarteschlange() {
		return inWarteschlange;
	}

	public void setInWarteschlange(boolean inWarteschlange) {
		this.inWarteschlange = inWarteschlange;
	}

	public boolean isImLift() {
		return imLift;
	}

	public void setImLift(boolean imLift) {
		this.imLift = imLift;
	}

}
