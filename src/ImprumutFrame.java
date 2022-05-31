import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ImprumutFrame extends JFrame {

	JLabel nume, prenume, CNP, alegere, data, eroare;
	JTextField numeT, prenumeT, CNPt, dataT;
	JTextArea cartiAlese;
	JPanel p1, p2, p3, p4, p5;
	JScrollPane sp;
	JComboBox<String> carti;
	JButton imprumuta;
	
	public ImprumutFrame() {
		super("Imprumut");
		
		ImageIcon icon = new ImageIcon("icon.png");
		this.setIconImage(icon.getImage());
		
		AscultatorImprumut ai = new AscultatorImprumut();
		
		nume = new JLabel("Nume: *");
		nume.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		prenume = new JLabel("Prenume: *");
		prenume.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		CNP = new JLabel("Carte identitate: CNP *");
		CNP.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		numeT = new JTextField(10);
		numeT.addActionListener(ai);
		numeT.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		prenumeT = new JTextField(10);
		prenumeT.addActionListener(ai);
		prenumeT.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		CNPt = new JTextField(13);
		CNPt.addActionListener(ai);
		CNPt.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		p1 = new JPanel();
		p1.setLayout(new GridLayout(3,2, 0, 15));
		p1.add(nume); p1.add(numeT);
		p1.add(prenume); p1.add(prenumeT);
		p1.add(CNP); p1.add(CNPt);
		
		alegere = new JLabel("Alege carti: *");
		alegere.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		carti = new JComboBox<>();
		carti.addActionListener(ai);
		carti.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		((JLabel)carti.getRenderer()).setHorizontalAlignment(SwingConstants.LEFT); // ------
		carti.setPreferredSize(new Dimension(300,35));
		
		TreeSet<Carte> cartiv = new TreeSet<>();
		cartiv = cartiValide();
		Iterator<Carte> it = cartiv.iterator();
		while(it.hasNext()) {
			Carte el = it.next();
			carti.addItem(el.getTitlu());
		}
		
		cartiAlese = new JTextArea(10, 20);
		sp = new JScrollPane(cartiAlese, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		cartiAlese.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		cartiAlese.setEditable(false);
		
		p2 = new JPanel();
		p2.add(alegere);
		p2.add(Box.createHorizontalStrut(20));
		p2.add(carti);
		p2.add(Box.createHorizontalStrut(10));
		p2.add(sp);
		
		data = new JLabel("Data returnare: *");
		data.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		imprumuta = new JButton("Imprumuta");
		imprumuta.setFocusable(false);
		imprumuta.setAlignmentX(Component.CENTER_ALIGNMENT);
		imprumuta.addActionListener(ai);
		imprumuta.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		dataT = new JTextField(15);
		dataT.addFocusListener(ai);
		dataT.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		dataT.setForeground(Color.LIGHT_GRAY);
		dataT.setText("yyyy/mm/dd"); // adaugi cand text = "" culoarea  = negru
		dataT.setHorizontalAlignment(SwingConstants.CENTER);
		
		p3 = new JPanel();
		p3.setLayout(new FlowLayout(FlowLayout.LEFT));
		p3.add(data);
		p3.add(Box.createHorizontalStrut(130)); // ------
		p3.add(dataT);
		
		eroare = new JLabel("Nu ati completat toate campurile obligatorii / corect campurile obligatorii");
		eroare.setAlignmentX(Component.CENTER_ALIGNMENT);
		eroare.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		eroare.setForeground(Color.red);
		eroare.setVisible(false);
		
		p4 = new JPanel();
		p4.setLayout(new BoxLayout(p4, BoxLayout.Y_AXIS));
		p4.add(imprumuta);
		p4.add(eroare);
		
		p5 = new JPanel();
		p5.setLayout(new GridLayout(2,1));
		p5.add(p3);
		p5.add(p4);
		
		p1.setBackground(new Color(191,146,112, 50));
		p2.setBackground(new Color(191,146,112, 50));
		p3.setBackground(new Color(191,146,112, 50));
		p4.setBackground(new Color(191,146,112, 50));
		
		add(p1, BorderLayout.NORTH);
		add(p2, BorderLayout.CENTER);
		add(p5, BorderLayout.SOUTH);
		
		
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		// Distanta dintre cele doua panel-urile marita
		
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
	
	
	public static TreeSet<Carte> cartiValide() {
		ColectieCarti c = new ColectieCarti();
		c.citesteCarti();
		TreeSet<Carte> r = new TreeSet<>();
		TreeSet<Carte> aux = c.getCarti();
		Iterator<Carte> it = aux.iterator();
		while(it.hasNext()) {
			Carte el = it.next();
			if(el.getStare() != true)
				r.add(el);
		}
		return r;
	}
	
	public static TreeSet<Carte> cartiFisier() {
		ColectieCarti c = new ColectieCarti();
		c.citesteCarti();
		TreeSet<Carte> r = new TreeSet<>();
		TreeSet<Carte> aux = c.getCarti();
		Iterator<Carte> it = aux.iterator();
		while(it.hasNext()) {
			Carte el = it.next();
			r.add(el);
		}
		return r;
	}
	
	
	class AscultatorImprumut implements FocusListener, ActionListener{
		private ColectieCarti cartiSelectate;
		private TreeSet<Carte> cartiNeselectate;
		private int contor = 0;
		private boolean erori = true;
		
		AscultatorImprumut() {
			cartiSelectate = new ColectieCarti();
			cartiNeselectate = cartiFisier();
		}
		
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == imprumuta) {
				if(numeT.getText().equals("") || prenumeT.getText().equals("")) {
					eroare.setVisible(true);
					erori = false;
					pack();
				}
				else if(CNPt.getText().equals("") || CNPt.getText().length() != 13) {
					eroare.setVisible(true);
					erori = false;
					pack();
				} 
				else if(!doarCifre(CNPt.getText(), CNPt.getText().length())) {
					eroare.setVisible(true);
					erori = false;
					pack();
				}
				else if(cartiAlese.getText().equals("")) {
					eroare.setVisible(true);
					erori = false;
					pack();
				} 
				else if(dataT.getText().equals("yyyy/mm/dd") || dataT.getText().equals("")) {
					eroare.setVisible(true);
					erori = false;
					pack();
				}
				else if(!dataT.getText().matches("\\d{4}/\\d{2}/\\d{2}")) {
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
					ColectieCarti aux = new ColectieCarti();
					aux.setCarti(cartiSelectate.getCarti());
					
					cartiSelectate.setCarti(cartiNeselectate);
					cartiSelectate.salveazaCarti();
					
					ColectieImprumuturi ci = ColectieImprumuturi.getInstanta();
					
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
					Imprumut i = new Imprumut(numeT.getText(), prenumeT.getText(), CNPt.getText(), LocalDate.parse(dataT.getText(), formatter));
					i.setCarti(aux);
					
//					System.out.println(i.getInformatii());
					
					ci.adaugaImprumut(i);
				}
				
				if(erori) {
					ImprumutFrame.this.dispose();
				}
			}
			
			if(e.getSource() == carti) {
				contor++;
				String s = (String)carti.getSelectedItem();
				
				if(contor != 1) {
					
					if(contor > 6) {
						JLabel warning = new JLabel("Numarul de carti maxim este 5");
						warning.setForeground(new Color(145,16,7));
						warning.setFont(new Font("Times New Roman", Font.BOLD, 18));
						JOptionPane.showMessageDialog(null, warning , "Numar depasit de carti", JOptionPane.WARNING_MESSAGE);
					}
					else {
						Iterator<Carte> it = cartiNeselectate.iterator();
						while(it.hasNext()) {
							Carte el = it.next();
							if(el.getTitlu().equals(s)) {
								el.setStare(true);
								cartiSelectate.adaugaCarte(el, true);


								StringBuffer sb = new StringBuffer();
								sb.append(cartiAlese.getText());
								sb.append("Titlu: " + el.getTitlu() + "\nEditura: " + el.getEditura() + "\n\n");
								cartiAlese.setText(sb.toString());
								pack();

								carti.removeItem(el.getTitlu());
							}
						}
					}
					
				} 
			}
			
			
		}

		@Override
		public void focusGained(FocusEvent e) {
			if(e.getSource() == dataT) {
				if(dataT.getText().equals("yyyy/mm/dd")) 
					dataT.setText("");
				
				dataT.setForeground(Color.black);
			}
			
		}

		@Override
		public void focusLost(FocusEvent e) {
			if(e.getSource() == dataT) {
				if(dataT.getText().equals("")) {
					dataT.setForeground(Color.LIGHT_GRAY);
					dataT.setText("yyyy/mm/dd");
				}
			}
			
		}		
	}
}
