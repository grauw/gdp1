/**
 * @(#)Quicksort.java
 *
 * Quicksort Applet application
 *
 * @author 
 * @version 1.00 2007/9/19
 */

import java.awt.*;
import java.lang.*;

public class Quicksort extends Thread
{
	protected static int[] sorteren = {3,7,12,13,4,8,2,11,8,6,5,7};
	protected int links, rechts;
	
	public Quicksort() {
		links = 0;
		rechts = sorteren.length-1;
	}
	
	public Quicksort(int links, int rechts) {
		this.links = links;
		this.rechts = rechts;
	}
	
	public static void main(String[] args)
	{
		Quicksort q = new Quicksort();
		System.out.println(q.naarString(sorteren));
		q.start();
		try {
			q.sleep(1000);
		} catch(Exception e) {
			System.out.println(e.toString());
		}
		System.out.println(q.naarString(sorteren));
	}
	
	public void run() {
		quicksort(sorteren, links, rechts);
	}
	
	
	public String naarString(int[]a)
	{
		String s = "";
		for(int b: a)
			s += b + " ";
		return s;
	}
	
	private void newQuicksortThread(int l, int r)
	{
		Quicksort q = new Quicksort(l, r);
		q.start();
	}
	
	private void quicksort (int[] a, int l, int r)
	{
/*		try {
		this.sleep(10);
		}catch(Exception e) {
		}
*/		//basis geval: lijst is leeg
		if (l>=r) return;

		
		//recursie stap: neem pivot element en houdt alles dat kleiner is links en
		//				 groter rechts en stop pivot element op de juiste plek
		
		//pivot element
		int p = a[l]; 
		//pointer links in de array 
		int i=l+1;
		//pointer rechts in de array
		int j=r; 
		
		while (i<=j)
		{
			if (a[i]<=p) i++;
			else if (a[j]>p)
				j--;
				else 
				verwissel(a, i, j);
		}
		
		//verwissel pivot element between partities
		verwissel(a, l, j);
		
		quicksort(a, l, j-1);
		newQuicksortThread(j+1, r);
	}
		
	//verwissel two elements in the array
	private void verwissel (int[] a, int i, int j)
	{ 
		int h = a[i];
		a[i] = a[j];
		a[j] = h;
	}
}