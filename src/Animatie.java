import java.awt.*;

/**
 * Animatie-klasse.
 * 
 * Een thread die een TekenCanvas aan het frame toevoegt en deze met enige regelmaat ververst.
 * 
 * @author Milan de Graaf (3117308),
 *         Laurens Holst (0122173)
 */
public class Animatie implements Runnable
{	
	boolean running = true;		// Boolean die aangeeft of de animatie nog loopt.
	int sleep_teken;			// Duur van de sleep tussen animaties in ms.
	TekenCanvas tekenPlek;
	
	/**
	 * Constructor voor animatie-thread.
	 * @param a Duur van de sleep tussen animaties in ms.
	 * @param frame Het Frame-object.
	 * @param sort De sorteer-array.
	 * @param hoogste De hoogste waarde in de sorteer-array.
	 */
	public Animatie(int a, Frame frame, int[] sort, int hoogste)
	{
		sleep_teken = a;
		tekenPlek = new TekenCanvas(sort, hoogste);
		
		frame.add(tekenPlek);
	}
	
	/**
	 * De animatie-thread die elke sleep_teken ms het TekenCanvas repaint.
	 */
	public void run()
	{
		if (MainWindow.DEBUG)
			System.out.println("Animatie started");
		while(running)
		{
			try
			{
				Thread.sleep(sleep_teken);				
			}
			catch(Exception e)
			{}
			tekenPlek.repaint();
		}
		if (MainWindow.DEBUG)
			System.out.println("Animatie stopped");
	}
	
	/**
	 * Signaleer de animatie-thread dat hij moet stoppen met draaien.
	 */
	public void stopRunning() {
		running = false;
	}
}
