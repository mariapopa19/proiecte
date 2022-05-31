package chOnLine;

import java.util.*;
public class CH {
	private Punct adaug;
	private ArrayList<Punct> puncte;
	private ArrayList<Punct> hull;
	
	public CH(ArrayList<Punct> puncte, Punct adaug) {
		this.puncte = puncte;
		this.adaug = adaug;
		hull = new ArrayList<Punct>();
	}
	
	public ArrayList<Punct> adauga() {
		addPoint(puncte, adaug);
		return puncte;
	}
	
	public boolean inside(ArrayList<Punct> a, Punct p) {
		Punct mid = new Punct(0,0);
		int n = a.size();
		
		for(int i = 0 ; i < n ; i++) {
			mid.setX(mid.getX() + a.get(i).getX());
			mid.setY(mid.getY() + a.get(i).getY());
		}
		
		for(int i = 0, j ; i < n ; i++)
		{
			j = (i + 1) % n;
			int x1 = a.get(i).getX() * n, x2 = a.get(j).getX() * n;
			int y1 = a.get(i).getY() * n, y2 = a.get(j).getY() * n;
			int a1 = y1 - y2;
			int b1 = x2 - x1;
			int c1 = x1 * y2 - y1 * x2;
			int for_mid = a1 * mid.getX() + b1 * mid.getY() + c1;
			int for_p = a1 * p.getX() * n + b1 * p.getY() * n + c1;
			if(for_mid * for_p < 0)
				return false;
		}
		return true;
	}
	
	public void addPoint(ArrayList<Punct> a, Punct p) {
		if(inside(a, p))
			return;
		
		int ind = 0;
		int n = a.size();
		for(int i = 1; i < n ; i++) 
		{
			if(p.sqDist(a.get(i)) < p.sqDist(a.get(ind)))
			{
				ind = i;
			}
		}
		
		int up = ind;
		while(p.orientation(a.get(up), a.get((up + 1) % n)) >= 0)
			up = (up + 1) % n;
		
		int low = ind;
		while(p.orientation(a.get(low), a.get((n + low - 1) % n)) <= 0)
			low = (n + low - 1) % n;
		
		ArrayList<Punct> ret = new ArrayList<Punct>();
		
		int curr = up;
		ret.add(a.get(curr));
		
		while(curr != low)
		{
			curr = (curr + 1) % n;
			ret.add(a.get(curr));
		}
		
		ret.add(p);
		puncte.clear();
		puncte.addAll(ret);		
	}
	
}

