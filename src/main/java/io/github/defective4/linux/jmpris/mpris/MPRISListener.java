package io.github.defective4.linux.jmpris.mpris;

import io.github.defective4.linux.jmpris.model.MusicTrack;

public interface MPRISListener {
    void trackChanged(MusicTrack track);
}
