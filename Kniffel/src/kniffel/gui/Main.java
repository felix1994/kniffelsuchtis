package kniffel.gui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
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

import kniffel.helpers.Player;
import kniffel.wizards.MainWizard;

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
        shell.setSize(900, 600);
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
        gd_table.widthHint = 590;
        gd_table.heightHint = 200;
        table.setLayoutData(gd_table);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);

        TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
        tblclmnNewColumn.setWidth(119);
        tblclmnNewColumn.setText("...");
        tblclmnNewColumn.setWidth(200);

        TableColumn tblclmnAndy = new TableColumn(table, SWT.CENTER);
        tblclmnAndy.setWidth(100);
        tblclmnAndy.setText("Andy");

        TableColumn tblclmnClaudi = new TableColumn(table, SWT.CENTER);
        tblclmnClaudi.setWidth(100);
        tblclmnClaudi.setText("Claudi");

        TableColumn tblclmnFlo = new TableColumn(table, SWT.CENTER);
        tblclmnFlo.setWidth(100);
        tblclmnFlo.setText("Flo");

        TableColumn tblclmnFelix = new TableColumn(table, SWT.CENTER);
        tblclmnFelix.setWidth(100);
        tblclmnFelix.setText("Felix");

        final TableItem tableItem = new TableItem(table, SWT.NONE);
        tableItem.setText("Gesamtzahlungen");

        final TableItem tableItem_1 = new TableItem(table, SWT.NONE);
        tableItem_1.setText("Durchschn. Zahl. pro Spiel");

        final TableItem tableItem_2 = new TableItem(table, SWT.NONE);
        tableItem_2.setText("Durchschn pktzahl pro Spiel");

        TableItem tableItem_3 = new TableItem(table, SWT.NONE);
        tableItem_3.setText(" ");

        final TableItem tableItem_4 = new TableItem(table, SWT.NONE);
        tableItem_4.setText("#gespielte Spiele");
        MenuItem mntmNeu = new MenuItem(menu_1, SWT.NONE);
        mntmNeu.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent arg0) {
                WizardDialog dialog = new WizardDialog(shell, new MainWizard(players));
                dialog.open();
                if (dialog.getReturnCode() == 0) {
                    MessageBox dialog3 = new MessageBox(shell, SWT.ICON_QUESTION | SWT.OK | SWT.CANCEL);
                    dialog3.setText("Info");
                    dialog3.setMessage("Ergebnisse speichern ?");
                    if (dialog3.open() == SWT.OK) {
                        setValuesInStatstxt();
                        displayTableItem(tableItem, "Gesamtzahlungen");
                        displayTableItem(tableItem_1, "Durchschnittl. Zahlung pro Spiel");
                        displayTableItem(tableItem_2, "Durchschnittl. Punktzahl");
                        displayTableItem(tableItem_4, "Gespielte Spiele");
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
                } catch (DocumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        mntmPdfExportieren.setText("PDF exportieren");

        fillStatistic(tableItem, tableItem_1, tableItem_2, tableItem_4);

    }

    private void setValuesInStatstxt() {
        PrintWriter pWriter = null;
        try {
            pWriter = new PrintWriter(new BufferedWriter(new FileWriter("stats.txt")));
            pWriter.println("Andy");
            pWriter.println(Double.toString(Andy.getGesamteZahlungen()));
            pWriter.println(Double.toString(Andy.getDurschnittlichezahlungProSpiel()));
            pWriter.println(Integer.toString(Andy.getDurschnittlichePunktzahl()));
            pWriter.println(Integer.toString(Andy.getGespielteSpiele()));
            pWriter.println(Integer.toString(Andy.getGesamtPunktzahl()));
            pWriter.println("Claudi");
            pWriter.println(Double.toString(Claudi.getGesamteZahlungen()));
            pWriter.println(Double.toString(Claudi.getDurschnittlichezahlungProSpiel()));
            pWriter.println(Integer.toString(Claudi.getDurschnittlichePunktzahl()));
            pWriter.println(Integer.toString(Claudi.getGespielteSpiele()));
            pWriter.println(Integer.toString(Claudi.getGesamtPunktzahl()));
            pWriter.println("Flo");
            pWriter.println(Double.toString(Flo.getGesamteZahlungen()));
            pWriter.println(Double.toString(Flo.getDurschnittlichezahlungProSpiel()));
            pWriter.println(Integer.toString(Flo.getDurschnittlichePunktzahl()));
            pWriter.println(Integer.toString(Flo.getGespielteSpiele()));
            pWriter.println(Integer.toString(Flo.getGesamtPunktzahl()));
            pWriter.println("Felix");
            pWriter.println(Double.toString(Felix.getGesamteZahlungen()));
            pWriter.println(Double.toString(Felix.getDurschnittlichezahlungProSpiel()));
            pWriter.println(Integer.toString(Felix.getDurschnittlichePunktzahl()));
            pWriter.println(Integer.toString(Felix.getGespielteSpiele()));
            pWriter.println(Integer.toString(Felix.getGesamtPunktzahl()));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            if (pWriter != null) {
                pWriter.flush();
                pWriter.close();
            }
        }

    }

    private void fillStatistic(TableItem tableItem, TableItem tableItem_1, TableItem tableItem_2, TableItem tableItem_4)
            throws IOException {
        try {
            FileReader fr = new FileReader("stats.txt");
            BufferedReader br = new BufferedReader(fr);
            String zeile = "";
            int zähler = 0;
            while (zähler < 24) {
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
                int pkt = Integer.parseInt(gesamtPunktzahl);
                switch (zeile) {
                case "Andy":
                    Andy.setDurschnittlichePunktzahl(durchpktzahl);
                    Andy.setGesamteZahlungen(gesamtzahlungen);
                    Andy.setGespielteSpiele(gesamtspiele);
                    Andy.setDurschnittlichezahlungProSpiel(durchzahl);
                    Andy.setGesamtPunktzahl(pkt);
                    break;
                case "Claudi":
                    Claudi.setDurschnittlichePunktzahl(durchpktzahl);
                    Claudi.setGesamteZahlungen(gesamtzahlungen);
                    Claudi.setGespielteSpiele(gesamtspiele);
                    Claudi.setDurschnittlichezahlungProSpiel(durchzahl);
                    Claudi.setGesamtPunktzahl(pkt);
                    break;
                case "Felix":
                    Felix.setDurschnittlichePunktzahl(durchpktzahl);
                    Felix.setGesamteZahlungen(gesamtzahlungen);
                    Felix.setGespielteSpiele(gesamtspiele);
                    Felix.setDurschnittlichezahlungProSpiel(durchzahl);
                    Felix.setGesamtPunktzahl(pkt);
                    break;
                case "Flo":
                    Flo.setDurschnittlichePunktzahl(durchpktzahl);
                    Flo.setGesamteZahlungen(gesamtzahlungen);
                    Flo.setGespielteSpiele(gesamtspiele);
                    Flo.setDurschnittlichezahlungProSpiel(durchzahl);
                    Flo.setGesamtPunktzahl(pkt);
                    break;
                }
                zähler += 6;
            }

            displayTableItem(tableItem, "Gesamtzahlungen");
            displayTableItem(tableItem_1, "Durchschnittl. Zahlung pro Spiel");
            displayTableItem(tableItem_2, "Durchschnittl. Punktzahl");
            displayTableItem(tableItem_4, "Gespielte Spiele");

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
                text[x] = Double.toString(player.getGesamteZahlungen() / 100) + " €";
                break;
            case "Durchschnittl. Zahlung pro Spiel":
                text[x] = Double.toString(player.getDurschnittlichezahlungProSpiel() / 100) + " €";
                break;
            case "Durchschnittl. Punktzahl":
                text[x] = Integer.toString(player.getDurschnittlichePunktzahl());
                break;
            case "Gespielte Spiele":
                text[x] = Integer.toString(player.getGespielteSpiele());
                break;
            }

        }
        tableItem.setText(text);
    }

    public void doExport(Table table) throws DocumentException, IOException {
        Calendar cal = Calendar.getInstance();
        String date = cal.getTime().toString();
        String formattedDate = date.substring(4, 10);
        final String PDF = "/Users/FFRITZSC/Kniffelstand" + formattedDate.trim() + ".pdf";
        final String PDF2 = System.getProperty("user.dir") + "/Kniffelstand_" + formattedDate + ".pdf";
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(PDF2));
        document.open();
        document.addTitle("Kniffel Zwischenstand am: " + date);
        document.add(new Phrase("Kniffel Zwischenstand am: " + date));
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
        for (int i = 0; i < 5; i++) {
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

}
