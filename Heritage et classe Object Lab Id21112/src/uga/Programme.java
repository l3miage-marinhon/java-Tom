package uga;

public class Programme {

    public static void main(String[] args) {
    // Dé-commenter ces lignes après la question 6.
    
       System.out.println("---- Ligne 1: Humain h = new Humain(); ----");
        Humain h = new Humain();
        System.out.println(h);

    // Dé-commenter ces lignes après la question 15.
    
        System.out.println("---- Ligne 2: Bebe bebe = new Bebe(); ----");
        Bebe bebe = new Bebe();
        System.out.println(bebe);
        System.out.println("---- Ligne 3: Humain enfant = new Bebe(); ----");
        Humain enfant = new Bebe();
        System.out.println(enfant);
        enfant.setAge(6);
        System.out.println("---- Ligne 4: Modif age enfant; ----");
        System.out.println(enfant);
        System.out.println("---- Ligne 5: Bebe bebe2 = new Bebe(9, 4); ----");
        Bebe bebe2 = new Bebe(5, 10);
        System.out.println(bebe2);
        System.out.println("---- Ligne 6: Bebe bebe3 = new Bebe(2, 9); ----");
        Bebe bebe3 = new Bebe(2, 9);
        System.out.println(bebe3);
        
        System.out.println("---- Ligne 7: Humain bebe5 = new Bebe(5, 32); ----");
        Humain bebe5 = new Bebe(5, 32);
        System.out.println(bebe5);
        
        Humain h10 = new Humain(54);
        System.out.println(h10);
        h10 = new Bebe();
        System.out.println(h10);
    }
}
