package fr.gsb.applicrdr.controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import fr.gsb.applicrdr.technique.Session;
import fr.gsb.applicrdr.vues.VueAuthentification;
import fr.gsb.applicrdr.vues.VueGsb;

public class ControleurGsb implements ActionListener {

	private VueGsb vue ;
	
	public ControleurGsb (VueGsb vue) {
		super() ;
		System.out.println("ControleurGsb::ControleurGsb()") ;
		this.vue = vue ;
		this.enregistrerEcouteur();
	}
	
	public VueGsb getVue() {
		return vue;
	}

	public void setVue(VueGsb vue) {
		this.vue = vue;
	}

	private void enregistrerEcouteur() {
		System.out.println("ControleurGsb::enregistrerEcouteur()") ;
		this.vue.getItemConnecter().addActionListener(this);
		this.vue.getItemQuitter().addActionListener(this);
		this.vue.getItemDeconnecter().addActionListener(this);
		this.vue.getItemConsulter().addActionListener(this);
		this.vue.getItemHesitants().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("ControleurGsb::actionPerformed()") ;
		Object SrcEvnt = e.getSource() ;
		
		if (SrcEvnt == this.vue.getItemConnecter()) {
			 this.vue.changerVue("vueAuthentification");
		}
		
		else if (SrcEvnt == this.vue.getItemQuitter()) {
			int reponse = JOptionPane.showConfirmDialog(vue, "Quitter ?", "Quitter", JOptionPane.YES_NO_OPTION) ;
			if (reponse == JOptionPane.YES_OPTION) {
				System.exit(0) ;
			}
		}
		
		else if (SrcEvnt == this.vue.getItemDeconnecter()) {
			int reponse = JOptionPane.showConfirmDialog(vue, "Deconnexion ?", "Déconnexion", JOptionPane.YES_NO_OPTION) ;
			if (reponse == JOptionPane.YES_OPTION) {
				Session.fermerSession();
				this.vue.setMenusDeconnecte();
				this.vue.changerVue("vueAccueil") ;
			}
		}
		
		else if (SrcEvnt == this.vue.getItemConsulter()) {
			this.vue.getVueCompteRendu().initInterface();
			this.vue.changerVue("vueCompteRendu") ;
		}
		
		else if (SrcEvnt == this.vue.getItemHesitants()) {
//			this.vue.getVuePraticienHesitant().initInterface();
			this.vue.changerVue("vuePraticienHesitant") ;
			
		}
	}
}
