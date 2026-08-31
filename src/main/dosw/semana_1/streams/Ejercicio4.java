package dosw.semana_1.streams;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Ejercicio 04 - Personas mayores de edad
 * Operadores: filter() - map()
 */
public class Ejercicio4 {
    public static void main(String[] args) {
        List<User> users = List.of(
                new User(1, "Camila", 22, true),
                new User(2, "Andres", 17, false),
                new User(3, "Brayan", 25, true),
                new User(4, "Diana", 16, false),
                new User(5, "Julian", 21, true)
        );

        List<String> mayoresDeEdad = users.stream()
                .filter(u -> u.getAge() >= 18)
                .map(User::getName)
                .collect(Collectors.toList());

        System.out.println("Personas mayores de edad: " + mayoresDeEdad);
    }
}
