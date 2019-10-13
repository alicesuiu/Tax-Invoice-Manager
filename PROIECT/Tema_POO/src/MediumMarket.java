public class MediumMarket extends Magazin {
	private String tip = "MediumMarket";
	public double getTotalCategorieCuTaxe(String categorie) {
		int i, j;
		double suma = 0, pret;
		for(i = 0; i < vect.size(); i++) {
			Factura f = (Factura) vect.get(i);
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
	
	public double calculScutiriTaxe() {
		int i, j;
		double reducere = 0, depasire;
		for(i = 0; i < vect.size(); i++) {
			Factura f = (Factura) vect.get(i);
			for(j = 0; j < f.v.size(); j++) {
				String categorie = f.v.get(j).getProdus().getCategorie();
				depasire = 0.5 * getTotalCuTaxe();
				if(getTotalCategorieCuTaxe(categorie) > depasire) {
					// depaseste 50%
					reducere = 0.05;
					break;
				}		
			}
		}
		return reducere;
	}
	
	public int compareTo(Magazin m) {
		return (int) getTotalFaraTaxe() - (int) m.getTotalFaraTaxe();
	}
	
	public String getTip() {
		return tip;
	}
}
