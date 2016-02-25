package core;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

public class Warteschlange extends Thread {

	private Uhr uhr;
	private Queue<Skifahrer> ws;
	private Stack<Integer> wartezeiten = new Stack<Integer>();
	private int fahrerGesamt;

	public Warteschlange(Uhr uhr) {
		ws = new LinkedList<Skifahrer>();
		for (int i = 0; i < 50; i++) {
			ws.add(new Skifahrer(uhr, this));
		}
		this.uhr = uhr;
	}

	public Queue<Skifahrer> getWs() {
		return ws;
	}

	@Override
	public void run() {
		Random random = new Random();
		int counter = 51;
		while (uhr.getUhrzeit() < 1400) {
			for (int i = 0; i < 5; i++) {
				ws.add(new Skifahrer(uhr, this));
				counter++;
				fahrerGesamt = counter;
			}
			try {
				Thread.sleep(random.nextInt(400) + 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void addWartezeit(int x) {
		wartezeiten.add(x);
	}

	public Stack<Integer> getWartezeiten() {
		return wartezeiten;
	}

	public int getFahrergesamt() {
		return fahrerGesamt;
	}

}
