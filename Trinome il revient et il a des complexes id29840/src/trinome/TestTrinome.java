package trinome;

public class TestTrinome {

    public static void main(String[] args) {
        // Testez votre classe trinôme ici.
        // Voici un exemple de test:  1.125x^2+5x +2.0 = 0.0
        Trinome trinome = new Trinome(1.125, 5, 2.0);
        // delta = 5^2 - 4*1.125*2 = 25 - 9 = 16 = 4^2
        // => Ce trinôme a 2 solutions réelles: x = (-5-4)/(2*1.125) = -4 et x = (-5+4)/(2*1.125) = -0.44444....
        System.out.println("Le trinôme " + trinome.getString() + " a " + trinome.nbRacine() + " solution(s).");
        if (trinome.getRacine1().isReel()) {
            System.out.println("Solution 1 (réelle): " + trinome.getRacine1().reel);
        } else {
            System.out.println("Solution 1 (complexe): " + trinome.getRacine1().getString());
        }
        if (trinome.getRacine2().isReel()) {
            System.out.println("Solution 2 (réelle): " + trinome.getRacine2().reel);
        } else {
            System.out.println("Solution 2 (complexe): " + trinome.getRacine2().getString());
        }
    // Testez d'autres trinômes (avec a=0 ou b <0, ou delta < 0, ou...)
    }
}
