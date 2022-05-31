import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;
import java.util.TreeSet;

public class InapoiereMeniu extends JFrame{
	
	JPanel p1, p2, p3;
	JLabel cnp, eroare;
	JTextField cnpT;
	JButton cauta;
	
	InapoiereMeniu() {
		super("Returnare");
		
		AscultatorReturnare ar = new AscultatorReturnare();
		
		ImageIcon icon = new ImageIcon("icon.png");
		this.setIconImage(icon.getImage());
		
		cnp = new JLabel("CNP: *");
		cnp.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		cnpT = new JTextField(13);
		cnpT.addActionListener(ar);
		cnpT.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		p1 = new JPanel();
		p1.add(cnp);
		p1.add(Box.createHorizontalStrut(10));
		p1.add(cnpT);
		
		cauta = new JButton("Cauta");
		cauta.addActionListener(ar);
		cauta.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		cauta.setFocusable(false);
		
		p2 = new JPanel();
		p2.setLayout(new FlowLayout(FlowLayout.CENTER));
		p2.add(cauta);
		
		eroare = new JLabel("Nu ati completat corect campul obligatoriu");
		eroare.setAlignmentX(Component.CENTER_ALIGNMENT);
		eroare.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		eroare.setForeground(Color.red);
		eroare.setVisible(false);
		
		p3 = new JPanel();
		p3.add(eroare);
		
		p1.setBackground(new Color(191,146,112, 50));
		p2.setBackground(new Color(191,146,112, 50));
		p3.setBackground(new Color(191,146,112, 50));
	
		add(p1, BorderLayout.NORTH);
		add(p2);
		add(p3, BorderLayout.SOUTH);
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public static boolean doarCifre(String str, int n)
    {
		boolean v = true;
        for (int i = 0; i < n; i++) {
            if (!Character.isDigit(str.charAt(i))) {
            	v = false;
            }
        }
        return v;
    }
	
	class AscultatorReturnare implements ActionListener{
		private boolean erori = true;

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == cauta) {
				if(cnpT.getText().equals("") || cnpT.getText().length() != 13) {
					eroare.setVisible(true);
					erori = false;
					pack();
				} 
				else if(!doarCifre(cnpT.getText(), cnpT.getText().length())) {
					eroare.setVisible(true);
					erori = false;
					pack();
				}
				else {
					eroare.setVisible(false);
					erori = true;
					pack();
				}
				
				if(erori) {
					ColectieImprumuturi ci = ColectieImprumuturi.getInstanta();
					TreeSet<Imprumut> imprumuturilePersoanei = ci.cautaImprumut(cnpT.getText());
					String cnp = cnpT.getText();
					Iterator<Imprumut> it = imprumuturilePersoanei.iterator();
					
					if(imprumuturilePersoanei.size() == 0) {
						JLabel text = new JLabel("CNP inexistent");
						text.setForeground(new Color(145,16,7));
						text.setFont(new Font("Times New Roman", Font.BOLD, 18));
						JOptionPane.showMessageDialog(null, text, "CNP Invalid", JOptionPane.ERROR_MESSAGE);
					}
					else /// ------------------------------------------------------------
						new ListaImprumuturi(cnp);
					
				}
			}
			
		}
		
	}
}
