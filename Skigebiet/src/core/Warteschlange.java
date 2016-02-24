package core;

import java.util.LinkedList;
import java.util.Queue;

public class Warteschlange extends Thread {

	private Uhr uhr;
	private Queue<Skifahrer> ws;

	public Warteschlange(Uhr uhr) {
		ws = new LinkedList<Skifahrer>();
		for (int i = 0; i < 30; i++) {
			ws.add(new Skifahrer(uhr, this));
		}
		this.uhr = uhr;
	}

	public Queue<Skifahrer> getWs() {
		return ws;
	}

	@Override
	public void run() {
		// Random random = new Random();
		// while (true) {
		// for (int i = 0; i < 9; i++)
		// ws.add(new Skifahrer(uhr, this));
		// try {
		// Thread.sleep(random.nextInt(400) + 100);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		// }
	}

}
