package masinaTuring;
import java.util.*;

public class ListaTranzitii {
	
	ArrayList<Tranzitie> lista;

	public ListaTranzitii(ArrayList<Tranzitie> lista) {
		super();
		this.lista = lista;
	}
	
	public ListaTranzitii() {
		this.lista = new ArrayList<Tranzitie>();
	}
	
	public void adaugaTranzitie(Tranzitie t) {
		lista.add(t);
	}
	
	Tranzitie gasesteTranzitie(String stare, char simbol) throws Exception {
        for(int i=0; i<this.lista.size(); ++i)
        {
            Tranzitie t = this.lista.get(i);
            if(t.getSt_inceput().equals(stare) && t.getSymbolX() == simbol)
                return t;
        }
        return null;
        //throw new Exception("Tranzitia nu a fost gasita");
    }
}
