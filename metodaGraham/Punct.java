package metodaGraham;


public class Punct {
	
	private double x,y;
	
	public Punct(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Punct() {
		this(0,0);
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public String toString() {
		String str1 = String.format("%.02f", x);
		String str2 = String.format("%.02f", y);
		return "(" + str1 + ", " + str2 + ")";
	}

	public int compareTo(Punct b) {
		if(x == b.getX() && y == b.getY()) {
			return 0;
		}
		return 1;
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
}
