package wtf.norma.nekito.module.value;

public abstract class Value<T> {

    private final String name;

    public Value(String name) {
        this.name = name;
    }

    public abstract T get();

    public abstract void set(T value);

    public String getName() {
        return name;
    }
}
