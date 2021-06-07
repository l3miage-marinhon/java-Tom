package quincaillerie;

// TODO Question 13: écrire la classe PieceCompositeEnMontee.
// 
public class PieceCompositeMontee extends PieceComposite{
	protected int dureeMontage;
	protected double prixMontage;
	
	public PieceCompositeMontee(String nom, String reference, int dureeMontage, double prixMontage) {
		super(nom, reference);
		this.dureeMontage=dureeMontage;
		this.prixMontage=prixMontage;
		
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
        System.out.println("durée de garantie : "+dureeGarantie()+" mois");
        System.out.println("durée de fabrication : "+(dureeFabrication()+dureeMontage)+" jour(s)");
        System.out.println("durée de montage : "+this.dureeMontage+" jour(s)");
        System.out.println("prix du montage : "+this.prixMontage+" euros");
        System.out.println("composants : ");
        for(Piece p :liste) {
        	System.out.println("\t"+p.toString());
		}	
        System.out.println();
	}
	
	
	@Override
	public double prix() {
		// TODO Auto-generated method stub
		double somme=this.prixMontage;
		for(Piece p :super.liste) {
			somme+=p.prix();
		}
		return somme;
	}
	@Override
	public int dureeGarantie() {
		// TODO Auto-generated method stub
		return super.dureeGarantieDeBase()+6;
	}
	
	
}