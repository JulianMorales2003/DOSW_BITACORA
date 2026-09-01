package dosw.semana_4.patrones.ejercicio8;

import java.util.ArrayList;
import java.util.List;

/**
 * Ejercicio 08 - Sistema de Pedidos en Restaurante
 * Patrones: Builder + Observer
 */
public class Ejercicio8 {

    enum Size { SMALL, MEDIUM, LARGE }
    enum Meat { SIMPLE_BEEF, DOUBLE_BEEF, CHICKEN }

    static class Pedido {
        final Size size;
        final Meat meat;
        final List<String> toppings;
        final List<String> sides;
        private final List<PedidoObserver> observers = new ArrayList<>();

        Pedido(Size size, Meat meat, List<String> toppings, List<String> sides) {
            this.size = size; this.meat = meat; this.toppings = toppings; this.sides = sides;
        }
        void addObserver(PedidoObserver o) { observers.add(o); }
        void confirm() {
            System.out.println("Pedido confirmado: " + size + " " + meat + " con " + toppings + " y " + sides);
            for (PedidoObserver o : observers) o.onPedidoConfirmado(this);
        }
    }

    static class PedidoBuilder {
        private Size size = Size.MEDIUM;
        private Meat meat = Meat.SIMPLE_BEEF;
        private final List<String> toppings = new ArrayList<>();
        private final List<String> sides = new ArrayList<>();
        PedidoBuilder setSize(Size s) { this.size = s; return this; }
        PedidoBuilder setMeat(Meat m) { this.meat = m; return this; }
        PedidoBuilder addTopping(String... t) { toppings.addAll(List.of(t)); return this; }
        PedidoBuilder addSide(String... s) { sides.addAll(List.of(s)); return this; }
        Pedido build() { return new Pedido(size, meat, List.copyOf(toppings), List.copyOf(sides)); }
    }

    interface PedidoObserver { void onPedidoConfirmado(Pedido pedido); }
    static class KitchenService implements PedidoObserver {
        public void onPedidoConfirmado(Pedido pedido) { System.out.println("[Cocina] Preparando pedido..."); }
    }
    static class BillingService implements PedidoObserver {
        public void onPedidoConfirmado(Pedido pedido) { System.out.println("[Facturacion] Generando cuenta..."); }
    }
    static class DeliveryService implements PedidoObserver {
        public void onPedidoConfirmado(Pedido pedido) { System.out.println("[Domicilio] Preparando ruta..."); }
    }

    public static void main(String[] args) {
        Pedido pedido = new PedidoBuilder()
                .setSize(Size.LARGE)
                .setMeat(Meat.DOUBLE_BEEF)
                .addTopping("queso", "lechuga")
                .addSide("papas", "gaseosa")
                .build();

        pedido.addObserver(new KitchenService());
        pedido.addObserver(new BillingService());
        pedido.addObserver(new DeliveryService());

        pedido.confirm();
    }
}
