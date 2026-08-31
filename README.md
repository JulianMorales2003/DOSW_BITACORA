# DOSW_BITACORA

Bitacora del curso **DOSW** (Java 21/24 + Git) — Streams, Expresiones Lambda y Programacion Funcional.

Estructura: `src/main/dosw/semana_1/streams` (Streams basicos) y `src/main/dosw/semana_2/pokemon` (Bitacora Pokemon).

---

## Comandos de Git utilizados en este repositorio

Este proyecto se trabajó siguiendo un flujo de ramas (Git Flow simplificado): `main` y `develop` como ramas permanentes, una rama por semana (`feature/semana-n-dosw`) y una rama corta por cada ejercicio (`feature/semana-n-dosw-ejercicio-n`).

| Comando | Qué hace | Cuándo se usó aquí |
|---|---|---|
| `git clone <url>` | Descarga el repositorio completo a la máquina local | Al empezar a trabajar en un equipo nuevo |
| `git status` | Muestra el estado actual: qué archivos están modificados, en staging, o sin trackear | Antes de cada `add`/`commit`, para confirmar qué se va a guardar |
| `git add <archivo>` | Mueve un archivo al "staging area" (lo prepara para el próximo commit) | Se usó por archivo, no `git add .`, para mantener commits específicos por ejercicio |
| `git commit -m "mensaje"` | Guarda los cambios en staging como un punto fijo del historial | Un commit por ejercicio, con mensajes tipo `feat: ejercicio N semana X` |
| `git push origin <rama>` / `git push -u origin <rama>` | Sube los commits locales al repositorio remoto (GitHub). El `-u` además vincula la rama local con la remota para que futuros `push`/`pull` no necesiten especificar destino | Al terminar cada rama de semana y al sincronizar `develop`/`main` |
| `git checkout -b <rama>` | Crea una rama nueva y se cambia a ella en un solo paso | Para crear cada rama de ejercicio y de semana |
| `git checkout <rama>` | Cambia a una rama que ya existe | Para volver a la rama de la semana después de terminar un ejercicio |
| `git merge --no-ff <rama> -m "mensaje"` | Fusiona una rama sobre la actual. `--no-ff` fuerza un commit de merge explícito (en vez de "aplanar" el historial), así queda evidencia de que existió la rama | Al fusionar cada ejercicio a su rama de semana, y cada semana a `develop` |
| `git branch -d <rama>` | Borra una rama local (solo si ya fue fusionada) | Después de fusionar cada rama de ejercicio — las ramas de semana NO se borran, quedan como evidencia |
| `git branch` | Lista las ramas locales y marca en cuál se está parado | Para verificar en qué rama se estaba trabajando |
| `git log --oneline --graph --all` | Muestra el historial de commits en forma de árbol, con las ramas | Para revisar que los merges quedaran bien encadenados |
| `git remote -v` / `git remote set-url origin <url>` | Muestra o cambia la URL del repositorio remoto | Al renombrar el repositorio en GitHub, para actualizar la URL local |
| `git rm -r --cached <carpeta>` | Deja de trackear una carpeta sin borrarla del disco | Para sacar `.idea/` y `out/` del control de versiones después de agregarlas a `.gitignore` |

**Flujo típico para un ejercicio nuevo:**
```powershell
git checkout -b feature/semana-N-dosw-ejercicio-X
git add ruta/al/Archivo.java
git commit -m "feat: ejercicio X semana N"
git checkout feature/semana-N-dosw
git merge --no-ff feature/semana-N-dosw-ejercicio-X -m "merge: ejercicio X a semana N"
git branch -d feature/semana-N-dosw-ejercicio-X
```

---

# SEMANA No 1 — DOSW Manejo de Streams

## Datos personales:
- Nombre y Apellido: Julian Morales
- Codigo de Estudiante: 1000091825
- Curso: DOSW

---

### Ejercicio 01 — Numeros Pares mayores a diez
**Operadores utilizados:** `filter()`

Dada una lista de numeros enteros, se debe obtener una nueva lista solo con los numeros pares mayores a 10. Entrada: [3,8,10,12,15,18,20] — Salida esperada: [12,18,20].

**Codigo implementado:**
```java
package dosw.semana_1.streams;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Ejercicio 01 - Numeros pares mayores a diez
 * Operador: filter()
 */
public class Ejercicio1 {
    public static void main(String[] args) {
        List<Integer> numeros = List.of(3, 8, 10, 12, 15, 18, 20);

        List<Integer> pares = numeros.stream()
                .filter(n -> n % 2 == 0 && n > 10)
                .collect(Collectors.toList());

        System.out.println("Numeros pares mayores a 10: " + pares);
    }
}
```

**Captura de ejecucion:**

![Ejercicio 1](docs/capturas/semana_1/ejercicio1.png)

**Explicacion:**
Se usa `filter()` con una condicion compuesta (`n % 2 == 0 && n > 10`) para quedarnos unicamente con los numeros pares mayores a diez, y se recolecta el resultado en una lista con `collect(Collectors.toList())`.

---

### Ejercicio 02 — Cantidad de Palabras con mas de 4 caracteres
**Operadores utilizados:** `filter() - map() - sorted()`

Dada una lista de palabras, se filtran las que tengan mas de 4 caracteres, se convierten a mayusculas, se ordenan alfabeticamente y se obtiene la cantidad total resultante.

**Codigo implementado:**
```java
package dosw.semana_1.streams;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Ejercicio 02 - Cantidad de palabras con mas de 4 caracteres
 * Operadores: filter() - map() - sorted()
 */
public class Ejercicio2 {
    public static void main(String[] args) {
        List<String> palabras = List.of("java", "stream", "api", "functional", "code", "git");

        List<String> resultado = palabras.stream()
                .filter(p -> p.length() > 4)
                .map(String::toUpperCase)
                .sorted()
                .collect(Collectors.toList());

        System.out.println("Palabras resultantes: " + resultado);
        System.out.println("Cantidad de palabras resultantes: " + resultado.size());
    }
}
```

**Captura de ejecucion:**

![Ejercicio 2](docs/capturas/semana_1/ejercicio2.png)

**Explicacion:**
El pipeline encadena `filter()` para descartar palabras cortas, `map()` con `String::toUpperCase` para transformarlas, y `sorted()` para el orden alfabetico. El tamano final se obtiene con `.size()` sobre la lista resultante.

---

### Ejercicio 03 — Obtener nombres de los Usuarios
**Operadores utilizados:** `filter() - map() - sorted()`

Dada una lista de usuarios (id, name, age, active), se filtran los usuarios activos, se obtienen sus nombres en mayuscula y se ordenan alfabeticamente. Se crea la clase `User` como apoyo.

**Codigo implementado:**
```java
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
```

**Captura de ejecucion:**

![Ejercicio 3](docs/capturas/semana_1/ejercicio3.png)

**Explicacion:**
`filter(User::isActive)` deja solo los usuarios activos; `map()` transforma cada uno a su nombre en mayuscula; `sorted()` ordena alfabeticamente el resultado final.

---

### Ejercicio 04 — Personas mayores de edad
**Operadores utilizados:** `filter() - map()`

Usando la misma clase `User`, se filtran las personas mayores de edad (edad >= 18) y se obtienen sus nombres.

**Codigo implementado:**
```java
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
```

**Captura de ejecucion:**

![Ejercicio 4](docs/capturas/semana_1/ejercicio4.png)

**Explicacion:**
Se aplica `filter()` sobre la edad para quedarnos con los mayores de edad, y luego `map(User::getName)` para proyectar unicamente el nombre de cada uno.

---

### Ejercicio 05 — Transacciones Bancarias
**Operadores utilizados:** `peek() - anyMatch()`

Dada una lista de transacciones bancarias (`Transaction`: id, amount, approved), se procesa la lista usando `peek()` para ver cada transaccion y `anyMatch()` para verificar si existe al menos una transaccion no aprobada, retornando si el lote es valido.

**Codigo implementado:**
```java
package dosw.semana_1.streams;

import java.util.List;

/**
 * Ejercicio 05 - Transacciones bancarias
 * Operadores: peek() - anyMatch()
 */
public class Ejercicio5 {
    public static void main(String[] args) {
        List<Transaction> transactions = List.of(
                new Transaction("T1", 150.0, true),
                new Transaction("T2", 300.0, true),
                new Transaction("T3", 75.5, false),
                new Transaction("T4", 500.0, true)
        );

        boolean existeNoAprobada = transactions.stream()
                .peek(System.out::println)
                .anyMatch(t -> !t.isApproved());

        boolean loteValido = !existeNoAprobada;

        System.out.println("Existe al menos una transaccion no aprobada: " + existeNoAprobada);
        System.out.println("El lote de transacciones es valido: " + loteValido);
    }
}
```

**Captura de ejecucion:**

![Ejercicio 5](docs/capturas/semana_1/ejercicio5.png)

**Explicacion:**
`peek(System.out::println)` imprime cada transaccion a medida que se recorre el stream (unicamente con fines de trazabilidad, sin modificar el flujo); `anyMatch(t -> !t.isApproved())` determina si existe alguna transaccion no aprobada. El lote se considera valido cuando no existe ninguna transaccion no aprobada.

---

> NOTA: se siguio esta estructura para los 5 ejercicios correspondientes (vistos en la clase de LAB01).

---

# SEMANA No 2 — Bitacora Pokemon

## Datos de Entrenador:
- Nombre y Apellido: Julian Morales
- Codigo de Estudiante: 1000091825
- Curso: DOSW

---

## Nivel 1 — Entrenador Novato
_Operaciones basicas con Streams_

### Ejercicio 01 — Pokemon Tipo Fuego
**Operadores utilizados:** `filter()`

Dada una lista de Pokemon con nombre y tipo, obtener unicamente aquellos cuyo tipo sea Fuego.

**Codigo implementado:**
```java
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
```

**Captura de ejecucion:**

![Ejercicio 1](docs/capturas/semana_2/ejercicio1.png)

**Explicacion:**
Se filtra el stream con `filter()` comparando el tipo con "Fuego" y se proyecta el nombre con `map()`.

---

### Ejercicio 02 — Pokedex Gritona
**Operadores utilizados:** `map()`

Transformar todos los nombres de Pokemon a mayusculas.

**Codigo implementado:**
```java
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
```

**Captura de ejecucion:**

![Ejercicio 2](docs/capturas/semana_2/ejercicio2.png)

**Explicacion:**
Se aplica `map(String::toUpperCase)` a cada nombre del stream y se unen con `String.join` para mostrarlos.

---

### Ejercicio 03 — Poder Total del Equipo
**Operadores utilizados:** `reduce()`

Dada una lista de niveles de Pokemon, calcular la suma total de niveles del equipo.

**Codigo implementado:**
```java
package dosw.semana_2.pokemon;

import java.util.List;

/**
 * Ejercicio 03 - Poder Total del Equipo
 * Operador: reduce()
 */
public class Ejercicio3 {
    public static void main(String[] args) {
        List<Integer> niveles = List.of(45, 62, 38, 71, 55, 29);

        int sumaTotal = niveles.stream()
                .reduce(0, Integer::sum);

        System.out.println("Suma total de niveles: " + sumaTotal);
    }
}
```

**Captura de ejecucion:**

![Ejercicio 3](docs/capturas/semana_2/ejercicio3.png)

**Explicacion:**
`reduce(0, Integer::sum)` acumula el total sumando cada nivel del stream, partiendo de un valor identidad de 0.

---

### Ejercicio 04 — Pokemon Alfa
**Operadores utilizados:** `max(Comparator)`

Encontrar el Pokemon con el nivel mas alto dentro del equipo.

**Codigo implementado:**
```java
package dosw.semana_2.pokemon;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Ejercicio 04 - Pokemon Alfa
 * Operador: max(Comparator)
 */
public class Ejercicio4 {

    record PokemonNivel(String nombre, int nivel) {}

    public static void main(String[] args) {
        List<PokemonNivel> equipo = List.of(
                new PokemonNivel("Pikachu", 45),
                new PokemonNivel("Charmander", 62),
                new PokemonNivel("Squirtle", 38),
                new PokemonNivel("Snorlax", 90),
                new PokemonNivel("Mewtwo", 88)
        );

        Optional<PokemonNivel> alfa = equipo.stream()
                .max(Comparator.comparingInt(PokemonNivel::nivel));

        alfa.ifPresent(p -> System.out.println("Pokemon Alfa: " + p.nombre() + " (nivel " + p.nivel() + ")"));
    }
}
```

**Captura de ejecucion:**

![Ejercicio 4](docs/capturas/semana_2/ejercicio4.png)

**Explicacion:**
`max(Comparator.comparingInt(...))` recorre el stream comparando niveles y retorna un `Optional` con el Pokemon de mayor nivel.

---

### Ejercicio 05 — Pokemon Legendarios
**Operadores utilizados:** `filter() + count()`

Contar cuantos Pokemon del equipo tienen nivel superior a 80.

**Codigo implementado:**
```java
package dosw.semana_2.pokemon;

import java.util.List;

/**
 * Ejercicio 05 - Pokemon Legendarios
 * Operadores: filter() + count()
 */
public class Ejercicio5 {

    record PokemonNivel(String nombre, int nivel) {}

    public static void main(String[] args) {
        List<PokemonNivel> equipo = List.of(
                new PokemonNivel("Pikachu", 45),
                new PokemonNivel("Mewtwo", 88),
                new PokemonNivel("Dragonite", 82),
                new PokemonNivel("Squirtle", 38),
                new PokemonNivel("Mew", 85),
                new PokemonNivel("Charmander", 62)
        );

        long cantidad = equipo.stream()
                .filter(p -> p.nivel() > 80)
                .count();

        List<String> nombres = equipo.stream()
                .filter(p -> p.nivel() > 80)
                .map(PokemonNivel::nombre)
                .toList();

        System.out.println("Pokemon con nivel > 80: " + cantidad + " " + nombres);
    }
}
```

**Captura de ejecucion:**

![Ejercicio 5](docs/capturas/semana_2/ejercicio5.png)

**Explicacion:**
`filter()` deja solo los Pokemon con nivel > 80 y `count()` obtiene la cantidad total que cumple la condicion.

---

## Nivel 2 — Entrenador Intermedio
_Filtrado y ordenamiento avanzado_

### Ejercicio 06 — Pokedex Sin Duplicados
**Operadores utilizados:** `distinct()`

Dada una lista de Pokemon con elementos repetidos, generar una nueva coleccion donde cada Pokemon aparezca una sola vez.

**Codigo implementado:**
```java
package dosw.semana_2.pokemon;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Ejercicio 06 - Pokedex Sin Duplicados
 * Operador: distinct()
 */
public class Ejercicio6 {
    public static void main(String[] args) {
        List<String> pokedex = List.of("Pikachu", "Charmander", "Pikachu", "Squirtle", "Charmander", "Mewtwo");

        List<String> sinDuplicados = pokedex.stream()
                .distinct()
                .collect(Collectors.toList());

        System.out.println(sinDuplicados);
    }
}
```

**Captura de ejecucion:**

![Ejercicio 6](docs/capturas/semana_2/ejercicio6.png)

**Explicacion:**
`distinct()` elimina los duplicados del stream basandose en el `equals()` de `String`, preservando el primer orden de aparicion.

---

### Ejercicio 07 — Orden del Profesor Oak
**Operadores utilizados:** `sorted()`

Ordenar alfabeticamente los nombres de los Pokemon.

**Codigo implementado:**
```java
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
```

**Captura de ejecucion:**

![Ejercicio 7](docs/capturas/semana_2/ejercicio7.png)

**Explicacion:**
`sorted()` aplica el orden natural (alfabetico) de `String` sobre el stream de nombres.

---

### Ejercicio 08 — Evoluciones Preparadas
**Operadores utilizados:** `filter()`

Dada una lista de Pokemon con el atributo `puedeEvolucionar`, obtener unicamente los que esten listos para evolucionar.

**Codigo implementado:**
```java
package dosw.semana_2.pokemon;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Ejercicio 08 - Evoluciones Preparadas
 * Operador: filter()
 */
public class Ejercicio8 {

    record PokemonEvolucion(String nombre, boolean puedeEvolucionar) {}

    public static void main(String[] args) {
        List<PokemonEvolucion> equipo = List.of(
                new PokemonEvolucion("Pikachu", true),
                new PokemonEvolucion("Raichu", false),
                new PokemonEvolucion("Charmander", true),
                new PokemonEvolucion("Charizard", false),
                new PokemonEvolucion("Squirtle", true),
                new PokemonEvolucion("Blastoise", false)
        );

        List<String> listos = equipo.stream()
                .filter(PokemonEvolucion::puedeEvolucionar)
                .map(PokemonEvolucion::nombre)
                .collect(Collectors.toList());

        System.out.println("Listos para evolucionar: " + listos);
    }
}
```

**Captura de ejecucion:**

![Ejercicio 8](docs/capturas/semana_2/ejercicio8.png)

**Explicacion:**
`filter(PokemonEvolucion::puedeEvolucionar)` usa una method reference sobre el record para dejar solo los Pokemon listos para evolucionar.

---

## Nivel 3 — Lider de Gimnasio
_Manipulacion de objetos complejos_

### Ejercicio 09 — Equipo Elite
**Operadores utilizados:** `filter()`

Mostrar unicamente los Pokemon cuyo poderCombate sea superior a 500. A partir de aqui se usa la clase `Pokemon`.

**Codigo implementado:**
```java
package dosw.semana_2.pokemon;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Ejercicio 09 - Equipo Elite
 * Operador: filter()
 */
public class Ejercicio9 {
    public static void main(String[] args) {
        List<Pokemon> equipo = List.of(
                new Pokemon(1L, "Pikachu", "Electrico", 45, 320, "Kanto", false),
                new Pokemon(2L, "Mewtwo", "Psiquico", 70, 680, "Kanto", true),
                new Pokemon(3L, "Dragonite", "Dragon", 55, 530, "Kanto", false),
                new Pokemon(4L, "Squirtle", "Agua", 20, 210, "Kanto", false),
                new Pokemon(5L, "Gengar", "Fantasma", 40, 495, "Kanto", false),
                new Pokemon(6L, "Charizard", "Fuego", 58, 610, "Kanto", false)
        );

        List<Pokemon> equipoElite = equipo.stream()
                .filter(p -> p.getPoderCombate() > 500)
                .collect(Collectors.toList());

        System.out.println("Equipo Elite (PC > 500): " + equipoElite);
    }
}
```

**Captura de ejecucion:**

![Ejercicio 9](docs/capturas/semana_2/ejercicio9.png)

**Explicacion:**
Se filtra el stream de objetos `Pokemon` comparando `getPoderCombate()` contra 500.

---

### Ejercicio 10 — Pokedex Compacta
**Operadores utilizados:** `map() + collect()`

Generar una lista que contenga unicamente los nombres de todos los Pokemon del equipo.

**Codigo implementado:**
```java
package dosw.semana_2.pokemon;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Ejercicio 10 - Pokedex Compacta
 * Operadores: map() + collect()
 */
public class Ejercicio10 {
    public static void main(String[] args) {
        List<Pokemon> equipo = List.of(
                new Pokemon(1L, "Pikachu", "Electrico", 45, 320, "Kanto", false),
                new Pokemon(2L, "Mewtwo", "Psiquico", 70, 680, "Kanto", true),
                new Pokemon(3L, "Dragonite", "Dragon", 55, 530, "Kanto", false),
                new Pokemon(4L, "Squirtle", "Agua", 20, 210, "Kanto", false),
                new Pokemon(5L, "Gengar", "Fantasma", 40, 495, "Kanto", false),
                new Pokemon(6L, "Charizard", "Fuego", 58, 610, "Kanto", false)
        );

        List<String> nombres = equipo.stream()
                .map(Pokemon::getNombre)
                .collect(Collectors.toList());

        System.out.println(nombres);
    }
}
```

**Captura de ejecucion:**

![Ejercicio 10](docs/capturas/semana_2/ejercicio10.png)

**Explicacion:**
`map(Pokemon::getNombre)` proyecta cada objeto `Pokemon` a su nombre, y `collect()` construye la lista final de Strings.

---

### Ejercicio 11 — Poder Promedio
**Operadores utilizados:** `mapToDouble() + average()`

Calcular el promedio de poderCombate de todos los Pokemon del equipo.

**Codigo implementado:**
```java
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
```

**Captura de ejecucion:**

![Ejercicio 11](docs/capturas/semana_2/ejercicio11.png)

**Explicacion:**
`mapToDouble()` convierte el stream de objetos en un `DoubleStream` con el poder de combate, y `average()` calcula el promedio como `OptionalDouble`.

---

### Ejercicio 12 — Campeon Regional
**Operadores utilizados:** `max(Comparator)`

Obtener el Pokemon con mayor poderCombate de toda la lista.

**Codigo implementado:**
```java
package dosw.semana_2.pokemon;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Ejercicio 12 - Campeon Regional
 * Operador: max(Comparator)
 */
public class Ejercicio12 {
    public static void main(String[] args) {
        List<Pokemon> equipo = List.of(
                new Pokemon(1L, "Pikachu", "Electrico", 45, 320, "Kanto", false),
                new Pokemon(2L, "Mewtwo", "Psiquico", 70, 680, "Kanto", true),
                new Pokemon(3L, "Dragonite", "Dragon", 55, 530, "Kanto", false),
                new Pokemon(4L, "Charizard", "Fuego", 58, 610, "Kanto", false)
        );

        Optional<Pokemon> campeon = equipo.stream()
                .max(Comparator.comparingDouble(Pokemon::getPoderCombate));

        campeon.ifPresent(p -> System.out.println("Campeon: " + p.getNombre() + " con PC: " + p.getPoderCombate()));
    }
}
```

**Captura de ejecucion:**

![Ejercicio 12](docs/capturas/semana_2/ejercicio12.png)

**Explicacion:**
`max(Comparator.comparingDouble(Pokemon::getPoderCombate))` retorna el Pokemon con el poder de combate mas alto.

---

### Ejercicio 13 — Organizar por Tipo
**Operadores utilizados:** `groupingBy()`

Agrupar todos los Pokemon por su tipo y mostrar el listado por grupo.

**Codigo implementado:**
```java
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
```

**Captura de ejecucion:**

![Ejercicio 13](docs/capturas/semana_2/ejercicio13.png)

**Explicacion:**
`Collectors.groupingBy(Pokemon::getTipo, Collectors.mapping(...))` agrupa los Pokemon en un `Map<String, List<String>>` segun su tipo.

---

### Ejercicio 14 — Organizar por Region
**Operadores utilizados:** `groupingBy()`

Agrupar los Pokemon segun su region de origen. A partir de aqui se usa tambien la clase `Entrenador`.

**Codigo implementado:**
```java
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
```

**Captura de ejecucion:**

![Ejercicio 14](docs/capturas/semana_2/ejercicio14.png)

**Explicacion:**
Misma estrategia que el ejercicio 13 pero agrupando por `getRegion()` en lugar de `getTipo()`.

---

## Nivel 4 — Alto Mando
_Objetos anidados y comparaciones_

### Ejercicio 15 — Maestro de Gimnasios
**Operadores utilizados:** `max(Comparator)`

Dado un listado de entrenadores con sus medallas, encontrar el entrenador con mas medallas.

**Codigo implementado:**
```java
package dosw.semana_2.pokemon;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Ejercicio 15 - Maestro de Gimnasios
 * Operador: max(Comparator)
 */
public class Ejercicio15 {
    public static void main(String[] args) {
        List<Entrenador> entrenadores = List.of(
                new Entrenador(1L, "Ash", 8, List.of()),
                new Entrenador(2L, "Misty", 5, List.of()),
                new Entrenador(3L, "Brock", 6, List.of()),
                new Entrenador(4L, "Gary", 10, List.of())
        );

        Optional<Entrenador> campeon = entrenadores.stream()
                .max(Comparator.comparingInt(Entrenador::getMedallas));

        campeon.ifPresent(e -> System.out.println("Campeon de gimnasios: " + e.getNombre()
                + "\nMedallas obtenidas: " + e.getMedallas()));
    }
}
```

**Captura de ejecucion:**

![Ejercicio 15](docs/capturas/semana_2/ejercicio15.png)

**Explicacion:**
`max(Comparator.comparingInt(Entrenador::getMedallas))` recorre el stream de entrenadores y retorna el de mayor numero de medallas.

---

### Ejercicio 16 — Entrenadores Experimentados
**Operadores utilizados:** `filter()`

Mostrar unicamente los entrenadores que posean mas de 5 medallas.

**Codigo implementado:**
```java
package dosw.semana_2.pokemon;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Ejercicio 16 - Entrenadores Experimentados
 * Operador: filter()
 */
public class Ejercicio16 {
    public static void main(String[] args) {
        List<Entrenador> entrenadores = List.of(
                new Entrenador(1L, "Ash", 8, List.of()),
                new Entrenador(2L, "Misty", 5, List.of()),
                new Entrenador(3L, "Brock", 6, List.of()),
                new Entrenador(4L, "Gary", 10, List.of()),
                new Entrenador(5L, "May", 3, List.of()),
                new Entrenador(6L, "Dawn", 7, List.of())
        );

        List<Entrenador> experimentados = entrenadores.stream()
                .filter(e -> e.getMedallas() > 5)
                .collect(Collectors.toList());

        System.out.println("Entrenadores con > 5 medallas: " + experimentados);
    }
}
```

**Captura de ejecucion:**

![Ejercicio 16](docs/capturas/semana_2/ejercicio16.png)

**Explicacion:**
`filter(e -> e.getMedallas() > 5)` deja solo los entrenadores que superan las 5 medallas.

---

### Ejercicio 17 — Equipo Mas Poderoso
**Operadores utilizados:** `mapToDouble() + sum()`

Calcular cual entrenador tiene la suma total de poderCombate mas alta entre todos sus Pokemon.

**Codigo implementado:**
```java
package dosw.semana_2.pokemon;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Ejercicio 17 - Equipo Mas Poderoso
 * Operadores: mapToDouble() + sum()
 */
public class Ejercicio17 {
    public static void main(String[] args) {
        List<Entrenador> entrenadores = List.of(
                new Entrenador(1L, "Ash", 8, List.of(
                        new Pokemon(1L, "Pikachu", "Electrico", 45, 850, "Kanto", false),
                        new Pokemon(2L, "Charizard", "Fuego", 58, 1000, "Kanto", false)
                )),
                new Entrenador(2L, "Gary", 10, List.of(
                        new Pokemon(3L, "Blastoise", "Agua", 60, 1200, "Kanto", false),
                        new Pokemon(4L, "Nidoking", "Veneno", 55, 1140, "Kanto", false)
                )),
                new Entrenador(3L, "Brock", 6, List.of(
                        new Pokemon(5L, "Onix", "Roca", 50, 900, "Kanto", false),
                        new Pokemon(6L, "Geodude", "Roca", 40, 770, "Kanto", false)
                ))
        );

        Optional<Entrenador> masPoderoso = entrenadores.stream()
                .max(Comparator.comparingDouble(e -> e.getEquipo().stream()
                        .mapToDouble(Pokemon::getPoderCombate)
                        .sum()));

        masPoderoso.ifPresent(e -> {
            double poder = e.getEquipo().stream().mapToDouble(Pokemon::getPoderCombate).sum();
            System.out.println("Entrenador mas poderoso: " + e.getNombre());
            System.out.println("Poder acumulado del equipo: " + poder);
        });
    }
}
```

**Captura de ejecucion:**

![Ejercicio 17](docs/capturas/semana_2/ejercicio17.png)

**Explicacion:**
Para cada entrenador se calcula la suma de poder de su equipo con un stream anidado (`mapToDouble().sum()`), y `max(Comparator.comparingDouble(...))` determina cual entrenador tiene el total mas alto.

---

## Nivel 5 — Campeon de la Liga Pokemon DOSW
_Analisis avanzado y rankings_

### Ejercicio 18 — Top 5 Pokemon Mas Fuertes
**Operadores utilizados:** `sorted() + limit(5)`

Generar un ranking de los cinco Pokemon con mayor poderCombate de toda la Pokedex.

**Codigo implementado:**
```java
package dosw.semana_2.pokemon;

import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Ejercicio 18 - Top 5 Pokemon Mas Fuertes
 * Operadores: sorted() + limit(5)
 */
public class Ejercicio18 {
    public static void main(String[] args) {
        List<Pokemon> pokedex = List.of(
                new Pokemon(1L, "Pikachu", "Electrico", 45, 320, "Kanto", false),
                new Pokemon(2L, "Mewtwo", "Psiquico", 70, 680, "Kanto", true),
                new Pokemon(3L, "Dragonite", "Dragon", 55, 530, "Kanto", false),
                new Pokemon(4L, "Squirtle", "Agua", 20, 210, "Kanto", false),
                new Pokemon(5L, "Gengar", "Fantasma", 40, 495, "Kanto", false),
                new Pokemon(6L, "Charizard", "Fuego", 58, 610, "Kanto", false)
        );

        List<Pokemon> top5 = pokedex.stream()
                .sorted(Comparator.comparingDouble(Pokemon::getPoderCombate).reversed())
                .limit(5)
                .toList();

        IntStream.range(0, top5.size())
                .mapToObj(i -> "#" + (i + 1) + " " + top5.get(i).getNombre()
                        + " -- PC: " + (int) top5.get(i).getPoderCombate())
                .forEach(System.out::println);
    }
}
```

**Captura de ejecucion:**

![Ejercicio 18](docs/capturas/semana_2/ejercicio18.png)

**Explicacion:**
`sorted(Comparator.comparingDouble(Pokemon::getPoderCombate).reversed())` ordena de mayor a menor poder, y `limit(5)` se queda con los primeros cinco. El ranking se imprime con `IntStream.range()` para evitar ciclos tradicionales.

---

### Ejercicio 19 — Top 3 Entrenadores
**Operadores utilizados:** `sorted() + limit(3)`

Generar un ranking de los 3 mejores entrenadores considerando: 1 mas medallas, 2 mayor poder acumulado, 3 orden alfabetico como criterio de desempate.

**Codigo implementado:**
```java
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
```

**Captura de ejecucion:**

![Ejercicio 19](docs/capturas/semana_2/ejercicio19.png)

**Explicacion:**
Se construye un `Comparator` compuesto con `thenComparing()` que aplica los tres criterios en cascada (medallas desc, poder desc, nombre asc), y luego `sorted().limit(3)` obtiene el podio.

---

### Ejercicio 20 — Pokedex Analitica
**Operadores utilizados:** `groupingBy() + counting()`

Construir una estructura que muestre: cantidad de Pokemon por tipo, por region, cantidad de legendarios, promedio de nivel y el Pokemon mas fuerte, todo usando unicamente Streams.

**Codigo implementado:**
```java
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
```

**Captura de ejecucion:**

![Ejercicio 20](docs/capturas/semana_2/ejercicio20.png)

**Explicacion:**
Se combinan varios pipelines independientes sobre la misma lista: `groupingBy()+counting()` para los conteos por tipo y region, `filter()+count()` para los legendarios, `mapToInt().average()` para el promedio de nivel, y `max(Comparator...)` para el Pokemon mas fuerte.

---

> NOTA: se siguio esta estructura por los 20 ejercicios correspondientes de este taller.

---

## Retos Especiales (si aplica)
- [ ] Reto Legendario — Method References
- [ ] Reto Shiny — Buenas practicas de commits
- [ ] Reto Mewtwo — Ejercicio propuesto

---

## Estrategia de ramas (Git Flow)
- Ramas principales: `main` y `develop`.
- Por cada semana: `feature/semana-n-dosw`.
- Por cada ejercicio: `feature/semana-n-dosw-ejercicio-n`, mergeada hacia la rama de la semana y luego eliminada.
- Al completar la semana, `feature/semana-n-dosw` se fusiona a `develop` mediante Pull Request (esta rama semanal no se elimina, queda como evidencia).
- Al cierre de cada ciclo, se sincroniza `develop` hacia `main`.
