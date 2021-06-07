package apobanque;

class CompteBancaire {
	int numero;
	String client;
	double solde;
	double decouvertAutorise;
	
	CompteBancaire(){
		numero=0;
		client="John Doe";
		solde=0;
		decouvertAutorise=0;
	}
	CompteBancaire(int numero,String nom){
		this.numero=numero;
		this.client=nom;
		this.solde=100;
		this.decouvertAutorise=50;
	}
	CompteBancaire(int numero,String nom,double somme){
		this.numero=numero;
		this.client=nom;
		this.solde=somme;
		this.decouvertAutorise=somme/2;
	}
	
	void depot(double somme) {
		if(somme>0)this.solde+=somme;
	}
	
	boolean retrait(double somme) {
		if(somme>0) {
			if(somme>solde+decouvertAutorise) {
				solde=-(decouvertAutorise);
				return false;
			}
			else {
				solde-=somme;
				return true;
			}
		}else { 
			return false;
		}
	}
	
	boolean estADecouvert() {
		return solde<0;
	}
	
	int getNumero() {
		return numero;
	}

	String getClient() {
		return client;
	}
	double getSolde() {
		return solde;
	}
	String infos() {
		String info="----------------------------------------\n";
		info+="Client : "+client+"\n";
		info+="Numéro du compte: "+numero+'\n';
		if(solde>0) {
			info+="Solde créditeur : "+solde+'\n';
		}else {
			info+="Solde débiteur : "+solde+'\n';
		}
		info+="Découvert autorisé : "+decouvertAutorise+'\n';
		info+="----------------------------------------\n";
		return info;
	}
}
