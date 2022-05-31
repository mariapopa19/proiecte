import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.*;
public class ColectieImprumuturi {
	private TreeSet<Imprumut> imprumuturi;
	private static ColectieImprumuturi instanta;
	
	private ColectieImprumuturi() {
		imprumuturi = new TreeSet<>();
	}
	
	public TreeSet<Imprumut> getImprumuturi() {
		return imprumuturi;
	}
	
	public static ColectieImprumuturi getInstanta() {
		if(instanta == null) 
			instanta = new ColectieImprumuturi();
		
		return instanta;
	}
	
	public void adaugaImprumut(Imprumut i) {
		imprumuturi.add(i);
	}
	
	public TreeSet<Imprumut> cautaImprumut(String CNP) {
		Iterator<Imprumut> it = imprumuturi.iterator();
		TreeSet<Imprumut> a = new TreeSet<>();
		while(it.hasNext()) {
			Imprumut el = it.next();
			if(el.getPersoana().getCNP().compareTo(CNP) == 0) {
				a.add(el);
			}
		}
		return a;
	}
	
	public void stergereImprumut(String CNP) {
		TreeSet<Imprumut> cauta = cautaImprumut(CNP);
		Iterator<Imprumut> itImprumut = imprumuturi.iterator();
		Iterator<Imprumut> itCauta = cauta.iterator();
		while(itCauta.hasNext()) {
			Imprumut ca = itCauta.next();
			while(itImprumut.hasNext()) {
				Imprumut im = itImprumut.next();
				if(im.getCarti().equals(ca.getCarti())) {
					itImprumut.remove();
					itCauta.remove();
				}
			}
		}
	}
	
	
	// ---------------------------------------------------------------------
	public void stergereImprumut(Imprumut im) {
		Iterator<Imprumut> it = imprumuturi.iterator();
		while(it.hasNext()) {
			Imprumut aux = it.next();
			if(im.compareTo(aux) == 0) {
				it.remove();
			}
		}
	}
	
	public void salveazaImprumut() {
		try {
			PrintWriter out = new PrintWriter(new FileWriter("imprumut.txt")); // Aici schimba cu imprumut1 -----------
			for(Imprumut i : imprumuturi) {
				out.println(i.getInformatii());
			}
			out.close();
		} catch(IOException e) {
			System.err.print(e.getMessage());
		}
	}
	
	public void citesteFisier() {
		String l, l1, cod, titlu, editura, stare, dc, dr;
		String[] s, s1, s2, s3;
		TreeSet<Imprumut> aux = new TreeSet<>();
		ColectieCarti carti = new ColectieCarti();
		Carte c;
		try {
			BufferedReader br = new BufferedReader(new FileReader("imprumut.txt"));
			while((l = br.readLine()) != null) {
				carti = new ColectieCarti();
				s = l.split("\\|");
				
				Persoana p = new Persoana(s[0], s[1],s[2]);
				
				s1 = s[3].split("_");
				for(int i = 0 ; i < s1.length - 1 ; i = i + 4) {
					s2 = s1[i].split(" ");
					cod = s2[1];
					titlu = s1[i + 1];
					editura = s1[i + 2];
					stare = s1[i + 3];
					
					// System.out.println(cod + " " + titlu + " " + editura + " " + stare);
					c = new Carte(cod, titlu, editura, Boolean.parseBoolean(stare));
					carti.adaugaCarte(c, c.getStare());
				}
				
				dc = s[4];
				dr = s[5];
				
				Imprumut i = new Imprumut(s[0], s[1],s[2], LocalDate.parse(dc), LocalDate.parse(dr));
				i.setCarti(carti);
				aux.add(i);
			}
			br.close();
			imprumuturi.clear();
			imprumuturi.addAll(aux);
		}  catch(IOException e) {
			System.err.print(e.getMessage());

		}
	}
	
	
	
}
