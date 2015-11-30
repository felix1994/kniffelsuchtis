package kniffel.wizards;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import kniffel.helpers.Player;

import org.eclipse.jface.wizard.Wizard;

public class MainWizard extends Wizard {

	private FirstPage first;
	private List<Player> players;

	public MainWizard(List<Player> players) {
		super();
		this.players = players;
	}

	public void addPages() {
		first = new FirstPage("first");
		addPage(first);
	}

	@Override
	public boolean performFinish() {
		if (canFinish()) {
			Map<String, String> values = first.getValues();
			for (Player player : players) {
				if (values.containsKey(player.getName())) {
					player.addGespieltesSpiel();
					player.addToGesamtpunktzahl(values.get(player.getName()));
				}
			}
			int anzahlSpieler = values.keySet().size();

			// Hilfsfelder befüllen
			// ------------------------------------------------
			int[] ergebnisse = new int[anzahlSpieler];
			String[] names = new String[anzahlSpieler];
			int x = 0;
			for (Entry<String, String> entry : values.entrySet()) {
				ergebnisse[x] = Integer.parseInt(entry.getValue());
				x++;
			}
			Arrays.sort(ergebnisse);
			for (String name : values.keySet()) { // BEI GLEICHSTAND IMMER DER
													// // GLEICHE NAME IM ARRAY
				for (int y = 0; y < anzahlSpieler; y++) {
					if (ergebnisse[y] == Integer.parseInt(values.get(name))) {
						names[y] = name;
					}
				}
			}
			// -------------------------------------------------------------------
			switch (anzahlSpieler) {
			case 2:
				Player player = getPlayer(names[0]);
				player.addToGesamtzahlungen(ergebnisse[1] - ergebnisse[0]);
				Player player2 = getPlayer(names[1]);
				player2.addToGesamtzahlungen(0);
				break;
			case 3:
				Player player3 = getPlayer(names[0]);
				player3.addToGesamtzahlungen(ergebnisse[2] - ergebnisse[0]
						+ ergebnisse[1] - ergebnisse[0]);
				Player player4 = getPlayer(names[1]);
				player4.addToGesamtzahlungen(ergebnisse[2] - ergebnisse[1]);
				Player player5 = getPlayer(names[2]);
				player5.addToGesamtzahlungen(0);
				break;
			case 4:
				Player player6 = getPlayer(names[0]);
				player6.addToGesamtzahlungen(ergebnisse[3] - ergebnisse[0]
						+ ergebnisse[2] - ergebnisse[0]);
				Player player7 = getPlayer(names[1]);
				player7.addToGesamtzahlungen(ergebnisse[3] - ergebnisse[1]
						+ ergebnisse[2] - ergebnisse[1]);
				Player player8 = getPlayer(names[2]);
				player8.addToGesamtzahlungen(ergebnisse[3] - ergebnisse[2]);
				Player player9 = getPlayer(names[3]);
				player9.addToGesamtzahlungen(0);
				break;
			}

			return true;
		}
		return false;
	}

	public Player getPlayer(String name) {
		for (Player player : players) {
			if (player.getName().equalsIgnoreCase((name)))
				return player;
		}
		return null;
	}

	public boolean canFinish() {
		return ((this.getContainer().getCurrentPage() == first) && (this
				.getContainer().getCurrentPage().canFlipToNextPage())) ? true
				: false;
	}
}
