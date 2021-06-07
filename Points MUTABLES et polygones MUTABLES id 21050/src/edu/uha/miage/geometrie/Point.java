/*
   @author Yvan Maillot (yvan.maillot@uha.fr)

    Écrire une classe Point avec :
    1. des attributs x et y de type double, pour les coordonnées cartésiennes,
    2. des attributs rho et theta de type double, pour les coordonnées polaires,
    3. la possibilité de le construire par défaut (0,0),
    4. la possibilité de le construire en donnant ses coordonnées cartésiennes,
    5. la possibilité de le construire en donnant ses coordonnées polaires,
    6. tous les setters et tous les getters,
    7. une méthode pour lui appliquer une translation,
    8. une méthode pour lui appliquer une rotation par rapport à l’origine,
    9. des méthodes pour l’afficher sur la sortie standard.

    TRÈS TRÈS IMPORTANT : les responsabilités de cette classe sont

    1.  de garantir l'intégrité des attributs de ses instances et en particulier 
        que leurs coordonnées cartésiennes et polaires soient TOUJOURS 
        cohérentes les unes envers les autres,

    2.  qu'elles aient un theta dans [0,2*PI[,

    3.  qu'elles aient un rho positif (comme il n'est pas possible d'empêcher 
        la construction d'un point avec un rho négatif, ou de mettre rho à jour 
        avec un valeur négative, nous devons définir une interprétation d'un rho 
        négatif. Nous considérerons que le point de coordonées polaires 
        [-rho:theta] est égal au point de coordonnées polaires [rho:(theta+PI)%(2*PI)]

    Attention de bien respecter les noms des attributs et des méthodes proposés.

    Il est recommandé de traiter les ToDo dans l'ordre de leur numéro :
        - 1.01 avant 1.02
        - 1.12 avant 2.01
    Même si, parfois, ils ne sont pas rangés dans cet ordre.

*/
package edu.uha.miage.geometrie;

import static java.lang.Math.*;

/**
 * Classe Point pour modéliser des points du plan cartésien.
 *
 * @author yvan
 */
public class Point {
	private double x;
	private double y;
	
	private double rho;
	private double theta;
    
	public Point(double rho ,double theta,boolean polaire){
		if(polaire) {
			setRho(rho);
			setTheta(theta);
			cfromp();
		}else {
			setX(rho);
			setY(theta);
			pfromc();
		}
		
	}
	
	public Point(){
		this(0,0,false);
	}
	public Point(double x,double y){
		this(x,y,false);
	}
	
	
	
	 public double getX() {
		return this.x;
	}
	
	public double getY(){
		return this.y;
	}
	
	public double getRho() {
		return rho;
	}
	
	public double getTheta() {
		return theta;
	}

	public void setX(double x) {
		this.x=x;
		pfromc();
	}
	
	public void setY(double y) {
		this.y=y;
		pfromc();
	}
	
	public void setRho(double rho) {
		if(rho>0){
			this.rho=rho;
			cfromp();
		}else {
			this.rho=-rho;
			setTheta(theta+PI);
			cfromp();
		}
	}
	public void setTheta(double theta) {
		if(theta<0) {
			this.theta=theta*(-1);
		}else {
			this.theta=theta;		
		}
	}
	
	/**
     * Cadeau : une méthode d'instance privée pfromc qui calcule les coordonnées
     * polaires à partir des coordonnées cartésiennes.
     *
     * Elle assure également la cohérence de théta s'il n'est pas dans [0,2*PI[.
     *
     * Il n'y aura qu'à copier-coller le code suivant quand vous aurez
     * correctement déclaré les attributs de cette classe.
     *
     *    private void pfromc() {
     *        rho = sqrt(x*x+y*y);
     *        theta = atan2(y, x);
     *        theta %= (2*PI);
     *        if (theta < 0)
     *            theta += 2*PI;
     *    }
     */
    private void pfromc() {
    /* TODO 1.03. Coller ici le code ci-dessus quand les attributs de
	cette classe seront correctement déclarés.*/
			 rho = sqrt(x*x+y*y);
             theta = atan2(y, x);
             theta %= (2*PI);
             if (theta < 0)
                 theta += 2*PI;

    }

    /**
     * Cadeau : une méthode d'instance privée cfromp qui calcule les coordonnées
     * cartésiennes à partir des coordonnées polaires.
     *
     * Elle assure également la cohérence
     *    - de rho s'il est négatif,
     *    - et de théta s'il n'est pas dans [0,2*PI].
     *
     * Il n'y aura qu'à copier-coller le code suivant quand vous aurez
     * correctement déclaré les attributs de cette classe.
     *
     *    private void cfromp() {
     *        if (rho < 0) {
     *            rho = -rho;
     *            theta += PI;
     *        }
     *        theta %= (2*PI);
     *        if (theta < 0)
     *            theta += 2*PI;
     *        x = rho*cos(theta);
     *        y = rho*sin(theta);
     *    }
     */
    private void cfromp() {
    /* TODO 1.04. Coller ici le code ci-dessus quand les attributs de
	cette classe seront correctement déclarés.
	*/
    	if (rho < 0) {
    	                 rho = -rho;
    	                 theta += PI;
    	            }
    	             theta %= (2*PI);
    	             if (theta < 0)
    	                 theta += 2*PI;
    	             x = rho*cos(theta);
    	             y = rho*sin(theta);
    }

    
    public void translation(double dx, double dy) {
    /* TODO 1.09. Écrire une méthode translation(double dx, double dy) qui applique
	à ce point la translation de dx en x et dy en y donnés en paramètres.

	Attention aux pièges.
	*/
    	x=+dx;
    	y=+dy;
    	pfromc();
    	
    }

    public void rotation(double dtheta) {
    /* TODO 1.10. Écrire une méthode rotation(double dtheta) qui applique
	à ce point la rotation de dtheta donnée en paramètres.
	Attention aux pièges.
	*/
    	theta+=dtheta;
    	theta%=(2*PI);
    	if(rho<0) {
    		theta=+PI;
    		theta%=(2*PI);
    	}
    	cfromp();
    		
    }

    public void afficher(boolean polaire) {
    /* TODO 1.11. Écrire une méthode afficher(boolean polaire) qui affiche
	sur la sortie standard les coordonnées de ce point 
	soit polaires soit cartésiennes selon l'état du paramètre polaire
	en respectant les formats suivants :
		 polaire => [<rho>:<theta>]
		 !polaire => (<x>, <y>)
	*/
    	if(polaire) {
    		System.out.print("["+getRho()+":"+getTheta()+"]");
    	}else {
    		System.out.print("("+getX()+","+getY()+")");
    	}
    }

    public void afficher() {
    /* TODO 1.12. Écrire une méthode afficher() qui, par défaut, affiche
	sur la sortie standard les coordonnées cartésiennes de
	ce point en respectant le format décrit précédemment.
	*/
    	afficher(false);
    }
    /* TODO 1.01. Déclarer les coordonnées cartésiennes x et y du point, de type double.
	Veiller à choisir des modificateurs adaptés.
	Respecter les noms x et y
	*/
    // 
    /* TODO 1.02. Déclarer les coordonnées polaires rho et theta du point, de type double.
	Veiller à choisir des modificateurs adaptés.
	Respecter les noms rho et theta
	*/
    // 
    /* TODO 1.05. Écrire le constructeur à trois paramètres 
	Point(double roux, double touy, boolean polaire)
	qui permet de construire un point à partir des deux paramètres 
	de type double qui sont :
		1. soit ses coordonnées cartésiennes, 
		2. soit ses coordonnées polaires, 

	Le troisième paramètre de type booléen permet de distinguer
	si les coordonnées sont polaires ou cartésiennes:
		vrai : les deux premiers paramètres sont des coordonnées polaires
		faux : les deux premiers paramètres sont des coordonnées cartésiennes
	*/
    // 
    /* TODO 1.06. Écrire le constructeur à deux paramètres de type double qui 
	qui construit le point à partir de ses coordonnées cartésiennes
	*/
    // 
    /* TODO 1.07. Écrire le constructeur par défaut qui construit
	le point de coordonnées cartésiennes (0, 0)
	*/
    // 
    /* TODO 1.08. Ajouter un setter et un getter à chaque attribut.
	
	Attention aux pièges.
	*/
    // 
}
