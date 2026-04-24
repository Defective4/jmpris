package io.github.defective4.linux.jmpris;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class DBusMonitor implements AutoCloseable {

    private final Process process;

    public DBusMonitor(String selector) throws IOException {
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
                System.out.println(reader.readObject());
            }
        }
    }

}
