package metodaPenelor;

public class TestPoligon {

    public static void main(String[] args) {
        Punct[] A=new Punct[5];


        A[0]=new Punct(3,2);
        A[1]=new Punct(1,4);
        A[2]=new Punct(2,-3);
        A[3]=new Punct(-3,-2);
        A[4]=new Punct(2,-2);
        
        System.out.println();

        for(Punct a : A) {
        	System.out.println(a);
        }
        System.out.println();


        Punct M=new Punct(2,2);
        
        for(Punct a : A) {
        	System.out.println(a);
        }
        System.out.println();



        Poligon poli=new Poligon(5);

        poli.translatie(A,M);
        
        for(Punct a : A) {
        	System.out.println(a);
        }
        System.out.println();


        poli.ordoneazaVarfuri(A);
        
        for(Punct a : A) {
        	System.out.println(a);
        }
        System.out.println();



        poli.cautaM(M,A);





    }

}
