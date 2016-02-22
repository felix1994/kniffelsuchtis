package core;

public class Start {

	private int maxAnzahlSkifahrer;
	private int liftdauer;
	private int fahrtdauerMax;
	private int fahrtdauerMin;
	private int abfertigungVomLift;
	private int anzahlBeiEröffnung;

	private Warteschlange ws;
	private Uhr uhr;

	public Start(int maxAnzahlSkifahrer, int liftdauer, int fahrtdauerMax, int fahrtdauerMin, int abfertigungVomLift,
			int anzahlBeiEröffnung) {
		this.maxAnzahlSkifahrer = maxAnzahlSkifahrer;
		this.liftdauer = liftdauer;
		this.fahrtdauerMax = fahrtdauerMax;
		this.fahrtdauerMin = fahrtdauerMin;
		this.abfertigungVomLift = abfertigungVomLift;
		this.anzahlBeiEröffnung = anzahlBeiEröffnung;

		ws = new Warteschlange();
		ws.add(anzahlBeiEröffnung);
		uhr = new Uhr();

		startSimulation();
	}

	public void startSimulation() {
		uhr.start();
		while (uhr.getUhrzeit() <= 1600) {
		}
	}

}
