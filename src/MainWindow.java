import javax.swing.JFrame;
import java.awt.event.*;

public class MainWindow extends JFrame implements WindowListener {

	static final long serialVersionUID = 1231203787146672L;
	Animatie animatie;

	public MainWindow() {
		setName("Visualisatie");
		this.setSize(640,480);
		this.setVisible(true);
		
		Quicksort q = new Quicksort();
		animatie = new Animatie(20, this, Quicksort.sorteren, Quicksort.hoogste);
		this.addWindowListener(this);
		
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
	
	public void windowActivated(WindowEvent e) {
	}
	
	public void windowClosed(WindowEvent e) {
	}
	
	public void windowClosing(WindowEvent e) {
		System.out.println("closing");
		animatie.stopRunning();
		this.dispose();
	}
	
	public void windowDeactivated(WindowEvent e) {
	}
	
	public void windowDeiconified(WindowEvent e) {
	}
	
	public void windowIconified(WindowEvent e) {
	}
	
	public void windowOpened(WindowEvent e) {
	}

}
