package core;

public class Start extends Thread {

	private Warteschlange ws;
	private Lift lift;
	private Uhr uhr;

	public Start() {
		uhr = new Uhr();
		ws = new Warteschlange(uhr);
		lift = new Lift(ws, uhr);

		letsGo();
	}

	public void letsGo() {
		uhr.start();
		ws.start();
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
				System.err.println("Insgesamt befördert: " + lift.getPersBefördert());
				System.err.println("Kabinen gefahren: " + lift.getLifteGesamtGefahren());
				this.stop();
			}
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
