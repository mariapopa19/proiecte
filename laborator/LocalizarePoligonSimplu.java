package laborator;

import java.util.Scanner;

public class LocalizarePoligonSimplu {
	public static void main(String[] args) {

		try (Scanner scn = new Scanner(System.in)) {
			System.out.print("Numarul de varfuri: ");
			int n = scn.nextInt();
			n++;
			Punct[] puncte = new Punct[n];
			for(int i = 0 ; i < n - 1 ; i++) {
				double x = scn.nextDouble();
				double y = scn.nextDouble();
				puncte[i] = new Punct(x,y);
				System.out.println();
			}

			System.out.println("Introduceti coordonatele punctului M: ");
			System.out.print("x = ");
			double x = scn.nextDouble();
			System.out.print("y = ");
			double y = scn.nextDouble();
			Punct M = new Punct(x,y);
			System.out.println("M(" + M.getX() + "," + M.getY() + ")");


			puncte[n - 1] = puncte[0];
			int c = 0; 
			boolean dec = false;
			Punct A = new Punct();
			Punct B = new Punct();
			System.out.println();
			for(int i = 0 ; i < n - 1; i++) {
				if((puncte[i].getY() == puncte[i + 1].getY()) && (puncte[i + 1].getY() == M.getY()) && (((M.getX() - puncte[i].getX()) * (M.getX() - puncte[i + 1].getX())) <= 0)) {
					System.out.println("M se afla pe frontiera");
					dec = true;
					break;
				}

				if(puncte[i].getY() > puncte[i + 1].getY()) {
					A.setX(puncte[i].getX());
					A.setY(puncte[i].getY());
					B.setX(puncte[i+1].getX());
					B.setY(puncte[i+1].getY());
				}

				if(puncte[i].getY() < puncte[i + 1].getY()) {
					A.setX(puncte[i+1].getX());
					A.setY(puncte[i+1].getY());
					B.setX(puncte[i].getX());
					B.setY(puncte[i].getY());
				}

				if((puncte[i].getY() != puncte[i + 1].getY()) && (M.getY() > B.getY()) && (M.getY() < A.getY())) {
					if(getDeterminant(A,M,B) > 0)
						c++;

					if(getDeterminant(A,M,B) == 0) {
						System.out.println("M se afla pe Frontiera");
						dec = true;
						break;
					}
				}

				if((puncte[i].getY() != puncte[i + 1].getY()) && (M.getY() == A.getY())) {
					if(M.getX() < A.getX())
						c++;
					if(M.getX() == A.getX()) {
						System.out.println("M se afla pe frontiera");
						dec = true;
						break;
					}

				}

				if((puncte[i].getY() != puncte[i + 1].getY()) && (M.getY() == B.getY()) && (M.getX() == B.getX())) {
					System.out.println("M se afla pe Frontiera");
					dec = true;
					break;
				}				
			}
			if(!dec) {
				if(c % 2 == 0)
					System.out.println("M se afla in exterior");
				else
					System.out.println("M se afla in interior");
			}
		}
	}

	public static double getDeterminant(Punct A, Punct B, Punct C) {
		return (A.getX() * (B.getY() - C.getY())) - (A.getY()*(B.getX() - C.getX())) + ((B.getX() * C.getY() - B.getY() * C.getX()));
	}
}
