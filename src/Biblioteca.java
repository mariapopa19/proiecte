import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;

public class Biblioteca extends JFrame{
	
	JButton imprumut, inapoiere;
	JLabel l1;
	JPanel p1, p2;
	
	public Biblioteca() {
		super("Biblioteca");
		
		AscultatorBiblioteca ab = new AscultatorBiblioteca();
		
		ImageIcon icon = new ImageIcon("icon.png");
		this.setIconImage(icon.getImage());
		
		l1 = new JLabel(new ImageIcon("bi.jpg"));	
		
		p2 = new JPanel();
		p2.add(l1);
		
		imprumut = new JButton("Imprumut");
		imprumut.addActionListener(ab);
		imprumut.setFocusable(false);
		imprumut.setFont(new Font("Times New Roman", Font.BOLD, 18));
		imprumut.setPreferredSize(new Dimension(200,40));
		
		
		inapoiere = new JButton("Inapoiere");
		inapoiere.addActionListener(ab);
		inapoiere.setFocusable(false);
		inapoiere.setFont(new Font("Times New Roman", Font.BOLD, 18));
		inapoiere.setPreferredSize(new Dimension(200,40));
		
		p1 = new JPanel();
		p1.add(imprumut);
		p1.add(inapoiere);
		
		p1.setBackground(new Color(243,197,197, 90));
		p2.setBackground(new Color(243,197,197, 90));
		
		add(p2, BorderLayout.NORTH);
		add(p1);
		
		addWindowListener(ab);   
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	class AscultatorBiblioteca extends WindowAdapter implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == imprumut) {
				new ImprumutFrame();
			}
			else {
				new InapoiereMeniu();
			}	
		}
		
		@Override
		public void windowClosing(WindowEvent e) {
			ColectieImprumuturi ci = ColectieImprumuturi.getInstanta();
			ci.salveazaImprumut();	
		}
		
		public void windowOpened(WindowEvent e) {    
		    File fisier = new File("imprumut.txt");
		    if(fisier.exists()) {
		    	ColectieImprumuturi ci = ColectieImprumuturi.getInstanta();
		    	ci.citesteFisier();
		    }
		    else
		    {
		    	ColectieImprumuturi ci = ColectieImprumuturi.getInstanta();
		    }
		}    
	}
}
