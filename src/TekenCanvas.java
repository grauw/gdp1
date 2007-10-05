import java.awt.*;
import java.awt.image.*;

/**
 * TekenCanvas-klasse.
 * 
 * Een canvas die de sorteer-array tekent.
 * 
 * @author Milan de Graaf (3117308),
 *         Laurens Holst (0122173)
 */
public class TekenCanvas extends Canvas
{
	static final long serialVersionUID = 1231203787146671L;
	int[] sorteren;
	int maxWaarde;
	static final int winHoogte = 300;
	static final int winBreedte = 480;
	BufferedImage bufferedImage;
	Graphics2D gBuf;
	
	/**
	 * Initialiseert het canvas.
	 * @param a Verwijzing naar de op te tekenen array.
	 * @param hoogste Hoogste waarde in de array.
	 */
	public TekenCanvas(int[] a, int hoogste)
	{
		//We gebruiken een bufferedImage om het flikkeren van het beeld tegen te gaan
		bufferedImage = new BufferedImage(winBreedte + 1, winHoogte + 1, BufferedImage.TYPE_INT_RGB);
		gBuf = bufferedImage.createGraphics();
		
		//We hebben het Font BOLD gemaakt, zodat het wat beter leesbaar was.
		Font oldFont = gBuf.getFont();
		gBuf.setFont(new Font(oldFont.getName(), oldFont.getStyle() + Font.BOLD, oldFont.getSize()));
		
		sorteren = a;
		maxWaarde = hoogste;
		this.setSize(winBreedte + 1, winHoogte + 1);
	}
	
	/**
	 * We gebruiken de methode update in plaats van paint om het flikkeren van het scherm volledig tegen te gaan.
	 */
	public void update(Graphics g)
	{
		//Dit is het tekengebied
		gBuf.clearRect(0, 0, winBreedte, winHoogte);
		
		double breedte = winBreedte / (double)sorteren.length; //de breedte van een staaf
		
		for(int a = 0; a < sorteren.length; a++)
		{
			int hoogte = (int) (winHoogte / (double)maxWaarde * sorteren[a] + 0.5);	//de +0.5 is er vanwege de afronding
			int x1 = (int) (a * breedte + 0.5);  		
			int x2 = (int) ((a + 1) * breedte + 0.5);	
			
			gBuf.setColor(Color.yellow);
			//de double breedte casten naar int leverde afrondingsfouten op,
			//dus hebben we het gedaan door x2 - x1  en ieder apart te casten met afrondingscorrectie
			gBuf.fillRect(x1, winHoogte - hoogte, x2 - x1, hoogte);
			gBuf.setColor(Color.red);
			gBuf.drawRect(x1, winHoogte - hoogte, x2 - x1, hoogte);
		}
		
		gBuf.setColor(Color.white);
		gBuf.drawString("Nr. of threads: " + Quicksort.tc.threadCount, 2, gBuf.getFontMetrics().getHeight());
		
		g.drawImage(bufferedImage, 0, 0, winBreedte + 1, winHoogte + 1, this);
	}
}
