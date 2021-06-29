package main;

import fileAttente.FileAttente;
import fileAttente.Personne;

public class Main {
	public static void main(String[] args) {
		Personne p1 = new Personne("Michel", 39);
		Personne p2 = new Personne("Beebou", 19);
		Personne p3 = new Personne("Wesh", 5);
		FileAttente<Personne> f1 = new FileAttente<>();
		f1.entre(p1);
		f1.entre(p2);
		f1.entre(p3);
		System.out.println(f1);
		Personne psort = f1.sort();
		System.out.println(f1);
		System.out.println(psort + " est sorti de la file");
		
	}
}
