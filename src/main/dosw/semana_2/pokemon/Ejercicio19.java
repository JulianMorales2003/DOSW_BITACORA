package dosw.semana_2.pokemon;

import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Ejercicio 19 - Top 3 Entrenadores
 * Operadores: sorted() + limit(3)
 */
public class Ejercicio19 {

    record EntrenadorRanking(String nombre, int medallas, double poderAcumulado) {}

    public static void main(String[] args) {
        List<EntrenadorRanking> entrenadores = List.of(
                new EntrenadorRanking("Gary", 10, 2340),
                new EntrenadorRanking("Ash", 8, 1850),
                new EntrenadorRanking("Dawn", 7, 2100),
                new EntrenadorRanking("Brock", 6, 1670)
        );

        Comparator<EntrenadorRanking> criterio = Comparator
                .comparingInt(EntrenadorRanking::medallas).reversed()
                .thenComparing(Comparator.comparingDouble(EntrenadorRanking::poderAcumulado).reversed())
                .thenComparing(EntrenadorRanking::nombre);

        List<EntrenadorRanking> top3 = entrenadores.stream()
                .sorted(criterio)
                .limit(3)
                .toList();

        IntStream.range(0, top3.size())
                .mapToObj(i -> "#" + (i + 1) + " " + top3.get(i).nombre()
                        + " -- " + top3.get(i).medallas() + " medallas, PC: " + (int) top3.get(i).poderAcumulado())
                .forEach(System.out::println);
    }
}
