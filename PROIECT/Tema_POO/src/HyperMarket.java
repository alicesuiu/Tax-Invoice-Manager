public class HyperMarket extends Magazin {
	private String tip = "HyperMarket";
	public double calculScutiriTaxe() {
		int i;
		double reducere = 0, depasire;
		for(i = 0; i < vect.size(); i++) {
			Factura f = (Factura) vect.get(i);
			depasire = 0.1 * getTotalCuTaxe();
			if(f.getTotalCuTaxe() > depasire) {
					// depaseste 10%
					reducere = 0.01;
					break;
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
