package metodaGraham;


public class Nod {
	
	private Punct P;
	private Nod next, prev;
	
	public Nod(Punct P, Nod next, Nod prev) {
		this.P = P;
		this.next = next;
		this.prev = prev;
	}
	
	public Nod() {
		
	}
	
	public void setNext(Nod n) {
		next = n;
	}
	
	public void setPrev(Nod n) {
		prev = n;
	}
	
	public void setP(Punct P) {
		this.P = P;
	}
	
	public Nod getNext() {
		return next;
	}
	
	public Nod getPrev() {
		return prev;
	}
	
	public Punct getP() {
		return P;
	}
	
	public String toString() {
		return P + " ";
	}
	
	
	
	
}

