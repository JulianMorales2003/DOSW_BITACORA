package dosw.semana_4.patrones.ejercicio9;

/**
 * Ejercicio 09 - Sistema de Autenticacion Empresarial
 * Patrones: Strategy + Chain of Responsibility
 */
public class Ejercicio9 {

    static class Credentials {
        String usuario;
        Credentials(String usuario) { this.usuario = usuario; }
    }
    static class AuthResult {
        boolean success;
        String usuario;
        AuthResult(boolean success, String usuario) { this.success = success; this.usuario = usuario; }
    }

    interface AuthStrategy { AuthResult authenticate(Credentials c); }
    static class PasswordStrategy implements AuthStrategy {
        public AuthResult authenticate(Credentials c) {
            System.out.println("Autenticando con usuario/contrasenia");
            return new AuthResult(true, c.usuario);
        }
    }
    static class GoogleStrategy implements AuthStrategy {
        public AuthResult authenticate(Credentials c) {
            System.out.println("Autenticando con Google");
            return new AuthResult(true, c.usuario);
        }
    }
    static class BiometricStrategy implements AuthStrategy {
        public AuthResult authenticate(Credentials c) {
            System.out.println("Autenticando con biometria");
            return new AuthResult(true, c.usuario);
        }
    }

    static class AccessDeniedException extends RuntimeException {
        AccessDeniedException(String msg) { super(msg); }
    }

    static abstract class Validator {
        private Validator next;
        Validator setNext(Validator next) { this.next = next; return next; }
        void validate(AuthResult result) {
            check(result);
            if (next != null) next.validate(result);
        }
        abstract void check(AuthResult result);
    }
    static class CredentialValidator extends Validator {
        void check(AuthResult result) {
            if (!result.success) throw new AccessDeniedException("Credenciales invalidas");
            System.out.println("Credenciales validas para " + result.usuario);
        }
    }
    static class PermissionValidator extends Validator {
        void check(AuthResult result) { System.out.println("Permisos verificados para " + result.usuario); }
    }
    static class LocationValidator extends Validator {
        void check(AuthResult result) { System.out.println("Ubicacion verificada para " + result.usuario); }
    }
    static class TimeValidator extends Validator {
        void check(AuthResult result) { System.out.println("Horario laboral verificado para " + result.usuario); }
    }

    static class AuthService {
        AuthResult login(AuthStrategy strategy, Credentials credentials, Validator chain) {
            AuthResult result = strategy.authenticate(credentials);
            chain.validate(result);
            System.out.println("Acceso concedido a " + result.usuario);
            return result;
        }
    }

    public static void main(String[] args) {
        Validator cred = new CredentialValidator();
        Validator perm = new PermissionValidator();
        Validator loc = new LocationValidator();
        Validator time = new TimeValidator();
        cred.setNext(perm).setNext(loc).setNext(time);

        AuthService service = new AuthService();
        service.login(new GoogleStrategy(), new Credentials("julian"), cred);
    }
}
