
public class Persoana {

	private String nume, prenume, CNP;
	
	public Persoana(String nume, String prenume, String CNP) {
		this.nume = nume;
		this.prenume = prenume;
		this.CNP = CNP;
	}
	
	public String getNume() {
		return nume;
	}
	
	public String getPrenume() {
		return prenume;
	}
	
	public String getCNP() {
		return CNP;
	}
	
	public String toString() {
		return nume + "|" + prenume + "|" + CNP;
	}
	
	public String informatiiPersoana() {
		return nume + " " + prenume + " " + CNP;
	}
}
