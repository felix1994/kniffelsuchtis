package guessing.game;

import java.util.Random;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class Main {

	protected Shell shell;
	private Text text;
	private static int randomNumber;
	private Label lblAnzahlVersuche;
	private Label versuche;
	private  Label hint;
	private Label lblNewLabel;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			randomNumber = createRandomNumber();
			Main window = new Main();
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
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		shell.setLayout(new GridLayout(3, false));
		new Label(shell, SWT.NONE);
		
		lblAnzahlVersuche = new Label(shell, SWT.NONE);
		lblAnzahlVersuche.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		lblAnzahlVersuche.setText("Anzahl Versuche:");
		
		versuche = new Label(shell, SWT.NONE);
		versuche.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, true, 1, 1));
		versuche.setText("0");
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1));
		
		text = new Text(shell, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		text.addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				if(arg0.keyCode == SWT.CR)
					doEvent();
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		Button guess = new Button(shell, SWT.NONE);
		guess.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		guess.setText("Guess!");
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		hint = new Label(shell, SWT.NONE);
		GridData gd_hint = new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1);
		gd_hint.heightHint = 28;
		gd_hint.widthHint = 167;
		hint.setLayoutData(gd_hint);
		hint.setText("");
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		guess.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				doEvent();
				
			}
			
			
			

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});

	}

	protected void doEvent() {
		boolean failed = false;
		String theGuess = text.getText();
		int intGuess = 0;
		try {
			intGuess = Integer.parseInt(theGuess);
		}catch(NumberFormatException e){
			failed = true;
		}
		
		if(failed)
			return;
		
		if(randomNumber == intGuess){
			MessageBox box = new MessageBox(shell, SWT.NONE);
			box.setMessage("Gratuliere!! Die Zahl " + theGuess + " ist richtig  \n Du hast " + versuche.getText() + " Versuche gebraucht");
			box.open();
			resetAll();
			return;
		}
		if(randomNumber < intGuess)
			hint.setText("lower than " + theGuess);
		if(randomNumber > intGuess)
			hint.setText("higher than " + theGuess);
			
		String versuchanzahl = versuche.getText();
		int x = Integer.parseInt(versuchanzahl);
		int y = x+1;
		versuche.setText(String.valueOf(y));
		
		text.setText("");
		
	}

	protected void resetAll() {
		text.setText("");
		versuche.setText("0");
		hint.setText("");
		randomNumber = createRandomNumber();
		
	}

	private static int createRandomNumber() {
		Random zufallinho = new Random();
		int value = zufallinho.nextInt(100);
		return value;
	}
	
	

}
