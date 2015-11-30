package kniffel.wizards;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class FirstPage extends WizardPage {

	private Composite composite;
	private Combo combo;

	private Map<String, String> values = new HashMap<String, String>();
	private int anzahlSpieler;

	private Set<Control> controls = new HashSet<Control>();

	public FirstPage(String pageName) {
		super(pageName);
	}

	@Override
	public boolean canFlipToNextPage() {
		if (combo.getText().equals(""))
			return false;
		else
			return true;
	}

	@Override
	public void createControl(final Composite parent) {
		setTitle("Neues Spiel hinzufügen");
		composite = new Composite(parent, SWT.NONE);
		GridLayout gd = new GridLayout(8, true);
		composite.setLayout(gd);
		Label label = new Label(composite, SWT.NONE);
		label.setText("#Spieler");
		label.setSize(200, 200);
		combo = new Combo(composite, SWT.NONE);
		combo.add("2");
		combo.add("3");
		combo.add("4");
		combo.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent arg0) {
				anzahlSpieler = Integer.parseInt(combo.getText());
				displayNewFields2(combo.getText(), parent);
				setPageComplete(false);

			}
		});
		combo.addVerifyListener(new VerifyListener() {

			@Override
			public void verifyText(VerifyEvent arg0) {
				if (arg0.text.matches("\\d*")) {
					int value = Integer.parseInt(arg0.text);
					arg0.doit = ((value < 5) & (value > 0)) ? true : false;
				} else
					arg0.doit = false;

			}
		});
		GridData gdd = new GridData();
		gdd.horizontalSpan = 4;
		label.setLayoutData(gdd);
		combo.setLayoutData(gdd);
		setPageComplete(false);
		setControl(composite);

	}

	public void displayNewFields2(String value, Composite parent) {
		int players = Integer.parseInt(value);
		clearControls();
		switch (players) {
		case 2:
			composite.setLayout(new GridLayout(2, false));

			Label lblSpieler = new Label(composite, SWT.NONE);
			lblSpieler.setAlignment(SWT.CENTER);
			lblSpieler.setText("Spieler 1");

			Label lblSpieler_1 = new Label(composite, SWT.NONE);
			lblSpieler_1.setAlignment(SWT.CENTER);
			lblSpieler_1.setText("Spieler 2");

			final Combo combo = new Combo(composite, SWT.NONE);
			addNames(combo);
			combo.addModifyListener(new ModifyListener() {

				@Override
				public void modifyText(ModifyEvent arg0) {
					values.put(combo.getText(), "0");

				}
			});

			final Combo combo_1 = new Combo(composite, SWT.NONE);
			addNames(combo_1);
			combo_1.addModifyListener(new ModifyListener() {

				@Override
				public void modifyText(ModifyEvent arg0) {
					values.put(combo_1.getText(), "1");

				}
			});

			final Text text = new Text(composite, SWT.BORDER);
			text.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false,
					false, 1, 1));
			addTextModAndVerfList(text, "0");

			final Text text_1 = new Text(composite, SWT.BORDER);
			text_1.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false,
					false, 1, 1));
			addTextModAndVerfList(text_1, "1");
			addComboVerfList(combo);
			addComboVerfList(combo_1);

			controls.add(lblSpieler);
			controls.add(lblSpieler_1);
			controls.add(text);
			controls.add(text_1);
			controls.add(combo);
			controls.add(combo_1);
			break;

		case 3:
			composite.setLayout(new GridLayout(3, false));

			Label lblSpieler4 = new Label(composite, SWT.NONE);
			lblSpieler4.setAlignment(SWT.CENTER);
			lblSpieler4.setText("Spieler 1");

			Label lblSpieler_3 = new Label(composite, SWT.NONE);
			lblSpieler_3.setAlignment(SWT.CENTER);
			lblSpieler_3.setText("Spieler 2");

			Label lblSpieler_2 = new Label(composite, SWT.NONE);
			GridData gd_lblSpieler_2 = new GridData(SWT.CENTER, SWT.CENTER,
					false, false, 1, 1);
			gd_lblSpieler_2.widthHint = 84;
			lblSpieler_2.setLayoutData(gd_lblSpieler_2);
			lblSpieler_2.setText("Spieler 3");

			final Combo combo4 = new Combo(composite, SWT.NONE);
			addNames(combo4);
			combo4.addModifyListener(new ModifyListener() {

				@Override
				public void modifyText(ModifyEvent arg0) {
					values.put(combo4.getText(), "0");

				}
			});

			final Combo combo3 = new Combo(composite, SWT.NONE);
			addNames(combo3);
			combo3.addModifyListener(new ModifyListener() {

				@Override
				public void modifyText(ModifyEvent arg0) {
					values.put(combo3.getText(), "1");

				}
			});

			final Combo combo2 = new Combo(composite, SWT.NONE);
			combo2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
					false, 1, 1));
			addNames(combo2);
			combo2.addModifyListener(new ModifyListener() {

				@Override
				public void modifyText(ModifyEvent arg0) {
					values.put(combo2.getText(), "2");

				}
			});

			final Text text2 = new Text(composite, SWT.BORDER);
			text2.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false,
					false, 1, 1));
			addTextModAndVerfList(text2, "0");

			final Text text3 = new Text(composite, SWT.BORDER);
			text3.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false,
					false, 1, 1));
			addTextModAndVerfList(text3, "1");

			final Text text4 = new Text(composite, SWT.BORDER);
			text4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
					1, 1));
			addTextModAndVerfList(text4, "2");
			addComboVerfList(combo2);
			addComboVerfList(combo3);
			addComboVerfList(combo4);

			controls.add(text2);
			controls.add(text3);
			controls.add(text4);
			controls.add(combo2);
			controls.add(combo3);
			controls.add(combo4);
			controls.add(lblSpieler_2);
			controls.add(lblSpieler_3);
			controls.add(lblSpieler4);
			break;
		case 4:
			composite.setLayout(new GridLayout(4, false));

			Label lblSpieler5 = new Label(composite, SWT.NONE);
			lblSpieler5.setAlignment(SWT.CENTER);
			lblSpieler5.setText("Spieler 1");

			Label lblSpieler6 = new Label(composite, SWT.NONE);
			lblSpieler6.setAlignment(SWT.CENTER);
			lblSpieler6.setText("Spieler 2");

			Label lblSpieler7 = new Label(composite, SWT.NONE);
			GridData gd_lblSpieler7 = new GridData(SWT.CENTER, SWT.CENTER,
					false, false, 1, 1);
			gd_lblSpieler7.widthHint = 84;
			lblSpieler7.setLayoutData(gd_lblSpieler7);
			lblSpieler7.setText("Spieler 3");

			Label lblSpieler8 = new Label(composite, SWT.NONE);
			lblSpieler8.setText("Spieler 4");

			final Combo combo6 = new Combo(composite, SWT.NONE);
			addNames(combo6);
			combo6.addModifyListener(new ModifyListener() {

				@Override
				public void modifyText(ModifyEvent arg0) {
					values.put(combo6.getText(), "0");

				}
			});

			final Combo combo5 = new Combo(composite, SWT.NONE);
			addNames(combo5);
			combo5.addModifyListener(new ModifyListener() {

				@Override
				public void modifyText(ModifyEvent arg0) {
					values.put(combo5.getText(), "1");

				}
			});

			final Combo combo7 = new Combo(composite, SWT.NONE);
			combo7.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
					false, 1, 1));
			addNames(combo7);
			combo7.addModifyListener(new ModifyListener() {

				@Override
				public void modifyText(ModifyEvent arg0) {
					values.put(combo7.getText(), "2");

				}
			});

			final Combo combo8 = new Combo(composite, SWT.NONE);
			combo8.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
					false, 1, 1));
			addNames(combo8);
			combo8.addModifyListener(new ModifyListener() {

				@Override
				public void modifyText(ModifyEvent arg0) {
					values.put(combo8.getText(), "3");

				}
			});

			final Text text6 = new Text(composite, SWT.BORDER);
			text6.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false,
					false, 1, 1));
			addTextModAndVerfList(text6, "0");

			final Text text5 = new Text(composite, SWT.BORDER);
			text5.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false,
					false, 1, 1));
			addTextModAndVerfList(text5, "1");

			final Text text7 = new Text(composite, SWT.BORDER);
			text7.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
					1, 1));
			addTextModAndVerfList(text7, "2");

			final Text text8 = new Text(composite, SWT.BORDER);
			text8.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
					1, 1));
			addTextModAndVerfList(text8, "3");
			addComboVerfList(combo5);
			addComboVerfList(combo6);
			addComboVerfList(combo7);
			addComboVerfList(combo8);

			controls.add(text8);
			controls.add(text7);
			controls.add(text6);
			controls.add(text5);
			controls.add(combo5);
			controls.add(combo6);
			controls.add(combo7);
			controls.add(combo8);
			controls.add(lblSpieler6);
			controls.add(lblSpieler7);
			controls.add(lblSpieler8);
			controls.add(lblSpieler5);

			break;
		}
		composite.layout();

	}

	public void addTextModAndVerfList(final Text text, final String value) {
		text.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent arg0) {
				if (text.getText().length() != 3)
					return;
				for (String key : values.keySet()) {
					if (values.get(key).equals(value)) {
						values.put(key, text.getText());
					}
				}

			}
		});

		text.addVerifyListener(new VerifyListener() {

			@Override
			public void verifyText(VerifyEvent arg0) {
				arg0.doit = arg0.text.matches("\\d*");

			}
		});
	}

	public void addNames(Combo combo) {
		combo.add("Andy");
		combo.add("Claudi");
		combo.add("Flo");
		combo.add("Felix");
	}

	public int getAnzahlSpieler() {
		return anzahlSpieler;
	}

	public void setAnzahlSpieler(int anzahlSpieler) {
		this.anzahlSpieler = anzahlSpieler;
	}

	public Map<String, String> getValues() {
		return values;
	}

	public void setValues(Map<String, String> values) {
		this.values = values;
	}

	public void addComboVerfList(Combo c) {
		c.addVerifyListener(new VerifyListener() {

			@Override
			public void verifyText(VerifyEvent arg0) {
				Set<String> names = new HashSet<String>();
				names.add("Andy");
				names.add("Claudi");
				names.add("Felix");
				names.add("Flo");
				arg0.doit = names.contains(arg0.text);

			}
		});
	}

	public void clearControls() {
		for (Control c : controls) {
			c.dispose();
		}
		controls.clear();
	}

}
