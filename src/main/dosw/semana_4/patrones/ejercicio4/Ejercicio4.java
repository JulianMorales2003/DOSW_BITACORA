package dosw.semana_4.patrones.ejercicio4;

/**
 * Ejercicio 04 - Plataforma de Videojuegos: Personajes
 * Patrones: Builder + Decorator
 */
public class Ejercicio4 {

    interface Character { String attack(); }

    static class Warrior implements Character {
        private final String armor;
        private final String weapon;
        private final String skill;
        Warrior(String armor, String weapon, String skill) {
            this.armor = armor; this.weapon = weapon; this.skill = skill;
        }
        public String attack() {
            return "Ataque base (armadura:" + armor + ", arma:" + weapon + ", habilidad:" + skill + ")";
        }
    }

    static class WarriorBuilder {
        private String armor = "ninguna";
        private String weapon = "punios";
        private String skill = "ninguna";
        WarriorBuilder setArmor(String a) { this.armor = a; return this; }
        WarriorBuilder setWeapon(String w) { this.weapon = w; return this; }
        WarriorBuilder setSkill(String s) { this.skill = s; return this; }
        Warrior build() { return new Warrior(armor, weapon, skill); }
    }

    static abstract class CharacterDecorator implements Character {
        protected final Character wrapped;
        CharacterDecorator(Character wrapped) { this.wrapped = wrapped; }
    }
    static class ShieldDecorator extends CharacterDecorator {
        ShieldDecorator(Character c) { super(c); }
        public String attack() { return wrapped.attack() + " + escudo de hielo activo"; }
    }
    static class SpeedDecorator extends CharacterDecorator {
        SpeedDecorator(Character c) { super(c); }
        public String attack() { return wrapped.attack() + " + velocidad extra"; }
    }

    public static void main(String[] args) {
        Character warrior = new WarriorBuilder()
                .setArmor("acero")
                .setWeapon("espada")
                .setSkill("furia")
                .build();

        System.out.println(warrior.attack());

        Character powered = new ShieldDecorator(new SpeedDecorator(warrior));
        System.out.println(powered.attack());
    }
}
