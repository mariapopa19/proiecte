package simulareAFD;

//import java.io.FileNotFoundException;

public class TestAFD {
	public static void main(String[] args)throws Exception {
		Automat M = new Automat ("automat.txt");
		
		if(M.analizeazaCuvant("bbbac")) {
			M.printTranzitii();
			System.out.println("Cuvantul este acceptat! :)");
			M.print();
		}
		else {
			M.printTranzitii();
			System.out.println("Cuvantul nu este acceptat! :(");
		}
	}
}
