import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class GenerateOutput {
	ArrayList<Magazin> mini_market = new ArrayList<>();
	ArrayList<Magazin> medium_market = new ArrayList<>();
	ArrayList<Magazin> hyper_market = new ArrayList<>();
	
	void afisare(ArrayList<Magazin> market, String tip, Gestiune temp, String fisier) {
		FileWriter file = null;
		BufferedWriter bw = null;
		DecimalFormat df = new DecimalFormat("#.####");
		df.setRoundingMode(RoundingMode.CEILING);
		try {
			file = new FileWriter(fisier, true);
			bw = new BufferedWriter(file);
			bw.write(tip);
			bw.newLine();
			for(Magazin m : market) {
				bw.write(m.nume);
				bw.newLine();
				bw.newLine();
				String total = "Total ";
				total = total + df.format(m.getTotalFaraTaxe()) + " " + df.format(m.getTotalCuTaxe()) + " " + df.format(m.getTotalCuTaxeScutite());
				bw.write(total);
				bw.newLine();
				bw.newLine();
				bw.write("Tara");
				bw.newLine();
				for(Map.Entry<String, TreeMap<String, Integer>> entry : temp.taxe.entrySet()) {
					String country_line = entry.getKey() + " ";
					if(m.getTotalTaraFaraTaxe(entry.getKey()) == 0) {
						country_line = country_line + 0;
						bw.write(country_line);
						bw.newLine();
					}
					else {
						country_line = country_line + df.format(m.getTotalTaraFaraTaxe(entry.getKey())) + " " + df.format(m.getTotalTaraCuTaxe(entry.getKey())) + " " + df.format(m.getTotalTaraCuTaxeScutite(entry.getKey()));
						bw.write(country_line);
						bw.newLine();
					}
				}
				bw.newLine();
				Collections.sort(m.vect);
				int i;
				for(i = 0; i < m.vect.size(); i++) {
					Factura f = m.vect.get(i);
					bw.write(f.denumire);
					bw.newLine();
					bw.newLine();
					total = "Total " + df.format(f.getTotalFaraTaxe()) + " " + df.format(f.getTotalCuTaxe());
					bw.write(total);
					bw.newLine();
					bw.newLine();
					bw.write("Tara");
					bw.newLine();
					for(Map.Entry<String, TreeMap<String, Integer>> entry : temp.taxe.entrySet()) {
						String country_line = entry.getKey() + " ";
						if(f.getTotalTaraFaraTaxe(entry.getKey()) == 0) {
							country_line = country_line + 0;
							bw.write(country_line);
							bw.newLine();
						}
						else {
							country_line = country_line + df.format(f.getTotalTaraFaraTaxe(entry.getKey())) + " " + df.format(f.getTotalTaraCuTaxe(entry.getKey()));
							bw.write(country_line);
							bw.newLine();
						}
					}
					bw.newLine();
				}
			}
			bw.close();
			file.close();
		}
		catch(IOException e) {
			System.out.println("Eroare la citire");
		}
	}
	
	public void createLists(Gestiune gestiune, String fisier) {
		FileWriter file = null;
		BufferedWriter bw = null;
		try {
			file = new FileWriter(fisier, false);
			bw = new BufferedWriter(file);
			bw.write("");
			bw.close();
			file.close();
		}
		catch(IOException e) {
			System.out.println("Eroare la citire");
		}
		finally {
			for(Magazin m : gestiune.magazine) {
				if(m.getTip().equals("MiniMarket"))
					mini_market.add(m);
				else if(m.getTip().equals("MediumMarket"))
					medium_market.add(m);
				else
					hyper_market.add(m);
			}
			Collections.sort(mini_market);
			Collections.sort(medium_market);
			Collections.sort(hyper_market);
		}
	}
}
