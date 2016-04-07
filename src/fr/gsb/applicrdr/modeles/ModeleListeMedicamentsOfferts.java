package fr.gsb.applicrdr.modeles;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

import fr.gsb.applicrdr.entites.Offre;
import fr.gsb.applicrdr.entites.Rapport;
import fr.gsb.applicrdr.entites.Visiteur;

public class ModeleListeMedicamentsOfferts extends AbstractTableModel {

	private Rapport rapport ;
	
	private List<Offre> offres = new ArrayList<Offre>() ;
	
	private final String[] entetes = {"Médicaments", "Quantité"} ;
	
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return offres.size() ;
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
			return this.offres.get(rowIndex).getNomMedicament() ;
		case 1 : 
			return new Integer(this.offres.get(rowIndex).getQuantite()) ;
		default :
			return null ;
		}
	
	}
	
	public Class columnClass(int columnIndex) {
		switch(columnIndex) {
		case 0 :
			return String.class ;
		case 1 :
			return Integer.class ;
		default :
			return Object.class ;
		
		}
		
	}
	
	public String getColumnName(int columnIndex) {
		// TODO Auto-generated method stub
		return entetes[columnIndex] ;
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false ;
	}
	
	
	public void actualiser(Rapport rapport) {
		this.rapport = rapport ;
		this.offres = ModeleGSBCR.getModele().getOffres(rapport) ;
		this.fireTableDataChanged();
	}
	
//	public void actualiser(Visiteur unVisiteur, int mois, int annee){
//		System.out.println("ModeleTabRapports::actualiser()") ;
//		this.visiteur = unVisiteur ;
//		this.mois = mois ;
//		this.annee = annee ;
//		this.rapports = ModeleGSBCR.getModele().getRapports(unVisiteur, mois, annee) ;
//		this.fireTableDataChanged();
//	}
//	
//	public void actualiser(int ligne) throws SQLException{ 
//		Rapport unRapport = this.rapports.get(ligne) ;
//		ModeleGSBCR.getModele().setRapportLu(this.rapports.get(ligne));
////		ModeleGSBCR.getModele().setRapportLu(unRapport);
//		this.rapports = ModeleGSBCR.getModele().getRapports(this.visiteur, this.mois, this.annee) ;
//		this.fireTableDataChanged();
//	}

}
