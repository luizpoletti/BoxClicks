package com.boxstore.clicks.files;

import com.boxstore.clicks.Main;
import com.boxstore.clicks.utils.MultipleFile;
import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;

@Getter
public class ClickFile {

    private final MultipleFile file;
    private final FileConfiguration config;

    public ClickFile(Main main) {
        file = new MultipleFile(main, "inventories", "click.yml");
        config = file.getConfig();
    }

}
