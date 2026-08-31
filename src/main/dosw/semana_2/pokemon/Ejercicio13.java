package dosw.semana_2.pokemon;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Ejercicio 13 - Organizar por Tipo
 * Operador: groupingBy()
 */
public class Ejercicio13 {
    public static void main(String[] args) {
        List<Pokemon> equipo = List.of(
                new Pokemon(1L, "Squirtle", "Agua", 20, 210, "Kanto", false),
                new Pokemon(2L, "Psyduck", "Agua", 25, 260, "Kanto", false),
                new Pokemon(3L, "Charmander", "Fuego", 30, 300, "Kanto", false),
                new Pokemon(4L, "Vulpix", "Fuego", 22, 240, "Kanto", false),
                new Pokemon(5L, "Bulbasaur", "Planta", 28, 280, "Kanto", false)
        );

        Map<String, List<String>> porTipo = equipo.stream()
                .collect(Collectors.groupingBy(Pokemon::getTipo,
                        Collectors.mapping(Pokemon::getNombre, Collectors.toList())));

        porTipo.forEach((tipo, nombres) -> System.out.println(tipo + ": " + nombres));
    }
}
