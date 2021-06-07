package garage;

// TODO Ecrire la classe Citadine
// 
public class Citadine extends Vehicule {
	private final static  double CONSOMMATION=5.2;
	private final static  double CAPACITE = 40.0;
	private static final int IMMATPREFIX=1000;
	
	public Citadine() {
		super(CONSOMMATION,CAPACITE);
		setImmatriculation();
	}
	
	protected void setImmatriculation(){
		immatriculation=IMMATPREFIX+getImatCompteur();
	}
}