package chDIvideEtImpera;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


@SuppressWarnings("unused")
public class Test {

	public static void main(String[] args) {
		ArrayList<Punct> puncte = new ArrayList<Punct>();
		puncte.add(new Punct(-4, -3));
		puncte.add(new Punct(-3, 1));
		puncte.add(new Punct(-1, -2));
		puncte.add(new Punct(1, -1));
		puncte.add(new Punct(3, 2));
		puncte.add(new Punct(4, -4));
		
		ChDivide ch = new ChDivide(puncte);
		ch.raspuns();
	}
}
