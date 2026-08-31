package dosw.semana_2.pokemon;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Ejercicio 07 - Orden del Profesor Oak
 * Operador: sorted()
 */
public class Ejercicio7 {
    public static void main(String[] args) {
        List<String> pokedex = List.of("Squirtle", "Pikachu", "Mewtwo", "Bulbasaur", "Charmander", "Abra");

        List<String> ordenada = pokedex.stream()
                .sorted()
                .collect(Collectors.toList());

        System.out.println(ordenada);
    }
}
