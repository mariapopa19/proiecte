package localizareIncludereExcludere;

import java.util.*;

import java.io.*;

public class LocalizareIncludereExcludere {

	public static void main(String[] args) throws IOException {
		ArrayList<Punct> puncte = new ArrayList<Punct>();
		puncte = citestePuncte();
		
		ArrayList<Punct> L1 = new ArrayList<Punct>();
		ArrayList<Punct> L2 = new ArrayList<Punct>();
		
		L1 = calculeazaL1(puncte);
		L2 = calculeazaL2(puncte);
		
		int[][] m = new int[7][7];
		m = calculeazaMatrice(L1, L2);
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Introduceti coodonatele punctelor: ");
		String x = in.readLine();
		String y = in.readLine();
		String x1 = in.readLine();
		String y1 = in.readLine();
		String x2 = in.readLine();
		String y2 = in.readLine();
		String x3 = in.readLine();
		String y3 = in.readLine();
		
		Punct A = new Punct(Double.parseDouble(x), Double.parseDouble(y));
		Punct B = new Punct(Double.parseDouble(x1), Double.parseDouble(y1));
		Punct C = new Punct(Double.parseDouble(x2), Double.parseDouble(y2));
		Punct D = new Punct(Double.parseDouble(x3), Double.parseDouble(y3));
		
		
		int qA = getQ(A, L1, L2, m);
		int qB = getQ(B, L1, L2, m);
		int qC = getQ(C, L1, L2, m);
		int qD = getQ(D, L1, L2, m);
		System.out.println("qA: " + qA + "\nqB: " + qB + "\nqC: "+ qC + "\nqD: " + qD);
		
		int nrPuncte = qB - qA - qC + qD;
		System.out.println("In dreptunghiul dat se afla " + nrPuncte + " puncte.");

	}
	
	public static int getQ(Punct p, ArrayList<Punct> l1, ArrayList<Punct> l2, int[][] m) {
		Punct Ax = cautareBinaraX(l1, 0, l1.size() - 1, p);
		Punct Ay = cautareBinaraY(l2, 0, l2.size() - 1, p);
		
		int pozI = 0;
		for(int k = 0; k < l1.size(); k++)
			if(l1.get(k).getX() == Ax.getX() && l1.get(k).getY() == Ax.getY())
				pozI = k;
		int pozJ = 0;
		for(int k = 0; k < l2.size(); k++)
			if(l2.get(k).getX() == Ay.getX() && l2.get(k).getY() == Ay.getY())
				pozJ = k;
		
		return m[6 - pozI][pozJ];
	}
	
	public static ArrayList<Punct> citestePuncte() {
		Punct a1 = new Punct(2, 5);
		Punct a2 = new Punct(3, 3);
		Punct a3 = new Punct(6, 2);
		Punct a4 = new Punct(7, 4);
		Punct a5 = new Punct(9, 1);
		Punct a6 = new Punct(11, 6);
		
		ArrayList<Punct> puncte = new ArrayList<Punct>();
		Collections.addAll(puncte, a1, a2, a3, a4, a5, a6);
		
		return puncte;
		
	}
	
	
	public static ArrayList<Punct> calculeazaL1(ArrayList<Punct> p) {
		ArrayList<Double> temp = new ArrayList<Double>();
		ArrayList<Punct> x = new ArrayList<Punct>();
		for(int i = 0; i < p.size(); i++) 
			temp.add(p.get(i).getX());
		
		Collections.sort(temp);
		
		
		for(int i = 0 ; i < p.size(); i++) {
			for(int j = 0 ; j < temp.size(); j++)
				if(p.get(i).getX() == temp.get(j))
					x.add(p.get(j));
		}
		return x;
	}
	
	public static ArrayList<Punct> calculeazaL2(ArrayList<Punct> p) {
		ArrayList<Punct> y = new ArrayList<Punct>();
		ArrayList<Double> temp = new ArrayList<Double>();
		for(int i = 0; i < p.size(); i++) 
			temp.add(p.get(i).getY());
		
		Collections.sort(temp);
		
		for(int i = 0 ; i < p.size(); i++) {
			for(int j = 0 ; j < temp.size(); j++)
				if(p.get(i).getY() == temp.get(j))
					y.add(p.get(j));
		}
		
		return y;
	}
	
	public static int[][] calculeazaMatrice(ArrayList<Punct> l1, ArrayList<Punct>l2) {
		int[][] m = new int[7][7];
		for(int i = 0; i < 7; i++) 
			m[i][0] = 0;
			
		for(int j = 1; j < 7; j++) {
			int k = cautaPozitie(l2, l1.get(j - 1));
			for(int i = 0; i < k; i++) //for(int i = k; i < 7; i++)
				m[i][j] = m[i][j - 1];
			for(int i = k; i < 7; i++) //int i = 0; i < k; i++
				m[i][j] = m[i][j - 1] + 1;
		}
		
		int [][] m1 = new int [7][7];
		for(int i = 6, k = 0 ; i >= 0; i--, k++) {
			for(int j = 0 ; j < 7; j++) {
				m1[k][j] = m[i][j];
			}
		}
		
		System.out.println("Matricea punctelor este:");
		for(int i = 0; i < l1.size() + 1; i++) {
			for(int j = 0; j < l1.size() + 1; j++) {
				System.out.print(m1[i][j] + " ");
				}
			System.out.println();
		}
		return m1;		
	}
	
	public static int cautaPozitie(ArrayList<Punct> l, Punct el) {
		for(int i = 0; i < l.size(); i++)
			if(l.get(i).getX() == el.getX() && l.get(i).getY() == el.getY())
				return i + 1;
		return -1;
	}
	
	public static Punct cautareBinaraX(ArrayList<Punct> L, int i, int n, Punct b) {
		// returneaza elementul din dreapta pt x
		Punct r;
		if(i == n)
			return L.get(i);
		else if(b.getX() <= L.get((i + n) / 2).getX())
			r = cautareBinaraX(L, i, (i + n) / 2, b);
		else 
			r = cautareBinaraX(L, (i + n)/2 + 1, n, b);
		return r;
	}

	public static Punct cautareBinaraY(ArrayList<Punct> L, int i, int n, Punct b) {
		// returneaza elementul din dreapta pt y
		Punct r;
		if(i == n)
			return L.get(i);
		else if(b.getY() <= L.get((i + n) / 2).getY())
			r = cautareBinaraY(L, i, (i + n) / 2, b);
		else 
			r = cautareBinaraY(L, (i + n)/2 + 1, n, b);
		return r;
	}

}
