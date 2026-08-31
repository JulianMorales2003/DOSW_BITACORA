package dosw.semana_2.pokemon;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Ejercicio 20 - Pokedex Analitica
 * Operadores: groupingBy() + counting()
 */
public class Ejercicio20 {
    public static void main(String[] args) {
        List<Pokemon> pokedex = List.of(
                new Pokemon(1L, "Charmander", "Fuego", 30, 300, "Kanto", false),
                new Pokemon(2L, "Vulpix", "Fuego", 22, 240, "Kanto", false),
                new Pokemon(3L, "Squirtle", "Agua", 20, 210, "Kanto", false),
                new Pokemon(4L, "Psyduck", "Agua", 25, 260, "Kanto", false),
                new Pokemon(5L, "Bulbasaur", "Planta", 28, 280, "Kanto", false),
                new Pokemon(6L, "Mewtwo", "Psiquico", 70, 680, "Kanto", true),
                new Pokemon(7L, "Mew", "Psiquico", 65, 600, "Kanto", true)
        );

        Map<String, Long> porTipo = pokedex.stream()
                .collect(Collectors.groupingBy(Pokemon::getTipo, Collectors.counting()));

        Map<String, Long> porRegion = pokedex.stream()
                .collect(Collectors.groupingBy(Pokemon::getRegion, Collectors.counting()));

        long legendarios = pokedex.stream()
                .filter(Pokemon::isLegendario)
                .count();

        double promedioNivel = pokedex.stream()
                .mapToInt(Pokemon::getNivel)
                .average()
                .orElse(0);

        String masFuerte = pokedex.stream()
                .max(Comparator.comparingDouble(Pokemon::getPoderCombate))
                .map(p -> p.getNombre() + " (PC: " + (int) p.getPoderCombate() + ")")
                .orElse("N/A");

        System.out.println("Por tipo: " + porTipo);
        System.out.println("Por region: " + porRegion);
        System.out.println("Legendarios: " + legendarios);
        System.out.printf("Promedio nivel: %.2f%n", promedioNivel);
        System.out.println("Mas fuerte: " + masFuerte);
    }
}
