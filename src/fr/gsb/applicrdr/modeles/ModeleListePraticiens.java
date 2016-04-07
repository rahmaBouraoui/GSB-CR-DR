package fr.gsb.applicrdr.modeles;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

import fr.gsb.applicrdr.entites.Praticien;
import fr.gsb.applicrdr.entites.Rapport;
import fr.gsb.applicrdr.entites.Visiteur;

public class ModeleListePraticiens  extends AbstractTableModel {
	
	private Praticien praticien ;
	private String nom ;
	private String ville ;
	private List<Praticien> praticiens = new ArrayList<Praticien>() ;
	private final String[] entetes = {"Nom du praticien", "Ville", "Coef. Confiance", "Dernière visite", "Coef. Notoriété"} ;
	
	public List<Praticien> getPraticiens() {
		return praticiens ;
	}
	
	public void setPraticiens(List<Praticien> praticiens) {
		this.praticiens = praticiens ;
	}
	
	public ModeleListePraticiens() {
		super() ;
	}
	
	@Override
	public int getRowCount() {
		return praticiens.size() ;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return entetes.length ;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		switch(columnIndex) {
			case 0 :
				return this.praticiens.get(rowIndex).getNom() ;
			case 1 : 
				return this.praticiens.get(rowIndex).getVille() ;
			case 2 : 
				return "" + this.praticiens.get(rowIndex).getCoefConf() ;
			case 3 : 
				return "" + this.praticiens.get(rowIndex).getDerniereVisite() ;
			case 4 : 
				return "" + this.praticiens.get(rowIndex).getCoefNot() ;
			default :
				return null ;
		}
	}
	
	public Class columnClass(int columnIndex) {
		switch(columnIndex) {
			case 0 :
				return String.class ;
			case 1 :
				return String.class ;
			case 2 :
				return String.class ;
			case 3 :
				return String.class ;
			case 4 :
				return String.class ;
			default :
				return Object.class ;
		
		}
		
	}
	@Override
	public String getColumnName(int columnIndex) {
		// TODO Auto-generated method stub
		return entetes[columnIndex] ;
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false ;
	}
	
	public void actualiserCoefNot(){
		System.out.println(" actualiserCoefNot") ;
		this.praticiens = ModeleGSBCR.getModele().getLesPraticiensParCoefNot() ;
		for(Praticien unPraticien : this.praticiens){
			System.out.println(unPraticien) ;
		}
		this.fireTableDataChanged();
	}
	
	public void actualiserCoefConf(){
		this.praticiens = ModeleGSBCR.getModele().getLesPraticiensParCoefConf() ;
		this.fireTableDataChanged();
	}
	
	public void actualiserTemps(){
		this.praticiens = ModeleGSBCR.getModele().getLesPraticiensParTemps() ;
		this.fireTableDataChanged();
	}

}
