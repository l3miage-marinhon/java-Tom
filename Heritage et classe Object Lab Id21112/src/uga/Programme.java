package uga;

public class Programme {

    public static void main(String[] args) {
    // Dé-commenter ces lignes après la question 6.
    
        System.out.println("---- Ligne 1: Humain h = new Humain(); ----");
        Humain h = new Humain();

    // Dé-commenter ces lignes après la question 15.
    
        System.out.println("---- Ligne 2: Bebe bebe = new Bebe(); ----");
        Bebe bebe = new Bebe();
        System.out.println("---- Ligne 3: Humain enfant = new Bebe(); ----");
        Humain enfant = new Bebe();
        enfant.setAge(6);
        

    }
}
