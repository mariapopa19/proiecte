package masinaTuring;
import java.io.BufferedReader;
import java.io.FileReader;


public class MasinaTuring {
	private String st_initiala;
	private String st_finala;
	private ListaTranzitii lista=new ListaTranzitii();
	
	MasinaTuring(String nume_fisier) throws Exception {
        BufferedReader buf=new BufferedReader(new FileReader(nume_fisier));
        this.st_initiala = buf.readLine();
        this.st_finala = buf.readLine();
        while(true) {
            String tmp=buf.readLine();
            if(tmp==null)
                break;
            String tmpbl[]=tmp.split(" ");
            Tranzitie tr=new Tranzitie(tmpbl[0], tmpbl[1], tmpbl[2].charAt(0), tmpbl[3].charAt(0), tmpbl[4].charAt(0));
            lista.adaugaTranzitie(tr);
        }
        buf.close();
    }
	
	
	public boolean analizeazaCuvant(String cuvant) throws Exception {
		int i=0; 
		String st_curenta = this.st_initiala;
		cuvant = 'B' + cuvant + 'B'; 
		String[] stare=st_finala.split(" ");  // multimea de stari finale
		while(true)
		{
			Tranzitie tranz = lista.gasesteTranzitie(st_curenta, cuvant.charAt(i));  // in tranz se tine minte tranzitia care are st_curenta ca stare initiala, si simbolX ca cuvant.charAt(i)
			if (tranz != null)
			{
				StringBuilder cuvant2 = new StringBuilder(cuvant);  // se construieste in cuvant2, cuvantul modificat conform tranzitiei.
				cuvant2.setCharAt(i, tranz.getSymbolY()); // setez simbolul pe care vreau sa il scriu peste
				cuvant = cuvant2.toString();
				if (tranz.getDeplasare() == 'L') // conform ultimului caracter din tranzitie (ex: B B L), ne deplasam cu un caracter mai in stanga/dreapta in cuvant
					--i;
				else if (tranz.getDeplasare() == 'R')
					++i;
				st_curenta = tranz.getSt_sfarsit(); // st_curenta devine ultima stare din tranzitia anterioara
				for (String s : stare)  // se verifica daca starea in care ne aflam acum se afla printre starile finale
					if (st_curenta.equals(s))
						return true;
			}
			else
				return false;
		}
	}
	
	public void existaTranzitie(String tranz, char symbol) throws Exception {
		Tranzitie tran = lista.gasesteTranzitie(tranz, symbol);
		if(tran != null) {
			System.out.println("Exista tranzitie!");
		}
		else System.out.println("Nu exista tranzitie!");
	}
	
}
