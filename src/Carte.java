
public class Carte implements Comparable<Carte> {

	private String cod_ISBN, titlu, editura;
	private boolean imprumutata;
	
	public Carte(String cod_ISBN, String titlu, String editura) {
		this.cod_ISBN = cod_ISBN;
		this.titlu = titlu;
		this.editura = editura;
		imprumutata = false;
	}
	
	public Carte(String cod_ISBN, String titlu, String editura, boolean imprumutata) {
		this.cod_ISBN = cod_ISBN;
		this.titlu = titlu;
		this.editura = editura;
		this.imprumutata = imprumutata;
	}
	
	public String getISBN() {
		return cod_ISBN;
	}
	
	public String getTitlu() {
		return titlu;
	}
	
	public String getEditura() {
		return editura;
	}
	
	public boolean getStare() {
		return imprumutata;
	}
	
	public void setStare(boolean stare) {
		this.imprumutata = stare;
	}
	
	public String getInformatii() {
		return " " + cod_ISBN + " _" + titlu + "_" + editura + "_" + imprumutata + "_";
	}
	
	public String informatiiCarte() {
		return titlu + " " + editura + " " + ", ";
	}
	

	@Override
	public int compareTo(Carte o) {
		return titlu.compareTo(o.titlu);
	}
}
