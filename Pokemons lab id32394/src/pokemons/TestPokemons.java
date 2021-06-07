package pokemons;

public class TestPokemons {

    public static void main(String[] args) {
        Pokemon[] pokemons = new Pokemon[4];
        pokemons[0] = new PokemonSportif("Pikachu", 18, 2, 0.85, 120);
        pokemons[1] = new PokemonCasanier("Salameche", 12, 2, 0.65, 8);
        pokemons[2] = new PokemonMer("Rondoudou", 45, 2);
        pokemons[3] = new PokemonCroisiere("Bulbizarre", 15, 3);
        for (Pokemon pok : pokemons) {
            System.out.println(pok.toString());
        }
        double vitesseMoyenne = 0.0;
        for (Pokemon pok : pokemons) {
            vitesseMoyenne += pok.vitesse();
        }
        vitesseMoyenne /= pokemons.length;
        System.out.println("La vitesse moyenne des pokemons est: " + vitesseMoyenne);
    }
}
