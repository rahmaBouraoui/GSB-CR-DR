package fr.gsb.applicrdr.vues;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import fr.gsb.applicrdr.controleurs.ControleurAuthentification;

public class VueAuthentification extends JPanel {
	
	private VueGsb vueMam ;
	
	private JLabel entete = new JLabel("Authentification") ;
	private JLabel loginLabel = new JLabel("Login : ") ;
	private JLabel mdpLabel = new JLabel("Mot de passe : ") ;
	private JButton bValider = new JButton("Valider") ;
	
	private JTextField loginField = new JTextField(10) ;
	private JPasswordField mdpField = new JPasswordField(10) ;
	private ControleurAuthentification controleur ;
	
	public VueAuthentification(VueGsb vueMam){
		super() ;
		System.out.println("VueAuthentification::VueAuthentification()") ;
		this.creerInterfaceUtilisateur();
		this.controleur = new ControleurAuthentification(this) ;
		this.vueMam = vueMam ;
	}

	
	
	public VueGsb getVueMam() {
		return vueMam;
	}



	public void setVueMam(VueGsb vueMam) {
		this.vueMam = vueMam;
	}
	private void creerInterfaceUtilisateur() {
		System.out.println("VueAuthentification::creerInterfaceUtilisateur()") ;
		
		Box boxPrincipal = Box.createVerticalBox();
		Box boxEnTete = Box.createHorizontalBox() ;
		Box boxChamps = Box.createHorizontalBox() ;
		Box boxEtiquettes = Box.createVerticalBox() ;
		Box boxSaisis = Box.createVerticalBox() ;
		Box boxValider = Box.createHorizontalBox() ;
		
		boxEnTete.add(entete) ;
		
		boxEtiquettes.add(loginLabel);
		boxEtiquettes.add(Box.createVerticalStrut(10)) ;
		boxEtiquettes.add(mdpLabel) ;
		
		boxSaisis.add(loginField);
		boxSaisis.add(Box.createVerticalStrut(10)) ;
		boxSaisis.add(mdpField);
		
		boxChamps.add(boxEtiquettes);
		boxChamps.add(boxSaisis) ;
		
		boxValider.add(bValider) ;
		
		boxPrincipal.add(boxEnTete) ;
		boxPrincipal.add(Box.createVerticalStrut(60)) ;
		boxPrincipal.add(boxChamps);
		boxPrincipal.add(Box.createVerticalStrut(20)) ;
		boxPrincipal.add(boxValider) ;
		
		this.add(boxPrincipal);
	}

	public JButton getbValider() {
		return bValider;
	}

	public void setbValider(JButton bValider) {
		this.bValider = bValider;
	}

	public JLabel getMdpLabel() {
		return mdpLabel;
	}

	public void setMdpLabel(JLabel mdpLabel) {
		this.mdpLabel = mdpLabel;
	}

	public JTextField getLoginField() {
		return loginField;
	}

	public void setLoginField(JTextField loginField) {
		this.loginField = loginField;
	}

	public JLabel getLoginLabel() {
		return loginLabel;
	}

	public void setLoginLabel(JLabel loginLabel) {
		this.loginLabel = loginLabel;
	}

	public JTextField getMdpField() {
		return mdpField;
	}

	public void setMdpField(JPasswordField mdpField) {
		this.mdpField = mdpField;
	}
	

}
