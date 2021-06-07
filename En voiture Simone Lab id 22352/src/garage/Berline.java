package garage;

// TODO Ecrire la classe Berline
// 
public class Berline extends Vehicule {
	private final static  double CONSOMMATION=6.4;
	private final static  double CAPACITE = 60;
	private static final int IMMATPREFIX=3000;
	
	public Berline() {
		super(CONSOMMATION,CAPACITE);
		setImmatriculation();
	}
	
	protected void setImmatriculation(){
		immatriculation=IMMATPREFIX+getImatCompteur();
	}
}