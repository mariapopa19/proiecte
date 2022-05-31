package chOnLine;


public class Punct {
	private int x, y;
	
	Punct(int x, int y) {
		this.x = x;
		this.y = y;
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
		if(x > 0 && y >= 0)
			return 1;
		if(x <= 0 && y > 0)
			return 2;
		if(x < 0 && y <= 0)
			return 3;
		if(x >= 0 && y < 0)
			return 4;
		
		return 0;
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
	
//	public int compareTo(Punct b) {
//		if(x == b.getX() && y == b.getY()) {
//			return 0;
//		}
//		return 1;
//	}
	
	public int orientation(Punct b, Punct c) {
		double res = (b.getY() - y) * (c.getX() - b.getX()) -
					 (c.getY() - b.getY()) * (b.getX() - x);
		
		if(res == 0)
			return 0;
		if(res > 0)
			return 1;
		return -1;
	}
	
	// distanta dintre 2 punct la patrat
	public int sqDist(Punct b) {
		return (x - b.getX()) * (x - b.getX()) + 
				(y - b.getY()) * (y - b.getY());
	}
	
}
