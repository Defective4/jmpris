package io.github.defective4.linux.jmpris.model;

public class DBusDictionary extends DBusObject {
    private final String key;
    private final DBusObject value;

    public DBusDictionary(String key, DBusObject value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public DBusObject getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "DBusDictionary [key=" + key + ", value=" + value + "]";
    }

}
