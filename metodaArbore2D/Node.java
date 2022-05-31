package metodaArbore2D;

public class Node {
	Node left;
	Node right;
	int []data;
	
	int t;
	// t = 1 -> taietura orizontala
	// t = 0 -> taietura verticala
	
	int m;
	
	// m = abscisa daca t = 0
	// m = ordonata daca t = 1
	
	public Node(int []x, int t) {
		this.left = null;
		this.right = null;
		this.t = t;
		
		data = new int[2];
		
		for(int i = 0 ; i < 2 ; i++)
			data[i] = x[i];
		
//		if(t == 0)
//			m = data[0];
//		else
//			m = data[1];
		m = data[t];
	}
	
	public String toString() {
//		return left + " " + right + " " + data[0] + " " + data[1];
		return data[0] + " " + data[1];
	}
}
