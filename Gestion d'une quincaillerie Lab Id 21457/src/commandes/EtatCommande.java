package commandes;

public enum EtatCommande {
	
	Acceptation("En cours d'acceptation"), Preparation("En cours de préparation"), 
	Livraison("En cours de livraison"), Terminee("Commande terminée");
	
	private String nom;
	
	private EtatCommande(String nom) {
		this.nom = nom;
	}
	
	@Override
	public String toString() {
		return nom;
	}
}
