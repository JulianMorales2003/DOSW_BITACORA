package dosw.semana_1.streams;

public class User {
    private final long id;
    private final String name;
    private final int age;
    private final boolean active;

    public User(long id, String name, int age, boolean active) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.active = active;
    }

    public long getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public boolean isActive() { return active; }
}
