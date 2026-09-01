package dosw.semana_4.patrones.ejercicio1;

/**
 * Ejercicio 01 - Plataforma de Pagos Inteligentes
 * Patrones: Strategy + Factory Method
 */
public class Ejercicio1 {

    interface PaymentStrategy { void process(double amount); }

    static class TarjetaStrategy implements PaymentStrategy {
        public void process(double amount) {
            System.out.println("Pago de $" + amount + " procesado con Tarjeta de Credito");
        }
    }
    static class PseStrategy implements PaymentStrategy {
        public void process(double amount) {
            System.out.println("Pago de $" + amount + " procesado con PSE");
        }
    }
    static class NequiStrategy implements PaymentStrategy {
        public void process(double amount) {
            System.out.println("Pago de $" + amount + " procesado con Nequi");
        }
    }
    static class PayPalStrategy implements PaymentStrategy {
        public void process(double amount) {
            System.out.println("Payment of $" + amount + " processed with PayPal");
        }
    }

    interface PaymentFactory { PaymentStrategy create(String metodo); }

    static class ColombiaPaymentFactory implements PaymentFactory {
        public PaymentStrategy create(String metodo) {
            return switch (metodo) {
                case "TARJETA" -> new TarjetaStrategy();
                case "PSE" -> new PseStrategy();
                case "NEQUI" -> new NequiStrategy();
                default -> throw new IllegalArgumentException("Metodo no soportado en Colombia: " + metodo);
            };
        }
    }
    static class UsaPaymentFactory implements PaymentFactory {
        public PaymentStrategy create(String metodo) {
            return switch (metodo) {
                case "PAYPAL" -> new PayPalStrategy();
                default -> throw new IllegalArgumentException("Metodo no soportado en USA: " + metodo);
            };
        }
    }

    static class Checkout {
        private final PaymentStrategy strategy;
        Checkout(PaymentStrategy strategy) { this.strategy = strategy; }
        void pay(double amount) { strategy.process(amount); }
    }

    public static void main(String[] args) {
        PaymentFactory factoryColombia = new ColombiaPaymentFactory();
        Checkout checkout1 = new Checkout(factoryColombia.create("NEQUI"));
        checkout1.pay(150000);

        PaymentFactory factoryUsa = new UsaPaymentFactory();
        Checkout checkout2 = new Checkout(factoryUsa.create("PAYPAL"));
        checkout2.pay(49.99);
    }
}
