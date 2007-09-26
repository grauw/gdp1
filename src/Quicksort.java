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
	protected int[] sorteren = {3,7,12,13,4,8,2,11,8,6,5,7};
	
	public static void main(String[] args)
	{
		Quicksort q = new Quicksort();
		q.start();
	}
	
	public void run() {
		System.out.println(naarString(sorteren));
		quicksort(sorteren,0,sorteren.length-1);
		System.out.println(naarString(sorteren));
	}
	
	
	public String naarString(int[]a)
	{
		String s = "";
		for(int b: a)
			s += b + " ";
		return s;
	}
	
	private void quicksort (int[] a, int l, int r)
	{
		//basis geval: lijst is leeg
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
				verwissel(a,i,j);
		} 
		
		//verwissel pivot element between partities
		verwissel(a,l,j);
		
		quicksort(a,l,j-1);
		quicksort(a,j+1,r);
	} 
		
	//verwissel two elements in the array
	private void verwissel (int[] a, int i, int j)
	{ 
		int h = a[i];
		a[i] = a[j];
		a[j] = h;
	}
}