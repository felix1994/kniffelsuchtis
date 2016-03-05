package core;

import java.util.Stack;

public class Start extends Thread {

	private Warteschlange ws;
	private SkifahrerHandler shandler;
	private Lift lift;
	private Uhr uhr;
	private int anzahlKabinen;
	private int anzahlPersUm8;
	private int abfahrtsdauerMax;
	private int abfahrtsdauerMin;
	private int maxSkifahrer;
	private int liftdauer;

	public Start(int anzahlKabineni, int anzahlPersUm8i, int abfahrtsdauerMaxi,
			int abfahrtsdauerMini, int maxSkifahreri, int liftdaueri) {
		uhr = new Uhr();
		ws = new Warteschlange(uhr);
		lift = new Lift(ws, uhr);
		shandler = new SkifahrerHandler(uhr, ws, 20);

		this.anzahlKabinen = anzahlKabineni;
		this.anzahlPersUm8 = anzahlPersUm8i;
		this.abfahrtsdauerMax = abfahrtsdauerMaxi;
		this.abfahrtsdauerMin = abfahrtsdauerMini;
		this.maxSkifahrer = maxSkifahreri;
		this.liftdauer = liftdaueri;

		setStandardValuesIfZero();
		letsGo();
	}

	private void setStandardValuesIfZero() {
		System.err.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
	}

	public void letsGo() {
		uhr.start();
		shandler.start();
		lift.start();
		this.start();
	}

	@Override
	public void run() {
		while (true) {
			if (uhr.getUhrzeit() >= 1600) {
				lift.stop();
				ws.stop();
				uhr.stop();
				System.err.println("Leere Sitze: " + lift.getLeereSitze());
				System.err.println("Insgesamt befördert: "
						+ lift.getPersBefördert());
				System.err.println("Kabinen gefahren: "
						+ lift.getLifteGesamtGefahren());
				System.err.println("Fahrer gesamt: " + ws.getFahrergesamt());
				Stack<Integer> stack = ws.getWartezeiten();
				int size = stack.size();
				int gesamt = 0;
				while (!stack.isEmpty()) {
					gesamt += stack.pop();
				}
				System.err.println("Durchschnittliche Wartezeit: "
						+ (gesamt / size));
				this.stop();
			}
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void stopAll() {
		lift.stop();
		ws.stop();
		uhr.stop();
		this.stop();

	}

}
