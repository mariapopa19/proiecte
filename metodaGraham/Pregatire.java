package metodaGraham;

import java.util.*;

public class Pregatire {
	
	private ArrayList<Punct> puncte; // primeste punctele citite
	private Punct CG; // centru de greutate
	
	Pregatire(ArrayList<Punct> x) {
		puncte = new ArrayList<Punct>();
		puncte = (ArrayList<Punct>) x.clone();
		
		CG = puncte.get(0).getCentruDeGreutate(puncte.get(1), puncte.get(2));
	}
	
	public ArrayList<Punct> getPuncte() {
		return puncte;
	}
	
	public void translatie() {
		for(int i = 0 ; i < puncte.size() ; i++) {
			puncte.set(i, new Punct(puncte.get(i).getX() - CG.getX() , puncte.get(i).getY() - CG.getY()));
		}
		
		CG.setX(0);
		CG.setY(0);
	}
	
	public void sortare() {
		for(int i = 0 ; i < puncte.size() - 1 ; i++) {
			for(int j = i + 1 ; j < puncte.size(); j++) {
				if(RelatieDeOrdine(puncte.get(i), puncte.get(j)) == 0) {
					Punct aux = new Punct(puncte.get(i).getX(), puncte.get(i).getY());
					puncte.set(i, new Punct(puncte.get(j).getX(), puncte.get(j).getY()));
					puncte.set(j, new Punct(aux.getX(), aux.getY()));
				}
			}
			
		}
		
//	System.out.println(puncte);	
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
	
	

	
	

}

