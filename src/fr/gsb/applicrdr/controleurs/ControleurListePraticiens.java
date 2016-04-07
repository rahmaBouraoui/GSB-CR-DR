package fr.gsb.applicrdr.controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.gsb.applicrdr.vues.VueGsb;
import fr.gsb.applicrdr.vues.VuePraticiensHesitants;

public class ControleurListePraticiens implements ActionListener {

	private VuePraticiensHesitants vuePraticienHesitant ;
	private VueGsb vueGsb ;
	
	public ControleurListePraticiens(VuePraticiensHesitants vue) {
		super() ;
		this.vuePraticienHesitant = vue ;
		this.enregistrerEcouteur();
	}
	
	private void enregistrerEcouteur() {
		System.out.println("ControleurListePraticien::enregistrerEcouteur()") ;
		this.vuePraticienHesitant.getRbCoefConf().addActionListener(this);
		this.vuePraticienHesitant.getRbTemps().addActionListener(this);
		this.vuePraticienHesitant.getRbCoefNot().addActionListener(this); 
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource() ;
		if(source == this.vuePraticienHesitant.getRbCoefNot()) {
			System.out.println("coefNot") ;
			this.vuePraticienHesitant.getModelePraticien().actualiserCoefNot() ;
		}
		else if(source == this.vuePraticienHesitant.getRbCoefConf()) {
			System.out.println("coefConf") ;
			this.vuePraticienHesitant.getModelePraticien().actualiserCoefConf() ;
			
		}
		else if(source == this.vuePraticienHesitant.getRbTemps()) {
			System.out.println("rbTemps") ;
			this.vuePraticienHesitant.getModelePraticien().actualiserTemps() ;
		}
		
	}

}
