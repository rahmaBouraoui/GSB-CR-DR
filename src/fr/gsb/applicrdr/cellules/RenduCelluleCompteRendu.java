package fr.gsb.applicrdr.cellules;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import fr.gsb.applicrdr.modeles.ModeleGSBCR;
import fr.gsb.applicrdr.modeles.ModeleListeComptesRendus;

public class RenduCelluleCompteRendu extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 1L;

	public RenduCelluleCompteRendu(){
		super() ;
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		
		
		DefaultTableCellRenderer composant = (DefaultTableCellRenderer) super.getTableCellRendererComponent(table, value, isSelected, hasFocus,	row, column);
	
		if(column == 2 || column == 3) {
			composant.setHorizontalAlignment(SwingConstants.CENTER) ;
		}
		
		else if(column == 0 || column == 1) {
			composant.setHorizontalAlignment(SwingConstants.LEFT) ;
		}
		
//		ModeleTabRapport sonModele = (ModeleTabRapport)table.getModel()
		boolean lu = ((ModeleListeComptesRendus)table.getModel()).getRapports().get(row).isLu() ;
		
		if(lu){
			composant.setBackground(Color.WHITE);
		}
		else {
			composant.setBackground(Color.GRAY);
		}
		
		
		return composant ;
	}
	
	
	
	
}
