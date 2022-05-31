import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

public class RestituireImprumut extends JFrame {
	
	private Imprumut im;
	private ArrayList<JCheckBox> cartiAduse = new ArrayList<>();
	
	JLabel lNume, lPrenume, lCNP, textCarti, dataAducerii, labelSumaPlata, eroare, ldata;
	JTextField tNume, tPrenume, tCNP, dataT, textSumaPlata, tdata;
	JPanel p1, p2, p12, p3, p4, p5, pPlata, pOk, pData;
	JButton plata, bOK;

	RestituireImprumut(Imprumut im) {
		super("Resituire Imprumut");
		this.im = im;
		
		ImageIcon icon = new ImageIcon("icon.png");
		this.setIconImage(icon.getImage());
		
		ldata = new JLabel("Data returnare imprumut: ");
		ldata.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		tdata = new JTextField();
		tdata.setText(String.valueOf(im.getDataReturnare()));
		tdata.setEditable(false);
		tdata.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		pData = new JPanel();
		pData.add(ldata);
		pData.add(tdata);
		
		AscultatorResituireImprumut ar = new AscultatorResituireImprumut();
		
		lNume = new JLabel("Nume: ");
		lNume.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		lPrenume = new JLabel ("Prenume: ");
		lPrenume.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		lCNP = new JLabel ("Carte de identitate CNP: ");
		lCNP.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		tNume = new JTextField(10);
		tNume.setText(im.getPersoana().getNume());
		tNume.setEditable(false);
		tNume.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		tPrenume = new JTextField(10);
		tPrenume.setText(im.getPersoana().getPrenume());
		tPrenume.setEditable(false);
		tPrenume.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		tCNP = new JTextField(13);
		tCNP.setText(im.getPersoana().getCNP());
		tCNP.setEditable(false);
		tCNP.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		p1 = new JPanel();
		p1.setLayout(new GridLayout(3,2));
		p1.add(lNume); p1.add(tNume);
		p1.add(lPrenume); p1.add(tPrenume);
		p1.add(lCNP); p1.add(tCNP);
		
		
		TreeSet<Carte> c = im.getCarti().getCarti();
		Iterator<Carte> it = c.iterator();
		while(it.hasNext()) {
			Carte aux = it.next();
			JCheckBox b = new JCheckBox(aux.getTitlu());
			b.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			b.setFocusable(false);
			b.addActionListener(ar);
			cartiAduse.add(b);
		}
		
		
		textCarti = new JLabel("Carti aduse: ");
		textCarti.setFont(new Font("Times New Roman", Font.PLAIN, 21));
		
		p2 = new JPanel();
		p2.setLayout(new BoxLayout(p2, BoxLayout.Y_AXIS));
		p2.add(textCarti);
		
		for(int i = 0 ; i < cartiAduse.size(); i++) {
			p2.add(cartiAduse.get(i));
		}
		
		p12 = new JPanel();
		p12.setLayout(new GridLayout(3,1));
		p12.add(p1);
		p12.add(p2);
		
		dataT = new JTextField(15);
		dataT.addFocusListener(ar);
		dataT.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		dataT.setForeground(Color.LIGHT_GRAY);
		dataT.setText("yyyy/mm/dd"); // adaugi cand text = "" culoarea  = negru
		dataT.setHorizontalAlignment(SwingConstants.CENTER);
		
		dataAducerii = new JLabel("Data aducerii: ");
		dataAducerii.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		p3 = new JPanel();
		p3.setLayout(new FlowLayout(FlowLayout.CENTER));
		p3.add(dataAducerii);
		p3.add(dataT);
		
		plata = new JButton("Plata");
		plata.setAlignmentX(CENTER_ALIGNMENT);
		plata.setFocusable(false);
		plata.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		plata.addActionListener(ar);
		
		pPlata = new JPanel();
		pPlata.setLayout(new BoxLayout(pPlata, BoxLayout.Y_AXIS));
		pPlata.add(pData); // ---------------------------------------------------------------->
		pPlata.add(p3);
		pPlata.add(plata);
		
		p12.add(pPlata);
		
		labelSumaPlata = new JLabel("Suma de platit: ");
		labelSumaPlata.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		labelSumaPlata.setVisible(false);
		
		textSumaPlata = new JTextField();
		textSumaPlata.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textSumaPlata.setEditable(false);
		textSumaPlata.addActionListener(ar);
		textSumaPlata.setVisible(false);
		
		eroare = new JLabel("Nu ati completat data corect");
		eroare.setAlignmentX(Component.CENTER_ALIGNMENT);
		eroare.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		eroare.setForeground(Color.red);
		eroare.setVisible(false);
		
		p5 = new JPanel();
		p5.add(labelSumaPlata);
		p5.add(textSumaPlata);
		
		p4 = new JPanel();
		p4.setLayout(new BoxLayout(p4, BoxLayout.Y_AXIS));
		p4.add(p5);
		p4.add(eroare);
		
		bOK = new JButton("OK");
		bOK.addActionListener(ar);
		bOK.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		bOK.setVisible(false);
		bOK.setFocusable(false);
		
		pOk = new JPanel();
		pOk.add(bOK);
		
//		p1.setBackground(new Color(191,146,112, 50));
//		p2.setBackground(new Color(191,146,112, 50));
//		p3.setBackground(new Color(191,146,112, 50));
//		pData.setBackground(new Color(191,146,112, 50));
//		pPlata.setBackground(new Color(191,146,112, 50));
//		p4.setBackground(new Color(191,146,112, 50));
//		p5.setBackground(new Color(191,146,112, 50));
//		pOk.setBackground(new Color(191,146,112, 50));
		
		add(p12, BorderLayout.NORTH);
		add(p4, BorderLayout.CENTER);
		add(pOk, BorderLayout.SOUTH);
		
		pack();	
		
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	class AscultatorResituireImprumut implements ActionListener, FocusListener{
		private boolean erori = true;
		private ArrayList<String> cartiSelectate;
		DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		
		AscultatorResituireImprumut() { 
			cartiSelectate = new ArrayList<>();
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == plata) {
				cartiSelectate = new ArrayList<>(); // ----------------------
				for(int i = 0 ; i < cartiAduse.size(); i++) {
					if(cartiAduse.get(i).isSelected()) {
						cartiSelectate.add(cartiAduse.get(i).getText());
					}
				}
				
				if(dataT.getText().equals("yyyy/mm/dd") || dataT.getText().equals("")) {
					eroare.setVisible(true);
					erori = false;
					pack();
				}
				else if(!dataT.getText().matches("\\d{4}/\\d{2}/\\d{2}")) {
					eroare.setVisible(true);
					erori = false;
					pack();
				}
				else if(ChronoUnit.DAYS.between(im.getDataReturnare(), LocalDate.parse(dataT.getText(),f)) < 0) {
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
					labelSumaPlata.setVisible(true);
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
					LocalDate l = LocalDate.parse(dataT.getText(), formatter);
					double p = im.deciziePlata(l, cartiSelectate.size());
					textSumaPlata.setText(String.valueOf(p));
					textSumaPlata.setVisible(true);
					
					bOK.setVisible(true);
					pack();
				}
				else {
					labelSumaPlata.setVisible(false);
					textSumaPlata.setVisible(false);
					bOK.setVisible(false);
					pack();
				}
			}
			
			if(e.getSource() == bOK) {
				im.salveazaArhiva();
				
				ColectieImprumuturi ci = ColectieImprumuturi.getInstanta();
				
//				ci.stergereImprumut(im.getPersoana().getCNP());
				ci.stergereImprumut(im);
				
				ColectieCarti c = new ColectieCarti();
				c.citesteCarti();
				TreeSet<Carte> carti = c.getCarti();
				Iterator<Carte> it = carti.iterator();
				while(it.hasNext()) {
					Carte carte = it.next();
					for(int i = 0 ; i < cartiSelectate.size(); i++) {
						if(carte.getTitlu().equals(cartiSelectate.get(i))) {
							carte.setStare(false);
						}
					}
				}
				c.salveazaCarti();
				
				RestituireImprumut.this.dispose();
				
				new ListaImprumuturi(im.getPersoana().getCNP());
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
