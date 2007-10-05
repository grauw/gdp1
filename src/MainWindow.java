import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class MainWindow extends Frame implements WindowListener, ActionListener, FocusListener {

	static final long serialVersionUID = 1231203787146672L;
	final static boolean DEBUG = true;
	
	Animatie animatie;
	TextField tfMaxThread, tfSleepVerwissel, tfSleepVergelijk;

	public MainWindow() {
		setName("Visualisatie");
		this.setSize(640,480);
		this.setLayout(new FlowLayout());
		this.setVisible(true);
		
		//Het aanmaken van een quicksort klasse en animatie klasse en een WindowListener toevoegen
		Quicksort q = new Quicksort();
		animatie = new Animatie(20, this, Quicksort.sorteren, Quicksort.hoogste);
		this.addWindowListener(this);
		
		//Het aanmaken van de TextFields en de ActionListener en FocusListener er aan toevoegen
		tfMaxThread = new TextField(Integer.toString(Quicksort.tc.threadMax));
		tfSleepVerwissel = new TextField(Integer.toString(Quicksort.sleep_verwissel));
		tfSleepVergelijk = new TextField(Integer.toString(Quicksort.sleep_vergelijk));
		tfMaxThread.addActionListener(this);
		tfSleepVerwissel.addActionListener(this);
		tfSleepVergelijk.addActionListener(this);
		tfMaxThread.addFocusListener(this);
		tfSleepVerwissel.addFocusListener(this);
		tfSleepVergelijk.addFocusListener(this);
		
		/*De container c wordt aangemaakt om er voor te zorgen dat de labels en de TextFields
		 *bij elkaar blijven staan en niet door de flow layout verspreid worden
		 *onder de tekening en naast de tekening
		 * */
		Container c = new Container();
		c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
		c.add(new Label("Max threads: "));
		c.add(tfMaxThread);
		c.add(new Label("Sleep verwissel: "));
		c.add(tfSleepVerwissel);
		c.add(new Label("Sleep vergelijk: "));
		c.add(tfSleepVergelijk);
		this.add(c);
		this.validate();
		
		//Hieronder het daadwerkelijke starten van de animatie en het quicksort process.
		Thread animatieThread = new Thread(animatie);
		animatieThread.start();
		q.start();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		new MainWindow();
	}
	
	
	//Hieronder de Listener methodes
	
	//WindowListener methodes
	public void windowClosing(WindowEvent e) {
		if (MainWindow.DEBUG)
			System.out.println("closing");
		animatie.stopRunning();
		Quicksort.stopRunning();
		this.dispose();
	}
	
	public void windowActivated(WindowEvent e) {}
	public void windowClosed(WindowEvent e) {}
	public void windowDeactivated(WindowEvent e) {}
	public void windowDeiconified(WindowEvent e) {}
	public void windowIconified(WindowEvent e) {}
	public void windowOpened(WindowEvent e) {}
	
	//ActionListener methode
	public void actionPerformed(ActionEvent e) {
		changedField((TextField)e.getSource());
	}
	
	//FocusListener methodes
	/*De FocusListener hebben we erin gedaan als alternatief,
	 *zodat er niet steeds op enter gedrukt hoeft te worden iedere keer
	 *dat er een nieuwe waarde in het veld wordt ingevoerd.
	 *Van veld wisselen is nu voldoende.
	 * */
	public void focusGained(FocusEvent e) {}
	public void focusLost(FocusEvent e) {
		changedField((TextField)e.getSource());
	}
	
	/*Gebruikte methode in focusLost en actionPerformed methodes
	 *Deze methode bekijkt de ingevulde waarde en als het mogelijk is verandert deze de waarde,
	 *als een ongeldige waarde is ingevuld wordt het TextField op zijn huidige waarde teruggezet.
	 */ 
	public void changedField(TextField tf) {
		if (tf == tfMaxThread) {
			try {	/*We kijken bij het maximum aantal threads naar het verschil en
			 		 *doen dan netzoveel notify's als is toegestaan, hierdoor hoeven
			 		 *we geen notifyAll te gebruiken.
			 		 */
					
				int newThreadMax = Integer.parseInt(tf.getText());
				int diff = newThreadMax - Quicksort.tc.threadMax;
				Quicksort.tc.threadMax = newThreadMax;
				if (diff > 0)
					Quicksort.tc.notifyCount(diff);
			} catch(Exception ex) {
				tf.setText(Integer.toString(Quicksort.tc.threadMax));
			}
		} else if (tf == tfSleepVergelijk) {
			try {
				Quicksort.sleep_vergelijk = Integer.parseInt(tf.getText());
			} catch(Exception ex) {
				tf.setText(Integer.toString(Quicksort.sleep_vergelijk));
			}
		} else if (tf == tfSleepVerwissel) {
			try {
				Quicksort.sleep_verwissel = Integer.parseInt(tf.getText());
			} catch(Exception ex) {
				tf.setText(Integer.toString(Quicksort.sleep_verwissel));
			}
		}
	}

}
