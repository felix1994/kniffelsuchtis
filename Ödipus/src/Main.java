/*
 * holdirdeinvertrag.de
 */
public class Main {

	static Feld[][] spielfeld = new Feld[7][7];

	public static void main(String[] args) {

		int startx;
		int starty;

		for (int x = 0; x < 7; x++) {
			for (int y = 0; y < 7; y++) {
				spielfeld[x][y] = new Feld(x, y);
			}
		}

		// Konfig
		startx = 3;
		starty = 5;

		setBuchstabe(6, 3, 'L');
		setBuchstabe(1, 1, 'A');
		setBuchstabe(5, 3, 'I');
		setBuchstabe(0, 5, 'O');
		setBuchstabe(5, 2, 'S');

		setHidnerniss(0, 2);
		setHidnerniss(1, 3);
		setHidnerniss(2, 3);
		setHidnerniss(3, 0);
		setHidnerniss(3, 6);
		setHidnerniss(4, 4);
		//

		startGame(startx, starty);

	}

	private static void startGame(int startx, int starty) {
		Feld feld;
		try {
			feld = spielfeld[startx][starty];
		} catch (ArrayIndexOutOfBoundsException e) {
			return;
		}
		if (feld.isHinderniss() || feld.isSchonBesucht())
			return;

		if (feld.getBuchstabe() != 'x')
			System.out.println(feld.getBuchstabe());

		feld.setSchonBesucht(true);

		startGame(startx + 1, starty);
		startGame(startx, starty + 1);
		startGame(startx - 1, starty);
		startGame(startx, starty - 1);

	}

	public static void setHidnerniss(int x, int y) {
		spielfeld[x][y].setHinderniss(true);
	}

	public static void setBuchstabe(int x, int y, char buchstabe) {
		spielfeld[x][y].setBuchstabe(buchstabe);
	}

}
