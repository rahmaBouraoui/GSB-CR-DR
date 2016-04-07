package fr.gsb.applicrdr.entites;

import fr.gsb.applicrdr.technique.DateFR;

public class Praticien {

	private int numero ;
	private String nom ;
	private String ville ;
	private int coefConf ;
	private double coefNot ;
	private DateFR derniereVisite ;
	
	public Praticien() {
		
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	
	
	public int getCoefConf() {
		return coefConf;
	}

	public void setCoefConf(int coefConf) {
		this.coefConf = coefConf;
	}

	public double getCoefNot() {
		return coefNot;
	}

	public void setCoefNot(double coefNot) {
		this.coefNot = coefNot;
	}

	public DateFR getDerniereVisite() {
		return derniereVisite;
	}

	public void setDerniereVisite(DateFR derniereVisite) {
		this.derniereVisite = derniereVisite;
	}

	@Override
	public String toString() {
		return "Praticien [numero=" + numero + ", nom=" + nom + ", ville="
				+ ville + "]";
	}

	

}
