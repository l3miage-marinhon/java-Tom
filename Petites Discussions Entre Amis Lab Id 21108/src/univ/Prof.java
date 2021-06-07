/*
 * @author : Yvan Maillot (yvan.maillot@uha.fr)
 */
package univ;

/**
 * Un prof est une personne. Mais, en plus, il enseigne un cours (par exemple
 * le Java).
 *
 * Il faudra donc rajouter, cours, un attribut de type String
 * (en respectant scrupuleusement l'orthographe et la casse).
 *
 * Il faudra créer un constructeur qui initialise un prof comme une personne
 * avec en plus, cours.
 *
 * Il faudra créer le getter pour cours (getCours())
 *
 * Un prof ne parle pas comme une personne ordinaire. Il emploie un langage cool
 * et suranné.
 *
 * Il faudra donc penser à redéfinir toutes les méthodes impliquées dans la
 * conversation. Dans ces méthodes, vous devrez utiliser les getters des attributs
 * plutôt que les attributs eux-mêmes quand vous en aurez besoin.
 *
 * (Toute ressemblance avec une personne connue ou fictive serait fortuite)
 *
 * ATTENTION : TRES IMPORTANT. Vous allez constater à l'initiation de l'exercice
 * que celui-ci ne compile pas. C'est normal. Compte-tenu de la structure des
 * classes, quelque chose manque dans les classes Prof et Etudiant.
 *
 * Lisez bien les messages d'erreur de compilation et faites ce qu'il faut pour
 * que ça compile.
 *
 * @author yvan
 */
public class Prof extends Personne {
	private String cours;

    // TODO 1.01 Un prof enseigne un cours (prévoir un attribut à cet effet)
    // 
    
    /**
     * TODO 1.02
     * Le constructeur initialise les attributs de ce prof (qui est une personne)
     * ainsi que son cours.
     *
     * @param nom (String)
     * @param prenom (String)
     * @param homme (true ou false)
     * @param cours de type String (par exemple "Java")
     */
    // TODO 1.02 Constructeur comme une personne normale avec le cours en plus
    // 
	public Prof(String nom,String Prenom,boolean Homme,String cours) {
		super(nom,Prenom,Homme);
		this.cours=cours;
	}
    /**
     * TODO 1.03
     * Getter pour le cours
     * @return
     */
    public String getCours() {
        return cours;
    // TODO 1.03
    }
    @Override
    public String getNom() {
    	return String.format("%S", super.getNom());
    }

    // TODO 1.04 Getter : un prof dit toujours son nom en majuscule
    // 


    /**
     * TODO 1.05
     *
     * Attention de bien lire ce qui suit.
     *
     * Remarque : une présentation non soutenue et aussi dite familière.
     *
     * La présentation familière d'un prof est la présentation
     * familière d'une personne ordinaire.
     *
     * Par exemple "Yvan Maillot"
     *
     * La présentation soutenue d'un prof est la présentation familière d'une
     * personne ordinaire à laquelle il rajoute ". J'enseigne " + le cours
     * qu'il enseigne.
     *
     * Par exemple
     * "Yvan Maillot. J'enseigne Java."
     *
     * @param soutenue (true ou false)
     * @return la présentation soutenue ou pas de ce prof sous forme d'une String
     */
    @Override
    public String presentation(boolean soutenue) {
        if(soutenue) {
        	return super.presentation(false)+". J'enseigne "+getCours()+"."; 
        }else {
        	return super.presentation(false);
        }
    // TODO 1.05 Redéfinir presentation() pour un prof
    }

    /**
     * TODO 1.06
     * Un prof ne dit pas "Bonjour" mais "Salut" à la place. Ensuite, il se présente
     * dans son langage soutenue.
     *
     * Par exemple, le bonjour de Yvan Maillot (Java) donne
     * "Salut, je suis Yvan MAILLOT. J'enseigne Java."
     *
     * @return une chaîne de caractères contenant le bonjour de ce prof
     */
    @Override
    protected String bonjour() {
        return "Salut, je suis "+presentation(true);
    // TODO 1.06 Redéfinir bonjour() pour un prof
    }

    /**
     * TODO 1.07
     * Pour répondre à un bonjour, un prof dit :
     *
     * "Hello "
     * suivi du prénom de la personne à qui il répond
     * suivi de ", moi c'est "
     * suivi de sa présentation familière
     * suivi de " et j'enseigne "
     * suivi de son cours
     *
     * Par exemple, Bruno Adam (les bases de données) qui répond au bonjour
     * de Yvan Maillot (Java) dirait
     * "Hello Yvan, moi c'est Bruno ADAM et j'enseigne les bases de données."
     *
     * @return une chaîne de caractères contenant la réponse de ce prof au
     * bonjour de la personne en paramètre
     */
    @Override
    protected String reponseAuBonjourDe(Personne personne) {
        return "Hello "+personne.getPrenom()+", moi c'est "+this.presentation(false)+" et j'enseigne "+this.getCours();
    // TODO 1.07 Redéfinir reponseAuBonjourDe() pour un prof
    }

    /**
     * TODO 1.08
     * Pour dire ça va, le prof, un peu vieillot dit "Ca get'z, " suivi du
     * prénom de celui à qui il s'adresse.
     *
     * Ça peut alors donner quelque chose comme :
     * "Ca get'z, Yvan"
     *
     * @param personne à qui ce prof s'adresse
     * @return une chaîne de caractères contenant le "ça va" de ce prof
     */
    @Override
    protected String caVa(Personne personne) {
        return "Ca get'z, "+personne.getPrenom();
    // TODO 1.08 Redéfinir caVA() pour un prof
    }

    /**
     * TODO 1.09
     * Pour répondre à un "ça va", le prof se lâche et sort les jeux de mots de
     * grand-père en lançant très fièrement "Ca roule, Raoul."
     *
     * (même si celui à qui il dit ça ne s'appelle pas Raoul).
     *
     * @param personne inutile dans le cas d'un prof
     * @return une chaîne de caractères contenant le "et toi" de ce prof
     */
    @Override
    protected String etVous(Personne personne) {
        return "Ca roule, Raoul";
    // TODO 1.09 Redéfinir etVous() pour un prof
    }
}
