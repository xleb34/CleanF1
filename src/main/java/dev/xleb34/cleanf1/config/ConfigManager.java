package dev.xleb34.cleanf1.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ConfigManager {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIG_PATH = FabricLoader.getInstance()
            .getConfigDir()
            .resolve("cleanf1.json");

    public static Config config = new Config();

    public static void load() {
        if (!Files.exists(CONFIG_PATH)) {
            save();
            return;
        }
        try {
            String json = Files.readString(CONFIG_PATH);
            Config loaded = GSON.fromJson(json, Config.class);
            if (loaded != null) config = loaded;
        } catch (IOException e) {
            System.err.println("[CleanF1] Failed to load config: " + e.getMessage());
        }
    }

    public static void save() {
        try {
            Files.writeString(CONFIG_PATH, GSON.toJson(config));
        } catch (IOException e) {
            System.err.println("[CleanF1] Failed to save config: " + e.getMessage());
        }
    }
}