public class ProdusComandat {
	private Produs produs;
	private double taxa;
	private int cantitate;
	
	public ProdusComandat(Produs produs, double taxa, int cantitate) {
		this.produs = produs;
		this.taxa = taxa;
		this.cantitate = cantitate;
	}
	
	public Produs getProdus() {
		return produs;
	}
	
	public void setProdus(Produs produs) {
		this.produs = produs;
	}
	
	public double getTaxa() {
		return taxa;
	}
	
	public void setTaxa(double taxa) {
		this.taxa = taxa;
	}
	
	public int getCantitate() {
		return cantitate;
	}
	
	public void setCantitate(int cantitate) {
		this.cantitate = cantitate;
	}
	
	@Override
	public String toString() {
		return produs + ", taxa= " + taxa + ", cantitate= " + cantitate;
	}
}
