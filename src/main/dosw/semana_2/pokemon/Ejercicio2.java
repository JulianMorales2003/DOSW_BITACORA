package dosw.semana_2.pokemon;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Ejercicio 02 - Pokedex Gritona
 * Operador: map()
 */
public class Ejercicio2 {
    public static void main(String[] args) {
        List<String> nombres = List.of("Pikachu", "Charmander", "Squirtle", "Bulbasaur");

        List<String> gritones = nombres.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        System.out.println(String.join(", ", gritones));
    }
}
