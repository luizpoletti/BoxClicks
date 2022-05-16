package com.boxstore.clicks.files;

import com.boxstore.clicks.Main;
import com.boxstore.clicks.utils.MultipleFile;
import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;

@Getter
public class MessagesFile {

    private final MultipleFile file;
    private final FileConfiguration config;

    public MessagesFile(Main main) {
        file = new MultipleFile(main, null, "messages.yml");
        config = file.getConfig();
    }

}
