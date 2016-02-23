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
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			uhrzeit += 10;
			if ((uhrzeit % 100) == 60) {
				uhrzeit += 40;
			}
			System.out.println("Uhrzeit: " + uhrzeit);
		}

	}

	public int getUhrzeit() {
		return uhrzeit;
	}

}