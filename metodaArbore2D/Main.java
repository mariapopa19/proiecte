package metodaArbore2D;

import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class Main {
	static int cnt = 0;

	public static void main(String[] args) throws IOException {
		Tree tree = new Tree();
		
		int[][] puncte = new int[15][15];
		puncte = citestePuncte();
		
		inserarePuncte(puncte, tree);
		
		// tree.preorder();
		
		int[][] dreptunghi = new int[5][5];
		initalizareDreptunghi(dreptunghi);		
		
		int[][] L = new int[10][10];
		
		//System.out.println(tree.root);
		
		Q(tree.root,dreptunghi,L);
		
		afisareL(L);
	}
	
	public static int[][] citestePuncte() throws IOException {
		try (BufferedReader in = new BufferedReader(new FileReader("vector.txt"))) {
			int[][] puncte = new int[10][10];
			String linie;
			int l = 0, c;
			while((linie = in.readLine()) != null) {
				c = 0;
				String[] s = linie.split(" ");
				puncte[l][c] = Integer.parseInt(s[0]);
				c++;
				puncte[l][c] = Integer.parseInt(s[1]);
				l++;
			}
			return puncte;
		} 
	}
	
	public static void afisarePuncte(int[][] puncte) {
		for(int i = 0 ; i < puncte.length; i++) {
			for(int j = 0 ; j < 2; j++) {
				System.out.print(puncte[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public static void inserarePuncte(int[][] puncte, Tree tree) {
		for(int i = 0 ; i < puncte.length; i++) {
			int x[] = new int[2];
			x[0] = puncte[i][0];
			x[1] = puncte[i][1];
			tree.insert(x);
		}
	}
	
	public static void initalizareDreptunghi(int[][] dreptunghi) {
		// Trebuie sa nu fie FRUNZA
		
		// A(Alfa1, Beta2)
		dreptunghi[0][0] = 6; // x
		dreptunghi[0][1] = 7; // y
		
		// B(Alfa2, Beta2)
		dreptunghi[1][0] = 10;
		dreptunghi[1][1] = 7;
		
		// C(Alfa2, Beta1)
		dreptunghi[2][0] = 10;
		dreptunghi[2][1] = 2;
		
		// D(Alfa1, Beta1)
		dreptunghi[3][0] = 6;
		dreptunghi[3][1] = 2;
	}
	
	public static void Q(Node v, int[][] d, int[][] L) {
		int l, r;
		 
		if(v == null)
			return;
		
		if(v.left != null || v.right != null) {
			if(v.t == 1) {
				l = d[2][1]; // Beta1
				r = d[1][1]; // Beta2
			}
			else {
				l = d[0][0];
				r = d[1][0];
			}
			
			if(v.m > l && v.m < r) {
				// P(v) apartine D
//				if( xi < xB && yi < yB &&
//						xi > xA && yi < yA &&
//						xi < xC && yi > yC &&
//						xi > xD && yi > yD)
				if((v.data[0] < d[1][0]) && (v.data[1] < d[1][1]) && // B
				   (v.data[0] > d[0][0]) && (v.data[1] < d[0][1]) && // A
				   (v.data[0] < d[2][0]) && (v.data[1] > d[2][1]) && // C
				   (v.data[0] > d[3][0]) && (v.data[1] > d[3][1])) { // D
					
					L[cnt][0] = v.data[0];
					L[cnt][1] = v.data[1];
					cnt++;
					
				}
			}
			
			if(v.m >= l)
				Q(v.left, d, L);
			
			if(v.m <= r)
				Q(v.right, d, L);
		}
	}
	
	public static void afisareL(int[][] L) {
		for(int i = 0 ; i < cnt ; i++) {
			System.out.println("(" + L[i][0] + ", " + L[i][1] + ")");
		}
	}
}
