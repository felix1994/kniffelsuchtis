package kniffel.helpers;

public class Spielergebnis {

	private String id;
	private String pkt;
	private String name;

	public Spielergebnis(String id) {
		this.id = id;
	}

	public Spielergebnis(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public Spielergebnis(String id, String pkt, String nix) {
		this.id = id;
		this.pkt = pkt;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPkt() {
		return pkt;
	}

	public void setPkt(String pkt) {
		this.pkt = pkt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
