package dosw.semana_4.patrones.ejercicio2;

import java.util.ArrayList;
import java.util.List;

/**
 * Ejercicio 02 - Sistema de Notificaciones Multicanal
 * Patrones: Observer + Factory Method
 */
public class Ejercicio2 {

    static class OrderEvent {
        String estado;
        OrderEvent(String estado) { this.estado = estado; }
    }

    interface Message { String build(); }
    static class EmailMessage implements Message {
        OrderEvent event;
        EmailMessage(OrderEvent e) { event = e; }
        public String build() { return "<html><body>Tu pedido cambio a: " + event.estado + "</body></html>"; }
    }
    static class SmsMessage implements Message {
        OrderEvent event;
        SmsMessage(OrderEvent e) { event = e; }
        public String build() {
            String texto = "Pedido: " + event.estado;
            return texto.length() > 160 ? texto.substring(0, 160) : texto;
        }
    }
    static class PushMessage implements Message {
        OrderEvent event;
        PushMessage(OrderEvent e) { event = e; }
        public String build() { return "{\"estado\":\"" + event.estado + "\"}"; }
    }

    interface MessageFactory { Message build(OrderEvent event); }
    static class EmailMessageFactory implements MessageFactory {
        public Message build(OrderEvent event) { return new EmailMessage(event); }
    }
    static class SmsMessageFactory implements MessageFactory {
        public Message build(OrderEvent event) { return new SmsMessage(event); }
    }
    static class PushMessageFactory implements MessageFactory {
        public Message build(OrderEvent event) { return new PushMessage(event); }
    }

    interface NotificationObserver { void notify(OrderEvent event); }
    static class EmailNotifier implements NotificationObserver {
        MessageFactory factory = new EmailMessageFactory();
        public void notify(OrderEvent event) {
            System.out.println("[EMAIL] " + factory.build(event).build());
        }
    }
    static class SmsNotifier implements NotificationObserver {
        MessageFactory factory = new SmsMessageFactory();
        public void notify(OrderEvent event) {
            System.out.println("[SMS] " + factory.build(event).build());
        }
    }
    static class PushNotifier implements NotificationObserver {
        MessageFactory factory = new PushMessageFactory();
        public void notify(OrderEvent event) {
            System.out.println("[PUSH] " + factory.build(event).build());
        }
    }

    static class Pedido {
        private final List<NotificationObserver> observers = new ArrayList<>();
        void addObserver(NotificationObserver o) { observers.add(o); }
        void cambiarEstado(String estado) {
            OrderEvent event = new OrderEvent(estado);
            for (NotificationObserver o : observers) o.notify(event);
        }
    }

    public static void main(String[] args) {
        Pedido pedido = new Pedido();
        pedido.addObserver(new EmailNotifier());
        pedido.addObserver(new SmsNotifier());
        pedido.addObserver(new PushNotifier());

        pedido.cambiarEstado("enviado");
        pedido.cambiarEstado("entregado");
    }
}
