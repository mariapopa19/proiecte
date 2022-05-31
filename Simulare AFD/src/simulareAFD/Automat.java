package simulareAFD;

import java.io.*;
import java.util.*;

public class Automat {
	private String st_initiala = "q0";
	private String st_initiala1 = "";
	private ListaTranzitii lt = new ListaTranzitii();
	private ArrayList<String> st_finale = new ArrayList<String>();
	private String st_finale_str;
	private Tranzitie tr1;
	private ArrayList<String> multime = new ArrayList<String>();
//	private Set <Character> alfabet = new HashSet<Character>();

	Automat(String nume_fisier) throws Exception {
		BufferedReader buf = new BufferedReader(new FileReader(nume_fisier));
		this.st_initiala = buf.readLine();
		st_initiala1 = st_initiala;
		st_finale_str = buf.readLine();
		String[] tbl = st_finale_str.split(" ");
		for (int i = 0; i < tbl.length; i++)
			st_finale.add(tbl[i]);
		while (true) {
			String tmp = buf.readLine();
			if (tmp == null)
				break;
			else {
				String[] tmpt = tmp.split(" ");
				Tranzitie tr = new Tranzitie(tmpt[0], tmpt[1].charAt(0), tmpt[2]);
				lt.addTranzitie(tr);
			}
		}
		buf.close();
	}

	boolean analizeazaCuvant(String word) throws Exception {
		for (int i = 0; i < word.length(); i++) {
			this.multime.add(st_initiala);
			Tranzitie tr = lt.findTranzitie(st_initiala, word.charAt(i));
//			alfabet.add(word.charAt(i));
			if (tr != null) {
				this.tr1 = tr;
				st_initiala = tr.getStSf();
			} else
				return false;
		}
		this.multime.add(st_initiala);
		String[] stare = st_finale_str.split(" ");
		for (int i = 0; i < stare.length; i++) {
			if (st_initiala.equals(stare[i]) || (word.length() == 0))
				return true;
		}
		return false;
	}

	

	void printTranzitii() throws Exception {
		System.out.print("Multimea tranzitiilor: ");
		System.out.print(multime.get(0) + " ");
		for (int j = 1; j < multime.size(); j++) {
			if (!multime.get(j - 1).equals(multime.get(j)))
				System.out.print(" " + multime.get(j) + " ");
		}
		System.out.println();
	}
	
	

	void print() throws Exception {
		ArrayList<Tranzitie> list = lt.getLista();
		System.out.println("Stari initiale posibile: " + st_initiala1);
		System.out.println("Stari finale posibile: " + st_finale_str);
		int i;
		System.out.println("Tranzitiile posibile:");
		for (i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getStIncep() + " " + list.get(i).getSimbol() + " " + list.get(i).getStSf());
		}

//		System.out.println("Stare initiala: "+st_initiala1);
//		System.out.println("Stare finala: " + this.tr1.getStSf());

	}
}
