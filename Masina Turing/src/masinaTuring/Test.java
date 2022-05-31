package masinaTuring;

public class Test {

	public static void main(String[] args) throws Exception {
		MasinaTuring mt = new MasinaTuring("mtL2.txt");
        if(mt.analizeazaCuvant("aaaabb"))
            System.out.println("Cuvantul este acceptat");
        else
            System.out.println("Cuvantul nu este acceptat");
        
        mt.existaTranzitie("q0", 'B');
	}

}
