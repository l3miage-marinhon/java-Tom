package trigenerique;

public class TestTableau {

    public static void main(String[] args) {
		Heure[] tab = new Heure[5];
		
		tab[0] = new Heure(20, 8, 10);
		tab[1] = new Heure(12, 25, 4);
		tab[2] = new Heure(20, 8, 15);
		tab[3] = new Heure(0, 25, 6);
		tab[4] = new Heure(-2, 8, 17);

        Tableau<Heure> obj = new Tableau<Heure>(tab);
        obj.affichage();
        obj.triBulles();
        obj.affichage();

    }
}
