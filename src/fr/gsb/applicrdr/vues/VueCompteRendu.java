package fr.gsb.applicrdr.vues;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import fr.gsb.applicrdr.controleurs.ControleurCompteRendu;
import fr.gsb.applicrdr.entites.Rapport;
import fr.gsb.applicrdr.modeles.ModeleListeComptesRendus;
import fr.gsb.applicrdr.modeles.ModeleListeMedicamentsOfferts;

public class VueCompteRendu extends JDialog {
	
	private ControleurCompteRendu controleur ;
	private Rapport rapport ;
	
	private JTextField tfNumero = new JTextField() ;
	private JTextField tfVisiteur = new JTextField() ;
	private JTextField tfPraticien = new JTextField() ;
	private JTextField tfCoefConfiance = new JTextField() ;
	private JTextField tfDateVisite = new JTextField() ;
	private JTextField tfDateRedaction = new JTextField() ;
	private JTable tabMedicaments ;
	private ModeleListeMedicamentsOfferts modeleOffres = new ModeleListeMedicamentsOfferts();
	
	private JButton bOk = new JButton("Ok") ;

	public VueCompteRendu(JFrame parent, Rapport rapport) {
		super(parent, "Consultation d'un compte-rendu", true);
		this.rapport = rapport ;
//		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setLocationRelativeTo(null) ;
		this.creerInterface();
		this.controleur = new ControleurCompteRendu(this) ;
		this.setVisible(true) ;
		
	}

	public ControleurCompteRendu getControleur() {
		return controleur;
	}

	public void setControleur(ControleurCompteRendu controleur) {
		this.controleur = controleur;
	}

	public Rapport getRapport() {
		return rapport;
	}

	public void setRapport(Rapport rapport) {
		this.rapport = rapport;
	}
	
	public JButton getbOk() {
		return bOk;
	}

	public void setbOk(JButton bOk) {
		this.bOk = bOk;
	}

	private void creerInterface() {
		
		this.tabMedicaments = new JTable(modeleOffres) ;
		this.tabMedicaments.setRowHeight(30) ;
		JScrollPane spMedicaments = new JScrollPane(this.tabMedicaments) ;
		spMedicaments.setPreferredSize(new Dimension(1090,420)) ;
		
		Box boxPrincipale = Box.createVerticalBox() ;
		boxPrincipale.add(new JLabel("Rapport numéro :")) ;
		boxPrincipale.add(tfNumero) ;
		tfNumero.setText(rapport.getNumero() + "");
		tfNumero.setEditable(false);
		
		boxPrincipale.add(new JLabel("Visiteur :")) ;
		boxPrincipale.add(tfVisiteur) ;
		tfVisiteur.setText(rapport.getLeVisiteur().getPrenom() +" " + rapport.getLeVisiteur().getNom());
		tfVisiteur.setEditable(false);
		
		boxPrincipale.add(new JLabel("Praticien :")) ;
		boxPrincipale.add(tfPraticien) ;
		tfPraticien.setText(rapport.getLePraticien().getNom() + "-" + rapport.getLePraticien().getVille());
		tfPraticien.setEditable(false);
		
		boxPrincipale.add(new JLabel("Coefficient de confiance :")) ;
		boxPrincipale.add(tfCoefConfiance) ;
		tfCoefConfiance.setText(rapport.getCoefConfiance() + "");
		tfCoefConfiance.setEditable(false);
		
		boxPrincipale.add(new JLabel("Date de visite :")) ;
		boxPrincipale.add(tfDateVisite) ;
		tfDateVisite.setText(rapport.getDateVisite() + "");
		tfDateVisite.setEditable(false);
		
		boxPrincipale.add(new JLabel("Date de rédaction :")) ;
		boxPrincipale.add(tfDateRedaction) ;
		tfDateRedaction.setText(rapport.getDateRedaction() + "");
		tfDateRedaction.setEditable(false);
		
		boxPrincipale.add(new JLabel("Echantillons offerts :")) ;
		boxPrincipale.add(spMedicaments) ;
		
		
		boxPrincipale.add(bOk) ;
		
		this.getContentPane().add(boxPrincipale);
		
		this.pack() ;
		
		this.modeleOffres.actualiser(this.rapport);
	}
	
}
