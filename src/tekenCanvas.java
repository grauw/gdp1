import java.awt.*;
public class tekenCanvas extends Canvas
{
	static final long serialVersionUID = 1231203787146671L;
	int[] sorteren;
	int maxWaarde;
	
	public tekenCanvas(int[] a, int hoogste)
	{
		sorteren = a;
		maxWaarde = hoogste;
		this.setSize(480, 480);
	}
	
	public void paint(Graphics g)
	{
		g.drawString(Quicksort.naarString(sorteren), 20, 20);
		
		for(int a = 0; a < sorteren.length; a++)
		{
			
		}
			
	}
}
