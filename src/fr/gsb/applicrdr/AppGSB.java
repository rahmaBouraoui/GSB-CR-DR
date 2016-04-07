package fr.gsb.applicrdr;

import fr.gsb.applicrdr.technique.ConnexionBD;
import fr.gsb.applicrdr.vues.VueGsb;

public class AppGSB {
	public static void main(String[] args) {
		System.out.println("AppGSB::main()") ;
		VueGsb vue = new VueGsb() ;
		ConnexionBD.getConnexion() ;
		
	}
	
}
