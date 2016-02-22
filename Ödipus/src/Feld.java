
public class Feld {

	int x;
	int y;
	boolean hinderniss;
	char buchstabe;
	boolean schonBesucht;
	
	public Feld(int x, int y){
		this.x = x;
		this.y = y;
		hinderniss = false;
		buchstabe = 'x';
	}

	public boolean isHinderniss() {
		return hinderniss;
	}

	public void setHinderniss(boolean hinderniss) {
		this.hinderniss = hinderniss;
	}
	
	

	public char getBuchstabe() {
		return buchstabe;
	}

	public void setBuchstabe(char buchstabe) {
		this.buchstabe = buchstabe;
	}
	
	public String toString(){
		return " x: " + String.valueOf(x) + " y: " + String.valueOf(y);
	}

	public boolean isSchonBesucht() {
		return schonBesucht;
	}

	public void setSchonBesucht(boolean schonBesucht) {
		this.schonBesucht = schonBesucht;
	}
	
	
}
