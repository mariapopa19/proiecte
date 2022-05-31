package simulareAFD;

public class Tranzitie {
	private String st_inceput;
	private char simbol;
	private String st_final;
	Tranzitie(String i, char s, String f){
		st_inceput = i;
		simbol = s;
		st_final = f;
	}
	String getStIncep() {
		return st_inceput;
	}
	char getSimbol() {
		return simbol;
	}
	String getStSf() {
		return st_final;
	}
}
