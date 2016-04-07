package fr.gsb.applicrdr.technique;

import fr.gsb.applicrdr.entites.Delegue;

public class Session {

	private static Session session = null;
	private Delegue delegue ;
	
	
	private Session(Delegue delegue) {
		super();
		this.delegue = delegue;
	}
	
	public static void ouvrirSession(Delegue delegue) {
		session = new Session(delegue) ;
	}
	
	public static void fermerSession() {
		session = null ;
	}
	
	@Override
	public String toString() {
		return "Session [delegue=" + delegue + "]";
	}

	public static Session getSession() {
		return session ;
	}

	public Delegue getDelegue() {
		return delegue;
	}
}
