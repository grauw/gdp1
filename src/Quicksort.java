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
	int sleep_verwissel = 1;
	int sleep_vergelijk = 1;
	int links, rechts;

	int threadnr;
	static ThreadCounter tc = new ThreadCounter();
	
	static int hoogste = sorteren[0];
	
	public Quicksort() {
		links = 0;
		rechts = sorteren.length-1;
		berekenHoogste();
	}
	
	public Quicksort(int links, int rechts) {
		this.links = links;
		this.rechts = rechts;
	}
	
	private static void berekenHoogste()
	{
		for(int a: sorteren)
			if(a>hoogste)
				hoogste = a;
	}
	
	public void run() {
		threadnr = tc.get();
		
		if (MainWindow.DEBUG)
			System.out.println("Quicksort " + threadnr + " started");
		quicksort(links, rechts);
		if (MainWindow.DEBUG)
			System.out.println("Quicksort " + threadnr + " stopped");
		
		tc.release();
	}
	
	public static String naarString(int[] a)
	{
		String s = "";
		for(int b: a)
			s += b + " ";
		return s;
	}
	
	private void newQuicksortThread(int l, int r)
	{
		//basis geval: lijst is leeg of bevat één element
		if (l >= r) return;
		Quicksort q = new Quicksort(l, r);
		q.start();
	}
	
	private void quicksort(int l, int r)
	{
		//basis geval: lijst is leeg of bevat één element
		if (l >= r) return;
		
		//recursie stap: neem pivot element en houdt alles dat kleiner is links en
		//				 groter rechts en stop pivot element op de juiste plek
		
		//l = pivot element
		//i = pointer links in de array 
		int i = l+1;
		//j = pointer rechts in de array
		int j = r;
		
		while (i<=j)
		{
			if (vergelijk(i, l))
				i++;
			else
				if (vergelijk(j, l))
					verwissel(i, j);
				else
					j--;
		}

		//verwissel pivot element between partities
		verwissel(l, j);
		
		quicksort(l, j-1);
		newQuicksortThread(j+1, r);
	}
	
	//verwissel two elements in the array
	private void verwissel(int i, int j)
	{ 
		int h = sorteren[i];
		sorteren[i] = sorteren[j];
		sorteren[j] = h;
		try {
			Thread.sleep(sleep_verwissel);
		} catch(Exception e) {
		}
	}
	
	//verwissel two elements in the array
	private boolean vergelijk(int i, int j)
	{
		boolean rv = sorteren[i] <= sorteren[j];
		try {
			Thread.sleep(sleep_vergelijk);
		} catch(Exception e) {
		}
		return rv;
	}
}