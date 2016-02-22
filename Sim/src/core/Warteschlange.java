package core;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Warteschlange extends Thread {

	private Queue<Skifahrer> ws;
	Random random = new Random();

	public Warteschlange() {
		ws = new LinkedList<Skifahrer>();
	}

	@Override
	public void run() {
		Random random = new Random();

	}

	public void add(int x) {
		int counter = 0;
		while (counter < x) {
			ws.add(new Skifahrer());
		}
	}

}
