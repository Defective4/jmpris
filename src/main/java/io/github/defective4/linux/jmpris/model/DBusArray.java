package io.github.defective4.linux.jmpris.model;

import java.util.Collections;
import java.util.List;

public class DBusArray extends DBusObject {
    private final List<DBusObject> items;

    public DBusArray() {
        this(Collections.emptyList());
    }

    public DBusArray(List<DBusObject> items) {
        this.items = List.copyOf(items);
    }

    public List<DBusObject> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "DBusArray [items=" + items + "]";
    }

}
