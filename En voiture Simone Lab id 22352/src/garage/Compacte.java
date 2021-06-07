package garage;

// TODO Ecrire la classe Compacte
// 
public class Compacte extends Vehicule {
	private final static  double CONSOMMATION=5.8;
	private final static  double CAPACITE = 50;
	private static final int IMMATPREFIX=2000;
	
	public Compacte() {
		super(CONSOMMATION,CAPACITE);
		setImmatriculation();
	}
	
	protected void setImmatriculation(){
		immatriculation=IMMATPREFIX+getImatCompteur();
	}
}