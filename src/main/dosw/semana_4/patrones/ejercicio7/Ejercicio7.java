package dosw.semana_4.patrones.ejercicio7;

/**
 * Ejercicio 07 - Flujo de Aprobacion de Documentos
 * Patrones: Chain of Responsibility + State
 */
public class Ejercicio7 {

    static class Document {
        String nombre;
        DocumentState state;
        Document(String nombre) { this.nombre = nombre; this.state = new DraftState(); }
        void approve() { state.approve(this); }
        void reject() { state.reject(this); }
        void setState(DocumentState state) {
            this.state = state;
            System.out.println("Documento '" + nombre + "' ahora esta en estado: " + state.getClass().getSimpleName());
        }
    }

    interface DocumentState {
        void approve(Document doc);
        void reject(Document doc);
    }
    static class DraftState implements DocumentState {
        public void approve(Document doc) { doc.setState(new InReviewState()); }
        public void reject(Document doc) { System.out.println("No se puede rechazar un borrador"); }
    }
    static class InReviewState implements DocumentState {
        public void approve(Document doc) { doc.setState(new ApprovedState()); }
        public void reject(Document doc) { doc.setState(new RejectedState()); }
    }
    static class ApprovedState implements DocumentState {
        public void approve(Document doc) { System.out.println("Ya esta aprobado"); }
        public void reject(Document doc) { System.out.println("No se puede rechazar un documento aprobado"); }
    }
    static class RejectedState implements DocumentState {
        public void approve(Document doc) { System.out.println("No se puede aprobar un documento rechazado"); }
        public void reject(Document doc) { System.out.println("Ya esta rechazado"); }
    }

    static abstract class DocumentHandler {
        private DocumentHandler next;
        DocumentHandler setNext(DocumentHandler next) { this.next = next; return next; }
        void handle(Document doc) {
            if (canHandle(doc)) process(doc);
            else if (next != null) next.handle(doc);
            else System.out.println("Nadie mas puede procesar el documento");
        }
        abstract boolean canHandle(Document doc);
        abstract void process(Document doc);
    }
    static class AutorHandler extends DocumentHandler {
        boolean canHandle(Document doc) { return doc.state instanceof DraftState; }
        void process(Document doc) {
            System.out.println("Revision del autor OK");
            doc.approve();
        }
    }
    static class LiderHandler extends DocumentHandler {
        boolean canHandle(Document doc) { return doc.state instanceof InReviewState; }
        void process(Document doc) {
            System.out.println("Revision del lider OK");
            doc.approve();
        }
    }

    public static void main(String[] args) {
        Document doc = new Document("Contrato-001");
        DocumentHandler autor = new AutorHandler();
        DocumentHandler lider = new LiderHandler();
        autor.setNext(lider);

        System.out.println("--- Primera pasada por la cadena ---");
        autor.handle(doc);
        System.out.println("--- Segunda pasada por la cadena ---");
        autor.handle(doc);
    }
}
