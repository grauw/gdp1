/**
 * @(#)Quicksort.java
 *
 * Quicksort Applet application
 *
 * @author 
 * @version 1.00 2007/9/19
 */

public class Quicksort extends Thread
{
	static int[] sorteren = {
			100, 53, 71, 69, 70, 48, 93, 78, 45, 87, 84, 41, 31, 98, 49, 46, 46, 56, 35, 100,
			79, 91, 20, 63, 90, 70, 25, 45, 33, 20, 25, 85, 44, 45, 91, 70, 95, 59, 54, 48,
			22, 12, 56, 16, 72, 93, 11, 10, 13, 33, 87, 91, 63, 56, 42, 22, 8, 85, 41, 72,
			86, 33, 13, 13, 85, 30, 91, 60, 47, 45, 91, 99, 93, 18, 75, 49, 8, 94, 93, 38,
			64, 68, 74, 31, 50, 75, 27, 61, 17, 74, 52, 1, 3, 81, 26, 71, 82, 67, 98, 19,
			41, 49, 99, 11, 76, 73, 4, 43, 75, 73, 1, 7, 75, 63, 74, 98, 46, 23, 30, 32,
			66, 5, 7, 55, 68, 99, 2, 1, 71, 45, 24, 46, 80, 52, 24, 68, 15, 77, 89, 93
		};
	static int sleep_verwissel = 20;	//De variabele voor het aantal milliseconden dat een thread moet slapen bij een verwissel
	static int sleep_vergelijk = 20;	//De variabele voor het aantal milliseconden dat een thread moet slapen bij een vergelijking
	int links, rechts;					/*De pointers van de linkergrens van de array en de rechtergrens van de array.
	 									 *Het deel hiertussen moet gesorteerd worden door de aanroep van de quicksort(int l, int r) methode
										 */
	int threadnr;
	static ThreadCounter tc = new ThreadCounter();
	static boolean running = true;
	
	static int hoogste = sorteren[0];
	
	public Quicksort() {  //De constructor voor de initiële aanroep
		links = 0;
		rechts = sorteren.length-1;
		berekenHoogste();
	}
	
	public Quicksort(int links, int rechts) { 	//De constructor voor de recursieve aanroepen
		this.links = links;
		this.rechts = rechts;
	}
	
	private static void berekenHoogste() //Bepaalt van alle getallen in de array welke de grootste is.
	{
		for(int a: sorteren)
			if(a>hoogste)
				hoogste = a;
	}
	
	public void run()	//De run methode vraagt aan de ThreadCounter of hij kan beginnen,
						//voert dan een quicksort uit en geeft zijn plaatsje weer vrij
	{
		threadnr = tc.get();
		
		if (MainWindow.DEBUG)
			System.out.println("Quicksort " + threadnr + " started");
		quicksort(links, rechts);
		if (MainWindow.DEBUG)
			System.out.println("Quicksort " + threadnr + " stopped");
		
		tc.release();
	}
	
	public static void stopRunning() {
		/*Deze methode zorgt ervoor dat de boolean running op false wordt gezet,
		 *dat er voor zorgt dat threads stoppen met draaien. Ookal wordt het programma
		 *voortijdig afgesloten. Zo blijft er geen Java Virtual Machine meer draaien.
		 **/
		running = false;
	}
	
	public static String naarString(int[] a)	//Debug optie die de array uitprint om te kijken
									//of de array ook correct gesorteerd wordt door de quicksort
	{
		String s = "";
		for(int b: a)
			s += b + " ";
		return s;
	}
	
	private void newQuicksortThread(int l, int r)
	{
		//basis geval: lijst is leeg of bevat één element
		if (l >= r) return;	//Het basis geval wordt gecheckt om niet onnodig extra threads aan te maken.
		Quicksort q = new Quicksort(l, r);
		q.start();
	}
	
	private void quicksort(int l, int r)
	{
		//basis geval: lijst is leeg of bevat één element
		if (l >= r) return;
		
		/*recursie stap: laat twee pointers lopen, een van links naar rechts en de andere van rechts naar links
		*				 als de waarde in de array bij de linker pointer groter is moet deze omgewisseld
		*				 worden met een rechterwaarde die kleiner is, als de linker en rechterpointer samenkomen
		*				 moet het pivot element daar neergezet worden, zodat deze op de goeie plek staat
		*/
		//l = plek van het pivot element in de array
		
		//i = pointer links in de array 
		int i = l+1;
		//j = pointer rechts in de array
		int j = r;
		
		while (i<=j && running) 	//De boolean running is er in geplaatst zodat een thread ook
		{							//stopt als het venster wordt afgesloten
			if (vergelijk(i, l))	//Hier wordt bekeken of het element op i kleiner is dan de pivot
				i++;
			else
				if (vergelijk(j, l))	//Hier bekijken we of het element op j groter is dan de pivot
					verwissel(i, j);
				else
					j--;
		}
		verwissel(l, j);		//Hier wordt het pivot element op de juiste plek gezet.
		
		if (running) {
			newQuicksortThread(j+1, r);
			quicksort(l, j-1);
		}
	}
	
	private void verwissel(int i, int j)	//verwissel de twee elementen in the array op de plaatsen i en j
	{ 
		int h = sorteren[i];
		sorteren[i] = sorteren[j];
		sorteren[j] = h;
		try {
			Thread.sleep(sleep_verwissel);
		} catch(Exception e) {
		}
	}
	
	private boolean vergelijk(int i, int j)	//Kijk of het eerste element kleiner of gelijk aan het tweede element is
	{
		boolean rv = sorteren[i] <= sorteren[j];
		try {
			Thread.sleep(sleep_vergelijk);
		} catch(Exception e) {
		}
		return rv;
	}
}