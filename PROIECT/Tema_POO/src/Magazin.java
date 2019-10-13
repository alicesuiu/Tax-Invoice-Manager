import java.util.Vector;

public abstract class Magazin implements IMagazin, Comparable<Magazin> {
	String nume;
	Vector<Factura> vect = new Vector<>();
	
	public double getTotalFaraTaxe() {
		int i;
		double suma = 0;
		for(i = 0; i < vect.size(); i++) {
			Factura f = (Factura) vect.get(i);
			suma = suma + f.getTotalFaraTaxe();
		}
		return suma;
	}
	
	public double getTotalCuTaxe() {
		int i;
		double suma = 0;
		for(i = 0; i < vect.size(); i++) {
			Factura f = (Factura) vect.get(i);
			suma = suma + f.getTotalCuTaxe();
		}
		return suma;
	}
	
	public double getTotalTaraFaraTaxe(String tara) {
		int i;
		double suma = 0;
		for(i = 0; i < vect.size(); i++) {
			Factura f = (Factura) vect.get(i);
			suma = suma + f.getTotalTaraFaraTaxe(tara);
		}
		return suma;
	}
	
	public double getTotalTaraCuTaxe(String tara) {
		int i;
		double suma = 0;
		for(i = 0; i < vect.size(); i++) {
			Factura f = (Factura) vect.get(i);
			suma = suma + f.getTotalTaraCuTaxe(tara);
		}
		return suma;
	}
	
	public double getTotalCuTaxeScutite() {
		return getTotalCuTaxe() - calculScutiriTaxe() * getTotalCuTaxe();
	}
	
	public double getTotalTaraCuTaxeScutite(String tara) {
		return getTotalTaraCuTaxe(tara) - calculScutiriTaxe() * getTotalTaraCuTaxe(tara);
	}
	
	public String toString() {
		String s = nume + "\n";
		int i;
		for(i = 0; i < vect.size(); i++)
			s = s + vect.get(i) + ",";
		s = s + "\n";
		return s;
	}
	
	public abstract double calculScutiriTaxe();
	public abstract String getTip();
	public abstract int compareTo(Magazin m);
}
