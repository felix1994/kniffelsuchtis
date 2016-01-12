package kniffel.gui;

import java.text.DecimalFormat;
import java.util.List;

import kniffel.helpers.Player;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.experimental.chart.swt.ChartComposite;

public class Kassendiagramm extends Dialog {

	protected Object result;
	protected Shell shell;
	private List<Player> players;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public Kassendiagramm(Shell parent, int style, List<Player> players) {
		super(parent, style);
		setText("SWT Dialog");
		this.players = players;
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), SWT.DIALOG_TRIM);
		shell.setSize(500, 500);
		shell.setText(getText());
		shell.setLayout(new GridLayout(4, false));

		DefaultPieDataset dataset = new DefaultPieDataset();
		double gesamt = 0;
		for (Player player : players) {
			String name = player.getName();
			double zahlung = player.getGesamteZahlungen() / 100;
			dataset.setValue(name, zahlung);
			gesamt += zahlung;
		}

		JFreeChart chart = ChartFactory.createPieChart("Kasse - Kreisdiagramm",
				dataset);

		final PiePlot plot = (PiePlot) chart.getPlot();
		plot.setSimpleLabels(true);
		final PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator(
				"{0}: {1} ({2})", new DecimalFormat("0"), new DecimalFormat(
						"0%"));
		plot.setLabelGenerator(gen);

		ChartFrame frame = new ChartFrame("yo", chart);
		frame.pack();
		final ChartComposite chartcomposite = new ChartComposite(shell,
				SWT.NONE, chart, true);
		chartcomposite.setLayoutData(new GridData(GridData.FILL_BOTH));
		chartcomposite.setChart(chart);
		chartcomposite.redraw();

		Label label = new Label(shell, SWT.NONE);
		label.setText("Gesamt: \n"
				+ Double.toString(Math.round(gesamt * 100) / 100));

		new Label(shell, SWT.NONE);
		Button bt = new Button(shell, SWT.CHECK);
		bt.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if (gen.equals(plot.getLabelGenerator())) {
					plot.setSimpleLabels(true);
					plot.setLabelGenerator(null);
				} else {
					plot.setLabelGenerator(gen);
				}
				shell.layout();

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
	}
}
