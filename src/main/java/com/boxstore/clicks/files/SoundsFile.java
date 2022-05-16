package com.boxstore.clicks.files;

import com.boxstore.clicks.Main;
import com.boxstore.clicks.utils.MultipleFile;
import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;

@Getter
public class SoundsFile {

    private final MultipleFile file;
    private final FileConfiguration config;

    public SoundsFile(Main main) {
        file = new MultipleFile(main, null, "sounds.yml");
        config = file.getConfig();
    }

}
