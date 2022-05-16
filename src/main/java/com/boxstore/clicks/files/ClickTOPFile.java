package com.boxstore.clicks.files;

import com.boxstore.clicks.Main;
import com.boxstore.clicks.utils.MultipleFile;
import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;

@Getter
public class ClickTOPFile {

    private final MultipleFile file;
    private final FileConfiguration config;

    public ClickTOPFile(Main main) {
        file = new MultipleFile(main, "inventories", "click-top.yml");
        config = file.getConfig();
    }

}
