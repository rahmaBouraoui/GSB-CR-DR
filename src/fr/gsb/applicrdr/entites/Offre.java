package fr.gsb.applicrdr.entites;

public class Offre {
	
	private String nomMedicament ;
	private int quantite ;
	
	public Offre(String nomMedicament, int quantite) {
		super();
		this.nomMedicament = nomMedicament;
		this.quantite = quantite;
	}
	
	public String getNomMedicament() {
		return nomMedicament;
	}
	
	public void setNomMedicament(String nomMedicament) {
		this.nomMedicament = nomMedicament;
	}
	
	public int getQuantite() {
		return quantite;
	}
	
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	
	@Override
	public String toString() {
		return "Offre [nomMedicament=" + nomMedicament + ", quantite=" + quantite + "]";
	}
	
	

}
