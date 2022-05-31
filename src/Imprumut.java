import java.io.*;
import java.time.*;
import java.time.temporal.*;

public class Imprumut implements Comparable<Imprumut> {
	private ColectieCarti carti;
	private Persoana persoana;
	private LocalDate dataCurenta;
	private LocalDate dataReturnare;
	private static final double pZi = 0.5;
	private static final double pCartePierduta = 50; // penalizare pentru pierderea unei carti
	
	public Imprumut(String nume, String prenume, String CNP, LocalDate dataReturnare) {
		persoana = new Persoana(nume, prenume, CNP);
		this.dataReturnare = dataReturnare;
		carti = new ColectieCarti();
		dataCurenta = LocalDate.now(); 
	}
	
	public Imprumut(String nume, String prenume, String CNP, LocalDate dataCurenta, LocalDate dataReturnare) {
		persoana = new Persoana(nume, prenume, CNP);
		this.dataReturnare = dataReturnare;
		this.dataCurenta = dataCurenta;
		carti = new ColectieCarti();
		
	}
	
	public Persoana getPersoana() {
		return persoana;
	}
	
	public LocalDate getDataReturnare() {
		return dataReturnare;
	}
	
	public LocalDate getDataCurenta() {
		return dataCurenta;
	}
	
	public ColectieCarti getCarti() {
		return carti;
	}
	
	public void setCarti(ColectieCarti c) {
		this.carti = c;
	}
	
	
	public double deciziePlata(LocalDate datac, int nrCartiAduse) {
		long zile = ChronoUnit.DAYS.between(dataReturnare, datac);
		if(zile == 0)
			return plata(nrCartiAduse);
		else
			return plata(nrCartiAduse, datac);
	}
	
	public double plata(int nrCartiAduse) {
	// plata fara penalizari pentru data
		int cartiPierdute = carti.getCarti().size() - nrCartiAduse;
		
		long zilet = 0;
		for(int i = 0; i < nrCartiAduse; i++) {
			long zile = ChronoUnit.DAYS.between(dataCurenta, dataReturnare);
			zilet += zile;
		}
		
		double penalizareCartiNeaduse = cartiPierdute * pCartePierduta;
		
		return (zilet * pZi) + penalizareCartiNeaduse;
	}
	
	public double plata(int nrCartiAduse, LocalDate dataPenalizare) {
	// plata cu penalizari pentru data
		double plataNormala = plata(nrCartiAduse);
		
		int zilet = 0;
		for(int i = 0; i < nrCartiAduse; i++) {
			long zile = ChronoUnit.DAYS.between(dataReturnare, dataPenalizare);
			zilet += zile;
		}

		return plataNormala + (zilet * pZi * 2);
	}
	
	public String getInformatii() {
		return persoana.toString() + "|" + carti.getInformatii() + " |" + dataCurenta + "|" + dataReturnare + "|";
	}
	
	public void salveazaArhiva() {
		try {
			PrintWriter out = new PrintWriter(new FileWriter("arhiva.txt", true));
			out.print(getInformatii() + "\n");
			out.close();
		} catch(IOException e) {
			System.err.print(e.getMessage());
		}
	}

	@Override
	public int compareTo(Imprumut o) {
		return dataReturnare.compareTo(o.getDataReturnare());
	}
	
}


