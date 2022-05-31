package metodaPenelor;

public class Poligon {

    private  Punct[]  P;
    private  int n;

    public Poligon(int n) {
        this.n = n;
        P=new Punct[n];
    }


    public Punct[] getP() {
        return P;
    }

    public void setP(Punct[] p) {
        P = p;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public Punct calculeazaG(Punct[] A){
        Punct G=new Punct(0,0);
        int gx=0;
        int gy=0;

        for(int i=0;i<A.length;i++){
            gx+=A[i].getX();
            gy+=A[i].getY();
        }
        gx/=A.length;
        gy/=A.length;
        G.setX(gx);
        G.setY(gy);

        return G;
    }

    public void translatie(Punct[] A,Punct M){
        Punct G=calculeazaG(A);

        for(int i=0;i<A.length;++i){
            A[i].setX(A[i].getX()-G.getX());
            A[i].setY(A[i].getY()-G.getY());
        }

        M.setX(M.getX()-G.getX());
        M.setY(M.getY()-G.getY());
        G.setX(0);
        G.setY(0);


    }

    public double calculeazaDet(Punct A, Punct M,Punct B)
    {
        double det;
        det = (A.getX()*M.getY() + M.getX()*B.getY()  + A.getY() *B.getX() )-(M.getY() *B.getX()  + A.getX() *B.getY()  + A.getY() *M.getX() );
        return det;
    }

    public int calculeazaCadran(Punct A)
    {
        int cA=0;

        if(A.getX()>0 && A.getY()>=0)
        {
            cA=1;
        }
        if(A.getX()<=0 && A.getY()>0)
        {
            cA=2;
        }
        if(A.getX()<0 && A.getY()<=0)
        {
            cA=3;
        }
        if(A.getX()>=0 && A.getY()<0)
        {
            cA=4;
        }
        return cA;
    }

    public void ordoneazaVarfuri(Punct[] A){
        Punct aux=new Punct(0,0);
        Punct O=new Punct(0,0);

        for(int i=0;i<n-1;i++){
            for(int j=i+1;j<n;j++){
                if(calculeazaCadran(A[j])<calculeazaCadran(A[i]) || (calculeazaCadran(A[j])==calculeazaCadran(A[i]) && (calculeazaDet(A[j],O,A[i])>0)) ){
                    aux=A[i];
                    A[i]=A[j];

                    A[j]=aux;
                }
            }
        }
    }

  public int cautaPozM(Punct M,Punct A[]){
        ordoneazaVarfuri(A);

        int Sol=-1, Left=0,Right=A.length;


        while(Left<=Right){
            int Mid=(Left+Right)/2;
            if(calculeazaCadran(A[Mid])==calculeazaCadran(M)){
                Sol=Mid;
                break;
            }


            if(calculeazaCadran(A[Mid])>calculeazaCadran(M))
                Right=Mid-1;

            if(calculeazaCadran(A[Mid])<calculeazaCadran(M))
                Left=Mid+1;



        }

        return Sol;
  }

  public void cautaM(Punct M,Punct A[]){
        int pozM=cautaPozM(M,A);
        int next;
        if(pozM==A.length-1){
            next=0;
        }else
        {
            next=pozM+1;
        }

        if(calculeazaDet(M,A[next],A[pozM])>0) System.out.println("Punctul se afla in exterior");
        else if(calculeazaDet(M,A[next],A[pozM])<0) System.out.println("Punctul se afla in interior");
        else System.out.println("Punctul se afla pe frontiera");

  }



}
