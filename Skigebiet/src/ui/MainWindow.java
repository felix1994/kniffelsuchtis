package ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.wb.swt.SWTResourceManager;

import core.Start;

public class MainWindow {

	protected Shell shell;
	private Start start;
	private Spinner anzahlKabinen;
	private Spinner anzahlPersUm8;
	private Spinner abfahrtsdauerMin;
	private Spinner abfahrtsdauerMax;
	private Spinner maxSkifahrer;
	private Spinner liftdauer;
	private Button btnSpeedDurchlauf;
	private Button btnStop;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainWindow window = new MainWindow();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
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
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(621, 386);
		shell.setText("SWT Application");
		shell.setLayout(new FillLayout(SWT.VERTICAL));

		Composite composite = new Composite(shell, SWT.BORDER);
		RowLayout rl_composite = new RowLayout(SWT.VERTICAL);
		rl_composite.justify = true;
		rl_composite.center = true;
		composite.setLayout(rl_composite);

		Button btnStart = new Button(composite, SWT.NONE);
		btnStart.setLayoutData(new RowData(72, 163));
		btnStart.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {

				int anzahlKabineni = anzahlKabinen.getSelection();
				int anzahlPersUm8i = anzahlPersUm8.getSelection();
				int abfahrtsdauerMaxi = abfahrtsdauerMax.getSelection();
				int abfahrtsdauerMini = abfahrtsdauerMin.getSelection();
				int maxSkifahreri = maxSkifahrer.getSelection();
				int liftdaueri = liftdauer.getSelection();
				start = new Start(anzahlKabineni, anzahlPersUm8i, abfahrtsdauerMaxi, abfahrtsdauerMini, maxSkifahreri,
						liftdaueri);
				btnStop.setEnabled(true);
			}
		});
		btnStart.setText("Start");

		Group grpKonfiguration = new Group(composite, SWT.NONE);
		grpKonfiguration.setLayoutData(new RowData(438, 149));
		grpKonfiguration.setText("Konfiguration");

		Label lblAnzahlKabinen = new Label(grpKonfiguration, SWT.NONE);
		lblAnzahlKabinen.setBounds(10, 21, 95, 24);
		lblAnzahlKabinen.setText("#Kabinen:");

		anzahlKabinen = new Spinner(grpKonfiguration, SWT.BORDER);
		anzahlKabinen.setBounds(122, 18, 47, 24);
		anzahlKabinen.setMaximum(50);
		anzahlKabinen.setMinimum(10);

		Label lblpersUm = new Label(grpKonfiguration, SWT.NONE);
		lblpersUm.setBounds(10, 57, 95, 15);
		lblpersUm.setText("#Pers um 8:00:");

		anzahlPersUm8 = new Spinner(grpKonfiguration, SWT.BORDER);
		anzahlPersUm8.setBounds(122, 54, 47, 22);

		Label lblAbfahrtsdauerminmax = new Label(grpKonfiguration, SWT.NONE);
		lblAbfahrtsdauerminmax.setBounds(10, 97, 106, 15);
		lblAbfahrtsdauerminmax.setText("Abfahrtsdauer Min:");

		abfahrtsdauerMin = new Spinner(grpKonfiguration, SWT.BORDER);
		abfahrtsdauerMin.setBounds(122, 94, 47, 22);

		abfahrtsdauerMax = new Spinner(grpKonfiguration, SWT.BORDER);
		abfahrtsdauerMax.setBounds(207, 94, 47, 22);

		Label lblMax = new Label(grpKonfiguration, SWT.NONE);
		lblMax.setBounds(175, 97, 26, 15);
		lblMax.setText("Max:");

		Label lblmaxSkifahrer = new Label(grpKonfiguration, SWT.NONE);
		lblmaxSkifahrer.setBounds(10, 142, 116, 15);
		lblmaxSkifahrer.setText("#Max. Skifahrer:");

		maxSkifahrer = new Spinner(grpKonfiguration, SWT.BORDER);
		maxSkifahrer.setBounds(122, 135, 47, 22);

		Label lblLiftdauer = new Label(grpKonfiguration, SWT.NONE);
		lblLiftdauer.setBounds(265, 21, 88, 15);
		lblLiftdauer.setText("Liftdauer in min:");

		liftdauer = new Spinner(grpKonfiguration, SWT.BORDER);
		liftdauer.setBounds(359, 18, 47, 22);

		btnSpeedDurchlauf = new Button(grpKonfiguration, SWT.CHECK);
		btnSpeedDurchlauf.setBounds(265, 56, 124, 16);
		btnSpeedDurchlauf.setText("Speed Durchlauf");

		Button btnSaveAs = new Button(composite, SWT.NONE);
		btnSaveAs.setEnabled(false);
		btnSaveAs.setLayoutData(new RowData(SWT.DEFAULT, 42));
		btnSaveAs.setText("Save Result");

		Button btnResetKonfig = new Button(composite, SWT.NONE);
		btnResetKonfig.setLayoutData(new RowData(73, 48));
		btnResetKonfig.setText("Reset Konfig");
		btnResetKonfig.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				anzahlKabinen.setSelection(10);
				anzahlPersUm8.setSelection(0);
				abfahrtsdauerMax.setSelection(0);
				abfahrtsdauerMin.setSelection(0);
				maxSkifahrer.setSelection(0);
				liftdauer.setSelection(0);
				btnSpeedDurchlauf.setSelection(false);
			}
		});

		btnStop = new Button(composite, SWT.NONE);
		btnStop.setEnabled(false);
		btnStop.setLayoutData(new RowData(73, 43));
		btnStop.setText("Stop");
		btnStop.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				start.stopAll();
				btnStop.setEnabled(false);
			}
		});

		Composite composite_1 = new Composite(shell, SWT.BORDER);
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));

		Composite composite_3 = new Composite(composite_1, SWT.V_SCROLL);
		composite_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));

		Composite composite_2 = new Composite(composite_1, SWT.NONE);
		composite_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));

		Group grpFilter = new Group(composite_2, SWT.NONE);
		grpFilter.setText("Filter");
		grpFilter.setBounds(10, 10, 272, 121);

		Button btnFilterTime = new Button(grpFilter, SWT.CHECK);
		btnFilterTime.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
			}
		});
		btnFilterTime.setBounds(71, 23, 93, 16);
		btnFilterTime.setText("filter time");

		Button btnFilterWs = new Button(grpFilter, SWT.CHECK);
		btnFilterWs.setBounds(71, 45, 93, 16);
		btnFilterWs.setText("filter ws");

		Button btnFilterXz = new Button(grpFilter, SWT.CHECK);
		btnFilterXz.setBounds(71, 67, 93, 16);
		btnFilterXz.setText("filter xz");

	}
}
