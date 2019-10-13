public class Produs implements Comparable<Produs> {
	private String denumire;
	private String categorie;
	private String taraOrigine;
	private double pret;
	
	public Produs(String denumire, String categorie, String taraOrigine, double pret) {
		this.denumire = denumire;
		this.categorie = categorie;
		this.taraOrigine = taraOrigine;
		this.pret = pret;
	}
	
	public void setDenumire(String denumire) {
		this.denumire = denumire;
	}
	
	public String getDenumire() {
		return denumire;
	}
	
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	
	public String getCategorie() {
		return categorie;
	}
	
	public void setTaraOrigine(String taraOrigine) {
		this.taraOrigine = taraOrigine;
	}
	
	public String getTaraOrigine() {
		return taraOrigine;
	}
	
	public void setPret(double pret) {
		this.pret = pret;
	}
	
	public double getPret() {
		return pret;
	}

	@Override
	public String toString() {
		return "denumire= " + denumire + ", categorie= " + categorie + ", taraOrigine= " + taraOrigine + ", pret= "
				+ pret  + "\n";
	}
	
	public int compareTo(Produs p) {
		return getDenumire().compareTo(p.getDenumire());
	}
	
	public boolean equals(Object o) {
		if(o == null)
			return false;
		if(!(o instanceof Produs))
			return false;
		Produs p = (Produs) o;
		if(getDenumire().equals(p.getDenumire()))
			if(getCategorie().equals(p.getCategorie()))
				if(getTaraOrigine().equals(p.getTaraOrigine()))
					if(getPret() == p.getPret())
							return true;
		return false;
	}
}
