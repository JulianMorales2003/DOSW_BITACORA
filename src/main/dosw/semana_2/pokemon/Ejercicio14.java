package dosw.semana_2.pokemon;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Ejercicio 14 - Organizar por Region
 * Operador: groupingBy()
 */
public class Ejercicio14 {
    public static void main(String[] args) {
        List<Pokemon> equipo = List.of(
                new Pokemon(1L, "Pikachu", "Electrico", 45, 320, "Kanto", false),
                new Pokemon(2L, "Chikorita", "Planta", 20, 210, "Johto", false),
                new Pokemon(3L, "Torchic", "Fuego", 22, 230, "Hoenn", false),
                new Pokemon(4L, "Piplup", "Agua", 20, 200, "Sinnoh", false),
                new Pokemon(5L, "Charmander", "Fuego", 30, 300, "Kanto", false),
                new Pokemon(6L, "Totodile", "Agua", 24, 250, "Johto", false)
        );

        Map<String, List<String>> porRegion = equipo.stream()
                .collect(Collectors.groupingBy(Pokemon::getRegion,
                        Collectors.mapping(Pokemon::getNombre, Collectors.toList())));

        porRegion.forEach((region, nombres) -> System.out.println(region + ": " + nombres));
    }
}
