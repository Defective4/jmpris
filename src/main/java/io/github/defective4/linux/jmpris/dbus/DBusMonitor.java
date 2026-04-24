package io.github.defective4.linux.jmpris.dbus;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import io.github.defective4.linux.jmpris.model.DBusArray;

public class DBusMonitor implements AutoCloseable {

    private final DBusListener listener;
    private final Process process;

    public DBusMonitor(String selector, DBusListener listener) throws IOException {
        this.listener = listener;
        process = new ProcessBuilder(new String[] { "dbus-monitor", "--session", "--monitor", selector }).start();
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    @Override
    public void close() {
        process.destroyForcibly();
    }

    public void monitor() throws IOException {
        try (DBusReader reader = new DBusReader(
                new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8))) {
            while (true) {
                DBusArray object = reader.readObject();
                if (object == null) return;
                listener.objectReceived(object);
            }
        }
    }

}
