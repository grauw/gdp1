import java.awt.*;

/**
 * Animatie-klasse.
 * 
 * @author Milan de Graaf (3117308),
 *         Laurens Holst (0122173)
 */
public class Animatie implements Runnable
{	
	boolean running = true;
	int sleep_teken = 30;
	TekenCanvas tekenPlek;
	
	public Animatie(int a, Frame frame, int[] sort, int hoogste)
	{
		sleep_teken = a;
		tekenPlek = new TekenCanvas(sort, hoogste);
		
		frame.add(tekenPlek);
	}
	
	
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
	
	public void stopRunning() {
		running = false;
	}
}
