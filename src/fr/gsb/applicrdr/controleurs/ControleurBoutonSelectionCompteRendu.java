package fr.gsb.applicrdr.controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JTable;

import fr.gsb.applicrdr.entites.Rapport;
import fr.gsb.applicrdr.modeles.ModeleListeComptesRendus;
import fr.gsb.applicrdr.vues.VueCompteRendu;
import fr.gsb.applicrdr.vues.VueGsb;

public class ControleurBoutonSelectionCompteRendu implements ActionListener {
	
	private int row ;
	private int column ;
	private JTable table ;
	private JButton bouton ;
	private VueGsb vueMam ;
	
	public VueGsb getVueMam() {
		return vueMam;
	}

	public void setVueMam(VueGsb vueMam) {
		this.vueMam = vueMam;
	}

	public ControleurBoutonSelectionCompteRendu(VueGsb vueMam) {
		super() ;
	}
	
	public JButton  getBouton(){	
		return this.bouton ;
	}
	
	public void setBouton(JButton bouton) {	
		this.bouton = bouton;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("CLIC") ;
//		((ModeleTabRapport)table.getModel()).getRapports().get(row).setLu(true);
		try {
//			((ModeleListeComptesRendus)table.getModel()).actualiser(this.row);
			System.out.println(((ModeleListeComptesRendus)table.getModel()).getRapports().get(row)) ;
			
			Rapport leRapport = ((ModeleListeComptesRendus)table.getModel()).getRapports().get(row) ;
			
			//Ouverture de la boîte de dialogue avec envoi du compte-rendu sélectionné
			new VueCompteRendu(this.vueMam, leRapport) ;
			
//		} catch (SQLException e1) {
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	

}
