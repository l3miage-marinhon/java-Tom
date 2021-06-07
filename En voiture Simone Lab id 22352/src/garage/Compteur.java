package garage;

import java.text.DecimalFormat;

public class Compteur {
	
	static private int RAZ=1000;
	private double totalisateur;
	private double partiel;
	
	public Compteur() {
		this.totalisateur=0;
		this.partiel=0;
	}
	public double getTotalisateur() {
		return totalisateur;
	}

	public double getPartiel() {
		return partiel;
	}
	
	public void resetPartiel() {
		partiel=0;
	}

	public void  add(double km) {
		if(getPartiel()+km >= RAZ) {
			this.totalisateur+=km;
			double newPartiel= getPartiel()+km-RAZ;
			this.partiel=newPartiel;
		}else {
			this.partiel+=km;
			this.totalisateur+=km;
		}
	}
	@Override
	public String toString() {
		DecimalFormat dfTotal = new DecimalFormat("00000.00");
        DecimalFormat dfPartiel = new DecimalFormat("000.00");
        String s = "compteur : [ total = ";
        s += dfTotal.format(getTotalisateur());
        s += "km | partiel = ";
        s += dfPartiel.format(getPartiel());
        s += "km ]";
        return s;
        }
	
}
