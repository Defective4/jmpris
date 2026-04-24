package io.github.defective4.linux.jmpris.model;

public class DBusString extends DBusObject {
    private final String string;

    public DBusString(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

    @Override
    public String toString() {
        return getString();
    }

}
