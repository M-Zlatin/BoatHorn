package net.boathornmod.boathornmod.sounds;

import net.boathornmod.boathornmod.BoatHornMod;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class BHModSounds {
    public static SoundEvent registerSoundEvent(String name) {
        Identifier id = new Identifier(BoatHornMod.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static final SoundEvent BOAT_HORN_SOUND1 = registerSoundEvent("boat_horn_sound1");
    public static final SoundEvent BOAT_HORN_SOUND2 = registerSoundEvent("boat_horn_sound2");
    public static final SoundEvent BOAT_HORN_SOUND3 = registerSoundEvent("boat_horn_sound3");
    public static final SoundEvent BOAT_HORN_SOUND4 = registerSoundEvent("boat_horn_sound4");
    public static final SoundEvent BOAT_HORN_SOUND5 = registerSoundEvent("boat_horn_sound5");

    public static void registerSounds() {
        BoatHornMod.LOGGER.info("Registered {} sounds for {}",
                Registries.SOUND_EVENT.getIds().stream()
                        .filter(id -> id.getNamespace().equals(BoatHornMod.MOD_ID))
                        .count(),
                BoatHornMod.MOD_ID);
    }
}