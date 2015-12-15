package kniffel.wizards;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;

public class DruckPage extends WizardPage{
	
	private int anzahl = 0;

	protected DruckPage(String pageName) {
		super(pageName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout gridLayout = new GridLayout(2, false);
		composite.setLayout(gridLayout);
		Label text = new Label(composite, SWT.NONE);
		text.setText("Wie viele Seiten wollen Sie drucken?");
		final Spinner spinner = new Spinner(composite,SWT.NONE);
		spinner.setMaximum(30);
		spinner.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent arg0) {
				anzahl = Integer.parseInt(spinner.getText());
				
			}
		});
		setControl(composite);
	}
	
	public int getAnzahl(){
		return anzahl;
	}

}
