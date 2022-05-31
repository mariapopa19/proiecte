package simulareAFD;

import java.util.*;

public class ListaTranzitii {
	private ArrayList <Tranzitie> lista;
	ListaTranzitii() {
		this.lista = new ArrayList <Tranzitie> ();
	}
	
	void addTranzitie(Tranzitie tr) {
		this.lista.add(tr);
	}
	
	Tranzitie findTranzitie(String stare, char simbol) throws Exception {
		for(int i = 0; i < this.lista.size(); i++) {
			Tranzitie tmp = this.lista.get(i);
			if(tmp.getStIncep().equals(stare) && tmp.getSimbol() == simbol)
				return tmp;
		}
		return null;
		//throw new Exception("Tranzitia nu a fost gasita!");
	}
	
	public ArrayList<Tranzitie> getLista() {
        return lista;
    }
}
