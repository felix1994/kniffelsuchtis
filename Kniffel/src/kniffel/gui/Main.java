package kniffel.gui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import kniffel.helpers.Player;
import kniffel.wizards.MainWizard;

import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class Main {

	protected Shell shell;
	private Table table;
	private List<Player> players = new ArrayList<Player>();
	private Player Andy;
	private Player Claudi;
	private Player Flo;
	private Player Felix;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Main window = new Main();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 * 
	 * @throws IOException
	 */
	public void open() throws IOException {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 * 
	 * @throws IOException
	 */
	protected void createContents() throws IOException {
		shell = new Shell();
		Image img = new Image(shell.getDisplay(), "becher.jpg");
		shell.setBackgroundImage(img);
		shell.setSize(750, 400);
		shell.setText("Kniffelsuchtis");
		shell.setLayout(new GridLayout(3, false));
		// Player-Map befüllen
		// ---------------------------------------------
		Andy = new Player("Andy");
		players.add(Andy);
		Claudi = new Player("Claudi");
		players.add(Claudi);
		Flo = new Player("Flo");
		players.add(Flo);
		Felix = new Player("Felix");
		players.add(Felix);
		// ------------------------------

		Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);

		MenuItem mntmMenu = new MenuItem(menu, SWT.CASCADE);
		mntmMenu.setText("Menu");

		Menu menu_1 = new Menu(mntmMenu);
		mntmMenu.setMenu(menu_1);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);

		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.setTouchEnabled(true);
		GridData gd_table = new GridData(SWT.FILL, SWT.FILL, false, false, 3, 1);
		gd_table.widthHint = 650;
		gd_table.heightHint = 200;
		table.setLayoutData(gd_table);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		Font font1 = new Font(Display.getCurrent(), "Arial", 11, SWT.BOLD);
		table.setFont(font1);
		

		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE,0);
		tblclmnNewColumn.setWidth(119);
		tblclmnNewColumn.setText("...");
		tblclmnNewColumn.setWidth(270);

		TableColumn tblclmnAndy = new TableColumn(table, SWT.CENTER,1);
		tblclmnAndy.setWidth(100);
		tblclmnAndy.setText("Andy");

		TableColumn tblclmnClaudi = new TableColumn(table, SWT.CENTER,2);
		tblclmnClaudi.setWidth(100);
		tblclmnClaudi.setText("Claudi");

		TableColumn tblclmnFlo = new TableColumn(table, SWT.CENTER,3);
		tblclmnFlo.setWidth(100);
		tblclmnFlo.setText("Flo");

		TableColumn tblclmnFelix = new TableColumn(table, SWT.CENTER,4);
		tblclmnFelix.setWidth(100);
		tblclmnFelix.setText("Felix");

		final TableItem tableItem = new TableItem(table, SWT.NONE);
		tableItem.setText("Gesamtzahlungen");

		final TableItem tableItem_1 = new TableItem(table, SWT.NONE);
		tableItem_1.setText("Durchschn. Zahl. pro Spiel");

		final TableItem tableItem_2 = new TableItem(table, SWT.NONE);
		tableItem_2.setText("Durchschn pktzahl pro Spiel");

		final TableItem tableItem_4 = new TableItem(table, SWT.NONE);
		tableItem_4.setText("#gespielte Spiele");
		
		final TableItem tableItem_3 = new TableItem(table, SWT.NONE);
		tableItem_3.setText("");
		
		final TableItem tableItem_5 = new TableItem(table, SWT.NONE);
		tableItem_5.setText("offene Zahlungen");
		
		final TableItem tableItem_6 = new TableItem(table, SWT.NONE);
		addBezahlenButton(tableItem_6, tableItem_5);
		
		
		MenuItem mntmNeu = new MenuItem(menu_1, SWT.NONE);
		mntmNeu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				WizardDialog dialog = new WizardDialog(shell, new MainWizard(
						players));
				dialog.open();
				if (dialog.getReturnCode() == 0) {
					MessageBox dialog3 = new MessageBox(shell,
							SWT.ICON_QUESTION | SWT.OK | SWT.CANCEL);
					dialog3.setText("Info");
					dialog3.setMessage("Ergebnisse speichern ?");
					if (dialog3.open() == SWT.OK) {
						setValuesInStatstxt();
						displayTableItem(tableItem, "Gesamtzahlungen");
						displayTableItem(tableItem_1,
								"Durchschnittl. Zahlung pro Spiel");
						displayTableItem(tableItem_2,
								"Durchschnittl. Punktzahl pro Spiel");
						displayTableItem(tableItem_4, "Gespielte Spiele");
						displayTableItem(tableItem_5, "offene Zahlungen");
						MessageBox box2 = new MessageBox(shell, SWT.OK);
						box2.setText("Info");
						box2.setMessage("Ergebnisse erfolgreich gespeichert");
						box2.open();
					} else {
						MessageBox box = new MessageBox(shell, SWT.OK);
						box.setText("My info");
						box.setMessage("Ergebnisse nicht gespeichert !");
						box.open();
					}
				}

			}
		});
		mntmNeu.setText("Neu");

		MenuItem mntmPdfExportieren = new MenuItem(menu_1, SWT.NONE);
		mntmPdfExportieren.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				try {
					doExport(table);
					MessageBox box = new MessageBox(shell, SWT.OK);
					box.setText("My info");
					box.setMessage("Exportieren erfolgreich !");
					box.open();
				} catch (DocumentException e) {
					MessageBox box = new MessageBox(shell, SWT.OK);
					box.setText("My info");
					box.setMessage("Exportieren fehlgeschlagen !");
					box.open();
					e.printStackTrace();
				} catch (IOException e) {
					MessageBox box = new MessageBox(shell, SWT.OK);
					box.setText("My info");
					box.setMessage("Exportieren fehlgeschlagen !");
					box.open();
					e.printStackTrace();
				}
			}
		});
		mntmPdfExportieren.setText("PDF exportieren");
		
		MenuItem regeln = new MenuItem(menu_1, SWT.NONE);
		regeln.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				final Browser browser = new Browser(shell, SWT.NONE);
				browser.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
				browser.setUrl("https://de.wikipedia.org/wiki/Kniffel#Spielregeln");
				final Button button = new Button(shell,SWT.PUSH);
				button.setText("Schließen");
				button.addSelectionListener(new SelectionListener() {
					
					@Override
					public void widgetSelected(SelectionEvent arg0) {
						browser.close();
						button.dispose();
						shell.layout();
						
					}
					
					@Override
					public void widgetDefaultSelected(SelectionEvent arg0) {
						// TODO Auto-generated method stub
						
					}
				});
				
				shell.layout();
			}
		});
		regeln.setText("Kniffel Regeln");

		fillStatistic(tableItem, tableItem_1, tableItem_2, tableItem_4, tableItem_5);

	}

	private void setValuesInStatstxt() {
		PrintWriter pWriter = null;
		try {
			pWriter = new PrintWriter(new BufferedWriter(new FileWriter(
					"stats.txt")));
			pWriter.println("Andy");
			pWriter.println(Double.toString(Andy.getGesamteZahlungen()));
			pWriter.println(Double.toString(Andy
					.getDurschnittlichezahlungProSpiel()));
			pWriter.println(Integer.toString(Andy.getDurschnittlichePunktzahl()));
			pWriter.println(Integer.toString(Andy.getGespielteSpiele()));
			pWriter.println(Integer.toString(Andy.getGesamtPunktzahl()));
			pWriter.println(Double.toString(Andy.getOffeneZahlungen()));
			pWriter.println("Claudi");
			pWriter.println(Double.toString(Claudi.getGesamteZahlungen()));
			pWriter.println(Double.toString(Claudi
					.getDurschnittlichezahlungProSpiel()));
			pWriter.println(Integer.toString(Claudi
					.getDurschnittlichePunktzahl()));
			pWriter.println(Integer.toString(Claudi.getGespielteSpiele()));
			pWriter.println(Integer.toString(Claudi.getGesamtPunktzahl()));
			pWriter.println(Double.toString(Claudi.getOffeneZahlungen()));
			pWriter.println("Flo");
			pWriter.println(Double.toString(Flo.getGesamteZahlungen()));
			pWriter.println(Double.toString(Flo
					.getDurschnittlichezahlungProSpiel()));
			pWriter.println(Integer.toString(Flo.getDurschnittlichePunktzahl()));
			pWriter.println(Integer.toString(Flo.getGespielteSpiele()));
			pWriter.println(Integer.toString(Flo.getGesamtPunktzahl()));
			pWriter.println(Double.toString(Flo.getOffeneZahlungen()));
			pWriter.println("Felix");
			pWriter.println(Double.toString(Felix.getGesamteZahlungen()));
			pWriter.println(Double.toString(Felix
					.getDurschnittlichezahlungProSpiel()));
			pWriter.println(Integer.toString(Felix
					.getDurschnittlichePunktzahl()));
			pWriter.println(Integer.toString(Felix.getGespielteSpiele()));
			pWriter.println(Integer.toString(Felix.getGesamtPunktzahl()));
			pWriter.println(Double.toString(Felix.getOffeneZahlungen()));
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (pWriter != null) {
				pWriter.flush();
				pWriter.close();
			}
		}

	}

	private void fillStatistic(TableItem tableItem, TableItem tableItem_1,
			TableItem tableItem_2, TableItem tableItem_4,TableItem tableItem_5) throws IOException {
		try {
			FileReader fr = new FileReader("stats.txt");
			BufferedReader br = new BufferedReader(fr);
			String zeile = "";
			int zähler = 0;
			while (zähler < 28) {
				zeile = br.readLine();
				if (zeile == null) {
					zähler++;
					continue;
				}
				String gesamtzahlungen = br.readLine();
				String durchzahl = br.readLine();
				String durchpktzahl = br.readLine();
				String gesamtspiele = br.readLine();
				String gesamtPunktzahl = br.readLine();
				String offeneZahlungen = br.readLine();
				int pkt = Integer.parseInt(gesamtPunktzahl);
				switch (zeile) {
				case "Andy":
					Andy.setDurschnittlichePunktzahl(durchpktzahl);
					Andy.setGesamteZahlungen(gesamtzahlungen);
					Andy.setGespielteSpiele(gesamtspiele);
					Andy.setDurschnittlichezahlungProSpiel(durchzahl);
					Andy.setGesamtPunktzahl(pkt);
					Andy.setOffeneZahlungen(offeneZahlungen);
					break;
				case "Claudi":
					Claudi.setDurschnittlichePunktzahl(durchpktzahl);
					Claudi.setGesamteZahlungen(gesamtzahlungen);
					Claudi.setGespielteSpiele(gesamtspiele);
					Claudi.setDurschnittlichezahlungProSpiel(durchzahl);
					Claudi.setGesamtPunktzahl(pkt);
					Claudi.setOffeneZahlungen(offeneZahlungen);
					break;
				case "Felix":
					Felix.setDurschnittlichePunktzahl(durchpktzahl);
					Felix.setGesamteZahlungen(gesamtzahlungen);
					Felix.setGespielteSpiele(gesamtspiele);
					Felix.setDurschnittlichezahlungProSpiel(durchzahl);
					Felix.setGesamtPunktzahl(pkt);
					Felix.setOffeneZahlungen(offeneZahlungen);
					break;
				case "Flo":
					Flo.setDurschnittlichePunktzahl(durchpktzahl);
					Flo.setGesamteZahlungen(gesamtzahlungen);
					Flo.setGespielteSpiele(gesamtspiele);
					Flo.setDurschnittlichezahlungProSpiel(durchzahl);
					Flo.setGesamtPunktzahl(pkt);
					Flo.setOffeneZahlungen(offeneZahlungen);
					break;
				}
				zähler += 7;
			}

			displayTableItem(tableItem, "Gesamtzahlungen");
			displayTableItem(tableItem_1, "Durchschnittl. Zahlung pro Spiel");
			displayTableItem(tableItem_2, "Durchschnittl. Punktzahl pro Spiel");
			displayTableItem(tableItem_4, "Gespielte Spiele");
			displayTableItem(tableItem_5, "offene Zahlungen");

			br.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void displayTableItem(TableItem tableItem, String value) {
		String[] text = new String[5];
		text[0] = value;
		for (Player player : players) {
			int x = 0;
			switch (player.getName()) {
			case "Andy":
				x = 1;
				break;
			case "Claudi":
				x = 2;
				break;
			case "Flo":
				x = 3;
				break;
			case "Felix":
				x = 4;
				break;
			}
			switch (value) {
			case "Gesamtzahlungen":
				text[x] = Double.toString(player.getGesamteZahlungen() / 100)
						+ " €";
				break;
			case "Durchschnittl. Zahlung pro Spiel":
				text[x] = Double.toString(player
						.getDurschnittlichezahlungProSpiel() / 100) + " €";
				break;
			case "Durchschnittl. Punktzahl pro Spiel":
				text[x] = Integer
						.toString(player.getDurschnittlichePunktzahl());
				break;
			case "Gespielte Spiele":
				text[x] = Integer.toString(player.getGespielteSpiele());
				break;
			case "offene Zahlungen":
				text[x] = Double.toString(player.getOffeneZahlungen() / 100.0 );
				break;
			}
			if(tableItem.getText().equals("offene Zahlungen")){
				if(text[x].equals("0.0")){
					Color green = Display.getCurrent().getSystemColor(SWT.COLOR_GREEN);
					tableItem.setBackground(x,green);
				}else{
					Color red = Display.getCurrent().getSystemColor(SWT.COLOR_RED);
					tableItem.setBackground(x,red);
				}
			}

		}
		tableItem.setText(text);
		
	}

	public void doExport(Table table) throws DocumentException, IOException {
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		//final String PDF = "/Users/FFRITZSC/Kniffelstand"
				//+ formattedDate.trim() + ".pdf";
		final String PDF2 = System.getProperty("user.dir") + "/Kniffelstand_"
				+ sdf.format(date).toString() + ".pdf";
		Document document = new Document();
		PdfWriter writer = PdfWriter.getInstance(document,
				new FileOutputStream(PDF2));
		document.open();
		document.addTitle("Kniffel Zwischenstand am: " + sdf.format(date).toString());
		document.add(new Phrase("Kniffel Zwischenstand am: " + sdf.format(date).toString()));
		PdfPTable pdfTable = new PdfPTable(5);
		PdfPCell names = new PdfPCell();
		names.setColspan(5);
		int x = 0;
		for (Player p : players) {
			if (x == 0)
				pdfTable.addCell("...");
			x++;
			names = new PdfPCell(new Phrase(p.getName()));
			pdfTable.addCell(names);
		}
		for (int i = 0; i < 7; i++) {
			PdfPCell cell = new PdfPCell();
			for (int j = 0; j < 5; j++) {
				cell = new PdfPCell(new Phrase(table.getItem(i).getText(j)));
				if (j == 0)
					cell.setHorizontalAlignment(200);
				pdfTable.addCell(cell);
			}
		}
		pdfTable.setWidths(new float[] { 50f, 20f, 20f, 20f, 20f });
		document.add(pdfTable);
		document.close();

	}
	
	public void addBezahlenButton(final TableItem tableItem , final TableItem tableItem_5){
		
		
		Button btAndy = new Button(table,SWT.PUSH);
		btAndy.setText("Bezahlen");
		btAndy.setFont(new Font(Display.getCurrent(), "Tahoma", 11, SWT.NONE));
		btAndy.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				String zuZahlen = Double.toString(Andy.bezhalen());
				displayTableItem(tableItem_5, "offene Zahlungen");
				setValuesInStatstxt();
				showMessage(zuZahlen);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				
				
			}
		});
		TableEditor editor = new TableEditor(table);
		editor.grabHorizontal  = true;
		editor.grabVertical = true;
		editor.setEditor(btAndy, tableItem, 1);
		
		Button btClaudi = new Button(table,SWT.PUSH);
		btClaudi.setText("Bezahlen");
		btClaudi.setFont(new Font(Display.getCurrent(), "Tahoma", 11, SWT.NONE));
		btClaudi.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				String zuZahlen = Double.toString(Claudi.bezhalen());
				displayTableItem(tableItem_5, "offene Zahlungen");	
				setValuesInStatstxt();
				showMessage(zuZahlen);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				
				
			}
		});
		TableEditor editor2 = new TableEditor(table);
		editor2.grabHorizontal  = true;
		editor2.grabVertical = true;
		editor2.setEditor(btClaudi, tableItem, 2);
		
		Button btFlo = new Button(table,SWT.PUSH);
		btFlo.setText("Bezahlen");
		btFlo.setFont(new Font(Display.getCurrent(), "Tahoma", 11, SWT.NONE));
		btFlo.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				String zuZahlen = Double.toString(Flo.bezhalen());
				displayTableItem(tableItem_5, "offene Zahlungen");
				setValuesInStatstxt();
				showMessage(zuZahlen);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				
				
			}
		});
		TableEditor editor3 = new TableEditor(table);
		editor3.grabHorizontal  = true;
		editor3.grabVertical = true;
		editor3.setEditor(btFlo, tableItem, 3);
		Button btFelix = new Button(table,SWT.PUSH);
		btFelix.setText("Bezahlen");
		btFelix.setFont(new Font(Display.getCurrent(), "Tahoma", 11, SWT.NONE));
		btFelix.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				String zuZahlen = Double.toString(Felix.bezhalen());
				displayTableItem(tableItem_5, "offene Zahlungen");
				setValuesInStatstxt();
				showMessage(zuZahlen);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				
				
			}
		});
		TableEditor editor4 = new TableEditor(table);
		editor4.grabHorizontal  = true;
		editor4.grabVertical = true;
		editor4.setEditor(btFelix, tableItem, 4);
		
	}
	
	public void showMessage(String value){
		MessageBox box = new MessageBox(shell, SWT.OK);
		box.setText("Zahltag");
		if(Double.parseDouble(value) == 0.0){
			box.setMessage("Gratuliere, du musst nichts zahlen, alles paletti =) ");
		}else
			if(Double.parseDouble(value) <= 1){
				box.setMessage("Zu zahlen: " + value + "€ \nDa hast du nochmal Glück gehabt");
		}else
			box.setMessage(value + "€ \n " + getRandomMessage());
		box.open();	
	}
	
	public String getRandomMessage(){
		Random random = new Random();
		int x = random.nextInt(100);
		if(x<100 && x>80)
			return "Merci beaucoup";
		if(x<81 && x>60)
			return "da macht Kniffeln Spaß, nichtwahr ?";
		if(x<61 && x>50)
			return "und die Kasse klingelt";
		if(x<51 && x>40)
			return "Money Money Money";
		if(x<41 && x>30)
			return "Immer rein in die Kasse";
		if(x<31 && x>10)
			return "Nächstes Mal einfach BEHARRLICHER spielen";
		if(x<11 && x>0)
			return "Zaaaahltag";
		return "Zaaaahltag";
	}
}
