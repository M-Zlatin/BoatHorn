package net.boathornmod.boathornmod.config;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public enum BoatHornSound {
    SOUND1("boathornmod:boat_horn_sound1"),
    SOUND2("boathornmod:boat_horn_sound2"),
    SOUND3("boathornmod:boat_horn_sound3"),
    SOUND4("boathornmod:boat_horn_sound4");

    private final String soundId;

    BoatHornSound(String soundId) {
        this.soundId = soundId;
    }

    public String getSoundId() {
        return soundId;
    }

    public SoundEvent getSoundEvent() {
        return SoundEvent.of(new Identifier(this.soundId));
    }

    public static BoatHornSound fromString(String name) {
        try {

            return valueOf(name.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid sound name: " + name);
            return SOUND1;
        }
    }
}
