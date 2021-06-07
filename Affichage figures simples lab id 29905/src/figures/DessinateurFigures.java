package figures;

class DessinateurFigures {

    void rectangle(int largeur, int hauteur) {
    	for(int i=0;i<hauteur;i++) {
    		for(int j=0;j<largeur;j++) {
    			System.out.print("*");
    		}
    		System.out.println();
    	}
    }

    void triangleGauche(int hauteur) {
    	for(int i=0;i<hauteur;i++) {
    		for(int j=0;j<=i;j++) {
    			System.out.print("*");
    		}
    		System.out.println();
    	}
    }

    void triangleDroite(int hauteur) {
    	for(int i=0;i<hauteur;i++) {
    		for(int j=0;j<hauteur;j++) {
    			if(j>=hauteur-1-i) {
    				System.out.print("*");
    			}else {
    				System.out.print(" ");
    			}
    		}
    		System.out.println();
    	}
    }

    void triangleIso(int hauteur) {
    	  for (int h = 0; h < hauteur; h++) {
              for (int i = 0; i < hauteur-h-1; i++) System.out.print(" ");
              System.out.print("*");
              for (int j = hauteur-h; j < hauteur+h-1; j++) System.out.print(" ");
              System.out.println(h != 0 ? "*" : "");
          }
    }
}
