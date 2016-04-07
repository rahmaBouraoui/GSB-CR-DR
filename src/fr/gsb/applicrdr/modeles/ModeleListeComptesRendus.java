package fr.gsb.applicrdr.modeles;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

import fr.gsb.applicrdr.entites.Rapport;
import fr.gsb.applicrdr.entites.Visiteur;
import fr.gsb.applicrdr.technique.DateFR;

public class ModeleListeComptesRendus extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	
//	private List<Rapport> rapports = ModeleListeVisiteurOld.getModele().getRapport("a17") ;
	
	private Visiteur visiteur ;
	private int mois ;
	private int annee ;
	
	private List<Rapport> rapports = new ArrayList<Rapport>() ;
	
	private final String[] entetes = {"Nom du praticien", "Ville", "Visite", "Rédaction", "TEST"} ;
	
	public List<Rapport> getRapports() {
		return rapports;
	}

	public void setRapports(List<Rapport> rapports) {
		this.rapports = rapports;
	}

	public ModeleListeComptesRendus() {
		super() ;
		System.out.println("ModeleTabRapports::ModeleTabRapports()") ;
	}
	
	@Override
	public int getRowCount() {
		System.out.println("ModeleTabRapports::getRowCount()") ;
	
		return this.rapports.size() ;
	}

	@Override
	public int getColumnCount() {
		System.out.println("ModeleTabRapports::getColumnCount()") ;
	
		return this.entetes.length ;
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
			return JButton.class ;
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
	public Object getValueAt(int rowIndex, int columnIndex) {
		System.out.println("ModeleTabRapports::getValueAt("+rowIndex+","+columnIndex+")") ;
	
		switch(columnIndex) {
			case 0 :
				return this.rapports.get(rowIndex).getLePraticien().getNom() ;
			case 1 : 
				return this.rapports.get(rowIndex).getLePraticien().getVille() ;
			case 2 :													
				return this.rapports.get(rowIndex).getDateVisite().toString() ;
			case 3 :													
				return this.rapports.get(rowIndex).getDateRedaction().toString() ;
			case 4 : 
				return "Sélectionner" ;
			default :
				return null ;
		}
		
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		System.out.println("ModeleTabRapports::isCellEditable()") ;
		
		if(columnIndex == 4){
			return true ;
		}
		else {
			return false ;
		}
	}
	
	public void actualiser(Visiteur unVisiteur, int mois, int annee){
		System.out.println("ModeleTabRapports::actualiser()") ;
		this.visiteur = unVisiteur ;
		this.mois = mois ;
		this.annee = annee ;
		this.rapports = ModeleGSBCR.getModele().getRapports(unVisiteur, mois, annee) ;
		this.fireTableDataChanged();
	}
	
	public void actualiser(int ligne) throws SQLException{ 
		Rapport unRapport = this.rapports.get(ligne) ;
		ModeleGSBCR.getModele().setRapportLu(this.rapports.get(ligne));
//		ModeleGSBCR.getModele().setRapportLu(unRapport);
		this.rapports = ModeleGSBCR.getModele().getRapports(this.visiteur, this.mois, this.annee) ;
		this.fireTableDataChanged();
	}

}
