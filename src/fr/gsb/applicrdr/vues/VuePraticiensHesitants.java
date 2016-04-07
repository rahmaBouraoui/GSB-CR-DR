package fr.gsb.applicrdr.vues;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import fr.gsb.applicrdr.controleurs.ControleurListeComptesRendus;
import fr.gsb.applicrdr.controleurs.ControleurListePraticiens;
import fr.gsb.applicrdr.modeles.ModeleListeComptesRendus;
import fr.gsb.applicrdr.modeles.ModeleListePraticiens;

public class VuePraticiensHesitants extends JPanel {

	private ControleurListePraticiens controleur ;
	private JLabel entete = new JLabel("Liste des Praticiens hésitants") ;
	private JRadioButton rbCoefConf = new JRadioButton("Le coefficient de confiance") ;
	private JRadioButton rbTemps = new JRadioButton("Le temps écoulé depuis la dernière visite") ;
	private JRadioButton rbCoefNot = new JRadioButton("Le coefficient de notoriété du praticien") ;
	private JTable tabPraticiens;
	private ModeleListePraticiens modelePraticien = new ModeleListePraticiens();
	
	public VuePraticiensHesitants() {
		super() ;
		this.controleur = new ControleurListePraticiens(this) ; 
		this.creerInterface();
	}
	
	public void creerInterface(){
					
			
			this.tabPraticiens = new JTable(modelePraticien) ;
			this.tabPraticiens.setRowHeight(30) ;
			JScrollPane spPraticiens = new JScrollPane(this.tabPraticiens) ;
			spPraticiens.setPreferredSize(new Dimension(1090,420)) ;
					
			Box boxPrincipale = Box.createVerticalBox() ;
			Box boxEtiquette = Box.createHorizontalBox() ;
			Box boxTableau = Box.createHorizontalBox() ;
			
			ButtonGroup criteres = new ButtonGroup() ;
			criteres.add(rbCoefConf); 
			criteres.add(rbTemps); 
			criteres.add(rbCoefNot); 
			rbCoefNot.setSelected(true);
			
			boxEtiquette.add(entete) ;
		
			boxPrincipale.add(Box.createVerticalStrut(10));
			boxPrincipale.add(boxEtiquette) ;
			boxPrincipale.add(Box.createVerticalStrut(10));
			boxPrincipale.add(boxTableau) ;
			boxPrincipale.add(Box.createVerticalStrut(10));
			boxPrincipale.add(rbCoefConf) ;
			boxPrincipale.add(rbTemps) ;
			boxPrincipale.add(rbCoefNot) ;
			boxPrincipale.add(Box.createVerticalStrut(30));
			boxPrincipale.add(spPraticiens) ;
			
			this.add(boxPrincipale) ;
			modelePraticien.actualiserCoefNot();
			
	}

	public ControleurListePraticiens getControleur() {
		return controleur;
	}

	public void setControleur(ControleurListePraticiens controleur) {
		this.controleur = controleur;
	}

	public JLabel getEntete() {
		return entete;
	}

	public void setEntete(JLabel entete) {
		this.entete = entete;
	}

	public JRadioButton getRbCoefConf() {
		return rbCoefConf;
	}

	public void setRbCoefConf(JRadioButton rbCoefConf) {
		this.rbCoefConf = rbCoefConf;
	}

	public JRadioButton getRbTemps() {
		return rbTemps;
	}

	public void setRbTemps(JRadioButton rbTemps) {
		this.rbTemps = rbTemps;
	}

	public JRadioButton getRbCoefNot() {
		return rbCoefNot;
	}

	public void setRbCoefNot(JRadioButton rbCoefNot) {
		this.rbCoefNot = rbCoefNot;
	}

	public JTable getTabPraticiens() {
		return tabPraticiens;
	}

	public void setTabPraticiens(JTable tabPraticiens) {
		this.tabPraticiens = tabPraticiens;
	}

	public ModeleListePraticiens getModelePraticien() {
		return modelePraticien;
	}

	public void setModelePraticien(ModeleListePraticiens modelePraticien) {
		this.modelePraticien = modelePraticien;
	}

	
	
}
