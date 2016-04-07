package fr.gsb.applicrdr.vues;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class VueAccueil extends JPanel {

	JLabel entete = new JLabel("Accueil") ;
	
	public VueAccueil() {
		super();
		System.out.println("VueAccueil::VueAccueil()") ;
		this.creerInterfaceUtilisateur() ;
	}

	private void creerInterfaceUtilisateur() {
		System.out.println("VueAccueil::creerInterfaceUtilisateur()") ;
		this.add(entete); 
	}
	
}
