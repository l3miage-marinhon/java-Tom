package quincaillerie;

// TODO Question 5: écrire la classe PieceCompositeEnKit.
// une pieces composite en kit est une pieces qui a besoins d'un certain temps de montage
//son prix est la somme de toutes ses pièces et son temps de garantie et celui de base /2 
public class PieceCompositeEnKit extends PieceComposite{
	protected int dureeMontage;
	
	public PieceCompositeEnKit(String nom, String reference, int dureeMontage) {
		super(nom, reference);
		this.dureeMontage=dureeMontage;
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setReference(String reference) {
		// TODO Auto-generated method stub
		//faire le test du debut de la chaine 
		
	}

	@Override
	public void affiche() {
		// TODO Auto-generated method stub
		System.out.println("nom : "+super.nom);
        System.out.println("référence : "+super.reference);
        System.out.println("prix : "+this.prix()+" euros");
        System.out.println("durée de garantie : "+this.dureeGarantie()+" mois");
        System.out.println("durée de fabrication : "+this.dureeFabrication()+" jour(s)");
        System.out.println("durée de montage particulier : "+this.dureeMontage+" jour(s)");
        System.out.println("composants : ");
        for(Piece p :liste) {
        	System.out.println("\t"+p.toString());
        }
        System.out.println("\n");
	}

	@Override
	public double prix() {
		// TODO Auto-generated method stub
		double somme=0;
		for(Piece p : liste) {
			somme+=p.prix();
		}
		return somme;
	}

	@Override
	public int dureeGarantie() {
		// TODO Auto-generated method stub
		return super.dureeGarantieDeBase()/2;
	}
	

	
}