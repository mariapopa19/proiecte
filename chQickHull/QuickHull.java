package chQickHull;

import java.util.*;

public class QuickHull {
	private ArrayList<Punct> a;
	private HashSet<Punct> hull;
	
	public QuickHull(ArrayList<Punct> a) {
		this.a = a;
		hull = new HashSet<Punct>();
	}
	
	// returneaza partea pe care se afla Punctul p fata de 
	// linia formata de p1, p2.
	
	// Translatie si produs vectorial
	public int findSide(Punct p1, Punct p2, Punct p) {
		int val = (p.getY() - p1.getY()) * (p2.getX() - p1.getX()) -
				  (p2.getY() - p1.getY()) * (p.getX() - p1.getX());
		
		
		if(val > 0)
			return 1; // left
		if(val < 0)  
			return -1; // right
		
		return 0; // on the line
	}
	
	// retunreaza distanta de la Punctul P la linia formata de punctele P1, P2
	public int lineDist(Punct p1, Punct p2, Punct p) {
		return  Math.abs((p.getY() - p1.getY()) * (p2.getX() - p1.getX()) -
				  (p2.getY() - p1.getY()) * (p.getX() - p1.getX()));
	}
	
	public void quickHull(Punct p1, Punct p2, int side) {
		int n = a.size();
		
		int ind = -1;
		int max_dist = 0;
		
		// gasim punctul care are distanta maxima fata de L
		// si care este pe partea lui L
		for(int i = 0 ; i < n ; i++) 
		{
			int temp = lineDist(p1, p2, a.get(i));
			if(findSide(p1, p2, a.get(i)) == side && temp > max_dist)
			{
				ind = i;
				max_dist = temp;
			}
			
		}
		
		// Daca punctele nu au fost gasite => Le adaugam 
		if(ind == -1)
		{
			hull.add(p1);
			hull.add(p2);
			return;
		}
		
		// punctele din afara triunghiului
		
		
		quickHull(a.get(ind), p1, -findSide(a.get(ind), p1, p2)); 
		// punctele P, P1 
		// Avem "-" deoarece vrem sa lucram in exteriorul triunghiului
		
		quickHull(a.get(ind), p2, -findSide(a.get(ind), p2, p1));
		// punctele P, P2
		// Avem "-" deoarece vrem sa lucram in exteriorul triunghiului
		
	}
	
	public void printHull() {
		// Minim 3 puncte
		int n = a.size();
		
		// cauta punctele cu x min si x max
		int min_x = 0, max_x = 0;
		for(int i = 1 ; i < n ; i++) 
		{
			if(a.get(i).getX() < a.get(min_x).getX())
				min_x = i;
			if(a.get(i).getX() > a.get(max_x).getX())
				max_x = i;
		}
		
		// Recursiv cauta infasurarea convexa folosind a[min_x] si a[max_x]
		
		quickHull(a.get(min_x), a.get(max_x), 1); // pe partea stanga
		quickHull(a.get(min_x), a.get(max_x), -1); // pe partea dreapta
		
		System.out.println("Punctele infasuratorii convexe(Hull) sunt: " + hull);
	}

}
