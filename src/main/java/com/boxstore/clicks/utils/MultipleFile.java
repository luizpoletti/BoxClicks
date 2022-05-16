package com.boxstore.clicks.utils;

import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

@Getter
public class MultipleFile {

    private JavaPlugin main;

    private File currentDirectory;
    private File file;
    private FileConfiguration config;

    public MultipleFile(JavaPlugin main, String directory, String fileName) {
        this.main = main;

        createDirectory(directory);
        createFile(directory, fileName);

        config = YamlConfiguration.loadConfiguration(file);
    }

    public MultipleFile(JavaPlugin main, String directory) {
        this.main = main;
        createDirectory(directory);
    }

    public void createDirectory(String directory) {
        currentDirectory = main.getDataFolder();

        if (directory != null) {
            currentDirectory = new File(main.getDataFolder(), directory.replace("/", File.separator));
            currentDirectory.mkdirs();
        }

    }

    public void createFile(String directory, String fileName) {
        file = new File(this.currentDirectory, fileName);

        if (!file.exists())
            main.saveResource(directory != null ? directory + File.separator + fileName : fileName, false);
    }

    public void saveConfig() throws IOException {
        config.save(file);
    }

    public void reloadConfig() {
        config.setDefaults(YamlConfiguration.loadConfiguration(file));
    }

}