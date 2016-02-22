package core;

public class Uhr extends Thread {

	int uhrzeit;
	private int[] uhrzeiten = { 800, 830, 900, 930, 1000, 1030, 1100, 1130, 1200, 1230, 1300, 1330, 1400, 1430, 1500,
			1530, 1600 };

	@Override
	public void run() {
		int counter = 0;
		uhrzeit = uhrzeiten[counter];
		while (uhrzeit <= 1600) {
			System.out.println(uhrzeit);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			counter++;
			if (counter > 16)
				this.stop();
			uhrzeit = uhrzeiten[counter];

		}

	}

	public int getUhrzeit() {
		return uhrzeit;
	}
}
