package net.boathornmod.boathornmod.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BHConfigManager {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final File CONFIG_FILE = new File("config/boat_horn.json");

    private static boolean isOn = true;
    public static BoatHornSound sound = BoatHornSound.SOUND1;
    private static float volume = 1.0f;
    private static float pitch = 1.0f;


    public static void load() {
        if (!CONFIG_FILE.exists()) {
            save();
            return;
        }

        try (FileReader reader = new FileReader(CONFIG_FILE)) {
            ConfigData data = GSON.fromJson(reader, ConfigData.class);
            if (data != null) {
                isOn = data.isOn;
                sound = BoatHornSound.fromString(data.Sound);
                volume = data.volume;
                pitch = data.pitch;
            }
        } catch (IOException e) {
            e.printStackTrace();
            save();
        }
    }

    public static void save() {
        ConfigData data = new ConfigData();
        data.isOn = isOn;
        data.Sound = sound.name();
        data.volume = volume;
        data.pitch = pitch;

        try {
            CONFIG_FILE.getParentFile().mkdirs();
            try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
                GSON.toJson(data, writer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isEnabled() { return isOn; }
    public static BoatHornSound getSound() { return sound; }
    public static String getSoundId() { return sound.getSoundId(); }
    public static float getVolume() { return volume; }
    public static float getPitch() { return pitch; }

    public static void setSound(BoatHornSound s) { sound = s; }
    public static void setVolume(float v) { volume = v; }
    public static void setPitch(float p) { pitch = p; }
    public static void setEnabled(boolean on) { isOn = on; }

    private static class ConfigData {
        public boolean isOn;
        public String Sound;
        public float volume;
        public float pitch;
    }
}
