package fr.gsb.applicrdr.modeles;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import fr.gsb.applicrdr.entites.Delegue;
import fr.gsb.applicrdr.entites.Rapport;
import fr.gsb.applicrdr.entites.Visiteur;
import fr.gsb.applicrdr.technique.ConnexionBD;
import fr.gsb.applicrdr.technique.Session;

public class ModeleListeVisiteurOld {
	private List<Visiteur> lesVisiteurs = ModeleGSBCR.getModele().getLesVisiteurs("RA");
//	private List<Visiteur> lesVisiteurs = new ArrayList<Visiteur>() ;
	private static ModeleListeVisiteurOld modele = null ;
	private String requeteGetVisiteurs = 	"Select TRAVAILLER.VIS_MATRICULE, VISITEUR.VIS_NOM, VISITEUR.VIS_PRENOM "
			+ 					"From VISITEUR "
			+ 						"Inner join TRAVAILLER On VISITEUR.VIS_MATRICULE = TRAVAILLER.VIS_MATRICULE "
			+ 					"Where TRAVAILLER.JJMMAA = ("
			+ 												"Select max(JJMMAA) "
			+ 												"From TRAVAILLER T2 "
			+ 												"Where T2.VIS_MATRICULE = TRAVAILLER.VIS_MATRICULE) "
			+ 					"And TRAVAILLER.TRA_ROLE = 'Visiteur' "
			+ 					"And TRAVAILLER. REG_CODE = ?" ;
	
	
	private String requeteGetRapports = "SELECT * "
										+ "FROM RAPPORT_VISITE "
										+ "WHERE VIS_MATRICULE = '?' " ;
	
	
	public ModeleListeVisiteurOld() {
		super() ;
		System.out.println("ModeleListeVisiteurs::ModeleListeVisiteur()") ;
	}

	public static ModeleListeVisiteurOld getModele() {
		if(modele == null ) {
			modele = new ModeleListeVisiteurOld() ;
		}
		return modele ;
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
	
	public List<Rapport> getRapport(String matricule) {
		
		Connection idConnexion = ConnexionBD.getConnexion() ;
		PreparedStatement pstmt = null  ;
		
		List<Rapport> lesRapports = new ArrayList<Rapport>();
		
		try {
			pstmt = (PreparedStatement) idConnexion.prepareStatement(requeteGetRapports) ;
			pstmt.setString(1, matricule);
			ResultSet resultat = pstmt.executeQuery() ;
			
			
			while (resultat.next()) {
				Rapport unRapport = new Rapport() ;
			
//				unRapport.setMatricule(resultat.getString("VIS_MATRICULE")) ;
//				unRapport.setNumeroRapport(resultat.getInt("RAP_NUM")) ;
//				unRapport.setNumeroPraticien(resultat.getInt("PRA_NUM")) ;
//				unRapport.setBilan(resultat.getString("RAP_BILAN")) ;
//				unRapport.setMotif(resultat.getString("RAP_MOTIF")) ;
//				unRapport.setCoefConfiance(resultat.getInt("RAP_COEFCONFIANCE")) ;
//				unRapport.setDateVisite(resultat.getDate("RAP_DATEVISITE")) ;
//				unRapport.setDateRedaction(resultat.getDate("RAP_DATEREDACTION")) ;
				unRapport.setLu(resultat.getBoolean("RAP_LU")) ;
				
				lesRapports.add(unRapport) ;
			}
			
		}
		catch (SQLException e){
			
		}
		return lesRapports ;
		
	}
	
	public void actualiser(){
		System.out.println("ModeleListeVisiteurs::actualiser()") ;
		
		if(Session.getSession() != null){
			String codeRegion = Session.getSession().getDelegue().getCodeRegion() ;
			this.lesVisiteurs = ModeleGSBCR.getModele().getLesVisiteurs(codeRegion); 
			System.out.println("Visiteurs :") ;
			for(Visiteur unVisiteur : this.lesVisiteurs){
				System.out.println("\t" + unVisiteur) ;
			}
		}
		else {
			this.lesVisiteurs = new ArrayList<Visiteur>() ;
		}
		
	}
	
	
	
}
