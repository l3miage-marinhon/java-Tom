package trigenerique;

public class Heure implements Comparable{
	private int h;
	private int min;
	private int sec;
	
	public Heure(int heure,int minute, int seconde) {
		this.h=(heure>=0 && heure<=23) ? h : 0 ;
		this.sec=(seconde>=0 && seconde<=59) ? seconde : 0;
		this.min=(minute>=0 && minute<=59) ? minute :0 ;
		
	}
	
	@Override
	public boolean estInferieur(Comparable comp) {
		boolean estInferieur =false;
		if( comp instanceof Heure) {
			Heure h=(Heure) comp;
			if(h.h>this.h)estInferieur=true;
			else if(h.h==this.h) {
				if(h.min>min)estInferieur=true;
				else if(h.min==min) {
					if(h.sec>sec)estInferieur=true;
				}
			}
		}
		return estInferieur;
	}
	
	@Override
	public String toString() {
		return h+":"+min+":"+sec;
	}
}