package minifacebook;

public class TestDateNaissance {

    public static void main(String[] args) {
        DateNaissance naissancePatty = new DateNaissance(29,2,1999);
        DateNaissance naissanceJack = new DateNaissance();
        naissancePatty.ecritDate();
        System.out.println();
        naissanceJack.ecritDate();
        System.out.println();
        System.out.println("l'âge de Jack est: " + naissanceJack.age());
    }
}
