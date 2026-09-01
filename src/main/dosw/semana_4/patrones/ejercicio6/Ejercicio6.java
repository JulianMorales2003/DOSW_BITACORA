package dosw.semana_4.patrones.ejercicio6;

import java.util.ArrayList;
import java.util.List;

/**
 * Ejercicio 06 - Motor de Recomendaciones
 * Patrones: Strategy + Observer
 */
public class Ejercicio6 {

    static class Content {
        String titulo;
        Content(String titulo) { this.titulo = titulo; }
        public String toString() { return titulo; }
    }
    static class User {
        String nombre;
        RecommendationAlgorithm algoritmo;
        User(String nombre, RecommendationAlgorithm algoritmo) { this.nombre = nombre; this.algoritmo = algoritmo; }
    }

    interface RecommendationAlgorithm { List<Content> recommend(User user); }
    static class GenreStrategy implements RecommendationAlgorithm {
        public List<Content> recommend(User user) { return List.of(new Content("Accion 1"), new Content("Accion 2")); }
    }
    static class HistoryStrategy implements RecommendationAlgorithm {
        public List<Content> recommend(User user) { return List.of(new Content("Basado en tu historial")); }
    }
    static class PopularityStrategy implements RecommendationAlgorithm {
        public List<Content> recommend(User user) { return List.of(new Content("Tendencia global 1")); }
    }

    interface PreferenceObserver { void onPreferenceChanged(User user); }
    static class HomePageComponent implements PreferenceObserver {
        public void onPreferenceChanged(User user) {
            System.out.println("[HomePage] Recargando con: " + user.algoritmo.recommend(user));
        }
    }
    static class NotificationService implements PreferenceObserver {
        public void onPreferenceChanged(User user) {
            System.out.println("[Notificaciones] Nueva recomendacion para " + user.nombre);
        }
    }
    static class SuggestedListComponent implements PreferenceObserver {
        public void onPreferenceChanged(User user) {
            System.out.println("[Sugeridos] Lista actualizada: " + user.algoritmo.recommend(user));
        }
    }

    static class UserProfile {
        User user;
        List<PreferenceObserver> observers = new ArrayList<>();
        UserProfile(User user) { this.user = user; }
        void addObserver(PreferenceObserver o) { observers.add(o); }
        void changeAlgorithm(RecommendationAlgorithm algoritmo) {
            user.algoritmo = algoritmo;
            for (PreferenceObserver o : observers) o.onPreferenceChanged(user);
        }
    }

    public static void main(String[] args) {
        User julian = new User("Julian", new GenreStrategy());
        UserProfile profile = new UserProfile(julian);
        profile.addObserver(new HomePageComponent());
        profile.addObserver(new NotificationService());
        profile.addObserver(new SuggestedListComponent());

        profile.changeAlgorithm(new PopularityStrategy());
        profile.changeAlgorithm(new HistoryStrategy());
    }
}
