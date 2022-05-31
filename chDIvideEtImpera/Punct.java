package chDIvideEtImpera;

import java.util.Comparator;

public class Punct implements Comparable<Punct>{
	private int x, y;
	private static Punct mid;
	
	Punct(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setMid(Punct mid) {
		this.mid = mid;
	}
	
	Punct(){
		this(0,0);
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
	
	public int getCadran() {
		if(x >= 0 && y >= 0)
			return 1;
		if(x <= 0 && y >= 0)
			return 2;
		if(x <= 0 && y <= 0)
			return 3;
		return 4;
	}
	
	public Punct getCentruDeGreutate(Punct B, Punct C) {
		Punct rez = new Punct();
		rez.setX((x + B.getX() + C.getX()) / 3);
		rez.setY((y + B.getY() + C.getY()) / 3);
		return rez;
	}
	
	public double getDeterminant(Punct B, Punct C) {
		return (x * (B.getY() - C.getY())) - (y *(B.getX() - C.getX())) + ((B.getX() * C.getY() - B.getY() * C.getX()));
	}


	@Override
	public int compareTo(Punct o) {
		Punct p = new Punct(x - mid.getX(), y - mid.getY()); // one
		Punct q = new Punct(o.getX() - mid.getX(), o.getY() - mid.getY()); // two
		
		int one = p.getCadran();
		int two = q.getCadran();
		
		if(one < two)
			return -1;
		
		if(p.getY() * q.getX() < q.getY() * p.getX())
			return -1;
		
		return 1;
	}
}
