package masinaTuring;

import java.util.Objects;

public class Tranzitie {
	private String st_inceput;
	private String st_sfarsit;
	private char symbolX;
	private char symbolY;
	private char deplasare;
	
	Tranzitie(String i, String s, char x, char y, char d){
		this.st_inceput = i;
		this.st_sfarsit = s;
		this.symbolX = x;
		this.symbolY = y;
		this.deplasare = d;
	}

	public String getSt_inceput() {
		return st_inceput;
	}

	public void setSt_inceput(String st_inceput) {
		this.st_inceput = st_inceput;
	}

	public String getSt_sfarsit() {
		return st_sfarsit;
	}

	public void setSt_sfarsit(String st_sfarsit) {
		this.st_sfarsit = st_sfarsit;
	}

	public char getSymbolX() {
		return symbolX;
	}

	public void setSymbolX(char symbolX) {
		this.symbolX = symbolX;
	}

	public char getSymbolY() {
		return symbolY;
	}

	public void setSymbolY(char symbolY) {
		this.symbolY = symbolY;
	}

	public char getDeplasare() {
		return deplasare;
	}

	public void setDeplasare(char deplasare) {
		this.deplasare = deplasare;
	}

	@Override
	public int hashCode() {
		return Objects.hash(deplasare, st_inceput, st_sfarsit, symbolX, symbolY);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tranzitie other = (Tranzitie) obj;
		return deplasare == other.deplasare && Objects.equals(st_inceput, other.st_inceput)
				&& Objects.equals(st_sfarsit, other.st_sfarsit) && symbolX == other.symbolX && symbolY == other.symbolY;
	}
	
	
}
