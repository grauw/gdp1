import java.awt.*;
import java.awt.image.*;

public class tekenCanvas extends Canvas
{
	static final long serialVersionUID = 1231203787146671L;
	int[] sorteren;
	int maxWaarde;
	static final int winHoogte = 300;
	static final int winBreedte = 480;
	BufferedImage bufferedImage;
	Graphics2D gBuf;
	
	public tekenCanvas(int[] a, int hoogste)
	{
		bufferedImage = new BufferedImage(winBreedte + 1, winHoogte + 1, BufferedImage.TYPE_INT_RGB);
		gBuf = bufferedImage.createGraphics();
		Font oldFont = gBuf.getFont();
		gBuf.setFont(new Font(oldFont.getName(), oldFont.getStyle() + Font.BOLD, oldFont.getSize()));
		
		sorteren = a;
		maxWaarde = hoogste;
		this.setSize(winBreedte + 1, winHoogte + 1);
	}
	
	public void update(Graphics g)
	{
		gBuf.clearRect(0, 0, winBreedte, winHoogte);
		
		double breedte = winBreedte / (double)sorteren.length;
		
		for(int a = 0; a < sorteren.length; a++)
		{
			int hoogte = (int) (winHoogte / (double)maxWaarde * sorteren[a] + 0.5);
			int x1 = (int) (a * breedte + 0.5);
			int x2 = (int) ((a + 1) * breedte + 0.5);
			
			gBuf.setColor(Color.yellow);
			gBuf.fillRect(x1, winHoogte - hoogte, x2 - x1, hoogte);
			gBuf.setColor(Color.red);
			gBuf.drawRect(x1, winHoogte - hoogte, x2 - x1, hoogte);
		}
		
		gBuf.setColor(Color.white);
		gBuf.drawString("Nr. of threads: " + Quicksort.tc.threadCount, 2, gBuf.getFontMetrics().getHeight());
		
		g.drawImage(bufferedImage, 0, 0, winBreedte + 1, winHoogte + 1, this);
	}
}
