package Pokedex;
import java.util.Locale;

public class Sportif extends Terrestre{
	private int freqCardiaque;

	public Sportif(String nom, double poids, int nbPattes, double taille, int freqCardiaque) {
		super(nom, poids, nbPattes, taille);
		setFreqCardiaque(freqCardiaque);
	}

	public int getFreqCardiaque() {
		return freqCardiaque;
	}

	public void setFreqCardiaque(int freqCardiaque) {
		this.freqCardiaque = (freqCardiaque<=0 ? 60 : freqCardiaque);
	}
	
	@Override
	public String toString() {
		return super.toString() + ", et ma fréquence cardiaque est de " + getFreqCardiaque() + "ppm";
	}
	
	@Override
	public void yo() {
		System.out.println("yo, vitesse getVitesse poke sportif = " + String.format(Locale.US, "%.2f", getVitesse()));
	}
}
