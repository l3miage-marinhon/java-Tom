package garage;

// TODO Ecrire la classe QuatreQuatre
// 
public class QuatreQuatre extends Vehicule {
	private final static  double CONSOMMATION=7.7;
	private final static  double CAPACITE = 80;
	private static final int IMMATPREFIX=4000;
	
	public QuatreQuatre() {
		super(CONSOMMATION,CAPACITE);
		setImmatriculation();
	}
	
	protected void setImmatriculation(){
		immatriculation=IMMATPREFIX+getImatCompteur();
	}
}