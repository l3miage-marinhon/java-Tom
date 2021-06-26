/*
 * @author : Yvan Maillot (yvan.maillot@uha.fr)
 */
package univ;

/**
 * Un étudiant est une personne. Mais il est affublé d'un surnom (par exemple
 * "poussin") et suit une année de formation (par exemple "L3 MIAGE").
 *
 * Il faudra donc rajouter, surnom et annee, deux attributs de type String
 * (en respectant scrupuleusement l'orthographe et la casse). Comme d'habitude
 * ces attributs doivent être encapsulés.
 *
 * Il faudra créer un constructeur qui initialise un étudiant comme une personne
 * avec en plus, surnom et annee.
 *
 * Il faudra créer les getters pour surnom (getSurnom()) et annee (getAnnee())
 *
 * Un étudiant ne parle pas comme une personne ordinaire. Il est assez peu
 * loquace et ne se préocupe guère de son interlocuteur. Seuls ses congénères le
 * comprennent car il emploie des borborygmes comme "Wesh". On le reconnaît
 * facilement car il termine toutes ses phrases par "Gros".
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
public class Etudiant extends Personne{
	
    // TODO 2.00 Les attributs surnom et annee d'un étudiant
    // 
	private String surnom;
	private String annee;
    /**
     * TODO 2.01
     * Le constructeur initialise les attributs de cet étudiant (qui est une personne)
     * ainsi que son surnon et son année.
     *
     * @param nom (String)
     * @param prenom (String)
     * @param homme (true ou false)
     * @param surnom (String) Par exemple "poussin"
     * @param annee (String) Par exemple "L3 Miage"
     */
    // TODO 2.01 Le constructeur comme une personne mais avec un surnom et l'année en plus
    // 
	public Etudiant(String nom, String prenom, boolean homme, String surnom, String annee) {
		super(nom, prenom, homme);
		this.surnom = surnom;
		this.annee = annee;
	}

    // TODO 2.02 Écrire un getter pour le surnom
	public String getSurnom() {
		return surnom;
	}

    // TODO 2.03 Écrire un getter pour l'année
    // 
	public String getAnnee() {
		return annee;
	}

    /**
     * TODO 2.04
     *
     * Remarque : une présentation non soutenue et aussi dite familière.
     *
     * La présentation familière d'un étudiant est son surnom.
     *
     *   - Par exemple pour "Alan" "Turing", dit "Enigma", étudiant en "L3 Miage"
     *     sa présentation familière est "Enigma"
     *
     * La présentation soutenue d'un étudiant est la présentation familière d'une
     * personne ordinaire à laquelle il rajoute ", étudiant en " et son année.
     *
     *   - Par exemple la présentation soutenue de ce même étudiant est
     *     "Alan Turing, étudiant en L3 Miage"
     *
     * Cette méthode ne doit pas pouvoir être redéfinissable.
     *
     * @param soutenue (true ou false)
     * @return la présentation soutenue ou pas de ce prof sous forme d'une String
     */
    // TODO 2.04 Redéfinir presentation(boolean soutenue) - Lire Javadoc 2.04
	@Override
	public String presentation(boolean soutenue) {
		return (soutenue ? super.presentation(false) + ", étudiant en " + getAnnee() : getSurnom());
	}
    /**
     * TODO 2.05
     * Un étudiant ne dit pas "Bonjour" mais "'Lut Gros".
     *
     * La méthode ne doit pas être redéfinissable.
     *
     * @return "'Lu Gros"
     */
     // TODO 2.05 Redéfinition de bonjour() pour un étudiant (la méthode ne doit pas être redéfinissable) - Lire Javadoc 2.05
	@Override
	public final String bonjour() {
		return "'Lut Gros";
	}
   /**
     * TODO 2.06
     * Un étudiant répond à un bonjour par un "Ouai Gros"
     *
     * La méthode ne doit pas être redéfinissable.
     *
     * @return "Ouai Gros"
     */
    // TODO 2.06 Redéfinition de reponseAuBonjourDe() pour un étudiant (la méthode ne doit pas être redéfinissable) - Lire Javadoc 2.0
	@Override
	public final String reponseAuBonjourDe(Personne personne) {
		return "Ouai Gros";
	}
    /**
     * TODO 2.07
     * Un étudiant demande si ça va en disant "Bien ou quoi, Gros ?"
     *
     * La méthode ne doit pas être redéfinissable.
     *
     * @return "Bien ou quoi, Gros ?"
     */
    // TODO 2.07 Redéfinition de caVa() pour un étudiant (la méthode ne doit pas être redéfinissable) - Lire Javadoc 2.07
	@Override
	public final String caVa(Personne personne) {
		return "Bien ou quoi, Gros ?";
	}
    /**
     * TODO 2.08
     * Un étudiant répond à un ça va en disant "Wesh, Gros"
     *
     * La méthode ne doit pas être redéfinissable.
     *
     * @return "Wesh, Gros"
     */
    // TODO 2.08 Redéfinition de etVous() pour un étudiant (la méthode ne doit pas être redéfinissable) - Lire Javadoc 2.08
	@Override
	public final String etVous(Personne personne) {
		return "Wesh, Gros";
	}
    
}
