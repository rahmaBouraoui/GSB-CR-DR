package fr.gsb.applicrdr.technique;

import java.sql.DriverManager;

import com.mysql.jdbc.Connection;

public class ConnexionBD {

	private static String dbURL = "jdbc:mysql://localhost:3306/GSBCR" ;
	private static String user = "root" ; 
	private static String password = "azerty" ;
	
	private static Connection connexion = null ;
	
	private ConnexionBD() {
		try {
			Class.forName("com.mysql.jdbc.Driver") ;
			connexion = (Connection) DriverManager.getConnection(dbURL, user, password) ;
		}
		catch(Exception e){
			System.out.println("Erreur ! : " + e.getMessage()) ;
		}
	}
	
	public static Connection getConnexion() {
		if (connexion == null) {
			new ConnexionBD() ;
		}
		return connexion ;
	}
	
	
}
