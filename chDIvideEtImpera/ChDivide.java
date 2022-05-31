package chDIvideEtImpera;

import java.util.*;

public class ChDivide {
	private ArrayList<Punct> puncte;
	public Punct mid;
	
	public ChDivide(ArrayList<Punct> puncte) {
		this.puncte = puncte;
	}
	
	// verifica daca linie trece prin poligon
	public int orietantion(Punct a, Punct b, Punct c) {
		
		int res = (b.getY() - a.getY()) * (c.getX() - b.getX()) -
				  (c.getY() - b.getY()) * (b.getX() - a.getX());
		
		if(res == 0)
			return 0;
		if(res > 0)
			return 1;
		return -1;
	}
	
	public ArrayList<Punct> merger(ArrayList<Punct> a, ArrayList<Punct> b){
		int n1 = a.size();
		int n2 = b.size();
		
		int ia = 0, ib = 0;
		
		// ia Punct cu x cel mai mare
	    for (int i=1; i<n1; i++) {
	    	if(a.get(i).getX() > a.get(ia).getX())
	    		ia = i;
	    }
	    
	    // ia Punctul cu x cel mai mic
	    for (int i=1; i<n2; i++) {
	    	if(b.get(i).getX() < b.get(ib).getX())
	    		ib = i;
	    }
	    
	    // cautam tangeta superioara
	    int inda = ia, indb = ib;
	    boolean done = false;
	    
	    while(!done) {
	    	done = true;
	    	while(orietantion(b.get(indb), a.get(inda), a.get((inda + 1) % n1)) >= 0)
	    			inda = (inda + 1) % n1;
	    	
	    	while(orietantion(a.get(inda), b.get(indb), b.get((n2 + indb - 1) % n2)) <= 0)
	    	{
	    		indb = (n2 + indb - 1) % n2;
	    		done = false;
	    	}
	    }
	    
	    // cautam tangeta inferioara
	    int uppera = inda, upperb = indb;
	    inda = ia;
	    indb = ib;
	    done = false;
	    int g = 0;
	    
	    while(!done) {
	    	done = true;
	    	while(orietantion(a.get(inda), b.get(indb), b.get((indb + 1) % n2)) >= 0)
	    			indb = (indb + 1) % n2;
	    	
	    	while(orietantion(b.get(indb), a.get(inda), a.get((n1 + inda - 1) % n1)) <= 0)
	    	{
	    		inda = (n1 + inda - 1) % n1;
	    		done = false;
	    	}
	    }
	    
	    
	    
	    // uppera -> lowera
	    
	    // Uppera reprezinta punctul tangetei superioare din CH(S1) de sus
	    // Lower A reprezinta punctul tangetei superioare din CH(S1) de jos
	    // iei punctele din stanga tangetei superioara, parcurgand de la uppera la lowera
	    int lowera = inda, lowerb = indb;
	    ArrayList<Punct> ret = new ArrayList<Punct>();
	    
	    int ind = uppera;
	    ret.add(a.get(uppera));
	    while(ind != lowera)
	    {
	    	ind = (ind + 1) % n1;
	    	ret.add(a.get(ind));
	    }
	    
	    // lowerb -> upperb
	    
	    // iei punctele din dreapta tangentei inferioare
	    ind = lowerb;
	    ret.add(b.get(ind));
	    while(ind != upperb) {
	    	ind = (ind + 1) % n2;
	    	ret.add(b.get(ind));
	    }
	    return ret;
	}
	
	public ArrayList<Punct> bruteHull(ArrayList<Punct> a) {
		ArrayList<Punct> s = new ArrayList<Punct>();
		
		for (int i=0; i<a.size(); i++)
		{
			for (int j=i+1; j<a.size(); j++)
			{
				int x1 = a.get(i).getX(), x2 = a.get(j).getX();
				int y1 = a.get(i).getY(), y2 = a.get(j).getY();

				int a1 = y1-y2;
				int b1 = x2-x1;
				int c1 = x1*y2-y1*x2;
				int pos = 0, neg = 0;
				for (int k=0; k<a.size(); k++)
				{
					if (a1*a.get(k).getX()+b1*a.get(k).getY()+c1 <= 0)
						neg++;
					if (a1*a.get(k).getX()+b1*a.get(k).getY()+c1 >= 0)
						pos++;
				}
				if (pos == a.size() || neg == a.size())
				{
					s.add(a.get(i));
					s.add(a.get(j));
				}
			}
		}
		
		ArrayList<Punct> ret = (ArrayList<Punct>) s.clone();
		
		mid = new Punct(0,0);
		
		int n = ret.size();
		for(int i = 0 ; i < n ; i++)
		{
			mid.setX(mid.getX() + ret.get(i).getX());
			mid.setY(mid.getY() + ret.get(i).getY());
			ret.set(i, new Punct(ret.get(i).getX() * n, ret.get(i).getY() * n));
		}
		
		Punct temp = new Punct();
		temp.setMid(mid);
		Collections.sort(ret);
		
		for(int i = 0 ; i < n ; i++) {
			ret.set(i, new Punct(ret.get(i).getX() / n, ret.get(i).getY() / n));
		}
		return ret;	
	}
	
	public ArrayList<Punct> divide(ArrayList<Punct> a) {
		if(a.size() <= 5)
			return bruteHull(a);
		
		ArrayList<Punct> left = new ArrayList<Punct>();
		ArrayList<Punct> right = new ArrayList<Punct>();
		
		
		for(int i = 0; i < a.size() / 2; i++)
			left.add(a.get(i));
		
		for(int i = a.size() / 2; i < a.size() ; i++)
			right.add(a.get(i));
		
		ArrayList<Punct> left_hull = divide(left);
		ArrayList<Punct> right_hull = divide(right);
		
		return merger(left_hull, right_hull);		
	}
	
	public void raspuns() {
		System.out.println("Punctele infasuratorii convexe(divide) sunt: " + divide(puncte));
	}
}
