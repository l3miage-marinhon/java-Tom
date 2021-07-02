/*
 * @author : Yvan Maillot (yvan.maillot@uha.fr)
 */
package univ;

/**
 * NE PAS MODIFIER CETTE CLASSE
 *
 * Une personne a un nom, un prénom et un genre (homme ou femme). Il faudra
 * déclarer des attributs pour ça, nom et prenom de type String et homme, de
 * type boolean (en respectant exactement cette orthographe).
 *
 * Il faudra écrire le constructeur qui initialise ces attributs.
 *
 * Il faudra aussi prévoir des getters pour ces attributs
 *  - getNom()
 *  - getPrenom()
 *  - isHomme()
 *
 * Une personne aime bien se présenter. Elle le fait de deux manières, soit
 * courte (ou familière), sous longue (ou soutenue).Pour ça, il faudra écrire le
 * corps de la méthode
 * protected String presentation(boolean courte).
 *
 * Une personne est polie. Quand elle rencontre une autre personne, elle dit
 * bonjour et se présente. Il s'en suit un petit dialogue, du style :
 *
 * << Bonjour, je suis M. Luke Lucky >> dit Lucky Luke.
 * << Bonjour Lucky Luke.
 *    Moi c'est Mme Dalton Ma >> répond Ma Dalton.
 * << Comment allez-vous ? >> continue Ma Dalton.
 * << Ca va bien. Merci >> termine Lucky Luke.
 *
 * Ce dialogue est établi à l'aide des quatre méthodes, mises à votre disposition,
 * suivantes :
 *       1. public final void ditBonjourA(Personne personne)
 *       2. private void repondAuBonjourDe(Personne personne)
 *       3. private void demandeEtVousA(Personne personne)
 *       4. private void demandeSiCaVaA(Personne personne)
 *
 * ELLES N'ONT PAS À ÊTRE MODIFIÉES EN QUOIQUE CE SOIT.
 *
 * Les 4 précédentes méthodes invoquent 4 autres méthodes, également mises à
 * votre disposition, qui retournent des chaînes de caractères.
 *
 *    1. protected String bonjour() qui retourne
 *              "Bonjour, je suis " suivi de sa présentation longue.
 *
 *    2. protected String reponseAuBonjourDe(Personne personne) qui retourne
 *              "Bonjour " + personne.presentation(true) + ".\n   Moi c'est " + presentation();
 *
 *    3. protected String caVa(Personne personne) qui retourne "Comment allez-vous ?";
 *
 *    4. protected String etVous(Personne personne) qui retourne "Ca va bien. Merci"
 *
 * VOUS POUVEZ MODIFIER LEUR CONTENU POUR VOUS AMUSER.
 *
 * L'exécution de la classe autonome main.Main devrait engendrer le petit
 * dialogue entre Lucky et Ma Dalton déjà évoqué. Ce dialogue peut varier en
 * changeant le retour comme précédement expliqué.
 *
 * D'autres dialogues sont pour le moment "en commentaires". Vous pourrez les
 * décommenter et les tester quand vous aurez réalisé l'exercice.
 *
 * NE PAS MODIFIER CETTE CLASSE
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
public class Personne {

    /**
     * Affiche sur la sortie standard "<< **B** >> dit **P**. \n" où
     *        1. **B** est le résultat de l'invocation de bonjour()
     *        2. **P** est le résultat de l'invocation de presentation(false)
     * Puis invoque personne.repondAuBonjourDe(this) pour continuer le dialogue
     *
     * @param personne est la personne à qui s'adresse le bonjour.
     *
     * NE PAS MODIFIER CETTE MÉTHODE
     */
    public final void ditBonjourA(Personne personne) {
        System.out.printf("<< %s >> dit %s. \n", bonjour(), presentation(false));
        personne.repondAuBonjourDe(this);
    }

    /**
     * Affiche sur la sortie standard "<< **R** >> répond **P**. \n" où
     *        1. **R** est le résultat de l'invocation de reponseAuBonjourDe(personne)
     *        2. **P** est le résultat de l'invocation de presentation(true)
     * Puis invoque demandeSiCaVaA(personne);
     *
     * @param personne est la personne à qui répondre
     *
     * NE PAS MODIFIER CETTE MÉTHODE
     */
    private void repondAuBonjourDe(Personne personne) {
        System.out.printf("<< %s >> répond %s.\n", reponseAuBonjourDe(personne), presentation(false));
        demandeSiCaVaA(personne);
    }

    /**
     * Affiche sur la sortie standard "<< **C** >> répond **P**. \n" où
     *        1. **C** est le résultat de l'invocation de caVa(personne)
     *        2. **P** est le résultat de l'invocation de presentation(true)
     * Puis invoque personne.demandeEtVousA(this);
     *
     * @param personne est la personne à qui demander si ça va.
     *
     * NE PAS MODIFIER CETTE MÉTHODE
     */
    private void demandeSiCaVaA(Personne personne) {
        System.out.printf("<< %s >> continue %s.\n", caVa(personne), presentation(false));
        personne.demandeEtVousA(this);
    }

    /**
     * Affiche sur la sortie standard "<< %s >> termine %s.\n" où
     *        1. **V** est le résultat de l'invocation de etVous(personne)
     *        2. **P** est le résultat de l'invocation de presentation(true)
     * Et le dialogue se termine.
     *
     * @param personne est la personne à qui on s'adresse.
     *
     * NE PAS MODIFIER CETTE MÉTHODE
     */
    private void demandeEtVousA(Personne personne) {
        System.out.printf("<< %s >> termine %s.\n", etVous(personne), presentation(false));
    }

    private String nom;

    private String prenom;

    private boolean homme;

    /**
     * Créer une personne en définissant son nom, son prenom et s'il est
     * un homme ou une femme
     * @param nom une String
     * @param prenom une String
     * @param homme un boolean vrai masculin, faux féminin
     *
     * Une fois ce constructeur déclaré, une erreur de compilation se révèlera.
     * C'est normal. À vous de voir quoi faire pour régler ce problème avant
     * de continuer.
     */
    public Personne(String nom, String prenom, boolean homme) {
        this.nom = nom;
        this.prenom = prenom;
        this.homme = homme;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public final boolean isHomme() {
        return homme;
    }

    /**
     * Retourne une chaîne de caractères qui constitue la présentation soutenue
     * ou familière de cette personne.
     *
     * La présentation familière est tout simplement le prénom suivi du nom
     * Par exemple, "Lucky Luke"
     *
     * La présentation soutenue est M. ou Mme (en fonction de isHomme()) suivi
     * du nom et du prénom
     * Par exemple, M. Luke Lucky
     *
     * ATTENTION : utilisez NÉCESSAIREMENT les getters et NON les attributs.
     *
     * @param soutenue pour déterminer le type de présentation
     *
     * @return la chaîne de présentation
     */
    protected String presentation(boolean soutenue) {
        if (soutenue) {
            return String.format("%s %s %s", (homme ? "M." : "Mme"), getNom(), getPrenom());
        } else {
            return String.format("%s %s", getPrenom(), getNom());
        }
    }

    /**
     * @return la présentation soutenue
     */
    protected String presentation() {
        return presentation(true);
    }

    /**
     * @return "Bonjour, je suis " suivi de la présentation soutenue de cette personne
     */
    protected String bonjour() {
        return "Bonjour, je suis " + presentation();
    }

    /**
     * @param personne la personne à qui répond cette personne.
     * @return "Bonjour "
     *         suivi de la présentation soutenue de personne (du paramètre personne)
     *         suivi d'un saut de ligne
     *         suivi de "Moi c'est "
     *         suivi de la présentation soutenue de cette personne.
     *
     *         Exemple : "Bonjour M.Luke Lucky.
     *                    Moi c'est Mme Dalton Ma"
     */
    protected String reponseAuBonjourDe(Personne personne) {
        return "Bonjour " + personne.presentation(true) + ".\n   Moi c'est " + presentation();
    }

    /**
     * @param personne la personne à s'adresser (ignorée ici).
     * @return "Comment allez-vous ?"
     */
    protected String caVa(Personne personne) {
        return "Comment allez-vous ?";
    }

    /**
     * Malgré le "et toi ?", la conversation s'arrête.
     * @param personne la personne à qui s'adresser (ignorée ici).
     * @return "Ca va bien. Merci et toi ?"
     */
    protected String etVous(Personne personne) {
        return "Ca va bien. Merci, et toi ?";
    }
}
