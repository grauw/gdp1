/**
 * @(#)Quicksort.java
 *
 * Quicksort Applet application
 *
 * @author 
 * @version 1.00 2007/9/19
 */
 
import java.awt.*;
import java.applet.*;

public class Quicksort
{
	
	public static void main(String[] args)
	{
		int[] sorteren = {3,7,12,23,4,43,2,7,8,6,45,7};
		quicksort(sorteren,0,sorteren.length-1);
	}
	
	public static String naarString(int[]a)
	{
		String s = "";
		for(int b: a)
			s += b + " ";
			
		return s;
	}
	
	private static void quicksort (int[] a, int l, int r)
	{
		//base case: list is empty
		if (l>=r) return;
		//recursion step: make partitie and sort recursively  
		int m = partitie(a, l, r);
		quicksort(a,l,m-1);
		quicksort(a,m+1,r);
	}
	
	//partitie the array from l+1 to r with pivot a[l]
	private static int partitie (int[] a, int l, int r)
	{ 
		int i=l+1;
		//pointer on left side  
		int j=r; 
		//pointer on right side  
		int p = a[l]; 
		//pivot element
		  
		//move pointer to center or verwissel if on wrong sides
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
		//return position of pivot element
		return j;
	} 
		
	//verwissel two elements in the array
	private static void verwissel (int[] a, int i, int j)
	{ 
		int h = a[i];
		a[i] = a[j];
		a[j] = h;
	}
}