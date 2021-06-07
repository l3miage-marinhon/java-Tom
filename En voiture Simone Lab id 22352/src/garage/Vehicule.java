package garage;

import java.text.DecimalFormat;

// TODO Ecrire la classe Vehicule

public abstract class Vehicule implements Comparable<Vehicule> {
	static private int imatCompteur;
	protected int immatriculation;
	private double jauge;
	private double consomation;
	private double capaciteReservoir;
	private Compteur compteur;
	
	protected Vehicule(double consomation, double capacite) {
			this.consomation=consomation;
			this.capaciteReservoir=capacite;
			this.jauge=0;
			this.compteur = new Compteur();
			imatCompteur++;
	}

	protected static int getImatCompteur() {
		return imatCompteur;
	}

	public int getImmatriculation() {
		return immatriculation;
	}

	public double getConsomation() {
		return consomation;
	}

	public double getCompteurTotalisateur() {
		return compteur.getTotalisateur();
	}
	public double getCompteurPartiel() {
		return compteur.getPartiel();
	}
	
	@Override
	public String toString() {
		DecimalFormat dfTotal = new DecimalFormat("0.00");
		String str="";
		str+="Véhicule ";
		str+=getImmatriculation();
		str+=" ("+dfTotal.format(getConsomation())+"L/100km) : ";
		str+=compteur.toString();
		str+=" ; jauge = ";
		str+=dfTotal.format(jauge)+"L";
		return str;
	}
	
	public void faireLePlein() {
		jauge=capaciteReservoir;
	}
	public double rouler(double distance) {
		if(jauge/getConsomation()*100 > distance) {
			compteur.add(distance);
			jauge-=(distance*getConsomation())/100;
			return distance; 
		}else {
			compteur.add((jauge/getConsomation())*100);
			double distancemax=jauge/getConsomation()*100;
			jauge=0;
			return distancemax;
		}

	}
	
	protected abstract void setImmatriculation();
	
	
	@Override
	public int compareTo(Vehicule v) {
		if(getCompteurTotalisateur() < v.getCompteurTotalisateur())
            return -1;
        if(getCompteurTotalisateur() > v.getCompteurTotalisateur())
            return 1;
        if(getCompteurTotalisateur() == v.getCompteurTotalisateur())
            return 0;
        return 0;
	}

}
// 
