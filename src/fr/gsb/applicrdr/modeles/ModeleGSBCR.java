package fr.gsb.applicrdr.modeles;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import fr.gsb.applicrdr.entites.Delegue;
import fr.gsb.applicrdr.entites.Offre;
import fr.gsb.applicrdr.entites.Praticien;
import fr.gsb.applicrdr.entites.Rapport;
import fr.gsb.applicrdr.entites.Visiteur;
import fr.gsb.applicrdr.technique.ConnexionBD;
import fr.gsb.applicrdr.technique.DateFR;
import fr.gsb.applicrdr.technique.Session;

public class ModeleGSBCR {
	
	private static ModeleGSBCR modele = null ;
	
	private ModeleListeVisiteurOld modeleListeVisiteurs ;
	
	private static final String requeteConnexion = 	"Select TRAVAILLER.VIS_MATRICULE, TRAVAILLER.REG_CODE "
						+ 		"From VISITEUR "
						+ 			"Inner join TRAVAILLER "
						+ 				"On VISITEUR.VIS_MATRICULE = TRAVAILLER.VIS_MATRICULE " 
						+ 		"Where TRAVAILLER.JJMMAA = ("
						+ 									"Select max(JJMMAA) "
						+ 									"From TRAVAILLER T2 "
						+ 									"Where T2.VIS_MATRICULE = TRAVAILLER.VIS_MATRICULE ) "
						+ 		"And TRAVAILLER.TRA_ROLE = 'Delegue' "
						+ 		"And VISITEUR.VIS_LOGIN = ? "
						+ 		"And VISITEUR.VIS_MDP = ? " ;
	
	private  static final String requeteGetVisiteurs = 	"Select TRAVAILLER.VIS_MATRICULE, VISITEUR.VIS_NOM, VISITEUR.VIS_PRENOM "
						+ 					"From VISITEUR "
						+ 						"Inner join TRAVAILLER On VISITEUR.VIS_MATRICULE = TRAVAILLER.VIS_MATRICULE "
						+ 					"Where TRAVAILLER.JJMMAA = ("
						+ 												"Select max(JJMMAA) "
						+ 												"From TRAVAILLER T2 "
						+ 												"Where T2.VIS_MATRICULE = TRAVAILLER.VIS_MATRICULE) "
						+ 					"And TRAVAILLER.TRA_ROLE = 'Visiteur' "
						+ 					"And TRAVAILLER. REG_CODE = ?" ;

	private  static final String requeteGetRapports = "select RAP_NUM, PRA_NOM, PRA_VILLE, RAP_DATEVISITE, RAP_DATEREDACTION, RAP_LU "
			+ "from PRATICIEN, RAPPORT_VISITE "
			+ "where RAPPORT_VISITE.PRA_NUM = PRATICIEN.PRA_NUM "
			+ "and VIS_MATRICULE = ? "
			+ "and MONTH(RAP_DATEVISITE) = ? "
			+ "and YEAR(RAP_DATEVISITE) = ?";
	
	private static final String requeteGetPraticiens = "select PRA_NOM, PRA_VILLE from PRATICIEN order by PRA_NOM" ;
	
	private static final String requeteGetOffres = "select MED_NOMCOMMERCIAL, OFF_QUANTITE"
			+ " from OFFRIR, MEDICAMENT"
			+ " where OFFRIR.MED_DEPOTLEGAL = MEDICAMENT.MED_DEPOTLEGAL"
			+ " and VIS_MATRICULE = ?"
			+ " and RAP_NUM = ? " ;
	
	private static final String requeteGetPraticiensHesitantsCN = "select RP.PRA_NUM, PRA_NOM, PRA_VILLE, "
			+ "PRA_COEFNOTORIETE, RAP_COEFCONFIANCE, RAP_DATEVISITE "
			+ "from PRATICIEN as P, RAPPORT_VISITE as RP "
			+ "where RP.PRA_NUM = P.PRA_NUM "
			+ "and RP.RAP_DATEVISITE = ("
			+ "select MAX(RAP_DATEVISITE)"
			+ "from  RAPPORT_VISITE "
			+ "where RP.PRA_NUM = RAPPORT_VISITE.PRA_NUM"
			+ ")"
			+ "order by PRA_COEFNOTORIETE DESC;" ;
 
	private static final String requeteGetPraticiensHesitantsCC = "select RP.PRA_NUM, PRA_NOM, PRA_VILLE, "
			+ "PRA_COEFNOTORIETE, RAP_COEFCONFIANCE, RAP_DATEVISITE "
			+ "from PRATICIEN as P, RAPPORT_VISITE as RP "
			+ "where RP.PRA_NUM = P.PRA_NUM "
			+ "and RP.RAP_DATEVISITE = ("
			+ "select MAX(RAP_DATEVISITE)"
			+ "from  RAPPORT_VISITE "
			+ "where RP.PRA_NUM = RAPPORT_VISITE.PRA_NUM"
			+ ")"
			+ "order by RAP_COEFCONFIANCE DESC;" ;
	
	private static final String requeteGetPraticiensHesitantsTemps = "select RP.PRA_NUM, PRA_NOM, PRA_VILLE, "
			+ "PRA_COEFNOTORIETE, RAP_COEFCONFIANCE, RAP_DATEVISITE "
			+ "from PRATICIEN as P, RAPPORT_VISITE as RP "
			+ "where RP.PRA_NUM = P.PRA_NUM "
			+ "and RP.RAP_DATEVISITE = ("
			+ "select MAX(RAP_DATEVISITE)"
			+ "from  RAPPORT_VISITE "
			+ "where RP.PRA_NUM = RAPPORT_VISITE.PRA_NUM"
			+ ")"
			+ "order by RAP_DATEVISITE DESC;" ;
	
	
	private ModeleGSBCR() {
		super(); 
	}

	public static ModeleGSBCR getModele() {
		if (modele == null) {
			modele = new ModeleGSBCR() ;
		}
		return modele;
	}
	
	public boolean seConnecter(String login, String mdp) {
		System.out.println("ModeleGSBCR::seConnecter()");
		Connection idConnexion = ConnexionBD.getConnexion() ;
		PreparedStatement pstmt = null  ;
		String matricule = "" ;
		String region = "" ;
	
		try {
			pstmt = (PreparedStatement) idConnexion.prepareStatement(requeteConnexion) ;
			pstmt.setString(1, login);
			pstmt.setString(2, mdp);
			ResultSet resultat = pstmt.executeQuery() ;
			resultat.next() ;
			matricule = resultat.getString("VIS_MATRICULE");
			region = resultat.getString("TRAVAILLER.REG_CODE");
			
			Delegue delegue = new Delegue(matricule, region) ;
			Session.ouvrirSession(delegue);
			System.out.println("MATRICULE : " + matricule + "\tREGION : " + region) ;
			return true;
		} 
		catch (SQLException e) {
			return false ;
		}
	}
	
	
	public List<Praticien> getLesPraticiensParCoefNot() {
		System.out.println("  getLesPraticiensParCoefNot") ;
		Connection idConnexion = ConnexionBD.getConnexion() ;
		PreparedStatement pstmt = null  ;
		
		List<Praticien> lesPraticiens = new ArrayList<Praticien>();
		
		try {
			pstmt = (PreparedStatement) idConnexion.prepareStatement(requeteGetPraticiensHesitantsCN) ;
			ResultSet resultat = pstmt.executeQuery() ;
			
			
			while (resultat.next()) {
				String unNom = resultat.getString("PRA_NOM") ;
				String uneVille = resultat.getString("PRA_VILLE") ;
				double unCoefNot = resultat.getDouble("PRA_COEFNOTORIETE") ;
				int unCoefConfiance = resultat.getInt("RAP_COEFCONFIANCE") ;
				String uneDateEn = resultat.getString("RAP_DATEVISITE") ;
				DateFR uneDate = DateFR.parseStringEn(uneDateEn) ;
				
				Praticien unPraticien = new Praticien() ;
				unPraticien.setNom(unNom) ;
				unPraticien.setVille(uneVille);
				unPraticien.setCoefNot(unCoefNot);
				unPraticien.setCoefConf(unCoefConfiance);
				unPraticien.setDerniereVisite(uneDate) ;
				
				lesPraticiens.add(unPraticien) ;
			}
			
		}
		catch (SQLException e){
			System.out.println("Erreur !!!") ;
		}
		return lesPraticiens ;
	}
	
	public List<Praticien> getLesPraticiensParCoefConf() {
		Connection idConnexion = ConnexionBD.getConnexion() ;
		PreparedStatement pstmt = null  ;
		
		List<Praticien> lesPraticiens = new ArrayList<Praticien>();
		
		try {
			pstmt = (PreparedStatement) idConnexion.prepareStatement(requeteGetPraticiensHesitantsCC) ;
			ResultSet resultat = pstmt.executeQuery() ;
			
			
			while (resultat.next()) {
				String unNom = resultat.getString("PRA_NOM") ;
				String uneVille = resultat.getString("PRA_VILLE") ;
				double unCoefNot = resultat.getDouble("PRA_COEFNOTORIETE") ;
				int unCoefConfiance = resultat.getInt("RAP_COEFCONFIANCE") ;
				String uneDateEn = resultat.getString("RAP_DATEVISITE") ;
				DateFR uneDate = DateFR.parseStringEn(uneDateEn) ;
				
				Praticien unPraticien = new Praticien() ;
				unPraticien.setNom(unNom) ;
				unPraticien.setVille(uneVille);
				unPraticien.setCoefNot(unCoefNot);
				unPraticien.setCoefConf(unCoefConfiance);
				unPraticien.setDerniereVisite(uneDate) ;
				
				lesPraticiens.add(unPraticien) ;
			}
			
		}
		catch (SQLException e){
			System.out.println("Erreur !!!") ;
		}
		return lesPraticiens ;
	}
	
	public List<Praticien> getLesPraticiensParTemps() {
		Connection idConnexion = ConnexionBD.getConnexion() ;
		PreparedStatement pstmt = null  ;
		
		List<Praticien> lesPraticiens = new ArrayList<Praticien>();
		
		try {
			pstmt = (PreparedStatement) idConnexion.prepareStatement(requeteGetPraticiensHesitantsTemps) ;
			ResultSet resultat = pstmt.executeQuery() ;
			
			
			while (resultat.next()) {
				String unNom = resultat.getString("PRA_NOM") ;
				String uneVille = resultat.getString("PRA_VILLE") ;
				double unCoefNot = resultat.getDouble("PRA_COEFNOTORIETE") ;
				int unCoefConfiance = resultat.getInt("RAP_COEFCONFIANCE") ;
				String uneDateEn = resultat.getString("RAP_DATEVISITE") ;
				DateFR uneDate = DateFR.parseStringEn(uneDateEn) ;
				
				Praticien unPraticien = new Praticien() ;
				unPraticien.setNom(unNom) ;
				unPraticien.setVille(uneVille);
				unPraticien.setCoefNot(unCoefNot);
				unPraticien.setCoefConf(unCoefConfiance);
				unPraticien.setDerniereVisite(uneDate) ;
				
				lesPraticiens.add(unPraticien) ;
			}
			
		}
		catch (SQLException e){
			System.out.println("Erreur !!!") ;
		}
		return lesPraticiens ;
	}
	
	public List<Visiteur> getLesVisiteurs(String codeRegion) {
		Connection idConnexion = ConnexionBD.getConnexion() ;
		PreparedStatement pstmt = null  ;
		
		List<Visiteur> lesVisiteurs = new ArrayList<Visiteur>();
		
		try {
			pstmt = (PreparedStatement) idConnexion.prepareStatement(requeteGetVisiteurs) ;
			pstmt.setString(1, codeRegion);
			ResultSet resultat = pstmt.executeQuery() ;
			
			
			while (resultat.next()) {
				String unMatricule = resultat.getString("VIS_MATRICULE") ;
				String unNom = resultat.getString("VIS_NOM") ;
				String unPrenom = resultat.getString("VIS_PRENOM") ;
				
				Visiteur unVisiteur = new Visiteur(unMatricule, unNom, unPrenom) ;
				
				lesVisiteurs.add(unVisiteur) ;
			}
			
		}
		catch (SQLException e){
			
		}
		return lesVisiteurs ;
	}

	public ModeleListeVisiteurOld getModeleListeVisiteurs() {
		return modeleListeVisiteurs;
	}
	
	public List<Offre> getOffres(Rapport rapport) {
		List<Offre> lesOffres = new ArrayList<Offre>() ;
		
		Connection idConnexion = ConnexionBD.getConnexion() ;
		PreparedStatement pstmt = null ;
		
		try {
			pstmt = (PreparedStatement) idConnexion.prepareStatement(requeteGetOffres) ;
			pstmt.setString(1, rapport.getLeVisiteur().getMatricule());
			pstmt.setInt(2, rapport.getNumero());
			System.out.println(pstmt.toString());
			ResultSet resultat = pstmt.executeQuery() ;
			
			while (resultat.next()) {
				
				String nomMedicament = resultat.getString("MED_NOMCOMMERCIAL") ;
				int quantite = resultat.getInt("OFF_QUANTITE") ;
				
				Offre uneOffre = new Offre(nomMedicament, quantite) ;
				lesOffres.add(uneOffre) ;
			}
		}
		catch (SQLException e){
			System.out.println(e.getMessage());
			
			e.printStackTrace();
		}
		
		System.out.println("-> Les offres : ") ;
		
		for(Offre uneOffre : lesOffres) {
			System.out.println(uneOffre) ;
		}
		
		return lesOffres ;
		
		
	}
	
	public List<Rapport> getRapports(Visiteur unVisiteur, Integer mois, Integer annee) {
		List<Rapport> lesRapports = new ArrayList<Rapport>() ;
		
		Connection idConnexion = ConnexionBD.getConnexion() ;
		PreparedStatement pstmt = null  ;
		
		try {
			pstmt = (PreparedStatement) idConnexion.prepareStatement(requeteGetRapports) ;
			pstmt.setString(1, unVisiteur.getMatricule());
			pstmt.setInt(2, mois);
			pstmt.setInt(3, annee);
			System.out.println(pstmt.toString());
			ResultSet resultat = pstmt.executeQuery() ;
			
			
			while (resultat.next()) {
				
				int numero = resultat.getInt("RAP_NUM") ;
				String nom = resultat.getString("PRA_NOM") ;
				String ville = resultat.getString("PRA_VILLE") ;
				
				Date dateVisite = resultat.getDate("RAP_DATEVISITE") ;
				
				Date dateRedaction = null ;
				try {
					dateRedaction = resultat.getDate("RAP_DATEREDACTION") ;
				}
				catch(SQLException e){
//					if(resultat.wasNull()){
//						dateRedaction = null ;
//					}
//					else {
//						e.printStackTrace();
//					}
				}
				
				boolean lu = resultat.getBoolean("RAP_LU") ;
				
				Rapport rapport = new Rapport() ;
				
				rapport.setNumero(numero);
				
				Praticien praticien = new Praticien() ;	
				praticien.setNom(nom);					
				praticien.setVille(ville);				
				
				rapport.setLePraticien(praticien) ;	
				rapport.setLeVisiteur(unVisiteur) ;
				
				rapport.setDateVisite(new DateFR(dateVisite));
				if(dateRedaction != null){
					rapport.setDateRedaction(new DateFR(dateRedaction));
				}
				rapport.setLu(lu); 
				
				lesRapports.add(rapport) ;
			}
			
		}
		catch (SQLException e){
			System.out.println(e.getMessage());
			
			e.printStackTrace();
		}
		
		System.out.println("-> Les rapports : ") ;
		
		for(Rapport unRapport : lesRapports) {
			System.out.println(unRapport) ;
		}
		
		return lesRapports ;
	}
	
	public void setRapportLu(Rapport unRapport) throws SQLException{
		
		Connection idConnexion = ConnexionBD.getConnexion() ;
		String requete = "update RAPPORT_VISITE set RAP_LU = 1 where RAP_NUM = " + unRapport.getNumero() ;
		Statement stmt = (Statement) idConnexion.createStatement() ;
		int resultat = stmt.executeUpdate(requete) ;
	}
	
	public Rapport getRapport(int numero){
		//...
		return null ;
	}
	
}
