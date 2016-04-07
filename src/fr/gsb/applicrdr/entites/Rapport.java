package fr.gsb.applicrdr.entites;

import fr.gsb.applicrdr.technique.DateFR;

public class Rapport {
	
//	private String matricule ;
	private int numero ;
	
	private Visiteur leVisiteur ; 
	public int getNumero() {
		return numero;
	}




	public void setNumero(int numero) {
		this.numero = numero;
	}




	public Visiteur getLeVisiteur() {
		return leVisiteur;
	}




	public void setLeVisiteur(Visiteur leVisiteur) {
		this.leVisiteur = leVisiteur;
	}

	private Praticien lePraticien ;	// Ajout
	
	private String bilan ;
	private String motif ;
	private int coefConfiance ;
	private DateFR dateVisite ;
	private DateFR dateRedaction ;
	private boolean lu ;
	
	public Rapport() {
		
	}

	


	public String getBilan() {
		return bilan;
	}

	public void setBilan(String bilan) {
		this.bilan = bilan;
	}

	public String getMotif() {
		return motif;
	}

	public void setMotif(String motif) {
		this.motif = motif;
	}

	public int getCoefConfiance() {
		return coefConfiance;
	}

	public void setCoefConfiance(int coefConfiance) {
		this.coefConfiance = coefConfiance;
	}

	public DateFR getDateVisite() {
		return dateVisite;
	}

	public void setDateVisite(DateFR dateVisite) {
		this.dateVisite = dateVisite;
	}

	public DateFR getDateRedaction() {
		return dateRedaction;
	}

	public void setDateRedaction(DateFR dateRedaction) {
		this.dateRedaction = dateRedaction;
	}

	public boolean isLu() {
		return lu;
	}

	public void setLu(boolean lu) {
		this.lu = lu;
	}

	public Praticien getLePraticien() {	// Ajout
		return lePraticien;
	}

	public void setLePraticien(Praticien lePraticien) {	// Ajout
		this.lePraticien = lePraticien;
	}




	@Override
	public String toString() {
		return "Rapport [numero=" + numero + ", leVisiteur=" + leVisiteur + ", lePraticien=" + lePraticien + ", bilan="
				+ bilan + ", motif=" + motif + ", coefConfiance=" + coefConfiance + ", dateVisite=" + dateVisite
				+ ", dateRedaction=" + dateRedaction + ", lu=" + lu + "]";
	}

	
	
	

}
