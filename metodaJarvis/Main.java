package metodaJarvis;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException{
		ArrayList<Punct> puncte = citestePuncte();
		vectorSortatX(puncte);
		puncteDreaptaStanga(puncte);
	}
	
	public static ArrayList<Punct> citestePuncte() throws IOException {
		try (BufferedReader in = new BufferedReader(new FileReader("puncte.txt"))) {
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
	
	public static void vectorSortatX(ArrayList<Punct> puncte) {
		for(int i = 0 ; i < puncte.size() - 1 ; i++) {
			for(int j = i + 1 ; j < puncte.size() ; j++) {
				if(puncte.get(i).getX() > puncte.get(j).getX()) {
					Collections.swap(puncte, i, j);
				}
			}
		}
	}
	
	public static double getDeterminant(Punct A, Punct B, Punct C) {
		return A.getX()*B.getY()+ B.getX()*C.getY()+ C.getX()*A.getY()-B.getY()*C.getX()-C.getY()*A.getX()-A.getY()*B.getX();
	}
	
	public static void puncteDreaptaStanga(ArrayList<Punct> puncte) {
		ArrayList<Punct> pN = new ArrayList<Punct>();
		ArrayList<Punct> pS = new ArrayList<Punct>();
		Punct temp, a, b, d;
		temp = new Punct(0,0);
		a = new Punct(0,0);
		b = new Punct(0,0);
		d = new Punct(0,0);
		boolean[] nodVizitat = new boolean[puncte.size()];
		int i = 0;
		
		a = puncte.get(0);
		b = puncte.get(puncte.size() - 1);
		nodVizitat[0] = true;
		pN.add(a);
		temp = a;
		
		while(temp.compareTo(b)!=0) {
			for(int j = i + 1 ; j < puncte.size() ; j++) {
				boolean ok = true;
				temp = puncte.get(j);
				nodVizitat[j] = true;
				for(int k = i + 1; k < puncte.size() ; k++) {
					d = puncte.get(k);
					if(!nodVizitat[k]) {
						if(getDeterminant(d, a, temp) > 0) {
							ok = false;
							break;
						}
					}
				}
				if(ok) {
					pN.add(temp);
					a=temp;
					for(int l = i + 1 ; l < j ; l++)
						nodVizitat[l] = true;
					i = j;
					break;
				} else
					nodVizitat[j] = false;
			}
			
		}
		
//		System.out.println("Acoperirea din partea de sus: " + pN);
		
		i = 0;
		temp = new Punct(0,0);
		d = new Punct(0,0);
		nodVizitat = new boolean[puncte.size()];
		
		a = puncte.get(0);
		b = puncte.get(puncte.size() - 1);
		nodVizitat[0] = true;
		pS.add(a);
		temp = a;
		
		while(temp.compareTo(b)!=0) {
			for(int j = i + 1 ; j < puncte.size() ; j++) {
				boolean ok = true;
				temp = puncte.get(j);
				nodVizitat[j] = true;
				for(int k = i + 1; k < puncte.size() ; k++) {
					d = puncte.get(k);
					if(!nodVizitat[k]) {
						if(getDeterminant(d, a, temp) < 0) {
							ok = false;
							break;
						}
					}
				}
				if(ok) {
					pS.add(temp);
					a=temp;
					for(int l = i + 1 ; l < j ; l++)
						nodVizitat[l] = true;
					i = j;
					break;
				} else
					nodVizitat[j] = false;
			}
			
		}
		
//		 System.out.println("Acoperirea din partea de jos: " + pS);
		
		ArrayList<Punct> list = new ArrayList<Punct>();
		list.addAll(pS);
		list.addAll(pN);
		
		Set<Punct> set = new HashSet<Punct>(list);
		list.clear();
		list.addAll(set);
		
		System.out.println("Acoperirea convexa este: " + list);
	}
	
	public static ArrayList<Punct> puncteStanga(ArrayList<Punct> puncte) {
		ArrayList<Punct> p = new ArrayList<Punct>();
		
		for(int i = puncte.size() ; i >= 0 ; i--) {
			p.set(i, puncte.get(i)); 
			p.set(i - 1, puncte.get(i - 1));
			for(int j = i - 2 ; j >= 0; j--) {
				if(getDeterminant(puncte.get(j), p.get(i), p.get(i - 1)) < 0) {
					p.set(i - 1, puncte.get(j));
				}
			}
		}
		return p;
	}
}
