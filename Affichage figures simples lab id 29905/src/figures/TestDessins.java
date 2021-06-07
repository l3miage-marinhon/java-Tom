package figures;

public class TestDessins {

    public static void main(String[] args) {
        DessinateurFigures fig = new DessinateurFigures();
        System.out.println("Dessin d'un carré de 5x7: ");
        fig.rectangle(3, 4);
        System.out.println("Dessin d'un triangle gauche de 8: ");
        fig.triangleGauche(8);
        System.out.println("Dessin d'un triangle droite de 7: ");
        fig.triangleDroite(7);
        System.out.println("Dessin d'un triangle isocelle de 6: ");
        fig.triangleIso(4);
    }
}
