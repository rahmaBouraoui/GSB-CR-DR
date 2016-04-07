package fr.gsb.applicrdr.entites;

public class Delegue {
	
	private String matricule;
	private String codeRegion ;
	
	@Override
	public String toString() {
		return "Delegue [matricule=" + matricule + ", codeRegion=" + codeRegion + "]";
	}

	public Delegue(String matricule, String codeRegion) {
		super();
		this.matricule = matricule;
		this.codeRegion = codeRegion;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public String getCodeRegion() {
		return codeRegion;
	}

	public void setCodeRegion(String codeRegion) {
		this.codeRegion = codeRegion;
	}	
	
}
