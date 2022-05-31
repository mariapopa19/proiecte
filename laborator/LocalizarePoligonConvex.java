package laborator;

import java.util.Scanner;

public class LocalizarePoligonConvex {

	public static void main(String[] args) {
		try (Scanner scn = new Scanner(System.in)) {
			System.out.print("Numarul de varfuri: ");
			int n = scn.nextInt();
			Punct[] puncte = new Punct[n];
			for(int i = 0 ; i < n ; i++) {
				double x = scn.nextDouble();
				double y = scn.nextDouble();
				puncte[i] = new Punct(x,y);
				System.out.println();
			}

			System.out.println("Introduceti M");
			System.out.print("x = ");
			double x = scn.nextDouble();
			System.out.print("y = ");
			double y = scn.nextDouble();
			Punct M = new Punct(x,y);
			System.out.println("M(" + M.getX() + "," + M.getY() + ")");
			System.out.println();

			n++;
			Punct[] aux = new Punct[n];
			System.arraycopy(puncte, 0, aux, 0, n - 1);
			aux[n - 1] = new Punct(M.getX(), M.getY());
			puncte = aux;
			aux = null;

			afisareVector(puncte, n);

			Punct CG = puncte[0].getCentruDeGreutate(puncte[1], puncte[2]);
			for(int i = 0 ; i < n ; i++) {
				puncte[i].setX(puncte[i].getX() - CG.getX());
				puncte[i].setY(puncte[i].getY() - CG.getY());
			}

			M.setX(M.getX() - CG.getX());
			M.setY(M.getY() - CG.getY());


			afisareVector(puncte, n);
			CG.setX(0);
			CG.setY(0);

			Sortare(puncte, n);	
			afisareVector(puncte, n);

			int poz = cautareBinara(M,puncte,n);
			int left = poz - 1;
			int right = poz + 1;

			System.out.println();
			System.out.println("Punctul M se afla intre punctele: A" + (left + 1) + " si A" + right);

			if(M.getDeterminant(puncte[left], puncte[right]) > 0)
				System.out.println("M se afla in interior");

			if(M.getDeterminant(puncte[left], puncte[right]) < 0)
				System.out.println("M se afla in exterior");

			if(M.getDeterminant(puncte[left], puncte[right]) == 0)
				System.out.println("M se afla pe frontiera");
		}
	}

	public static void afisareVector(Punct [] puncte, int n) {
		System.out.println();
		for(int i = 0 ; i < n ; i++) {
			System.out.println("A" + (i + 1) + "(" + puncte[i].getX() + "," + puncte[i].getY() + ")");
		}
	}


	public static int cautareBinara(Punct M, Punct[] puncte, int n) {
		int sol = -1, left = 0, right = n;
		while(left <= right) {
			int mid = (left + right) / 2;
			if(puncte[mid].getX() == M.getX() && puncte[mid].getY() == M.getY()) {
				sol = mid;
				break;
			}
			if(RelatieDeOrdine(puncte[mid], M) == 0) 
				right = mid - 1;
			if(RelatieDeOrdine(puncte[mid], M) == 1)
				left = mid + 1;
		}
		return sol;

	}

	public static int RelatieDeOrdine(Punct A, Punct B) {
		// B > A => 1
		// A > B => 0

		Punct O = new Punct();
		int cadA = A.getCadran();
		int cadB = B.getCadran();
		if(cadB == cadA) {
			if(B.getDeterminant(O, A) > 0) {
				return 1;
			}
			else
				return 0;
		}
		else
		{
			if(cadB > cadA)
				return 1;
			else 
				return 0;

		}
	}

	public static void Sortare(Punct[] puncte, int n) {
		for(int i = 0 ; i < n - 1 ; i++) {
			for(int j = i + 1 ; j < n; j++) {
				if(RelatieDeOrdine(puncte[i], puncte[j]) == 0) {
					Punct aux = new Punct();
					aux.setXY(puncte[i].getX(), puncte[i].getY());
					puncte[i].setXY(puncte[j].getX(), puncte[j].getY());
					puncte[j].setXY(aux.getX(), aux.getY());
				}
			}

		}


	}
}
