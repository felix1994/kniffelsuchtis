package core;

public class Start {

	private int maxAnzahlSkifahrer;
	private int liftdauer;
	private int fahrtdauerMax;
	private int fahrtdauerMin;
	private int abfertigungVomLift;
	private int anzahlBeiEr�ffnung;

	private Warteschlange ws;
	private Uhr uhr;

	public Start(int maxAnzahlSkifahrer, int liftdauer, int fahrtdauerMax, int fahrtdauerMin, int abfertigungVomLift,
			int anzahlBeiEr�ffnung) {
		this.maxAnzahlSkifahrer = maxAnzahlSkifahrer;
		this.liftdauer = liftdauer;
		this.fahrtdauerMax = fahrtdauerMax;
		this.fahrtdauerMin = fahrtdauerMin;
		this.abfertigungVomLift = abfertigungVomLift;
		this.anzahlBeiEr�ffnung = anzahlBeiEr�ffnung;

		ws = new Warteschlange();
		ws.add(anzahlBeiEr�ffnung);
		uhr = new Uhr();

		startSimulation();
	}

	public void startSimulation() {
		uhr.start();
		while (uhr.getUhrzeit() <= 1600) {
		}
	}

}
