package dosw.semana_2.pokemon;

import java.util.List;
import java.util.OptionalDouble;

/**
 * Ejercicio 11 - Poder Promedio
 * Operadores: mapToDouble() + average()
 */
public class Ejercicio11 {
    public static void main(String[] args) {
        List<Pokemon> equipo = List.of(
                new Pokemon(1L, "Pikachu", "Electrico", 45, 320, "Kanto", false),
                new Pokemon(2L, "Mewtwo", "Psiquico", 70, 680, "Kanto", true),
                new Pokemon(3L, "Dragonite", "Dragon", 55, 530, "Kanto", false),
                new Pokemon(4L, "Squirtle", "Agua", 20, 210, "Kanto", false),
                new Pokemon(5L, "Gengar", "Fantasma", 40, 495, "Kanto", false),
                new Pokemon(6L, "Charizard", "Fuego", 58, 610, "Kanto", false)
        );

        OptionalDouble promedio = equipo.stream()
                .mapToDouble(Pokemon::getPoderCombate)
                .average();

        promedio.ifPresent(p -> System.out.printf("Poder de combate promedio: %.2f%n", p));
    }
}
