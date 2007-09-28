import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class MainWindow extends Frame implements WindowListener, ActionListener, FocusListener {

	static final long serialVersionUID = 1231203787146672L;
	final static boolean DEBUG = false;
	
	Animatie animatie;
	TextField tfMaxThread, tfSleepVerwissel, tfSleepVergelijk;

	public MainWindow() {
		setName("Visualisatie");
		this.setSize(640,480);
		this.setLayout(new FlowLayout());
		this.setVisible(true);
		
		Quicksort q = new Quicksort();
		animatie = new Animatie(20, this, Quicksort.sorteren, Quicksort.hoogste);
		this.addWindowListener(this);
		
		tfMaxThread = new TextField(Integer.toString(Quicksort.tc.threadMax));
		tfSleepVerwissel = new TextField(Integer.toString(Quicksort.sleep_verwissel));
		tfSleepVergelijk = new TextField(Integer.toString(Quicksort.sleep_vergelijk));
		tfMaxThread.addActionListener(this);
		tfSleepVerwissel.addActionListener(this);
		tfSleepVergelijk.addActionListener(this);
		tfMaxThread.addFocusListener(this);
		tfSleepVerwissel.addFocusListener(this);
		tfSleepVergelijk.addFocusListener(this);
		
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
	
	public void actionPerformed(ActionEvent e) {
		changedField((TextField)e.getSource());
	}
	
	public void focusGained(FocusEvent e) {}
	public void focusLost(FocusEvent e) {
		changedField((TextField)e.getSource());
	}
	
	public void changedField(TextField tf) {
		if (tf == tfMaxThread) {
			try {
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
