package main;

import classes.*;

public class main {
	public static void main(String[] args) {
		Vehicule v1 = new Vehicule(35.0, 1352);
		System.out.println(v1);
		Vehicule v2 = new Avion(800.0, 73, 8);
		System.out.println(v2);
		Vehicule v3 = new Avion(800, 73, 6);
		System.out.println(v3);
		if(v3 instanceof Voiture) System.out.println("nbmoteur v3 : " + ((Avion)v3).getNbMoteur());
		Avion v4 = new Avion(-354.3, 42, 3);
		System.out.println(v4);
		Avion v5 = new Avion(800, 73, 2);
		System.out.println(v5);
		Avion v6 = new Avion(800, 73, 6);
		System.out.println(v6);
	}
}
