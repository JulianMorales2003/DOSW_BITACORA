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
