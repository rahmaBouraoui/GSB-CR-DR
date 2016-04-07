package fr.gsb.applicrdr.controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import fr.gsb.applicrdr.entites.Rapport;
import fr.gsb.applicrdr.entites.Visiteur;
import fr.gsb.applicrdr.modeles.ModeleGSBCR;
import fr.gsb.applicrdr.technique.Session;
import fr.gsb.applicrdr.vues.VueListeComptesRendus;
import fr.gsb.applicrdr.vues.VueGsb;

public class ControleurListeComptesRendus implements ActionListener{
	private VueListeComptesRendus vueCompteRendu ;
	private VueGsb vueGsb ;
	
	public ControleurListeComptesRendus(VueListeComptesRendus vue) {
		System.out.println("ControleurListeVisiteur::ControleurListeVisiteur()") ;
		this.vueCompteRendu = vue ;
		this.enregistrerEcouteur();
	}
	
	
	private void enregistrerEcouteur() {
		System.out.println("ControleurListeVisiteur::enregistrerEcouteur()") ;
		this.vueCompteRendu.getCbVisiteurs().addActionListener(this);
		this.vueCompteRendu.getCbAnnee().addActionListener(this);
		this.vueCompteRendu.getCbMois().addActionListener(this); 
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(Session.getSession() != null){		
			Object source = e.getSource() ;
		
			if(source == this.vueCompteRendu.getCbVisiteurs()) {
				System.out.println(this.vueCompteRendu.getCbVisiteurs().getSelectedItem()) ;
			}
			else if(source == this.vueCompteRendu.getCbAnnee()) {
				System.out.println(this.vueCompteRendu.getCbAnnee().getSelectedItem()) ;
			}
			else if(source == this.vueCompteRendu.getCbMois()) {
				System.out.println(this.vueCompteRendu.getCbMois().getSelectedItem()) ;
			}
			
			Visiteur visiteur =  (Visiteur) this.vueCompteRendu.getCbVisiteurs().getSelectedItem() ;
			Integer mois = (Integer) this.vueCompteRendu.getCbMois().getSelectedItem() ;
			Integer annee = (Integer) this.vueCompteRendu.getCbAnnee().getSelectedItem() ;
			
			System.out.println("VueCR : " + visiteur + " " + mois + " " + annee) ;
			
//			this.vueCompteRendu.getTabVisiteurs().getModel().notify();
			
			
			this.vueCompteRendu.getModeleRapport().actualiser(visiteur, mois, annee);
		}
		
//		List<Rapport> lesRapports = ModeleGSBCR.getModele().getRapports(visiteur, mois, annee) ;
//
//		System.out.println("--> Les rapports : ") ;
//		
//		for(Rapport unRapport : lesRapports) {
//			System.out.println(unRapport) ;
//		}
	}
}
