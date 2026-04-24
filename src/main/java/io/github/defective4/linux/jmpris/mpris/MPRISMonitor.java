package io.github.defective4.linux.jmpris.mpris;

import java.io.IOException;
import java.util.Optional;

import io.github.defective4.linux.jmpris.dbus.DBusMonitor;
import io.github.defective4.linux.jmpris.model.DBusArray;
import io.github.defective4.linux.jmpris.model.DBusDictionary;
import io.github.defective4.linux.jmpris.model.DBusObject;
import io.github.defective4.linux.jmpris.model.DBusString;
import io.github.defective4.linux.jmpris.model.MusicTrack;

public class MPRISMonitor extends DBusMonitor {

    public MPRISMonitor(MPRISListener listener) throws IOException {
        super("path=/org/mpris/MediaPlayer2, interface=org.freedesktop.DBus.Properties, member=PropertiesChanged",
                obj -> {
                    if (obj.size() > 1 && obj.get(0) instanceof DBusString str
                            && str.getString().equals("org.mpris.MediaPlayer2.Player")
                            && obj.get(1) instanceof DBusArray array) {
                        if (!array.isEmpty() && array.get(0) instanceof DBusDictionary meta
                                && "Metadata".equals(meta.getKey()) && meta.getValue() instanceof DBusArray metaArray) {
                            String artUrl = findDictionary(metaArray, "mpris:artUrl").orElse(null);
                            String album = findDictionary(metaArray, "xesam:album").orElse(null);
                            String albumArtist = findDictionary(metaArray, "xesam:albumArtist").orElse(null);
                            String artist = findDictionary(metaArray, "xesam:artist").orElse(null);
                            String title = findDictionary(metaArray, "xesam:title").orElse(null);
                            listener.trackChanged(new MusicTrack(artUrl, album, albumArtist, artist, title));
                        }
                    }
                });
    }

    private static Optional<String> findDictionary(DBusArray array, String key) {
        Optional<DBusDictionary> d = array.stream().filter(obj -> obj instanceof DBusDictionary dict
                && key.equals(dict.getKey())).map(obj -> (DBusDictionary) obj)
                .findAny();
        if (d.isPresent()) {
            DBusDictionary dict = d.get();
            DBusObject val = dict.getValue();
            if(val instanceof DBusString str)
                return Optional.of(str.getString());

            if(val instanceof DBusArray a && !array.isEmpty())
                return Optional.of(String.join(", ", a.stream().filter(obj -> obj instanceof DBusString)
                        .map(obj -> ((DBusString) obj).getString()).toArray(String[]::new)));
        }
        return Optional.empty();
    }
}
