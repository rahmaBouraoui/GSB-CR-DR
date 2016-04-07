package fr.gsb.applicrdr.controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import fr.gsb.applicrdr.modeles.ModeleGSBCR;
import fr.gsb.applicrdr.technique.Session;
import fr.gsb.applicrdr.vues.VueAuthentification;

public class ControleurAuthentification implements ActionListener {

	private VueAuthentification vue ;

	public ControleurAuthentification(VueAuthentification vue){
		super() ;
		System.out.println("ControleurAuthentification::ControleurAuthentification()") ;
		this.vue = vue ;
		this.enregistrerEcouteur() ;
	}
	
	private void enregistrerEcouteur() {
		this.vue.getbValider().addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.print("ControleurAuthentification::actionPerformed()") ;
		Object source = e.getSource() ;
		
		if (source == this.vue.getbValider()) {
			String login = this.vue.getLoginField().getText() ;
			String mdp = new String(this.vue.getMdpField().getText()) ;
			System.out.println("Login : " + login + "\tMDP : " + mdp + " ") ;
			boolean connexionOk = ModeleGSBCR.getModele().seConnecter(login, mdp) ;
			if (connexionOk) {
				System.out.println(" -->connexionOk") ;
				JOptionPane.showMessageDialog(this.vue, "Connexion !", "Connexion", JOptionPane.INFORMATION_MESSAGE);
				this.vue.getVueMam().setMenusConnecte();
				this.vue.getVueMam().changerVue("vueAccueil");
				this.vue.getLoginField().setText("");
				this.vue.getMdpField().setText("");
				System.out.println(Session.getSession().getDelegue()) ;
			}
			else {
				System.out.println(" -->!connexionOk") ;
				JOptionPane.showMessageDialog(this.vue, "Erreur !", "Connexion", JOptionPane.ERROR_MESSAGE);
				this.vue.getLoginField().setText("");
				this.vue.getMdpField().setText("");
			}
		}
		
	}

}
