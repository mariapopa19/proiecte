package chOnLine;

import java.util.ArrayList;


public class Test {

	public static void main(String[] args) {
		ArrayList<Punct> puncte = new ArrayList<Punct>();
		
		puncte.add(new Punct(4, -4));
		puncte.add(new Punct(-3, 1));
		puncte.add(new Punct(3, 2));
		puncte.add(new Punct(-4, -3));
		
		puncte.add(new Punct(1, -1)); // Interior
		
		puncte.add(new Punct(-2, -4)); // Exterior
		
		ArrayList<Punct> temp = new ArrayList<Punct>();
		temp.add(puncte.get(0));
		temp.add(puncte.get(1));
		temp.add(puncte.get(2));
		temp.add(puncte.get(3));
		
		System.out.println("Avem infasuratoarea convexa: ");
		System.out.println(temp);
		System.out.println();
		
		for(int i = 4 ; i < puncte.size() ; i++) {
			ArrayList<Punct> verfi = new ArrayList<Punct>();
			verfi.addAll(temp);
			CH ch = new CH(temp, puncte.get(i));
			temp = ch.adauga();
			System.out.println("Se adauga punctul "  + puncte.get(i));
			if(temp.equals(verfi))
				System.out.println("Punctul este in interior, nu se modifica infasuratoarea convexa");
			else {
				System.out.println("Punctul este in exterior, iar infasuratoarea convexa se modifica");
				System.out.println(temp);
			}
				
			System.out.println();
		}
	}

}
