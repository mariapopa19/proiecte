import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

public class ListaImprumuturi extends JFrame {
	
	JPanel p1;
	
	private TreeSet<Imprumut> imprumuturilePersoanei;
	private ArrayList<JButton> butoane = new ArrayList<JButton>();
	private ArrayList<JTextArea> texte = new ArrayList<JTextArea>();
	private ArrayList<JPanel> paneluri = new ArrayList<JPanel>();
	
	ListaImprumuturi(String CNP) {
		super("Lista Imprumuturi");
		
		ImageIcon icon = new ImageIcon("icon.png");
		this.setIconImage(icon.getImage());
		
		AscultatorLista al = new AscultatorLista();
		
		ColectieImprumuturi ci = ColectieImprumuturi.getInstanta();
		imprumuturilePersoanei = ci.cautaImprumut(CNP);
		
		Iterator<Imprumut> it = imprumuturilePersoanei.iterator();
		
		while(it.hasNext()) {
			Imprumut aux = it.next();
			
			JTextArea t = new JTextArea(" " + aux.getPersoana().informatiiPersoana() + "\n " 
					+ aux.getCarti().informatiiCarti() + "\n " 
					+ aux.getDataCurenta() + " -> " + aux.getDataReturnare());
			t.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			t.setEditable(false);
			t.setBackground(new Color(227,183,160, 60));
			texte.add(t);
			
			JButton button = new JButton("Imprumut " + texte.size());
			button.setFont(new Font("Times New Roman", Font.BOLD, 18));
			button.setFocusable(false);
			button.addActionListener(al);
			butoane.add(button);
			
			JPanel p2 = new JPanel();
			p2.setLayout(new FlowLayout(FlowLayout.LEFT));
			p2.add(t); p2.add(button);
			paneluri.add(p2);
		}
		
		p1 = new JPanel();
		p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));
		
		for(int i = 0 ; i < paneluri.size() ; i++) {
			p1.add(paneluri.get(i));
		}
		
		p1.setBackground(new Color(191,146,112, 50));
		
		add(p1);
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	class AscultatorLista implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			for(int i = 0 ; i < butoane.size(); i++) {
				if(e.getSource() == butoane.get(i)) {
					Iterator<Imprumut> it = imprumuturilePersoanei.iterator();
					int contor = 0;
					while(it.hasNext()) {
						Imprumut im = it.next();
						if(contor == i) {
							new RestituireImprumut(im);
							ListaImprumuturi.this.dispose();
						}
						contor++;
					}
				}
			}	
		}
	}
}
