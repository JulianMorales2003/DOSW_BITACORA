package dosw.semana_2.pokemon;

import java.util.List;

/**
 * Ejercicio 05 - Pokemon Legendarios
 * Operadores: filter() + count()
 */
public class Ejercicio5 {

    record PokemonNivel(String nombre, int nivel) {}

    public static void main(String[] args) {
        List<PokemonNivel> equipo = List.of(
                new PokemonNivel("Pikachu", 45),
                new PokemonNivel("Mewtwo", 88),
                new PokemonNivel("Dragonite", 82),
                new PokemonNivel("Squirtle", 38),
                new PokemonNivel("Mew", 85),
                new PokemonNivel("Charmander", 62)
        );

        long cantidad = equipo.stream()
                .filter(p -> p.nivel() > 80)
                .count();

        List<String> nombres = equipo.stream()
                .filter(p -> p.nivel() > 80)
                .map(PokemonNivel::nombre)
                .toList();

        System.out.println("Pokemon con nivel > 80: " + cantidad + " " + nombres);
    }
}
