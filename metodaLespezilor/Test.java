package metodaLespezilor;
import java.util.*;
import java.io.*;

public class Test {
	
	public static void main(String[] args) throws IOException {
		List<Varf> varfuri = new ArrayList<Varf>();
		varfuri = citesteVarfuri();
		
		List<Latura> DCEL = new ArrayList<Latura>();
		DCEL = citesteDCEL();
		
		Varf M = citesteM();
		
		for(int i = 0 ; i < varfuri.size(); i++) {
			varfuri.get(i).setAi(calculezAi(varfuri.get(i), DCEL));
			varfuri.get(i).setAi(calculezBi(varfuri.get(i), DCEL));
		}
		
		ArrayList<Integer> yVarfuri = new ArrayList<Integer>();
		
		for(int i = 0 ; i < varfuri.size(); i++) {
			yVarfuri.add(varfuri.get(i).getY());
		}
		
		int varfDreapta = cautare(yVarfuri, 0, yVarfuri.size() - 1, M.getY());
		int pozitie = 0;
		
		for(int i = 0 ; i < varfuri.size(); i++) {
			if(varfuri.get(i).getY() == varfDreapta)
				pozitie = i;
		}
		
		System.out.println("Varful M se afla in Lespedea " + pozitie); 
	}
	
	
	public static List<Latura> citesteDCEL() throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("DCELin.txt"));
		List<Latura> dcel = new ArrayList<Latura>();
		List<Integer> t = new ArrayList<Integer>();
		String linie;
		while((linie = in.readLine()) != null) {
			String[] s = linie.split(" ");
			for(int i = 0 ; i < s.length ; i++) {
				t.add(Integer.parseInt(s[i]));
			}
			// 						     l,     xV1,       yV1,      xV2,       yV2,       f1,       f2,       p1,       p2
			Latura temp = new Latura(t.get(0), t.get(1), t.get(2), t.get(3), t.get(4), t.get(5), t.get(6), t.get(7), t.get(8));
			dcel.add(temp);
		}
		in.close();
		return dcel;
	}
	
	public static List<Varf> citesteVarfuri() throws IOException {
		Varf v1 = new Varf(0, -1);
		Varf v2 = new Varf(-2, 0);
		Varf v3 = new Varf(0, 1);
		Varf v4 = new Varf(3, 2);
		Varf v5 = new Varf(-3, 3);
		Varf v6 = new Varf(1, 5);
		Varf v7 = new Varf(3, 6);
		Varf v8 = new Varf(-1, 8);
		List<Varf> varfuri = new ArrayList<Varf>();
		Collections.addAll(varfuri, v1, v2, v3, v4, v5, v6, v7, v8);
		return varfuri;
	}
	
	public static Varf citesteM() throws IOException {
		System.out.println("Coordonatele punctului M: ");
		System.out.print("x = ");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String x = in.readLine();
		System.out.print("y = ");
		String y = in.readLine();
		Varf M = new Varf(Integer.parseInt(x), Integer.parseInt(y));
		return M;
	}
	
	public static ArrayList <Integer> calculezAi(Varf V, List<Latura> DCEL) {
		// V - varful in care trebuie sa intre 
		ArrayList <Varf> temp = new ArrayList<Varf>();
		ArrayList <Integer> Ai = new ArrayList<Integer>();
		// varfurile care intra
		for(int i = 0 ; i < DCEL.size() ; i++) {
			if(DCEL.get(i).getV2().getX() == V.getX() && DCEL.get(i).getV2().getY() == V.getY()) {
				temp.add(DCEL.get(i).getV1());
			}
		}
		
		// varfurile care intra in ordine crescatoare
		Collections.sort(temp);
		
		for(int i = 0 ; i < DCEL.size(); i++) {
			for(int j = temp.size() - 1 ; j >= 0; j--)
				if(DCEL.get(i).getV1().getX() == temp.get(j).getX() && DCEL.get(i).getV1().getY() == temp.get(j).getY())
					Ai.add(DCEL.get(i).getnumeLatura());
		}
		
		return Ai;
	}
	
	public static ArrayList <Integer> calculezBi(Varf V, List<Latura> DCEL) {
		// V - varful in care trebuie sa iasa
		ArrayList <Varf> temp = new ArrayList<Varf>();
		ArrayList <Integer> Bi = new ArrayList<Integer>();
		// varfurile care ies
		for(int i = 0 ; i < DCEL.size() ; i++) {
			if(DCEL.get(i).getV1().getX() == V.getX() && DCEL.get(i).getV1().getY() == V.getY()) {
				temp.add(DCEL.get(i).getV2());
			}
		}
		// varfurile care ies in ordine crescatoare
		Collections.sort(temp);
		
		for(int i = 0 ; i < DCEL.size(); i++) {
			for(int j = 0 ; j < temp.size() ; j++)
				if(DCEL.get(i).getV2().getX() == temp.get(j).getX() && DCEL.get(i).getV2().getY() == temp.get(j).getY())
					Bi.add(DCEL.get(i).getnumeLatura());
		}
		
		return Bi;
	}
	
	public static int cautare(ArrayList<Integer> a, int i, int n, int b) {
		// returneaza elementul din dreapta 
		int r;
		if(i == n)
			return a.get(i);
		else if(b <= a.get((i + n) / 2))
			r = cautare(a, i, (i + n) / 2, b);
		else
			r = cautare(a, (i + n) / 2 + 1, n, b);
		return r;
	}
}
