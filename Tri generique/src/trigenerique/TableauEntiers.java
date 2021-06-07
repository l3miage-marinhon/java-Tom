package trigenerique;

public class TableauEntiers {

    private int[] tab;

    public TableauEntiers(int[] tab) {
        // on fait ici une recopie "superficielle"
        this.tab = tab;
    }

    public void affichage() {
        int i;
        String s = "";
        for (i = 0; i < tab.length; i++) s = s + "tab[" + i + "]=" + tab[i] + "\n";
        System.out.println(s);
    }

    public void triBulles() {
        int i, j;
        for (i = 0; i <= tab.length - 2; i++) {
            for (j = tab.length - 1; j > i; j--) {
                if (tab[j] < tab[j - 1]) {
                    int aux = tab[j];
                    tab[j] = tab[j - 1];
                    tab[j - 1] = aux;
                }
            }
        }
    }
}
