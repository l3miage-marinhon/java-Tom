package trigenerique;

public class TestTableauEntier {

    public static void main(String[] args) {
        int[] tab = { 10, 2, 6, 11, 7, 2, -1, 0, 9 };
        TableauEntiers obj = new TableauEntiers(tab);
        obj.affichage();
        obj.triBulles();
        obj.affichage();
    }
}
