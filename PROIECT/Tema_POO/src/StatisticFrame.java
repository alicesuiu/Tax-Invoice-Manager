import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.Border;

public class StatisticFrame extends JFrame {
	Gestiune gestiune = Gestiune.getInstance("produse.txt", "taxe.txt", "facturi.txt");
	JEditorPane magazinField, facturaField, countryField, categoryField;
	DecimalFormat df = new DecimalFormat("#.####");
	Vector<String> country = new Vector<>();
	Vector<String> category = new Vector<>();
	Vector<Magazin> magazinCountry = new Vector<>();
	Vector<Magazin> magazinCategory = new Vector<>();
	
	public StatisticFrame(String titlu) {
		super(titlu);
		setLayout(new GridLayout(2, 2, 10, 10));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		Border raisedbevel, loweredbevel, compound;
		raisedbevel = BorderFactory.createRaisedBevelBorder();
		loweredbevel = BorderFactory.createLoweredBevelBorder();
		compound = BorderFactory.createCompoundBorder(raisedbevel, loweredbevel);
		
		Border redline = BorderFactory.createLineBorder(Color.red);
		compound = BorderFactory.createCompoundBorder(redline, compound);
		JPanel panel1 = new JPanel(new GridLayout(2, 1));
		panel1.setBorder(compound);
		JLabel magazinLabel = new JLabel("Magazinul cu cele mai mari vanzari");
		magazinLabel.setFont(new Font("Roboto", Font.BOLD, 18));
		magazinLabel.setHorizontalAlignment(JLabel.CENTER);
		panel1.add(magazinLabel);
		magazinField = new JEditorPane();
		magazinField.setEditable(false);
		magazinField.setText(topMagazin());
		JScrollPane scrollPane1 = new JScrollPane(magazinField);
		panel1.add(scrollPane1);
		add(panel1);
		
		Border greenline = BorderFactory.createLineBorder(Color.green);
		compound = BorderFactory.createCompoundBorder(raisedbevel, loweredbevel);
		compound = BorderFactory.createCompoundBorder(greenline, compound);
		JPanel panel2 = new JPanel(new GridBagLayout());
		GridBagConstraints c1 = new GridBagConstraints();
		panel2.setBorder(compound);
		JLabel taraLabel = new JLabel("Magazinul cu cele mai mari vanzari pentru fiecare tara");
		taraLabel.setHorizontalAlignment(JLabel.CENTER);
		taraLabel.setFont(new Font("Roboto", Font.BOLD, 18));
		c1.gridx = 0;
		c1.gridy = 0;
		c1.fill = GridBagConstraints.HORIZONTAL;
		c1.gridwidth = 3;
		c1.insets = new Insets(0, 0, 40, 0);
		panel2.add(taraLabel, c1);
		magazinCountry = topTariVanzari();
		countryField = new JEditorPane();
		countryField.setEditable(false);
		JScrollPane scrollPane2 = new JScrollPane(countryField);
		JComboBox<String> countryList = new JComboBox<>(country);
		countryList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox) e.getSource();
				String magazinInfo = "";
				df.setRoundingMode(RoundingMode.CEILING);
				int i = cb.getSelectedIndex();
				if(i >= 0) {
					Magazin m = magazinCountry.get(i);
					if(m == null)
						countryField.setText("");
					else {
						magazinInfo = "Nume magazin: " + m.nume + "\n" + "\n";
						//String countryname = (String) cb.getSelectedItem();
						//magazinInfo += "Tara: " + countryname + "\n" + "\n";
						magazinInfo += "Total fara taxe: " + df.format(m.getTotalFaraTaxe()) + "\n" + "\n";
						magazinInfo += "Total cu taxe: " + df.format(m.getTotalCuTaxe()) + "\n" + "\n";
						magazinInfo += "Total cu taxe scutite: " + df.format(m.getTotalCuTaxeScutite());
						countryField.setText(magazinInfo);
					}
				}
			}
		});
		countryList.setSelectedIndex(0);
		c1.gridx = 0;
		c1.gridy = 1;
		c1.fill = GridBagConstraints.HORIZONTAL;
		c1.gridwidth = 3;
		c1.ipady = 10;
		c1.insets = new Insets(0, 0, 30, 0);
		panel2.add(countryList, c1);
		c1.gridx = 0;
		c1.gridy = 2;
		c1.fill = GridBagConstraints.HORIZONTAL;
		c1.ipady = 100;
		c1.insets = new Insets(0, 0, 0, 0);
		panel2.add(scrollPane2, c1);
		add(panel2);
		
		Border yellowline = BorderFactory.createLineBorder(Color.YELLOW);
		compound = BorderFactory.createCompoundBorder(raisedbevel, loweredbevel);
		compound = BorderFactory.createCompoundBorder(yellowline, compound);
		JPanel panel3 = new JPanel(new GridBagLayout());
		GridBagConstraints c2 = new GridBagConstraints();
		panel3.setBorder(compound);
		JLabel categorieLabel = new JLabel("Magazinul cu cele mai mari vanzari pentru fiecare categorie");
		categorieLabel.setFont(new Font("Roboto", Font.BOLD, 18));
		c2.gridx = 0;
		c2.gridy = 0;
		c2.fill = GridBagConstraints.HORIZONTAL;
		c2.gridwidth = 3;
		c2.insets = new Insets(0, 0, 40, 0);
		panel3.add(categorieLabel, c2);
		magazinCategory  = topCategoriiVanzari();
		categoryField = new JEditorPane();
		categoryField.setEditable(false);
		JScrollPane scrollPane3 = new JScrollPane(categoryField);
		JComboBox<String> categoryList = new JComboBox<>(category);
		categoryList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox) e.getSource();
				String magazinInfo = "";
				df.setRoundingMode(RoundingMode.CEILING);
				int i = cb.getSelectedIndex();
				if(i >= 0) {
					Magazin m = magazinCategory.get(i);
					magazinInfo = "Nume magazin: " + m.nume + "\n" + "\n";
					//String categoryname = (String) cb.getSelectedItem();
					//magazinInfo += "Categorie: " + categoryname + "\n" + "\n";
					magazinInfo += "Total fara taxe: " + df.format(m.getTotalFaraTaxe()) + "\n" + "\n";
					magazinInfo += "Total cu taxe: " + df.format(m.getTotalCuTaxe()) + "\n" + "\n";
					magazinInfo += "Total cu taxe scutite: " + df.format(m.getTotalCuTaxeScutite());
					categoryField.setText(magazinInfo);
				}	
			}
		});
		categoryList.setSelectedIndex(0);
		c2.gridx = 0;
		c2.gridy = 1;
		c2.fill = GridBagConstraints.HORIZONTAL;
		c2.gridwidth = 3;
		c2.ipady = 10;
		c2.insets = new Insets(0, 0, 30, 0);
		panel3.add(categoryList, c2);
		c2.gridx = 0;
		c2.gridy = 2;
		c2.fill = GridBagConstraints.HORIZONTAL;
		c2.ipady = 70;
		c2.insets = new Insets(0, 0, 0, 0);
		panel3.add(scrollPane3, c2);
		add(panel3);
		
		Border blueline = BorderFactory.createLineBorder(Color.blue);
		compound = BorderFactory.createCompoundBorder(raisedbevel, loweredbevel);
		compound = BorderFactory.createCompoundBorder(blueline, compound);
		JPanel panel4 = new JPanel(new GridLayout(3, 1));
		panel4.setBorder(compound);
		JLabel facturaLabel = new JLabel("Factura cu suma totala cea mai mare");
		facturaLabel.setHorizontalAlignment(JLabel.CENTER);
		facturaLabel.setFont(new Font("Roboto", Font.BOLD, 18));
		panel4.add(facturaLabel);
		facturaField = new JEditorPane();
		facturaField.setEditable(false);
		facturaField.setText(topFactura());
		JScrollPane scrollPane4 = new JScrollPane(facturaField);
		panel4.add(scrollPane4);
		add(panel4);
		
		getContentPane().setBackground(Color.decode("#81d4fa"));
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
	}
	public String topMagazin() {
		Magazin topVanzari = gestiune.magazine.get(0);
		int i; 
		df.setRoundingMode(RoundingMode.CEILING);
		for(i = 1; i < gestiune.magazine.size(); i++)
			if(gestiune.magazine.get(i).getTotalCuTaxe() > topVanzari.getTotalCuTaxe())
				topVanzari = gestiune.magazine.get(i);
		String magazinInfo = "";
		magazinInfo += "Nume: " + topVanzari.nume + "\n";
		magazinInfo += "\n" + "Total fara taxe: " + df.format(topVanzari.getTotalFaraTaxe()) + "\n";
		magazinInfo += "\n" + "Total cu taxe: " + df.format(topVanzari.getTotalCuTaxe()) + "\n";
		magazinInfo += "\n" + "Total cu taxe scutite: " + df.format(topVanzari.getTotalCuTaxeScutite()) + "\n";
		return magazinInfo;		
	}
	
	public Vector<Magazin> topTariVanzari() {
		Vector<Magazin> topTaraVanzari = new Vector<>();
		Magazin m;
		int i, j;
		for(i = 0; i < gestiune.produse.size(); i++)
			if(country.contains(gestiune.produse.get(i).getTaraOrigine()) != true)
				country.add(gestiune.produse.get(i).getTaraOrigine());
		double s;
		for(j = 0; j < country.size(); j++) {
			s = 0;
			m = null;
			for(i = 0; i < gestiune.magazine.size(); i++)
				if(s < gestiune.magazine.get(i).getTotalTaraCuTaxe(country.get(j))) {
					s = gestiune.magazine.get(i).getTotalTaraCuTaxe(country.get(j));
					m = gestiune.magazine.get(i);
				}
			topTaraVanzari.add(m);
		}
		return topTaraVanzari;	
	}
	
	public double getTotalCategorieCuTaxe(Magazin magazin, String categorie) {
		int i, j;
		double suma = 0, pret;
		for(i = 0; i < magazin.vect.size(); i++) {
			Factura f = (Factura) magazin.vect.get(i);
			for(j = 0; j < f.v.size(); j++) {
				if(categorie.equals(f.v.get(j).getProdus().getCategorie())) {
					ProdusComandat p = (ProdusComandat) f.v.get(j);
					pret = p.getProdus().getPret() + p.getProdus().getPret() * p.getTaxa() / 100;
					suma = suma + pret * p.getCantitate();
				}
			}
		}
		return suma;
	}
	
	/*public double getTotalCategorieFaraTaxe(Magazin magazin, String categorie) {
		int i, j;
		double suma = 0;
		for(i = 0; i < magazin.vect.size(); i++) {
			Factura f = (Factura) magazin.vect.get(i);
			for(j = 0; j < f.v.size(); j++) {
				if(categorie.equals(f.v.get(j).getProdus().getCategorie())) {
					ProdusComandat p = (ProdusComandat) f.v.get(j);
					suma = suma + p.getProdus().getPret() * p.getCantitate();
				}
			}
		}
		return suma;
	}
	
	public double getTotalCategorieCuTaxeScutite(Magazin magazin, String categorie) {
		return getTotalCategorieCuTaxe(magazin, categorie) - magazin.calculScutiriTaxe() * getTotalCategorieCuTaxe(magazin, categorie);
	}*/
	
	public Vector<Magazin> topCategoriiVanzari() {
		Vector<Magazin> topCategorieVanzari = new Vector<>();
		Magazin m;
		int i, j;
		for(i = 0; i < gestiune.produse.size(); i++)
			if(category.contains(gestiune.produse.get(i).getCategorie()) != true)
				category.add(gestiune.produse.get(i).getCategorie());
		double s;
		for(j = 0; j < category.size(); j++) {
			s = getTotalCategorieCuTaxe(gestiune.magazine.get(0), category.get(j));
			m = gestiune.magazine.get(0);
			for(i = 1; i < gestiune.magazine.size(); i++) {
				if(getTotalCategorieCuTaxe(gestiune.magazine.get(i), category.get(j)) > s) {
					s = getTotalCategorieCuTaxe(gestiune.magazine.get(i), category.get(j));
					m = gestiune.magazine.get(i);
				}
			}
			topCategorieVanzari.add(m);
		}
		return topCategorieVanzari;
	}
	
	public String topFactura() {
		Factura theBest = gestiune.magazine.get(0).vect.get(0);
		int i, j;
		df.setRoundingMode(RoundingMode.CEILING);
		for(i = 0; i < gestiune.magazine.size(); i++) {
			Magazin m = gestiune.magazine.get(i);
			for(j = 0; j < m.vect.size(); j++) {
				Factura f = m.vect.get(j);
				if(f.getTotalFaraTaxe() > theBest.getTotalFaraTaxe())
					theBest = f;
			}	
		}
		String facturaInfo = "";
		facturaInfo += "Denumire: " + theBest.denumire + "\n" + "\n";
		facturaInfo += "Total fara taxe: " + df.format(theBest.getTotalFaraTaxe()) + "\n" + "\n";
		facturaInfo += "Total cu taxe: " + df.format(theBest.getTotalCuTaxe());
		return facturaInfo;
	}
}
