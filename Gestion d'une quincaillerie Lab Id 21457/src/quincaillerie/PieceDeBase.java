package quincaillerie;

// TODO Question 2: Ecrire la classe PieceDeBase.
// 
public class PieceDeBase extends Piece{
	protected double prix;
	protected int dureeGarantie;
	protected int dureeFabrication;
	
	public PieceDeBase(String nom, String reference,double prix,int dureeGarantie,int dureeFabrication) {
		super(nom, reference);
		prix >0 ?this.prix=prix : this.prix=0;
		this.dureeGarantie=dureeGarantie;
		this.dureeFabrication=dureeFabrication;
		// TODO Auto-generated constructor stub
	}


	@Override
	protected void setReference(String reference) {
		// TODO Auto-generated method stub
		this.reference = reference;
	}
	
	@Override
	public void affiche() {
		// TODO Auto-generated method stub
		System.out.println("nom : "+super.nom);
		System.out.println("référence : "+super.reference);
		System.out.println("prix : "+prix()+" euros");
		System.out.println("garantie : "+dureeGarantie()+" mois");
		System.out.println("durée de fabrication : "+dureeFabrication()+" jour(s)");
		System.out.println();
		
	}

	@Override
	public double prix() {
		// TODO Auto-generated method stub
		return this.prix;
	}

	@Override
	public int dureeGarantie() {
		// TODO Auto-generated method stub
		return dureeGarantie;
	}

	@Override
	public int dureeGarantieDeBase() {
		// TODO Auto-generated method stub
		return  dureeGarantie;
	}

	@Override
	public int dureeFabrication() {
		// TODO Auto-generated method stub
		return dureeFabrication;
	}

}