package localizareIncludereExcludere;


public class Punct {
	private double x;
	private double y;
	private static int numePunct = 1;
	private int numar;

	public Punct(double x, double y) {
		numar = numePunct;
		numePunct++;
		this.x = x;
		this.y = y;
	}
	

	public int getNumePunct() {
		return numar;
	}

	public double getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String toString() {
		return x + " " + y;
	}

}
