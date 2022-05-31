import java.util.*;
import java.io.*;
public class ColectieCarti {
	private TreeSet<Carte> carti;
	
	public ColectieCarti() {
		carti = new TreeSet<>();
	}
	
	public TreeSet<Carte> getCarti() {
		return carti;
	}
	
	public void setCarti(TreeSet<Carte> carti) {
		this.carti = carti;
	}
	
	public StringBuffer getInformatii() {
		StringBuffer sb = new StringBuffer();
		Iterator<Carte> it = carti.iterator();
		while(it.hasNext()) {
			sb.append((it.next().getInformatii()));
		}
		return sb;
	}
	
	public StringBuffer informatiiCarti() {
		StringBuffer sb = new StringBuffer();
		Iterator<Carte> it = carti.iterator();
		while(it.hasNext()) {
			sb.append((it.next().informatiiCarte()));
		}
		return sb;
	}
	
	public void adaugaCarte(Carte aux, boolean imprumutata) {
		//Carte aux = new Carte(cod_ISBN, titlu, editura, imprumutata);
		carti.add(aux);
	}
	
	public void adaugaCarte(String cod_ISBN, String titlu, String editura) {
		Carte aux = new Carte(cod_ISBN, titlu, editura);
		carti.add(aux);
	}
	
	public void salveazaCarti() {
		try {
			PrintWriter out = new PrintWriter(new FileWriter("carti.txt")); // Aici schimba cu carti1 -----------
			for(Carte c : carti) {
				out.println(c.getInformatii());
			}
			out.close();
		} catch(IOException e) {
			System.err.print(e.getMessage());
		}
	}
	
	public void citesteCarti() {
		String l, cod;
		String[] s, s1;
		Carte c;
		TreeSet<Carte> aux = new TreeSet<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader("carti.txt"));
			while((l = br.readLine()) != null) {
				s = l.split("_");
				s1 = s[0].split(" ");
				cod = s1[1];
				c = new Carte(cod, s[1], s[2], Boolean.parseBoolean(s[3]));
				aux.add(c);
			}
			br.close();
			carti.clear();
			carti.addAll(aux);
		}  catch(IOException e) {
			System.err.print(e.getMessage());
		}
	}
}
