package fr.gsb.applicrdr.vues;

import java.awt.CardLayout;
import java.awt.Container;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import fr.gsb.applicrdr.controleurs.ControleurGsb;

public class VueGsb extends JFrame{
	private ControleurGsb controleur ;
	private Container conteneur ;
	private JMenu menuFichier = new JMenu("Fichier") ;
	private JMenu menuCompteRendu = new JMenu("Compte rendu") ;
	private JMenu menuPraticiens = new JMenu("Praticiens") ;
	private JMenu menuAide = new JMenu("Aide") ;
	
	private JMenuItem itemConnecter = new JMenuItem("Connection") ;
	private JMenuItem itemDeconnecter = new JMenuItem("Déconnection") ;
	private JMenuItem itemQuitter = new JMenuItem("Quitter") ;
	private JMenuItem itemConsulter = new JMenuItem("Consulter") ;
	private JMenuItem itemHesitants = new JMenuItem("Hésitants") ;
	
	private JPanel panneauPrincipal = new JPanel() ;
	
	private CardLayout cardLayout = new CardLayout(5, 5) ;
	private VueAccueil vueAccueil = new VueAccueil() ; 
	private VueAuthentification vueAuthentifications = new VueAuthentification(this) ;
	private VueListeComptesRendus vueCompteRendu = new VueListeComptesRendus(this) ;
	private VuePraticiensHesitants vuePraticienHesitant = new VuePraticiensHesitants() ;
//	private VueCompteRendu vueCompteRendu ;
	
	public VueGsb() {
		super();
		System.out.println("VuePrincipale::VuePrincipale()") ;
		
		this.setTitle("GSB") ;
		this.setSize(1300,500) ; 
		this.setLocationRelativeTo(null) ;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE) ;
		
		this.creerBarreMenus() ;
		this.setMenusDeconnecte();
		this.setVisible(true) ;
	
//		vueAuthentifications = new VueAuthentification(this) ;
		
		conteneur = this.getContentPane() ;
		conteneur.setLayout(cardLayout);
		
		conteneur.add("vueAccueil", vueAccueil);
		conteneur.add("vueAuthentification", vueAuthentifications);
		conteneur.add("vueCompteRendu", vueCompteRendu);
		conteneur.add("vuePraticienHesitant", vuePraticienHesitant);
		
		this.controleur = new ControleurGsb(this) ;
	}
	
	public void changerVue(String nomVue) {
		this.cardLayout.show(conteneur, nomVue);
	}
	
	
	public void setMenusConnecte() {
		System.out.println("VuePrincipale::setMenusConnecte()") ;
		this.itemConnecter.setEnabled(false);
		this.itemDeconnecter.setEnabled(true);
		this.menuPraticiens.setEnabled(true);
		this.menuCompteRendu.setEnabled(true);
	}

	
	

	public CardLayout getCardLayout() {
		return cardLayout;
	}




	public void setCardLayout(CardLayout cardLayout) {
		this.cardLayout = cardLayout;
	}




	public JPanel getPanneauPrincipal() {
		return panneauPrincipal;
	}



	public void setPanneauPrincipal(JPanel panneauPrincipal) {
		this.panneauPrincipal = panneauPrincipal;
	}



	public ControleurGsb getControleur() {
		return controleur;
	}



	public void setControleur(ControleurGsb controleur) {
		this.controleur = controleur;
	}



	public JMenu getMenuFichier() {
		return menuFichier;
	}



	public void setMenuFichier(JMenu menuFichier) {
		this.menuFichier = menuFichier;
	}



	public JMenu getMenuCompteRendu() {
		return menuCompteRendu;
	}



	public void setMenuCompteRendu(JMenu menuCompteRendu) {
		this.menuCompteRendu = menuCompteRendu;
	}



	public JMenu getMenuPraticiens() {
		return menuPraticiens;
	}



	public void setMenuPraticiens(JMenu menuPraticiens) {
		this.menuPraticiens = menuPraticiens;
	}



	public JMenu getMenuAide() {
		return menuAide;
	}



	public void setMenuAide(JMenu menuAide) {
		this.menuAide = menuAide;
	}



	public JMenuItem getItemConnecter() {
		return itemConnecter;
	}



	public void setItemConnecter(JMenuItem itemConnecter) {
		this.itemConnecter = itemConnecter;
	}



	public JMenuItem getItemDeconnecter() {
		return itemDeconnecter;
	}



	public void setItemDeconnecter(JMenuItem itemDeconnecter) {
		this.itemDeconnecter = itemDeconnecter;
	}



	public JMenuItem getItemQuitter() {
		return itemQuitter;
	}



	public void setItemQuitter(JMenuItem itemQuitter) {
		this.itemQuitter = itemQuitter;
	}



	public JMenuItem getItemConsulter() {
		return itemConsulter;
	}



	public void setItemConsulter(JMenuItem itemConsulter) {
		this.itemConsulter = itemConsulter;
	}



	public JMenuItem getItemHesitants() {
		return itemHesitants;
	}



	public void setItemHesitants(JMenuItem itemHesitants) {
		this.itemHesitants = itemHesitants;
	}
	
	



	public VuePraticiensHesitants getVuePraticienHesitant() {
		return vuePraticienHesitant;
	}

	public void setVuePraticienHesitant(VuePraticiensHesitants vuePraticienHesitant) {
		this.vuePraticienHesitant = vuePraticienHesitant;
	}

	public void setMenusDeconnecte() {
		System.out.println("VuePrincipale::setMenusConnecte()") ;
		this.itemConnecter.setEnabled(true);
		this.itemDeconnecter.setEnabled(false);
		this.menuPraticiens.setEnabled(false);
		this.menuCompteRendu.setEnabled(false);
		
	}

	private void creerBarreMenus() {
		System.out.println("VuePrincipale::creerBarreMenus()") ;
		
		JMenuBar barreMenus = new JMenuBar() ;
		
		menuFichier.add(itemConnecter) ;
		menuFichier.add(itemDeconnecter) ;
		menuFichier.add(new JSeparator()) ;
		menuFichier.add(itemQuitter) ;
		
		menuCompteRendu.add(itemConsulter) ;
		
		menuPraticiens.add(itemHesitants) ;
		
		barreMenus.add(menuFichier) ;
		barreMenus.add(menuCompteRendu) ;
		barreMenus.add(menuPraticiens) ;
		barreMenus.add(menuAide) ;
		
		this.setJMenuBar(barreMenus) ;
	}

	public VueListeComptesRendus getVueCompteRendu() {
		return vueCompteRendu;
	}
	
}
