package triSelection;

public class TableauEntiers {
	private int [] t;
 
    public TableauEntiers(int taille, int min, int max) {
        int i;
        t = new int[taille];
        for(i=0;i<t.length;i++)
            t[i] = Outils.nombreAleatoire(min, max);
    }
 
    public void affichage(){
        int i;
        String s="";
        for(i=0;i<t.length;i++)
            s = s + t[i] + " ";
        System.out.println(s);
    }
    public int positionMinimum(int debut) {
    	int indice_min=debut;
    	int valeur_min=t[debut];
    	for(int i=debut+1;i<t.length;i++) {
    		if(valeur_min>t[i]) {
    			valeur_min=t[i];
    			indice_min=i;
    		}
    	}
    	return indice_min;
    }
    public void echanger(int i, int j) {
    	int tmp=t[i];
    	t[i]=t[j];
    	t[j]=tmp;
    }
    void triSelection(){
    	for(int i=0;i<t.length;i++) {
    		echanger(i,positionMinimum(i));
    	}
    }
}
