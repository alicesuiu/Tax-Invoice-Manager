public class MiniMarket extends Magazin {
	private String tip = "MiniMarket";
	public double calculScutiriTaxe() {
		int i, j;
		double reducere = 0, depasire;
		for(i = 0; i < vect.size(); i++) {
			Factura f = (Factura) vect.get(i);
			for(j = 0; j < f.v.size(); j++) {
				String tara = f.v.get(j).getProdus().getTaraOrigine();
				depasire = 0.5 * getTotalCuTaxe();
				if(getTotalTaraCuTaxe(tara) > depasire) {
					// depaseste 50%
					reducere = 0.1;
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
