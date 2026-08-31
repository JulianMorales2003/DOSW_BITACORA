package dosw.semana_2.pokemon;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Ejercicio 01 - Pokemon Tipo Fuego
 * Operador: filter()
 */
public class Ejercicio1 {

    record PokemonSimple(String nombre, String tipo) {}

    public static void main(String[] args) {
        List<PokemonSimple> pokedex = List.of(
                new PokemonSimple("Pikachu", "Electrico"),
                new PokemonSimple("Charmander", "Fuego"),
                new PokemonSimple("Squirtle", "Agua"),
                new PokemonSimple("Vulpix", "Fuego"),
                new PokemonSimple("Bulbasaur", "Planta"),
                new PokemonSimple("Flareon", "Fuego")
        );

        List<String> tipoFuego = pokedex.stream()
                .filter(p -> p.tipo().equals("Fuego"))
                .map(PokemonSimple::nombre)
                .collect(Collectors.toList());

        System.out.println("Pokemon tipo Fuego: " + tipoFuego);
    }
}
