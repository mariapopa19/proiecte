package metodaLespezilor;
import java.util.*;

public class Varf implements Comparable<Object>{
	private int x,y;
	private ArrayList<Integer> Ai;
	private ArrayList<Integer> Bi;
		
	public Varf(int x, int y) {
		this.x = x;
		this.y = y;
		Ai = new ArrayList<Integer>();
		Bi = new ArrayList<Integer>();
	}
	
	public ArrayList<Integer> getAi(){
		return Ai;
	}
	
	public ArrayList<Integer> getBi(){
		return Bi;
	}
	
	@SuppressWarnings("unchecked")
	public void setAi(ArrayList<Integer> temp) {
		Ai = (ArrayList<Integer>) temp.clone();
	}
	
	@SuppressWarnings("unchecked")
	public void setBi(ArrayList<Integer> temp) {
		Bi = (ArrayList<Integer>) temp.clone();
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public String toString() {
		return x + " " + y;
	}

	@Override
	public int compareTo(Object v) {
		return (int)(this.x - ((Varf) v).getX());
	}
	
}
