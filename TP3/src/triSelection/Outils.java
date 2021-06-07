package triSelection;

public class Outils {
  // retourne un nombre aleatoire dans l'intervalle [min,max]
  // precondition : min <= max
  public static int nombreAleatoire(int min, int max) {
    return min + (int)Math.floor((max - min + 1) * Math.random());
      }
}