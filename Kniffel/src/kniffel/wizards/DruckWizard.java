package kniffel.wizards;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.MediaSizeName;

import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import kniffel.helpers.Player;

public class DruckWizard extends Wizard{
	
	DruckPage druckpage;

	@Override
	public boolean performFinish() {
		PrintService printer = PrintServiceLookup.lookupDefaultPrintService();
		File file = new File("P:\\git\\kniffelsuchtis\\Kniffel\\model_kniffel.pdf");
		DocFlavor flavor = DocFlavor.INPUT_STREAM.POSTSCRIPT;
		PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
		aset.add(MediaSizeName.ISO_A4);
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DocPrintJob job = printer.createPrintJob();
		Doc doc = new SimpleDoc(fis, flavor, null);
		System.out.println(druckpage.getAnzahl());
		try {
			int x = 0;
			while(x<druckpage.getAnzahl()){
				job.print(doc, aset);
				x++;
			}
		} catch (PrintException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	public DruckWizard() {
		super();
		setWindowTitle("Drucken");
	}
	
	public void addPages(){
		druckpage = new DruckPage("DruckPage");
		addPage(druckpage);
	}
	
	

}
