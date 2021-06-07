package bataille;

public enum Valeur {
	SEPT("sept"),
	HUIT("huit"),
	NEUF("neuf"),
	DIX("dix"),
	VALET("valet"),
	DAME("dame"),
	ROI("roi"),
	AS("as");
	
	private String nom;
	
	private Valeur(String nom) {
		this.nom=nom;
	}
	
	public String getNom(){
		return nom;
	}
}
