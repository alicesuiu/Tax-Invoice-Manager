import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Vector;

public class Gestiune {
	
	private static Gestiune instance = null;
	ArrayList<Produs> produse;
	ArrayList<Magazin> magazine;
	TreeMap<String, TreeMap<String, Integer>> taxe;
	
	private Gestiune(String produseFile, String taxeFile, String facturiFile) {
		updateFiles(produseFile, taxeFile, facturiFile);
	}
	
	public static Gestiune getInstance() {
		Load_Create_Files object = new Load_Create_Files("Incarcare fisiere");
		if(instance == null)
			instance = new Gestiune(object.produseFile.getName(), object.taxeFile.getName(), object.facturiFile.getName());
		return instance;
	}
	
	public static Gestiune getInstance(String produseFile, String taxeFile, String facturiFile) {
		if(instance == null)
			instance = new Gestiune(produseFile, taxeFile, facturiFile);
		return instance;
	}
	
	public void updateFiles(String produseFile, String taxeFile, String facturiFile) {
		citireProduse(produseFile);
		citireTaxe(taxeFile);
		citireMagazine(facturiFile);
	}
	
	void citireProduse(String fisier) {
		produse = new ArrayList<>();
		File f1 = null;
		int i;
		try {
			f1 = new File(fisier);
			Scanner s1 = new Scanner(f1);
			Scanner s1Line = new Scanner(s1.nextLine());
			Vector<String> tari = new Vector<>();
			s1Line.next();
			s1Line.next();
			while(s1Line.hasNext())
				tari.add(s1Line.next());
			while(s1.hasNextLine()) {
				Scanner sLine = new Scanner(s1.nextLine());
				String denumire = sLine.next();
				String categorie = sLine.next();
				for(i = 0; i < tari.size(); i++) {
					String taraOrigine = tari.get(i);
					double pret = sLine.nextDouble();
					if (pret > 0) {
						Produs p = new Produs(denumire, categorie, taraOrigine, pret);
						produse.add(p);
					}
				}
			}
			s1Line.close();
			s1.close();
		}
		catch(IOException e) {
			System.out.println("Eroare la citire");
		}
	}
	void citireTaxe(String fisier) {
		taxe = new TreeMap<>();
		File f1 = null;
		int i;
		try {
			f1 = new File(fisier);
			Scanner s1 = new Scanner(f1);
			Scanner s1Line = new Scanner(s1.nextLine());
			s1Line.next();
			Vector<String> tari = new Vector<>();
			while(s1Line.hasNext())
				tari.add(s1Line.next());
			while(s1.hasNextLine()) {
				Scanner sLine = new Scanner(s1.nextLine());
				String categorie = sLine.next();
				for(i = 0; i < tari.size(); i++) {
					String tara = tari.get(i);
					if(taxe.containsKey(tara)) {
						TreeMap<String, Integer> t = taxe.get(tara);
						if(t.containsKey(categorie)) {
							Integer procent = sLine.nextInt();
							t.put(categorie, procent);
						}
						else {
							Integer procent = sLine.nextInt();
							t.put(categorie, procent);
						}
					}
					else {
						TreeMap<String, Integer> t = new TreeMap<>();
						Integer procent = sLine.nextInt();
						t.put(categorie, procent);
						taxe.put(tara, t);
					}
				}
			}
			s1Line.close();
			s1.close();
		}
		catch(IOException e) {
			System.out.println("Eroare la citire");
		}
}	
	void citireMagazine(String fisier) {
		magazine = new ArrayList<>();
		File f1 = null;
		try {
			f1 = new File(fisier);
			Scanner s1 = new Scanner(f1);
			Magazin m = null;
			int i;
			while(s1.hasNextLine()) {
				String str = s1.nextLine();
				if(str.startsWith("Magazin")) {
					String[] string_magazin = str.split(":");
					String type = string_magazin[1];
					m = MarketFactory.createMagazin(type);
					m.nume = string_magazin[2];
					magazine.add(m);
					s1.nextLine();
				}
				else if(str.startsWith("Factura")) {
					Factura f = new Factura(str);
					s1.nextLine();
					String line = null;
					while(s1.hasNextLine() == true && (line = s1.nextLine()).isEmpty() == false) {
						String[] string_produs = line.split(" ");
						String denumire_produs = string_produs[0];
						String tara_produs = string_produs[1];
						int cantitate_produs = Integer.parseInt(string_produs[2]);
						for(i = 0; i < produse.size(); i++) {
							if(denumire_produs.equals(produse.get(i).getDenumire()))
								if(tara_produs.equals(produse.get(i).getTaraOrigine())) {
									TreeMap<String, Integer> t = taxe.get(produse.get(i).getTaraOrigine());
									Integer taxa_produs = t.get(produse.get(i).getCategorie());
									ProdusComandat pc = new ProdusComandat(produse.get(i), taxa_produs, cantitate_produs);
									f.v.add(pc);
									break;
								}
						}
					}
					m.vect.add(f);
				}
			}
			s1.close();
		}
		catch(IOException e) {
			System.out.println("Eroare la citire");
		}
		catch(NoSuchElementException e) {
			System.out.println("Eroare la citire");
		}
	}

	@Override
	public String toString() {
		return produse.toString() + "\n" + magazine.toString() + "\n" + taxe.toString();
	}
}
