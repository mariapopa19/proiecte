
import java.util.*;

public class CFG {
    static int np = 0;
    //aici se introduce limbajul
    static String  grammer[][] = {{"S", "SS", "XY","b","c"}, {"X", "XS", "a"}, {"Y", "YS", "b"}};
    static int cont = 0;
    static Set<String> productii = new HashSet<String>();
    
    //verifica daca stringul dat se gaseste in matricea limbajului
    static String check(String a){
        String to_ret = "";
        int count = 0;
        for(int i = 0; i < np; i++) {
            for(count = 0; count < grammer[i].length; count++){
                if(grammer[i][count].equals(a)){
                	productii.add(grammer[i][0] + " " + grammer[i][count]);
                    to_ret += grammer[i][0];
                }
            }
        }
//        cont++;
        return to_ret;
    }
    
    //face toate combinatiile pentru cele doua multimi date
    static String combinat(String a, String b){
        String to_ret = "", temp = "";
            for(int i = 0; i < a.length(); i++){
                for(int j = 0; j < b.length(); j++){
                    temp = "";
                    temp += a.charAt(i) + "" +  b.charAt(j);
                    to_ret += check(temp);
//                    System.out.println("To_ret: " + to_ret);
//                    cont++;
                }
            }
            cont++;
            System.out.println("Ret: " + to_ret);
        return to_ret;
    }
    public static void main(String[] args) {
        String start;
        int n = 0;
        Scanner in = new Scanner(System.in);
        start = "S";
        //np = numarul productiilor
        np = grammer.length;
//        System.out.println("NP = " + np);
        String temp;
        System.out.println("Introduceti cuvantul: ");
        String str = in.nextLine(), st = "", r = "";
        int count;
        String ans_mat[][] = new String[10][10];
        
        //completeaza diagonala principala a matricii
        for(int i = 0; i < str.length(); i++){
            r = "";
            st = "" + str.charAt(i);
            for(int j = 0; j < np; j++){
                for(count = 1; count < grammer[j].length; count++){
                    if(grammer[j][count].equals(st)){
                    	productii.add(grammer[j][0] + " " + grammer[j][count]);
                        r += grammer[j][0];
                    }
                }      
            }
            ans_mat[i][i] = r;
        }
//        System.out.println("r = " + r + "st = " + st);
        //completeaza restul matricii
        for(int i = 1; i < str.length(); i++){
            for(int j = i; j < str.length(); j++){
                r = "";
                for(int k = j - i; k < j; k++){
                    r += combinat(ans_mat[j - i][k], ans_mat[k + 1][j]);
//                    System.out.println("r = " + r);
                }
                ans_mat[j - i][j] = r;
            }
        }
        
        for(int i=0;i<str.length();i++)	//afisam matricea
        {
        	int k=0;
            int l=str.length()-i-1;
            for(int j=l;j<str.length();j++)
            {
                System.out.print("| "+ans_mat[k++][j] + " ");
            }
            System.out.println("| ");
        }
        
        System.out.println("alfa: " + ans_mat[2][1]);
        System.out.println("Nr de prductii folosite: " + productii.size() + " ");
        
        productii.clear();
        //pe ultima coloana a primului rand trebuie sa se gaseasca S
        if(ans_mat[0][str.length() - 1].indexOf(start) >= 0){
            accept();
        }
        else{
            reject();
        }
        
    }
    
    public static void accept(){
        System.out.println("Cuvantul este acceptat! :)");
        System.exit(0);
    }
    public static void reject(){
        System.out.println("Cuvantul nu este acceptat! :(");
        System.exit(0);
    }
    
}
