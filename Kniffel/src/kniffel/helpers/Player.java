package kniffel.helpers;

public class Player {

    private String name;
    private double gesamteZahlungen;
    private double durschnittlichezahlungProSpiel;
    private int durschnittlichePunktzahl;
    private int gespielteSpiele;
    private int gesamtPunktzahl;

    public Player(String name) {
        this.name = name;
    }

    public void addToGesamtpunktzahl(String value) {
        int x = Integer.parseInt(value);
        this.gesamtPunktzahl += x;
        this.durschnittlichePunktzahl = this.gesamtPunktzahl / this.gespielteSpiele;
    }

    public void addToGesamtzahlungen(int value) {
        this.gesamteZahlungen += value;
        this.durschnittlichezahlungProSpiel = this.gesamteZahlungen / this.gespielteSpiele;
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
        return durschnittlichezahlungProSpiel;
    }

    public int getDurschnittlichePunktzahl() {
        return durschnittlichePunktzahl;
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
}
