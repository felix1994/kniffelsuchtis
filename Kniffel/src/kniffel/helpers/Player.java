package kniffel.helpers;

public class Player {

	private String name;
	private double gesamteZahlungen;
	private double durschnittlichezahlungProSpiel;
	private int durschnittlichePunktzahl;
	private int gespielteSpiele;
	private int gesamtPunktzahl;
	private double offeneZahlungen;

	public Player(String name) {
		this.name = name;
	}

	public void addToGesamtpunktzahl(String value) {
		int x = Integer.parseInt(value);
		this.gesamtPunktzahl += x;
		this.durschnittlichePunktzahl = this.gesamtPunktzahl
				/ this.gespielteSpiele;
		this.durschnittlichePunktzahl = Math
				.round(this.durschnittlichePunktzahl * 100) / 100;
	}

	public void addToGesamtzahlungen(int value) {
		this.offeneZahlungen += value;
		this.gesamteZahlungen += value;
		this.durschnittlichezahlungProSpiel = this.gesamteZahlungen
				/ this.gespielteSpiele;
		this.durschnittlichezahlungProSpiel = Math
				.round(this.durschnittlichezahlungProSpiel * 100) / 100;
	}

	public int getGesamtPunktzahl() {
		return gesamtPunktzahl;
	}

	public void setGesamtPunktzahl(int gesamtPunktzahl) {
		this.gesamtPunktzahl = gesamtPunktzahl;
	}

	public String getName() {
		return name;
	}

	public double getDurschnittlichezahlungProSpiel() {
		return Math.round(100 * durschnittlichezahlungProSpiel) / 100;
	}

	public int getDurschnittlichePunktzahl() {
		return Math.round(100 * durschnittlichePunktzahl) / 100;
	}

	public int getGespielteSpiele() {
		return gespielteSpiele;
	}

	public double getGesamteZahlungen() {
		return gesamteZahlungen;
	}

	public void setDurschnittlichezahlungProSpiel(String value) {
		double v = Double.parseDouble(value);
		this.durschnittlichezahlungProSpiel = v;
	}

	public void setDurschnittlichePunktzahl(String value) {
		int v = Integer.parseInt(value);
		this.durschnittlichePunktzahl = v;
	}

	public void setGespielteSpiele(String value) {
		int v = Integer.parseInt(value);
		this.gespielteSpiele = v;
	}

	public void setGesamteZahlungen(String value) {
		double v = Double.parseDouble(value);
		this.gesamteZahlungen = v;
	}

	public void addGespieltesSpiel() {
		this.gespielteSpiele++;
	}

	public double getOffeneZahlungen() {
		return offeneZahlungen;
	}

	public void addToOffeneZahlungen(int offeneZahlungen) {
		this.offeneZahlungen += offeneZahlungen;
	}
	
	public void bezhalen(){
		this.offeneZahlungen = 0;
	}
	
	public void setOffeneZahlungen(String value){
		this.offeneZahlungen = Double.parseDouble(value);
	}

	
}
