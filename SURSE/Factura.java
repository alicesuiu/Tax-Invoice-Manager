import java.util.Vector;

public class Factura implements Comparable<Factura> {
	String denumire;
	Vector<ProdusComandat> v;
	
	public Factura(String denumire) {
		this.denumire = denumire;
		v = new Vector<>();
	}
	
	public double getTotalFaraTaxe() {
		int i;
		double suma = 0;
		for(i = 0; i < v.size(); i++) {
			ProdusComandat pc = (ProdusComandat) v.get(i);
			suma = suma + pc.getProdus().getPret() * pc.getCantitate();
		}
		return suma;
	}
	
	public double getTaxe() {
		int i;
		double suma = 0;
		for(i = 0; i < v.size(); i++) {
			ProdusComandat pc = (ProdusComandat) v.get(i);
			suma = suma + pc.getTaxa() * pc.getProdus().getPret() * pc.getCantitate() / 100;
		}
		return suma;
	}
	
	public double getTotalCuTaxe() {
		return getTotalFaraTaxe() + getTaxe();
	}
	
	public double getTotalTaraFaraTaxe(String tara) {
		int i;
		double suma = 0;
		for(i = 0; i < v.size(); i++) {
			if(tara.equals(v.get(i).getProdus().getTaraOrigine())) {
				ProdusComandat pc = (ProdusComandat) v.get(i);
				suma = suma + pc.getProdus().getPret() * pc.getCantitate();
			}
		}
		return suma;
	}
	
	public double getTaxeTara(String tara) {
		int i;
		double suma = 0;
		for(i = 0; i < v.size(); i++) {
			if(tara.equals(v.get(i).getProdus().getTaraOrigine())) {
				ProdusComandat pc = (ProdusComandat) v.get(i);
				suma = suma + pc.getTaxa() * pc.getProdus().getPret() * pc.getCantitate() / 100;
			}
		}
		return suma;
	}
	
	public double getTotalTaraCuTaxe(String tara) {
		return getTotalTaraFaraTaxe(tara) + getTaxeTara(tara);
	}
	
	public String toString() {
		String s = denumire + "\n";
		int i;
		for(i = 0; i < v.size(); i++)
			s = s + v.get(i) + ",";
		s = s + "\n";
		return s;
	}
	
	public int compareTo(Factura f) {
		return (int) getTotalCuTaxe() - (int) f.getTotalCuTaxe();
		
	}
}
