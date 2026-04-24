package io.github.defective4.linux.jmpris;

import io.github.defective4.linux.jmpris.dbus.DBusMonitor;
import io.github.defective4.linux.jmpris.mpris.MPRISMonitor;

public class Main {
    public static void main(String[] args) {
        try (DBusMonitor monitor = new MPRISMonitor(System.out::println)) {
            monitor.monitor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
