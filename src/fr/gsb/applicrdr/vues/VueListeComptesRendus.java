package fr.gsb.applicrdr.vues;

import java.awt.Dimension;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import fr.gsb.applicrdr.cellules.RenduBoutonCompteRendu;
import fr.gsb.applicrdr.cellules.RenduCelluleCompteRendu;
import fr.gsb.applicrdr.controleurs.ControleurListeComptesRendus;
import fr.gsb.applicrdr.editeurs.EditeurBoutonSelectionCompteRendu;
import fr.gsb.applicrdr.entites.Visiteur;
import fr.gsb.applicrdr.modeles.ModeleGSBCR;
import fr.gsb.applicrdr.modeles.ModeleListeVisiteurOld;
import fr.gsb.applicrdr.modeles.ModeleListeComptesRendus;
import fr.gsb.applicrdr.technique.Session;

public class VueListeComptesRendus extends JPanel {

	private JLabel entete = new JLabel("Liste des Visiteurs") ;
//	private ModeleListeVisiteurOld modele = new ModeleListeVisiteurOld() ;
	private RenduCelluleCompteRendu cellCRVisiteur ;
	private JComboBox<Visiteur> cbVisiteurs = new JComboBox<Visiteur>() ;
	private JComboBox<Integer> cbMois = new JComboBox<Integer>() ;
	private JComboBox<Integer> cbAnnee = new JComboBox<Integer>() ;
	private JTable tabRapports;
	private ModeleListeComptesRendus modeleRapport = new ModeleListeComptesRendus();
	private ControleurListeComptesRendus controleur ;
	private VueGsb vueMam ;
	
	public VueListeComptesRendus(VueGsb vueMam) {
		super();
		System.out.println("VueCompteRendu::VueCompteRendu()") ;
		creerInterface() ;
		appliquerRendu() ;	
		appliquerEditeur() ;
		this.vueMam = vueMam ;
		
		this.controleur = new ControleurListeComptesRendus(this) ; 
		
	}
	
	public VueGsb getVueMam() {
		return vueMam;
	}

	public void setVueMam(VueGsb vueMam) {
		this.vueMam = vueMam;
	}

	public void creerInterface(){
				
		
		this.tabRapports = new JTable(modeleRapport) ;
		this.tabRapports.setRowHeight(30) ;
		JScrollPane spVisiteurs = new JScrollPane(this.tabRapports) ;
		spVisiteurs.setPreferredSize(new Dimension(1090,420)) ;
				
		Box boxPrincipale = Box.createVerticalBox() ;
		Box boxEtiquette = Box.createHorizontalBox() ;
		Box boxTableau = Box.createHorizontalBox() ;
		
		
		boxEtiquette.add(entete) ;
	
		boxPrincipale.add(Box.createVerticalStrut(10));
		boxPrincipale.add(boxEtiquette) ;
		boxPrincipale.add(Box.createVerticalStrut(10));
		boxPrincipale.add(boxTableau) ;
		boxPrincipale.add(Box.createVerticalStrut(10));
		boxPrincipale.add(new JLabel("Visiteur : ")) ;
		boxPrincipale.add(cbVisiteurs) ;
		boxPrincipale.add(new JLabel("Mois : ")) ;
		boxPrincipale.add(cbMois) ;
		boxPrincipale.add(new JLabel("Annee : ")) ;
		boxPrincipale.add(cbAnnee) ;
		boxPrincipale.add(Box.createVerticalStrut(30));
		boxPrincipale.add(spVisiteurs) ;
		
		this.add(boxPrincipale) ;
		
		GregorianCalendar aujourdhui = new GregorianCalendar() ;
		System.out.println(aujourdhui) ;
		for(int numAnnee = aujourdhui.get(Calendar.YEAR) ; numAnnee >= 2010 ; numAnnee--) {
			System.out.println(numAnnee) ;
			cbAnnee.addItem(new Integer(numAnnee));
		}
		for(int numMois = 1 ; numMois <= 12 ; numMois ++) {
			System.out.println(numMois) ;
			cbMois.addItem(new Integer(numMois));
		}
		
	}
	
	public void initInterface(){
		
		
		
		
		List<Visiteur> lesVisiteurs = ModeleGSBCR.getModele().getLesVisiteurs(Session.getSession().getDelegue().getCodeRegion()) ;
		for(Visiteur unVisiteur : lesVisiteurs) {
			this.cbVisiteurs.addItem(unVisiteur) ;
		}
		
		
		Visiteur visiteur =  (Visiteur) this.getCbVisiteurs().getSelectedItem() ;
		Integer mois = (Integer) this.getCbMois().getSelectedItem() ;
		Integer annee = (Integer) this.getCbAnnee().getSelectedItem() ;
		
		this.modeleRapport.actualiser(visiteur, mois, annee);
		
		
	}

	
	public void initTableau() {
		
		
	}
	
	

//	public ModeleListeVisiteurOld getModele() {
//		return modele;
//	}
	
	public JTable getTabVisiteurs() {
		return tabRapports ;
	}

	public void appliquerEditeur() {
		this.tabRapports.getColumn("TEST").setCellEditor(new EditeurBoutonSelectionCompteRendu(new JCheckBox(), vueMam));
		
	}
	
	public void appliquerRendu() {
		RenduCelluleCompteRendu renduCellules = new RenduCelluleCompteRendu() ;
		this.tabRapports.getColumn("Nom du praticien").setCellRenderer(renduCellules) ;
		this.tabRapports.getColumn("Ville").setCellRenderer(renduCellules) ;
		this.tabRapports.getColumn("Visite").setCellRenderer(renduCellules) ;
		this.tabRapports.getColumn("Rédaction").setCellRenderer(renduCellules) ;
		
		RenduBoutonCompteRendu renduCelluleBouton = new RenduBoutonCompteRendu() ;
		this.tabRapports.getColumn("TEST").setCellRenderer(renduCelluleBouton) ;
	}

	public JComboBox<Visiteur> getCbVisiteurs() {
		return cbVisiteurs;
	}


	public JComboBox<Integer> getCbMois() {
		return cbMois;
	}


	public JComboBox<Integer> getCbAnnee() {
		return cbAnnee;
	}


	public ModeleListeComptesRendus getModeleRapport() {
		return modeleRapport;
	}
	
	
	
}
