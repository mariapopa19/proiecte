package metodaGraham;


public class List {
	
	private Nod start;
	private Nod end ;
	private int size;
	
	public List()
	{
		start = null;
		end = null;
		size = 0;
	}
	
	public int getSize() {
		return size;
	}
	
	public void insertAtEnd(Punct P)
    {
        Nod nodAdaug = new Nod(P, null, null);        
        if (start == null)
        {
            nodAdaug.setNext(nodAdaug);
            nodAdaug.setPrev(nodAdaug);
            start = nodAdaug;
            end = start;
        }
        else
        {
        	nodAdaug.setPrev(end);
            end.setNext(nodAdaug);
            start.setPrev(nodAdaug);
            nodAdaug.setNext(start);
            end = nodAdaug;    
        }
        size++;
    }
	
	public Nod find(Punct P) {
		Nod location = start;
		do 
		{
			if(location.getP().compareTo(P) == 0) {
				return location;
			}
			else
			{
				location = location.getNext();
			}
		}while(location != start);
		return null;
	}
	
	public void display(int a)
	// 0 -> afisare normala a unei liste dublu inlantuite
	// 1 -> afisare pentru infasurarea convexa
    {
        Nod parc = start;
        
        System.out.printf(start.getP() + ", ");
        
        parc = start.getNext();
        while (parc.getNext() != start) 
        {
        	System.out.print(parc.getP() + ", ");
            parc = parc.getNext();
        }
        
        if(a == 0) {
        	System.out.print(parc.getP() + ", ");
        	System.out.print(start.getP() + ".");
        }
        else 
        	System.out.print(parc.getP() + ". ");
        
        System.out.println();
    }
	
	

}
