package kniffel.gui;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;


public class Spielübersicht extends Dialog {

	protected Object result;
	protected Shell shell;
	
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public Spielübersicht(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
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
		shell = new Shell(getParent(), SWT.RESIZE | SWT.DIALOG_TRIM);
		shell.setLayout(new FillLayout());
		shell.setSize(450, 300);
		ScrolledComposite c2 = new ScrolledComposite(shell, SWT.BORDER |SWT.V_SCROLL);
	  
	   
		Label lbl = new Label(c2, SWT.NONE);
		FileReader fr;
		try {
			fr = new FileReader("spielübersicht.txt");
			BufferedReader br = new BufferedReader(fr);
			while(true){
				String zeile = br.readLine();
				if(zeile == null)
					break;
				if("end".equals(zeile))
					break;
				if(zeile.startsWith("Spiel"))
					lbl.setText(lbl.getText() + "\n");
				
				lbl.setText(lbl.getText() + zeile + "\n");
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 c2.setContent(lbl);
		 c2.setExpandHorizontal(true);
		 c2.setExpandVertical(true);
		 c2.setMinWidth(400);
		 c2.setMinHeight(400);
			
	}	

}
