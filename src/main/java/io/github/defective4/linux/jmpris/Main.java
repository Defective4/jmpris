package io.github.defective4.linux.jmpris;

public class Main {
    public static void main(String[] args) {
        try (DBusMonitor monitor = new DBusMonitor("path=/org/mpris/MediaPlayer2, interface=org.freedesktop.DBus.Properties, member=PropertiesChanged")){
            monitor.monitor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
