package fr.gsb.applicrdr.technique;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/** Représenter une date
 * @author xilim
 *
 */
public class DateFR extends GregorianCalendar {

	private static final long serialVersionUID = 1L;
	
	/** Constructeur
	 * @param jour Le numéro du jour
	 * @param mois Le numéro du mois
	 * @param annee Le numéro de l'année
	 */
	public DateFR(int jour,int mois, int annee){
		super(annee,mois-1,jour) ;
	}
	
	/** Constructeur par recopie
	 * @param date La date à recopier
	 */
	public DateFR(DateFR date){
		super(date.get(Calendar.YEAR),date.get(Calendar.MONTH),date.get(Calendar.DAY_OF_MONTH)) ;
	}
	
	public DateFR(Date date){
		super() ;
		this.setTime(date);
	}
	
	/** Constructeur (initialisation avec la date du jour courant)
	 * 
	 */
	public DateFR(){
		super(new GregorianCalendar().get(Calendar.YEAR),new GregorianCalendar().get(Calendar.MONTH),new GregorianCalendar().get(Calendar.DAY_OF_MONTH)) ;
	}
	
	public int getJour(){
		return this.get(Calendar.DAY_OF_MONTH) ;
	}
	
	public int getMois(){
		return this.get(Calendar.MONTH) + 1 ;
	}
	
	public int getAnnee(){
		return this.get(Calendar.YEAR) ;
	}
	
	/** La date est elle antérieure à une autre date ?
	 * @param autre L'autre date
	 * @return true si la date est antérieure à l'autre date et false dans le cas contraire
	 */
	public boolean estAnterieure(DateFR autre){
		if(this.before(autre)){
			return true ;
		}
		else {
			return false ;
		}
	}
	
	/** La date est elle postérieure à une autre date ?
	 * @param autre L'autre date
	 * @return true si la date est postérieure à l'autre date et false dans le cas contraire
	 */
	public boolean estPosterieure(DateFR autre){
		if(this.after(autre)){
			return true ;
		}
		else {
			return false ;
		}
	}
	
	/** La date est elle identique à une autre date ?
	 * @param autre L'autre date
	 * @return true si la date est identique à l'autre date et false dans le cas contraire
	 */
	public boolean estIdentique(DateFR autre){
		if(this.getJour() == autre.getJour() && this.getMois() == autre.getMois() && this.getAnnee() == autre.getAnnee()){
			return true ;
		}
		else {
			return false ;
		}
	}
	
	/** Convertir une chaîne de caractères en date
	 * 
	 * @param date La chaîne de caractères au format "JJ/MM/AAAA"
	 * @return La date
	 */
	public static DateFR parseString(String date){
		String [] champs = date.split("/") ;
		int jour = Integer.parseInt(champs[0]) ;
		int mois = Integer.parseInt(champs[1])  ;
		int annee = Integer.parseInt(champs[2]) ;
		return new DateFR(jour,mois,annee) ;
	}
	
	
	/** Convertir une chaîne de caractères en date EN
	 * 
	 * @param date La chaîne de caractères au format "AAAA/MM/JJ"
	 * @return La date
	 */
	public static DateFR parseStringEn(String date){
		String [] champs = date.split("-") ;
		int jour = Integer.parseInt(champs[2]) ;
		int mois = Integer.parseInt(champs[1])  ;
		int annee = Integer.parseInt(champs[0]) ;
		return new DateFR(jour,mois,annee) ;
	}
	
	/** Vérifier le format "JJ/MM/AAAA"
	 * @param source La chaîne sensée représenter une date
	 * @return true si la chaîne respecte le format "JJ/MM/AAAA" et false dans le cas contraire
	 */
	public static boolean estDate(String source){
		if(source.length() != 10){
			return false ;
		}
		else {
			try {
				int jour = Integer.parseInt(source.substring(0,2)) ;
				int mois = Integer.parseInt(source.substring(3,5)) - 1 ;
				int annee = Integer.parseInt(source.substring(6)) ;
				if(jour >= 1 && mois >= 0 && annee >= 1){
					new GregorianCalendar(annee,mois,jour) ;
					return true ;
				}
				else {
					return false ;
				}
			}
			catch(Exception e){
				return false ;
			}
		}
	}

	/* (non-Javadoc)
	 * @see java.util.Calendar#toString()
	 */
	@Override
	public String toString() {
		return String.format("%02d/%02d/%04d",this.get(Calendar.DAY_OF_MONTH),this.get(Calendar.MONTH)+1,this.get(Calendar.YEAR)) ;
	}
	
}