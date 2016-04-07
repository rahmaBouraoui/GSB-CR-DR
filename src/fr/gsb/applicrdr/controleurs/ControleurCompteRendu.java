package fr.gsb.applicrdr.controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.gsb.applicrdr.vues.VueCompteRendu;

public class ControleurCompteRendu implements ActionListener {
	
	private VueCompteRendu vue ;

	public ControleurCompteRendu(VueCompteRendu vue) {
		super();
		this.vue = vue;
		this.enregistrerEcouteur();
	}
	
	private void enregistrerEcouteur() {
		System.out.println("Mise en écoute") ;
		this.vue.getbOk().addActionListener(this); 
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//Basculer le rapport en mode lu
		System.out.println("Fermer ?") ;
		if(e.getSource() == this.vue.getbOk()) {
			System.out.println("Fermer !") ;
			this.vue.dispose() ;
		}
	}
	
	

}
