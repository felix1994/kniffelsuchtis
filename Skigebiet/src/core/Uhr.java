package core;

public class Uhr extends Thread {

	int uhrzeit;

	public Uhr() {
		uhrzeit = 800;
	}

	@Override
	public void run() {
		while (uhrzeit <= 1610) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			uhrzeit += 1;
			if ((uhrzeit % 100) == 60) {
				uhrzeit += 40;
			}
			System.err.println("Uhrzeit: " + uhrzeit);
		}

	}

	public int getUhrzeit() {
		return uhrzeit;
	}

}
