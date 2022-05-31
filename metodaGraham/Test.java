package metodaGraham;

import java.io.*;
import java.util.*;

public class Test {

	public static void main(String[] args) throws IOException {
		ArrayList<Punct> puncte =  citestePuncte();
		
		Pregatire p = new Pregatire(puncte);
		p.translatie();
		p.sortare();
		
		puncte = p.getPuncte();
		Punct absMin = gasestePunctAbsMin(puncte);
		
		List lista = new List();
		for(int i = 0 ; i < puncte.size() ; i++) {
			lista.insertAtEnd(puncte.get(i));
		}
		
		Nod nodAbsMin = lista.find(absMin);
		
		// lista.display(0);
		
		Graham(lista, nodAbsMin , absMin);

	}
	
	public static ArrayList<Punct> citestePuncte() throws IOException {
		try (BufferedReader in = new BufferedReader(new FileReader("puncteGraham.txt"))) {
			ArrayList<Punct> puncte = new ArrayList<Punct>();
			String linie;
			while((linie = in.readLine()) != null) {
				String[] s = linie.split(" ");
				Punct temp = new Punct(Integer.parseInt(s[0]), Integer.parseInt(s[1]));
				puncte.add(temp);
			}
			return puncte;
		} 
	}
	
	public static Punct gasestePunctAbsMin(ArrayList<Punct> puncte) {
		Punct min = puncte.get(0);
		for(int i = 1 ; i < puncte.size() ; i++) {
			if(min.getX() > puncte.get(i).getX())
				min = puncte.get(i);
		}
		return min;
	}
	
	public static void Graham(List lista, Nod v, Punct vm) {
		while(v.getNext().getP().compareTo(vm) == 1) {
			if(v.getP().getDeterminant(v.getNext().getP(), v.getNext().getNext().getP()) > 0) {
				v = v.getNext();
			} 
			else {
				// Delete B(v) from list
				
				v.setNext(v.getNext().getNext());
				v.getNext().getNext().setPrev(v);	
				
				if(v.getP().compareTo(vm) == 1) {
					v = v.getPrev();
				}
			}	
		}
		
		System.out.println("Infasuratoarea convexa(graham) este: ");
		lista.display(1);
	}

}
