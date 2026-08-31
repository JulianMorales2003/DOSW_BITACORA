package dosw.semana_1.streams;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Ejercicio 03 - Obtener nombres de los usuarios activos
 * Operadores: filter() - map() - sorted()
 */
public class Ejercicio3 {
    public static void main(String[] args) {
        List<User> users = List.of(
                new User(1, "Camila", 22, true),
                new User(2, "Andres", 19, false),
                new User(3, "Brayan", 25, true),
                new User(4, "Diana", 30, false),
                new User(5, "Julian", 21, true)
        );

        List<String> sortedUsers = users.stream()
                .filter(User::isActive)
                .map(u -> u.getName().toUpperCase())
                .sorted()
                .collect(Collectors.toList());

        System.out.println("Usuarios activos ordenados: " + sortedUsers);
    }
}
