package dosw.semana_4.patrones.ejercicio5;

/**
 * Ejercicio 05 - Integracion con Sistema Bancario Antiguo
 * Patrones: Adapter + Facade
 */
public class Ejercicio5 {

    static class LegacyBankService {
        void executeTransaction(String account, int cents) {
            System.out.println("[LEGACY] Ejecutando transaccion en cuenta " + account + " por " + cents + " centavos");
        }
        boolean verifyBalance(String account, int cents) {
            System.out.println("[LEGACY] Verificando saldo de " + account);
            return true;
        }
    }

    interface PaymentProcessor { void pay(double amount); }

    static class LegacyBankAdapter implements PaymentProcessor {
        private final LegacyBankService legacy;
        private final String account;
        LegacyBankAdapter(LegacyBankService legacy, String account) {
            this.legacy = legacy; this.account = account;
        }
        public void pay(double amount) {
            int cents = (int) Math.round(amount * 100);
            legacy.verifyBalance(account, cents);
            legacy.executeTransaction(account, cents);
        }
    }

    static class BankFacade {
        private final PaymentProcessor adapter;
        BankFacade() {
            System.out.println("Inicializando conexion...");
            System.out.println("Abriendo sesion segura...");
            System.out.println("Cargando contexto de cuenta...");
            this.adapter = new LegacyBankAdapter(new LegacyBankService(), "ACC-001");
        }
        void procesarPago(double monto) {
            adapter.pay(monto);
        }
    }

    public static void main(String[] args) {
        BankFacade facade = new BankFacade();
        facade.procesarPago(250000.50);
    }
}
