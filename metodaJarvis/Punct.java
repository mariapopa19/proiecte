package metodaJarvis;

public class Punct {
private double x,y;
	
	public Punct(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public String toString() {
		return x + " " + y;
	}

	public int compareTo(Punct b) {
		if(x == b.getX() && y == b.getY()) {
			return 0;
		}
		return 1;
	}

}
