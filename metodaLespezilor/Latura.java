package metodaLespezilor;

public class Latura {
	private int numeLatura, f1, f2, p1, p2;
	private Varf v1,v2;
	
	public Latura(int numeLatura, int xV1, int yV1, int xV2, int yV2, int f1, int f2, int p1, int p2) {
		this.numeLatura = numeLatura;
		this.f1 = f1;
		this.f2 = f2;
		this.v1 = new Varf(xV1, yV1);
		this.v2 = new Varf(xV2, yV2);
		this.p1 = p1;
		this.p2 = p2;
	}
	
	public int getnumeLatura() {
		return numeLatura;
	}
	
	public int getF1() {
		return f1;
	}
	
	public int getF2() {
		return f2;
	}
	
	public Varf getV1() {
		return v1;
	}
	
	public Varf getV2() {
		return v2;
	}
	
	public int getP1() {
		return p1;
	}
	
	public int getP2() {
		return p2;
	}
	
	public String toString() {
		return "Latura: " + numeLatura + " " + v1 + " " + v2 + " " + f1 + " " + f2 + " " + p1 + " " + p2;
	}
	

}
