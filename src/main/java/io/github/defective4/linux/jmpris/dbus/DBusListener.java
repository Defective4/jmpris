package io.github.defective4.linux.jmpris.dbus;

import io.github.defective4.linux.jmpris.model.DBusArray;

public interface DBusListener {
    void objectReceived(DBusArray obj);
}
